package ru.maxim.borisov.messenger.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.maxim.borisov.messenger.security.handler.AppEntryPointHandler;
import ru.maxim.borisov.messenger.security.handler.AppLoginSuccessHandler;
import ru.maxim.borisov.messenger.security.handler.AppLogoutSuccessHandler;

/**
 * Основной конфигурационный класс для настройки всего Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Метод для настройки провайдера авторизации. Позволяет зарегистрировать шифровальщик пароля и сервис для
     * получения текущего пользователя.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        final var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    /**
     * Метод, в котором описывается вся конфигураци Spring Security текущего проекта. Здесь видно на какие точки
     * доступа может ходить любой пользователь, на какие только авторизованный, определяется способ авторизации,
     * устанавливаются обработчики событий успешного логина и логаута, реализуются настройки сессий.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/socket/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler(new AppLoginSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(new AppLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AppEntryPointHandler())
                .and()
                .sessionManagement()
                .sessionFixation()
                .changeSessionId();
    }

    /**
     * Бин для регистрации шифровальщика пароля.
     *
     * @return шифровальщик пароля
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

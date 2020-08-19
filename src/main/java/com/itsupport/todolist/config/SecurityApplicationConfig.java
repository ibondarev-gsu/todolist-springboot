package com.itsupport.todolist.config;

import com.itsupport.todolist.models.Role;
import com.itsupport.todolist.service.interfaces.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityApplicationConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final AuthenticationFailureHandler failureHandler;
    private final PasswordEncoder passwordEncoder;

    public SecurityApplicationConfig(UserService userService,
                                     AuthenticationFailureHandler failureHandler,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.failureHandler = failureHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // указываем правила запросов
                // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                    .antMatchers("/style/**").permitAll()
                    .antMatchers( "/login", "/registration", "/active",
                            "/resetPassword", "/forgotPassword",
                            "/changePassword").anonymous()
                    .antMatchers("/updatePassword", "/user/**").hasAuthority(Role.USER.getAuthority())
                    .antMatchers("/admin").hasAuthority(Role.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()

                .formLogin()
//                // указываем страницу с формой логина
                    .loginPage("/login")
//                    // указываем action с формы логина
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/user")
                    .failureHandler(failureHandler)
//                    .failureUrl("/login?error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}

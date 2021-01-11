package com.iths.airtravels.security;


import com.iths.airtravels.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class Security extends WebSecurityConfigurerAdapter {

    private final UsersService usersService;

    public Security(UsersService usersService) {
        this.usersService = usersService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**").permitAll();

        http.formLogin()
                .loginPage("/login").permitAll()  // login.html
                .usernameParameter("user_email")  // input type = "email" name = "user_email"
                .passwordParameter("user_password") // input type = "email" name = "user_email"
                .loginProcessingUrl("/auth").permitAll()  // <form th:action = "@{'/auth'}" method = "post">
                .failureUrl("/login?error")
                .defaultSuccessUrl("/profile");

        http.logout()
                .logoutUrl("/logout").permitAll() // <form th:action = "@{'/logout'}" method = "post">
                .logoutSuccessUrl("/login");


        //    http.csrf().disable();
    }
}

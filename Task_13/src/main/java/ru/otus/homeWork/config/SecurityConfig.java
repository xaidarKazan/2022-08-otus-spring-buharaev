package ru.otus.homeWork.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.homeWork.service.AppUserDetailsService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeRequests().antMatchers( "/login" ).permitAll()
                .and()
                    .authorizeRequests().antMatchers("/api/authors/accessToEdit").hasAnyRole("MANAGER", "ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/api/authors/accessToDelete").hasRole("ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/api/genres/accessToEdit").hasAnyRole("MANAGER", "ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/api/genres/accessToDelete").hasRole("ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/api/books/accessToEdit").hasAnyRole("MANAGER", "ADMIN")
                .and()
                    .authorizeRequests().antMatchers("/api/books/accessToDelete").hasRole("ADMIN")
                .and()
                    .authorizeRequests().antMatchers( "/**" ).authenticated()

                .and()
                    .formLogin().permitAll()
                .and()
                    .rememberMe().tokenValiditySeconds(60*60)
                .and()
                    .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
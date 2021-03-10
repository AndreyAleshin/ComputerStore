package com.github.andreyaleshin.computer_store.config;

import com.github.andreyaleshin.computer_store.service.impl.UserDetailsServiceImpl;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration // используется для классов, которые определяют bean-компоненты
@EnableWebSecurity // можно убрать @Configuration, т.к. эта аннотация её уже включает
@EnableGlobalMethodSecurity(prePostEnabled = true) // защита отдельных методов (@Secured будет работать на методах)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // todo what it does? нет смысла (пока), т.к. интерфейс реализует только 1 класс
    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsService;

    /*
    @Autowired позволяет автоматически установить значение поля.
    Функциональность этой аннотации заключается в том, что нам не нужно заботиться о том,
    как лучше всего Bean'у передать экземпляр другого Bean. Spring сам найдет нужный Bean и
    подставит его значение в свойство, которое отмечено аннотацией.
     */
    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/resources/**",
                "/static/**",
                "/css/**",
                "/img/**",
                "/js/**",
                "/error/**",
                "/h2/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf - Cross Site Request Forgery
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/register", "/about").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
//                .defaultSuccessUrl("/", true)
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/error/403");

        http.headers().frameOptions().disable(); // for H2 database (should use h2-console ONLY in development)
    }

    // BCryptPasswordEncoder (can change password encryption implementation)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ServletRegistrationBean h2ServletRegistration() {
        var registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/h2/*");
        return registrationBean;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    //    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        var provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

}
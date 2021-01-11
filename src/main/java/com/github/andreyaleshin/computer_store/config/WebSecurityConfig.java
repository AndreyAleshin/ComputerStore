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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration // используется для классов, которые определяют bean-компоненты
@EnableWebSecurity // можно убрать @Configuration, т.к. эта аннотация её уже включает
@EnableGlobalMethodSecurity(prePostEnabled = true) // ???
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // todo what it does?
//    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsServiceImpl userDetailsService;

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
        // todo разобраться, что конкретно делает
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf - Cross Site Request Forgery
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").hasAuthority("USER") // hasAuthority add ???
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/", "/home", "/login", "/register", "/about", "/h2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
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
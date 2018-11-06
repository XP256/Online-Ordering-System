package com.imooc.sell.config;

import com.imooc.sell.PasswordEncoder.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@DependsOn("passwordEncoder")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MD5KEY = "zxpzxp";

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;


    @Autowired
    private MyPasswordEncoder passwordEncoder;

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;




    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select email, password, active from user_info where email=?")
                .authoritiesByUsernameQuery("select email, role from user_info where email=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/","/register","/buyer/product/**","/buyer/cart/list","/buyer/cart/save","/buyer/cart/remove","/buyer/cart/change","/css/**", "/js/**", "/fonts/**", "/index").permitAll() // 都可以访问

//                .antMatchers("/order/finish/**").access("hasAnyRole('EMPLOYEE', 'MANAGER')")
//                .antMatchers("/seller/product/new").access("hasAnyRole('MANAGER')")
//                .antMatchers("/seller/**/delete").access("hasAnyRole( 'MANAGER')")
//                .antMatchers("/seller/**").access("hasAnyRole('EMPLOYEE', 'MANAGER')")
                // Customer
                .antMatchers(HttpMethod.POST,"/buyer/cart/checkout/**").access("hasAnyRole('CUSTOMER')")
                //.antMatchers("/buyer/cart/**").access("!hasAnyRole('EMPLOYEE', 'MANAGER')")

                .antMatchers("/buyer/order/**").authenticated()
                .antMatchers("/profiles/**").authenticated()
                .anyRequest().permitAll()


                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/login") // Submit URL
                .failureUrl("/login?error")//
                .usernameParameter("email")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .and()
                .rememberMe().key(MD5KEY)
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }


}


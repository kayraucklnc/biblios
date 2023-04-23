package com.biblios.huceng.security;

import com.biblios.huceng.security.filter.CustomAuthenticationFilter;
import com.biblios.huceng.security.filter.CustomAuthorizationFilter;
import com.biblios.huceng.util.RoleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    /*
    For authentication, this will tell Spring where to look for users(userDetailService) and what
    password encoder to use.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");


        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/ws",
                        "/ws/**"
                )
                .permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll(); // this will not secure any path you specify
        http.authorizeRequests().antMatchers("/api/signup").permitAll();
        http.authorizeRequests().antMatchers("/api/user/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/signup/approve").hasAnyAuthority(RoleUtil.ROLE_ADMIN);
        http.authorizeRequests().antMatchers("/api/post/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_STUDENT, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE);
        http.authorizeRequests().antMatchers("/api/book/**").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_STUDENT);

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/search").hasAnyAuthority(RoleUtil.ROLE_ADMIN, RoleUtil.ROLE_ACADEMICIAN, RoleUtil.ROLE_GRADUATE, RoleUtil.ROLE_STUDENT);

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

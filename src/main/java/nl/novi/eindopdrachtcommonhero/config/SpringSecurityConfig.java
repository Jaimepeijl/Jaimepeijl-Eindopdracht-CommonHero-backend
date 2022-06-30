package nl.novi.eindopdrachtcommonhero.config;

import nl.novi.eindopdrachtcommonhero.filter.JwtRequestFilter;
import nl.novi.eindopdrachtcommonhero.services.CustomUserDetailsService;
import nl.novi.eindopdrachtcommonhero.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    JwtUtil jwtUtil;
    private final  CustomUserDetailsService customUserDetailsService;

    public SpringSecurityConfig(JwtUtil jwtUtil,  CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService);
        auth.inMemoryAuthentication()

                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/signin").permitAll()
//                .antMatchers(HttpMethod.POST, "/signup").permitAll()
//                .antMatchers(HttpMethod.POST, "/vacancies").permitAll()
//                .antMatchers(HttpMethod.POST, "/gebruikers/signup").permitAll()
//                .antMatchers(HttpMethod.GET, "/gebruikers/*").permitAll()
//                .antMatchers(HttpMethod.GET, "/").permitAll()
//                .antMatchers(HttpMethod.GET, "/hulp-aanbieden").permitAll()
//                .antMatchers(HttpMethod.GET, "/hulp-vragen").permitAll()
//                .and()
//                .authorizeRequests()
////                .antMatchers(HttpMethod.GET,"/gebruikers").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/gebruikers/del/**").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers("/authenticate").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtUtil, customUserDetailsService),  UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
    }
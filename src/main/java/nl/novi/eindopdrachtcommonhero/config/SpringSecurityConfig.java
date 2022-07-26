package nl.novi.eindopdrachtcommonhero.config;

import nl.novi.eindopdrachtcommonhero.filter.JwtRequestFilter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private DataSource dataSource;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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
                .antMatchers(HttpMethod.POST, "/authenticate/**").permitAll()
                .antMatchers(HttpMethod.POST, "/vacancies/offer").permitAll()
                .antMatchers(HttpMethod.POST, "/vacancies/search").permitAll()
                .antMatchers(HttpMethod.GET, "/vacancies").permitAll()
                .antMatchers(HttpMethod.GET, "/vacancies/**").permitAll()
                .antMatchers(HttpMethod.GET, "/gebruikers/**").permitAll()
                .antMatchers(HttpMethod.POST, "/gebruikers/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**/photo").authenticated()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers("/download/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtUtil, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    }
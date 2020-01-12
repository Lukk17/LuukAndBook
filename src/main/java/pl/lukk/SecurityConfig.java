package pl.lukk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import pl.lukk.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
    return new SpringDataUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/restAdmin/**").permitAll()
        .antMatchers("/index").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user/add").permitAll()
        .antMatchers("/user").authenticated()
        .antMatchers("/user/**").authenticated()
        .antMatchers("/message/**").authenticated()
        .antMatchers("/booking/offer/book").authenticated()
        .antMatchers("/booking/owner/**").hasAnyRole("ADMIN","OWNER")
        .antMatchers("/offer/owner/**").hasAnyRole("ADMIN","OWNER")
        .antMatchers("/offer/user/**").hasAnyRole("ADMIN","USER", "GROUP-MANAGER")
    
        .and().formLogin().loginPage("/login")                      //  login page
        .failureUrl("/login?error=true")                            //  adress for error login
    
        .and().exceptionHandling().accessDeniedPage("/403")         // when user have no authorization on page
        .and().logout().logoutSuccessUrl("/")
        .permitAll();
    }
    
    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}

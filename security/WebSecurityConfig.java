package Land.Development.Agency.myboard.security;

import Land.Development.Agency.myboard.WebUser.WebUser;
import Land.Development.Agency.myboard.WebUser.WebUserRepository;
import Land.Development.Agency.myboard.WebUser.WebUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private WebUserRepository webUserRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        WebUser admin = new WebUser("Aiden", "x29587629@gmail.com", "root123", WebUserRole.ADMIN);
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        if (!webUserRepository.findByEmail(admin.getEmail()).isPresent()) {
            webUserRepository.save(admin);
        }
        WebUser normal = new WebUser("normal", "aidenlin07@gmail.com", "root123", WebUserRole.USER);
        normal.setPassword(bCryptPasswordEncoder.encode(normal.getPassword()));
        if (!webUserRepository.findByEmail(normal.getEmail()).isPresent()) {
            webUserRepository.save(normal);
        }

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasAuthority(WebUserRole.ADMIN.name())
                .antMatchers("/manage").hasAuthority(WebUserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().successForwardUrl("/");
    }
}

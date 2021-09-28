package by.kagan.businesslayer.config;

import by.kagan.businesslayer.auth.token.jwt.JwtFilter;
import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String FREE_ENDPOINTS_AUTH = "/api/auth/**";
    private static final String FREE_ENDPOINTS_SIGN_UP = "/api/signup/**";
    private static final String USER_ENDPOINTS = "/api/user/**";
    private static final String ADMIN_ENDPOINTS = "/api/admin/**";

<<<<<<< HEAD
    private final JwtFilter jwtFilter;
=======
//    TODO: инжект через поле нежелателен.
    @Autowired
    private JwtFilter jwtFilter;
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c

    private final AccountAuthorizationService authorizationService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(authorizationService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers(USER_ENDPOINTS).authenticated()
                .antMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
                .antMatchers(FREE_ENDPOINTS_SIGN_UP, FREE_ENDPOINTS_AUTH).permitAll()
=======
//                TODO: избегать использования литералов. Возможное исключение -  url'ы. Зачем здесь antMatchers  в таком виде?
                .antMatchers("/test").hasRole("USER")
                .antMatchers("/login", "/signup", "/swagger-ui.html", "/signupconfirmation").permitAll()
>>>>>>> db5e34a213d4d01fb0dfa01266314437ff3d106c
                .and()
//                TODO: есть ли необходимость addFilterBefore?
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider());
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authorizationService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

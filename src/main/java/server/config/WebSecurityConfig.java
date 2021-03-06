package server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import server.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    //Доступ только для не зарегистрированных пользователей
                    .antMatchers("/registration").not().fullyAuthenticated()
                    //Доступ только для пользователей с ролью Администратор
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/").hasRole("USER")
                    //Доступ разрешен всем пользователей
                    .antMatchers("/",
                            "/resources/**",
                            "/main",
                            "/institute/get-institutions",
                            "/division/get-division-by-userId/{id}",
                            "/semester/{id}",
                            "/faculty/{id}",
                            "/course/{id}",
                            "/department/{id}",
                            "/group/{id}",
                            "/schedule/{id}",
                            "/group/get-name/{id}",
                            "/division/get-id/{id}",
                            "/group/get-semesterRange/{id}",
                            "/group/get-version/{id}",
                            "/group/update-version/{id}",
                            "/institute/get-institute-by-userId/{id}",
                            "/teacher/get-teachers-by-userId/{id}",
                            "/css/**", "/js/**", "/images/**").permitAll()
                     //Все остальные страницы требуют аутентификации
                    .anyRequest().authenticated()
                .and()
                    //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    //Перенарпавление на главную страницу после успешного входа
                    .defaultSuccessUrl("/profile").permitAll()
                .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .csrf()
                    .disable();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }

}
//package demo.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//
//@Configuration
//@EnableWebSecurity
//public class ConfigSpringSecurity extends WebSecurityConfigurerAdapter {
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/home","/vendor/**","/js/**","/css/**","/fonts/**","/image/**").permitAll()
//		.anyRequest().authenticated()
//		.and().formLogin().loginPage("/login").permitAll()
//	      .defaultSuccessUrl("/home",true)
//	      .failureUrl("/login?error=falied")
//	      .loginProcessingUrl("/j_spring_security_check");
//	}
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//          .withUser("user1").password(passwordEncoder().encode("123"))
//          .authorities("ROLE_USER");
//    }
//
//}

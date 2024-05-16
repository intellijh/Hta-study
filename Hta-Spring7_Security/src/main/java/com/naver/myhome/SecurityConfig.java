package com.naver.myhome;

import com.naver.myhome.security.CustomUserDetailsService;
import com.naver.myhome.security.LoginFailHandler;
import com.naver.myhome.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity //Spring Security 활성화시켜 모든 요청이 스프링 시큐리티의 제어를 받도록 합니다.
@Configuration //스프링 IOC Container 에게 해당 클래스를 Bean 구성 Class 임을 알려주는 것입니다.
public class SecurityConfig {

    private DataSource dataSource;
    private LoginSuccessHandler loginSuccessHandler;
    private LoginFailHandler loginFailHandler;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(DataSource dataSource, LoginSuccessHandler loginSuccessHandler, LoginFailHandler loginFailHandler, CustomUserDetailsService customUserDetailsService) {
        this.dataSource = dataSource;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailHandler = loginFailHandler;
        this.customUserDetailsService = customUserDetailsService;
    }

    /*
                개발자가 직접 제어가 불가능한 외부 라이브러리등을 Bean으로 만들려할 때 사용됩니다.
                @Configuration 붙어있는 클래스 안에서 사용합니다.
            * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        /*
        (1) loginPage("/member/login") : 스프링에서 제공되는 login 페이지가 아닌 내가 만든 페이지로 이동합니다.

        (2) loginProcessingUrl("/member/loginProcess") : 로그인을 처리하기위한 Url을 매개변수에 넣습니다.
        (form의 action에 해당하는 경로 적으세요. method는 post방식이어야 합니다.)

        (3) usernameParameter("id") : 사용자의 계정명을 어떠한 파라미터명으로 받을 것 인지 설정합니다.
                                      form의 input 태그의 사용자 계정 name과 동일하게 작성합니다.
                                      우리가 사용하는 아이디가 스프링 시큐리티에서는 username으로 사용됩니다.

        (4) passwordParameter("password") : form의 input 태그 패스워드 name과 동일하게 작성합니다.

        (5) successHandler(AuthenticationSuccessHandler successHandler) : 로그인 성공 시 처리할 핸들러를 매개변수로 사용합니다.
        * */
        httpSecurity.formLogin((fo) -> fo.loginPage("/member/login")
                .loginProcessingUrl("/member/loginProcess")
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler));

        /*
        1. httpSecurity.logout(): Spring Security에서 제공하는 HttpSecurity 객체의 logout() 메서드를
           호출하여 로그아웃 설정을 시작합니다.

        2. logoutSuccessUrl("/member/login"): 로그아웃 성공 후에 사용자를 이동시킬 URL을 지정합니다.

        3. logoutUrl("/member/logout"): 로그아웃을 수행할 URL을 지정합니다.
           /member/logout으로 설정되어 있으므로 이 URL로 요청이 오면 로그아웃이 수행됩니다.

        4. invalidateHttpSession(true): HTTP 세션을 무효화할지 여부를 지정합니다.
           true로 설정되어 있으므로 로그아웃 시 HTTP 세션이 무효화됩니다.

        5. deleteCookies("remember-me", "JSESSION_ID"): 로그아웃 시 삭제할 쿠키를 지정합니다.
           "remember-me"와 "JSESSION_ID"라는 두 개의 쿠키를 삭제하도로 설정되어 있습니다.
        * */
        httpSecurity.logout((lo) -> lo.logoutSuccessUrl("/member/login")
                .logoutUrl("/member/logout")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me", "JSESSION_ID"));

        /*
        로그인 유지하기 기능
        (1) 로그인 폼에서 체크박스의 name이 "remember-me"를 선택하면 기능이 작동합니다.

        (2) rememberMeParameter("remember-me") : 체크박스의 name 속성의 값을 매개변수로 작성합니다.
            예) <input type="checkbox" name="remember-me">

        (3) userDetailsService(customUserDetailsService()) :
            사용자 인증과 관련된 작업을 수행할 때 사용할 사용자 상세 정보 서비스를 설정합니다.

        (4) tokenValiditySeconds(2419200) :
            초 단위로 2419200초를 설정했으므로, 약 28일 동안 "remember-me" 토큰이 유효하게 됩니다.

        (5) tokenRepository(tokenRepository()) : 데이터 베이스에 토큰을 저장합니다.
        * */
        httpSecurity.rememberMe((me) -> me.rememberMeParameter("remember-me")
                .userDetailsService(customUserDetailsService)
                .tokenValiditySeconds(2419200)
                .tokenRepository(tokenRepository()));

        return httpSecurity.build();
    }

    /*
        1. 스프링 시큐리티(Spring Security)란 자바 서버 개발을 위해 필요로한 인증, 권한 부여 및 기타 보안 기능을
           제공하는 프레임워크(클래스와 인터페이스 모임)입니다.
        2. BCryptPasswordEncoder는 스프링 시큐리티(Spring Security) 프레임워크에서 제공하는 클래스 중 하나로
           BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 인코딩해주는 메서드와
           데이터 베이스에 저장된 비밀번호와 일치하는지를 알려주는 메서드를 가진 클래스입니다.
        3. BCryptPasswordEncoder란 PasswordEncoder 인터페이스를 구현한 클래스입니다
    * */
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        //PersistentTokenRepository의 구현체인 JdbcTokenRepositoryImpl 클래스 사용합니다.
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}

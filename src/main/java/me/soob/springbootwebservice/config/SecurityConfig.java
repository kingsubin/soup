package me.soob.springbootwebservice.config;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * WebSecurity는 FilterChainPorxy를 생성하는 필터
     * web.igonoring().antMatchers -> 해당 경로의 파일들은 Spring Security가 무시할 수 있도록 설정
     * 파일 기준은 resources/static 디렉토리
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 하위 폴더는 인증 무시
        web.ignoring().antMatchers("/css/**","/js/**");
    }

    /**
     * HttpSecurity 를 통해 HTTP 요청에 대한 웹 기반 보안을 구성
     * authorizeRequests() -> HttpServletRequest 에 따라 access 를 제한
     * formLogin -> form 기반으로 인증, 로그인 정보는 기본적으로 HttpSession을 이용
     * loginPage() -> 커스텀 로그인 폼 사용시
     * logout() -> 로그아웃 지원 메소드, WebSecurityConfigureAdapter 사용시 자동 적용
     * invalidateHttpSession(true) -> 세션 값 초기화하는 작업
     *
     * .usernameParameter("파라미터명")
     * 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데,
     * usernameParameter() 메서드를 통해 파라미터명을 변경할 수 있습니다. ( 예제에는 적용 안함 )
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/members/info").hasRole("MEMBER")
                .antMatchers("/**").permitAll()
             .and() // 로그인 설정
                .formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/members/login/result")
                .permitAll()
             .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/members/login/result")
                .invalidateHttpSession(true)
             .and()
                .exceptionHandling().accessDeniedPage("/members/denied");
    }

    /**
     * Spring Security 에서 모든 인증은 AuthenticationManager 를 통해 이루어지며
     * AuthenticationManager 를 생성하기 위해 Builder 사용
     * 로그인 처리 즉, 인증을 위해서는 UserDetailService 를 통해서 필요한 정보를 가져오는데
     * MemberSerivce에서 구현했음
     *
     * 비밀번호 암호화를 위해 passwordEncoder 사용
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}

package HitecIsFuture.demo.web.login;


import HitecIsFuture.demo.domain.login.LoginService;
import HitecIsFuture.demo.web.SessionConst;
import HitecIsFuture.demo.web.cookie.CookieClass;
import HitecIsFuture.demo.web.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CookieClass cookieClass;


    @PostMapping("/login")
    public String loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 데이터를 받는 형식
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // String으로 받아서 넘겨줘도 objectMapper가 알아서 JSON 처리를 해준다.

        LoginForm loginForm = objectMapper.readValue(messageBody, LoginForm.class);

        if (loginForm == null) {
            // 로그인 실패
            // 사실 애매한 코드, loginForm이 받을때부터 빌 가능성이 있나?
            // NotEmpty가 있으니까 안 그럴거 같긴함
            return "EXCEPTION_LOGINFORM_EMPTY";
            // fail이라는 body를 HTTP에 넣어서 return
        }
        Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (member == null) {
            // 이건 잘못된 정보로 인한 로그인 실패
            return "EXCEPTION_WRONG_ID_OR_PASSWORD";
        }


        // ******************************** 2022.01.19 : 세션 방식의 쿠키로 Update
        // 로그인 성공 : 쿠키를 부여
        // Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        // response.addCookie(idCookie);
        // 고유 ID에 대한 정보를 저장
        // ******************************** 2022.01.19 : 세션 방식의 쿠키로 Update



        // 로그인 성공
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true);


        // 세션에 로그인 회원 정보 보관
        // application.properties에 지정된 값 변경을 통해 세션 만료 시간을 설정 가능
        // 현재 값은 1800(30분)
        // 세션 만료는 페이지 refresh를 할 경우 초기화
        // 즉, 같은 페이지에 30분동안 아무것도 안하고 있을 시 세션 만료 (로그인 해제)
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);


        // 로그인 성공
        return "LOGIN_SUCCESS";


        // TODO
        // 생각해봐야할 점 : 일단 당장은 로그인을 했을 때 Member을 JSON으로 넘겨주지 않고, 로그인 성공에 대한 상태만 넘겨주었다.
        // 로그인을 성공하면 다시 홈 화면이나 다른 학생/학부모에 알맞은 창으로 넘어갈거라고 생각해서이다.
        // 만약 로그인을 성공하자마자 멤버에 대해 필요하다면 멤버를 memberRepository에서 꺼내서 return하면 된다.

    }



    // ******************************** 2022.01.19 : 세션 방식의 쿠키로 Update
    // 로그아웃
    /*@PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        cookieClass.expireCookie(response, "memberId");

        return "LOGOUT_SUCCESS";
    }*/
    // ******************************** 2022.01.19 : 세션 방식의 쿠키로 Update


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 쿠키를 생성할 필요가 없으므로 false를 parameter으로 전달

        // null이 아니면 세션 만료
        if (session != null) {
            session.invalidate();
            return "LOGOUT_SUCCESS";
        }
        // 잘못된 접근 : 세션이 원래 없었는데 로그아웃 시도
        else {
            return "EXCEPTION_NOT_SIGNED_IN_STATUS";
        }
    }


}



// 로그인에 성공하면 세션에 member 객체가 들어있다.
// 이를 세션에서 꺼내서 가져다 쓰면 된다.
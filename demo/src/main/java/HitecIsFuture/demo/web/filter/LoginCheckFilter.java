package HitecIsFuture.demo.web.filter;

import HitecIsFuture.demo.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] notNeedLoginURL = {"/", "/members/sign_up", "/api", "/login", "/logout", "/css/*"};
    // 로그인이 되지 않은 상태에서도 접근 가능한 URL

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                HttpSession session = httpServletRequest.getSession(false);

                // 세션을 받아와서 세션이 없거나 세션이 등록된 정보와 다르면 (즉, 로그인이 되어 있지 않은 경우)
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {

                    log.info("로그인하지 않은 상태로 임의 접근 발생");

                    // 로그인이 되지 않았다는 정보를 return
                    // ***********************************************
                    // 프론트엔드에서 이 정보를 받으면 home으로 리다이렉션해줘야 한다.
                    response.getWriter().write("EXCEPTION_REQUIRED_SIGNING_IN");

                    return;
                }
            }
            log.info("로그인 인증 성공");
            chain.doFilter(request, response);
            // 다음 필터가 있으면 필터를 호출, 필터가 없으면 서블릿, 컨트롤러를 호출

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("로그인 인증절차 완료");
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(notNeedLoginURL, requestURI);
    }
}

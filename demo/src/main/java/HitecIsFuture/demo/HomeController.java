package HitecIsFuture.demo;


import HitecIsFuture.demo.web.SessionConst;
import HitecIsFuture.demo.web.cookie.CookieClass;
import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.member.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final CookieClass cookieClass;

    // 홈 화면에 대한 처리

    // ******************************** 2022.01.19 : 세션 방식의 쿠키로 Update

    /*@PostMapping("/")
    @ResponseBody
    public String home(@CookieValue(name = "memberId", required = false) Long memberId,
                       HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 로그인된 사용자가 없으면 null data를 JSON 형식으로 return
        if (memberId == null) {
            return null;
        }

        Optional<Member> member = memberRepository.findById(memberId);

        if (member.isPresent()) {
            // 사용자가 존재하면
            // 사용자를 JSON 형식으로 HTTP body에 포함하여 반환

            Map<String, Object> resultMap = new HashMap<>();

            resultMap.put("id", member.get().getId());
            resultMap.put("loginId", member.get().getLoginId());
            resultMap.put("name", member.get().getName());
            resultMap.put("age", member.get().getAge());
            resultMap.put("gender", member.get().getGender());
            resultMap.put("job", member.get().getJob());
            resultMap.put("self_diagnosis_notification", member.get().isSelf_diagnosis_notification());

            String resultJson;
            resultJson = new ObjectMapper().writeValueAsString(resultMap);
            return resultJson;


        }
        else {
            // 쿠키에 저장된 ID에 해당하는 사용자가 유효하지 않은 사용자면
            // 쿠키를 만료시키고
            // 로그인이 되지 않은 사용자와 동일하게 처리
            cookieClass.expireCookie(response, "memberId");
            return null;
        }

        // TODO
        // 선생님과 학생 창을 나누려고 하는데, 프론트엔드에서 넘겨받은 JSON을 처리할 수 있는지 확인 필요
        // 일단 로그인이 되어있으면 홈화면에서 쿠키도 있고, 쿠키를 이용해 HTTP body에 게속 member JSON 을 가지고 있음
        // 로그인이 안되어 있거나 (또는 실패하면) HTTP body에 아무것도 가지고 있지 않음

        // TODO
        // 홈 화면에 들어왔을 때 쿠키 정보로 멤버를 조회하고, 해당 멤버의 마지막 자가진단 시간이 오늘이 아니라면, 자가진단을 하는
        // 창으로 이동 (알림에 해당하는 정보를 담은 JSON을 return)
        // 이것 또한 위와 같이 넘겨받은 프론트엔드가 자가진단의 유무를 가져가서 로직을 짤 수 있는지가 관건

    }*/

    // ******************************** 2022.01.19 : 세션 방식의 쿠키로 Update




    @PostMapping("/")
    @ResponseBody
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                            HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // SessionAttribute 어노테이션은 세션을 생성하지는 못한다.

        // 로그인하지 않은 상태일 경우
        if (member == null) {
            return null;
        }
        // 로그인한 경우 멤버에 대한 JSON을 반환
        else {
            Map<String, Object> resultMap = new HashMap<>();

            resultMap.put("id", member.getId());
            resultMap.put("loginId", member.getLoginId());
            resultMap.put("name", member.getName());
            resultMap.put("age", member.getAge());
            resultMap.put("gender", member.getGender());
            resultMap.put("job", member.getJob());
            resultMap.put("self_diagnosis_notification", member.isSelf_diagnosis_notification());

            String resultJson;
            resultJson = new ObjectMapper().writeValueAsString(resultMap);
            return resultJson;
        }

    }

}

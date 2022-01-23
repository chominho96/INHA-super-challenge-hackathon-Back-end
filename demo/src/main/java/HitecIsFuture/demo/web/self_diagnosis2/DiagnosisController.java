package HitecIsFuture.demo.web.self_diagnosis2;

import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.member.MemberRepository;
import HitecIsFuture.demo.web.self_diagnosis.SelfDiagnoseForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/self_diagnosis2")
public class DiagnosisController {
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/write")
    public String writeForm(@CookieValue(name = "memberId", required = false) Long memberId,
            HttpServletRequest request,HttpServletResponse response) throws IOException {

        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()){
            return "NULL_EXCEPTION";
        }
        log.info("멤버 찾기 성공");
        log.info("멤버{}", member);

        ServletInputStream inputStream = request.getInputStream();
        // 데이터를 받는 형식
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        DiagnosisForm diagnosisForm = objectMapper.readValue(messageBody, DiagnosisForm.class);
        log.info("오브젝트 매핑 성공");


        member.get().setSelf_diagnosis_notification(true);
        member.get().setStop_going_school(diagnosisForm.isCheck());
        member.get().setDay(diagnosisForm.getDay());
        member.get().setMonth(diagnosisForm.getMonth());

        memberRepository.update(member.get());

        log.info("변환 후 멤버{}", member);
        return "CLEAR";
    }
}

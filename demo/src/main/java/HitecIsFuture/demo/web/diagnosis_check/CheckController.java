package HitecIsFuture.demo.web.diagnosis_check;

import HitecIsFuture.demo.domain.service.MemberService;
import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/check")
public class CheckController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/search")
    public String searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        //needNotification();
        List<Member> allStudent = memberRepository.findAllStudent();
        if(allStudent.isEmpty())
            return "NULL_STUDENT";
        String resultJson;
        resultJson = new ObjectMapper().writeValueAsString(allStudent);
        return resultJson;
    }

    @GetMapping("/send")
    public String sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{
        needNotification();
        List<Member> notCheckStudent = memberService.checkStudent();
        if(notCheckStudent.isEmpty())
            return "NULL_STUDENT";
        String resultJson;
        resultJson = new ObjectMapper().writeValueAsString(notCheckStudent);
        return resultJson;
    }

    public void needNotification(){
        List<Member> allStudent=memberRepository.findAllStudent();
        for(int i=0;i<allStudent.size();i++) {
            Member member = allStudent.get(i);
            LocalDate now = LocalDate.now();
            log.info("멤버{}", member);
            if (!(member.getDay() == now.getDayOfMonth() && member.getMonth() == now.getMonthValue())) {
                member.setSelf_diagnosis_notification(false);
            }
        }
    }
}

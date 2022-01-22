package HitecIsFuture.demo.web.diagnosis_check;

import HitecIsFuture.demo.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/check")
public class CheckController {
    private final MemberRepository memberRepository;

    @GetMapping("/search")
    public void searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{

    }

    @GetMapping("/send")
    public void sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException{

    }
}

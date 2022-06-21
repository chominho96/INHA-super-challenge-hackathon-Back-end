package HitecIsFuture.demo.web.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 자가진단 미참여 학생 SMS 전송
@RestController
@RequestMapping("/sendMessage")
public class messageController {
    private sens_sms_v2 sens;
    messageController(){
        this.sens = new sens_sms_v2();
    }
    @PostMapping("/please")
    void sendMessage(){
        sens.service("01025881978");
    }
}

package HitecIsFuture.demo.domain.login;


import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).
                filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}

package HitecIsFuture.demo.domain.service;

import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입
    public Long join(Member member) {
        memberRepository.findBy

    }


}

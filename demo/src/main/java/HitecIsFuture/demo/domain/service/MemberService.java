package HitecIsFuture.demo.domain.service;


import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    // 회원가입
    public void join(Member member) {
        //validateDuplicateMember(member);
        memberRepository.save(member);
        //return member.getId();
    }

    public List<Member> checkStudent(){
        List<Member> tmp= new ArrayList<>();
        List<Member> allStudent = memberRepository.findAllStudent();
        for(int i=0;i<allStudent.size();i++){
            if(allStudent.get(i).isSelf_diagnosis_notification()==false)
                tmp.add(allStudent.get(i));
        }
        return tmp;
    }
    // 회원 ID 중복처리
    // 중복되는 내용이고, 실패시 JSON으로 return 할 것이므로 일단 생략

    /*private void validateDuplicateMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원ID입니다.");
                });
    }*/


}

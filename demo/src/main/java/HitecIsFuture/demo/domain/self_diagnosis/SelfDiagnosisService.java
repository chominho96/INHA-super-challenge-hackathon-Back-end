package HitecIsFuture.demo.domain.self_diagnosis;


import HitecIsFuture.demo.web.member.Member;
import HitecIsFuture.demo.web.self_diagnosis.AllDiagnosisFormRepository;
import HitecIsFuture.demo.web.self_diagnosis.MemoryPersonalDiagnosisFormRepository;
import HitecIsFuture.demo.web.self_diagnosis.PersonalDiagnoseFormRepository;
import HitecIsFuture.demo.web.self_diagnosis.SelfDiagnoseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelfDiagnosisService {
    private final AllDiagnosisFormRepository allDiagnosisFormRepository;

    // 새 회원 자가진단서 repository 추가
    // 회원가입 시 중복 검사를 하므로, 따로 중복검사를 하지 않아도 된다.
    public void join_new_member(Member member) {
        allDiagnosisFormRepository.save(new MemoryPersonalDiagnosisFormRepository(member));
    }

    // 특정 회원 자가진단서 추가
    public void create_new_diagnosis(Member member, SelfDiagnoseForm selfDiagnoseForm) {
        Optional<PersonalDiagnoseFormRepository> repository = allDiagnosisFormRepository.findById(member.getId());

        repository.ifPresent(personalDiagnoseFormRepository -> personalDiagnoseFormRepository.save(selfDiagnoseForm));
    }



}
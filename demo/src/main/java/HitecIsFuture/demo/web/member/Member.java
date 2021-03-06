package HitecIsFuture.demo.web.member;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotNull
    private int age;
    @NotNull
    private Gender gender;
    @NotNull
    private Job job;
    @NotEmpty
    private String phoneNumber;



    // 자가진단 알림을 보낼지 판단하는 객체
    // 1. 자가진단 알림은 자가진단을 안 했을 시 30분 간격(DEFAULT)으로 나타난다.
    // 2. 자가진단 알림은 선생님 객체가 알림 보내기를 눌렀을 떄 일괄적으로 나타난다.
    // TRUE : 알림 O
    // FALSE : 알림 X
    private int day;
    private int month;

    private boolean stop_going_school;

    private boolean self_diagnosis_notification;

    public Member() {

    }

    // 특별하게 date와 self_diagnosis_notification를 초기화해줘야 하므로 별도 생성자 생성
    // 중요 : 빈 생성자를 만들어주지 않으면 cannot deserialize from object value ERROR 발생
    public Member(String loginId, String password, String name, int age, Gender gender, Job job, String number) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.phoneNumber = number;
        this.day = 0;
        this.month =0;
        this.stop_going_school = false;
        // 자가진단을 하지 않은 상태이므로 true
        this.self_diagnosis_notification = true;
    }


}

package HitecIsFuture.messageTest;

import HitecIsFuture.demo.web.message.sens_sms_v2;
import org.junit.jupiter.api.Test;

public class messeageSmsTest {

    @Test
    public static void main(String[] args) {
        sens_sms_v2 sms = new sens_sms_v2();
        sms.service();
    }
}

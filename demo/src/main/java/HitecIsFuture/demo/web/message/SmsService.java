/*
package HitecIsFuture.demo.web.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Component
public class SmsService {
    private ApplicationNaverSENS applicationNaverSENS = new ApplicationNaverSENS();
    public SendSmsResponseDto sendSms(String recipientPhoneNumber, String content) throws ParseException,
            JsonProcessingException, UnsupportedEncodingException, InvalidKeyException,
            NoSuchAlgorithmException, URISyntaxException {
        String time = Long.toString(System.currentTimeMillis());
        List<MessagesRequestDto> messages = new ArrayList<>();
        messages.add(new MessagesRequestDto(recipientPhoneNumber,content));
        SmsRequestDto smsRequestDto = new SmsRequestDto("SMS", "COMM", "82", applicationNaverSENS.getSendfrom(), "MangoLtd", messages);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", applicationNaverSENS.getAccessKey());
        String sig = makeSignature(time);
        System.out.println("sig -> " + sig);
        headers.set("x-ncp-apigw-signature-v2", sig);

    }



    public String makeSignature(Long time) throws UnsupportedEncodingException,
            InvalidKeyException, NoSuchAlgorithmException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + applicationNaverSENS.getAccessKey() + "/messages";
        String timestamp = time.toString();
        String accessKey = applicationNaverSENS.getAccessKey(); // access key id (from portal or Sub Account)
        String secretKey = applicationNaverSENS.getSecretKey();
        String message = new StringBuilder().append(method).append(space).append(url)
                .append(newLine).append(timestamp).append(newLine).append(accessKey)
                .toString();
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);
        return encodeBase64String;
    }
}
*/

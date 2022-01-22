package HitecIsFuture.demo.web.message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class sens_sms_v2 {
    public void service(String phoneNum){
        String hostNameUrl = "https://sens.apigw.ntruss.com";
        String requestUrl= "/sms/v2/services/";
        String requestUrlType = "/messages";
        String accessKey ="SF2GxyjYHF87kIF8oKyJ";
        String secretKey="HQsXMsqIoZrwyMHehIGhk5CsZVuGQEYT74D9aTUJ";
        String serviceId="ncp:sms:kr:279253967886:inhahackerton";
        String method = "POST";
        String timestamp = Long.toString(System.currentTimeMillis());
        requestUrl+=serviceId+requestUrlType;
        String apiUrl = hostNameUrl + requestUrl;

        JSONObject bodyJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();

        toJson.put("subject","hi");
        toJson.put("content","hihi");
        toJson.put("to",phoneNum);
        toArr.add(toJson);

        bodyJson.put("type","lms");
        bodyJson.put("contentType","");
        bodyJson.put("countryCode","82");
        bodyJson.put("from","01083188286");
        bodyJson.put("subject","hihihi");
        bodyJson.put("content","hihihihihihihi");
        bodyJson.put("messages",toArr);

        String body = bodyJson.toString();

        System.out.println(body);

        try{
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type","application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key",accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl,timestamp,method,accessKey,secretKey));
            con.setRequestMethod(method);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes());
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.println("responseCode"+" "+responseCode);
            if(responseCode==202){
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }else{
                br=new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = br.readLine())!=null){
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static String makeSignature(String url,String timestamp, String method, String accessKey,String secretKey)
        throws NoSuchAlgorithmException, InvalidKeyException{
        String space = " ";
        String newLine = "\n";

        String message = new StringBuilder().append(method)
                .append(space).append(url).append(newLine)
                .append(timestamp).append(newLine).append(accessKey)
                .toString();

        SecretKeySpec signingKey;
        String encodeBase64String;
        try{
            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"),"HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        }catch (UnsupportedEncodingException e){
            encodeBase64String = e.toString();
        }
        return encodeBase64String;
    }
}

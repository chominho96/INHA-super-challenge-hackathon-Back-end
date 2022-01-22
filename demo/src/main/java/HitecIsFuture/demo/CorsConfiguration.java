package HitecIsFuture.demo;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CORS 에러 해결 방안
// https://studyandwrite.tistory.com/374 - 참고

public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("members/sign_up").allowedOrigins("http://localhost:3000");
        registry.addMapping("login").allowedOrigins("http://localhost:3000");
        registry.addMapping("home").allowedOrigins("http://localhost:3000");
        registry.addMapping("check/search").allowedOrigins("http://localhost:3000");
        registry.addMapping("check/send").allowedOrigins("http://localhost:3000");
        registry.addMapping("self_diagnosis2/write").allowedOrigins("http://localhost:3000");
    }
}

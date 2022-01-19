package HitecIsFuture.demo.web;


import HitecIsFuture.demo.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

// 필터를 등록하는 방법
// 스프링 부트를 사용할 때 FilterRegistrationBean을 사용
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        // 등록할 필터 지정
        filterFilterRegistrationBean.setOrder(1);
        // 첫번째 필터
        filterFilterRegistrationBean.addUrlPatterns("/*");
        // 모든 주소에 대해 실행
        // 별도로 제외 대상을 지정했으므로 모든 주소로 지정해도 된다.

        return filterFilterRegistrationBean;
    }
}

package net.runningcoder.config;

import net.runningcoder.web.AppGrantedAuthority;
import net.runningcoder.web.annotaion.auth.AuthenticationInterceptor;
import net.runningcoder.web.annotaion.auth.AuthorizationInterceptor;
import net.runningcoder.web.annotaion.auth.GrantedAuthority;
import net.runningcoder.web.filter.CustomCorsFilter;
import net.runningcoder.web.filter.HttpServletRequestReplacedFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/api/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Bean
    public FilterRegistrationBean getCharacterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("utf-8");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(characterEncodingFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean getHttpServletRequestReplacedFilter() {
        HttpServletRequestReplacedFilter httpServletRequestReplacedFilter = new HttpServletRequestReplacedFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(httpServletRequestReplacedFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean getCorsFilter() {
        CustomCorsFilter corsFilter = new CustomCorsFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(corsFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/api/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(3);
        return registrationBean;
    }

    @Bean
    public GrantedAuthority grantedAuthority() {
        return new AppGrantedAuthority();
    }
}

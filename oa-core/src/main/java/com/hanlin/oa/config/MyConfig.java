package com.hanlin.oa.config;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {
//public class MyConfig implements WebMvcConfigurer {
//public class MyConfig extends WebMvcConfigurerAdapter {


    /**
     * 19      * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 20      * 需要重新指定静态资源
     * 21      * @param registry
     * 22
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
    }

    /**
     * Description:  默然访问页<br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2018/4/4 10:54
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/index.html");
//        registry.addViewController("/").setViewName("forward:/sys/passport/toLogin");
//        registry.addRedirectViewController("/", "/index.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
    }


    /**
     * Description:  <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2018/4/4 16:23
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了login，其他都拦截判断
//        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger-ui.html", "/static/**", "/sys/toLogin", "/sys/login");
//        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/mobile/**");
//        super.addInterceptors(registry);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }


    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
package net.runningcoder.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/6.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        final String basePackage = "net.runningcoder.web.controller";

        Docket build = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();

        List<ResponseMessage> responseMessageList = Lists.newArrayList();
        build.globalResponseMessage(RequestMethod.GET, responseMessageList);
        build.globalResponseMessage(RequestMethod.POST, responseMessageList);
        build.globalResponseMessage(RequestMethod.PUT, responseMessageList);
        build.globalResponseMessage(RequestMethod.DELETE, responseMessageList);
        return build;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEMO RESTful APIs")
                .description("描述")
                .termsOfServiceUrl("http://")
                .version("1.0")
                .build();
    }
}

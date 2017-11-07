package net.runningcoder.config;

import com.netflix.hystrix.HystrixCommandProperties;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import net.runningcoder.remote.LocalApi;
import net.runningcoder.remote.LocalApiFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangmaocheng on 2017/11/7.
 */
@Configuration
public class HystrixFeignConfig {
    @Bean
    LocalApi localApi() throws InterruptedException {
        return HystrixFeign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .setterFactory((target, method) ->
                        new SetterFactory.Default().create(target, method).andCommandPropertiesDefaults(
                                HystrixCommandProperties.defaultSetter()
                                        .withExecutionTimeoutInMilliseconds(10000)))
                .target(LocalApi.class, "http://localhost:8080", new LocalApiFallback());
    }
}

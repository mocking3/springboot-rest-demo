package net.runningcoder.web;

import net.runningcoder.web.dto.rsp.base.RspDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(value = "net.runningcoder.web")
public class AppResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public RspDto beforeBodyWrite(Object dto, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();

        RspDto rspDto = null;
        if (dto instanceof RspDto) {
            rspDto = (RspDto) dto;
        } else {
            rspDto = new RspDto(dto);
        }
        return rspDto;
    }
}
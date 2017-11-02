package net.runningcoder.web;

import lombok.extern.slf4j.Slf4j;
import net.runningcoder.web.dto.rsp.base.ErrorRspDto;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public final ErrorRspDto handleException(Exception ex, WebRequest request) {
        if (!(ex instanceof RestException))
            log.error("系统异常：{}", ex);

        ErrorRspDto erb = null;
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            erb = new ErrorRspDto(RspCode.ERROR_METHOD);

        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            erb = new ErrorRspDto(RspCode.SYSTEM_ERROR);

        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            erb = new ErrorRspDto(RspCode.SYSTEM_ERROR);

        } else if (ex instanceof MissingServletRequestParameterException) {
            erb = new ErrorRspDto(RspCode.PARAMS_LOST);

        } else if (ex instanceof ServletRequestBindingException) {
            erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);

        } else if (ex instanceof ConversionNotSupportedException) {
            erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);

        } else if (ex instanceof TypeMismatchException) {
            erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);

        } else if (ex instanceof HttpMessageNotReadableException) {
            erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);

        } else if (ex instanceof HttpMessageNotWritableException) {
            erb = new ErrorRspDto(RspCode.SYSTEM_ERROR);

        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = e.getBindingResult();

            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String code = fieldError.getCode();
                if ("NotBlank".equals(code) ||
                        "NotNull".equals(code)) {
                    erb = new ErrorRspDto(RspCode.PARAMS_LOST);
                    break;
                } else if ("Pattern".equals(code)) {
                    erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);
                    break;
                } else if ("Length".equals(code)) {
                    erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);
                    break;
                } else if ("Max".equals(code)) {
                    erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);
                    break;
                } else if ("Min".equals(code)) {
                    erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);
                    break;
                } else if ("Phone".equals(code)) {
                    erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);
                    break;
                }
            }

        } else if (ex instanceof MissingServletRequestPartException) {
            erb = new ErrorRspDto(RspCode.SYSTEM_ERROR);

        } else if (ex instanceof BindException) {
            erb = new ErrorRspDto(RspCode.PARAMS_NOT_VALID);

        } else if (ex instanceof ConstraintViolationException) {
            erb = new ErrorRspDto(RspCode.SYSTEM_ERROR);

        } else if (ex instanceof RestException) {
            RestException e = (RestException) ex;
            erb = new ErrorRspDto(e.getErrorCode(), e.getErrorMsg());
        } else {
            erb = new ErrorRspDto(RspCode.SYSTEM_ERROR);
        }
        return erb;
    }

}

package net.runningcoder.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.runningcoder.web.RspCode;
import net.runningcoder.web.dto.rsp.ErrorCodeInfoDto;
import net.runningcoder.web.dto.rsp.base.CollectionRspDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangmaocheng on 2017/11/7.
 */
@Api(tags = "错误码接口", description = "无")
@RestController
@RequestMapping(value = "/api/error-codes")
public class ErrorCodeController {

    @ApiOperation(value = "错误码列表", notes = "系统中可能出现的错误码")
    @ResponseBody
    @GetMapping
    public CollectionRspDto<ErrorCodeInfoDto> errorCodes() {
        CollectionRspDto<ErrorCodeInfoDto> rspDto = new CollectionRspDto<>();
        for (RspCode rspCode : RspCode.values()) {
            rspDto.getData().add(new ErrorCodeInfoDto(rspCode.getCode(), rspCode.getMessage()));
        }
        rspDto.setTotal(rspDto.getData().size());
        return rspDto;
    }
}

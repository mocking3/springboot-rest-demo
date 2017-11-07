package net.runningcoder.web.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.runningcoder.remote.LocalApi;
import net.runningcoder.web.annotaion.auth.Authorization;
import net.runningcoder.web.annotaion.version.Version;
import net.runningcoder.web.dto.rsp.base.RspDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Api(tags = "示例接口", description = "展示一些例子")
@RestController
@RequestMapping(value = "/api/example")
public class ExampleController {

    @Autowired
    private LocalApi localApi;

    @ApiOperation(value = "例子1", notes = "接口版本（version>=1）+认证+授权（必须有OP_001权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问token", paramType = "header", required = true),
            @ApiImplicitParam(name = "version", value = "接口版本", paramType = "header", dataType = "int", required = true)
    })
    @Version(1)
    @Authorization(value = {"OP_001"})
    @ResponseBody
    @GetMapping(value = "1")
    public Object test1() {
        JSONObject json = new JSONObject();
        json.put("result", true);
        return json;
    }

    @ApiOperation(value = "例子2", notes = "认证+授权（必须有OP_002权限）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问token", paramType = "header", required = true)
    })
    @Authorization(value = "OP_002")
    @ResponseBody
    @GetMapping(value = "2")
    public Object test2() {
        JSONObject json = new JSONObject();
        json.put("result", true);
        return json;
    }

    @ApiOperation(value = "例子3", notes = "外部服务降级")
    @ResponseBody
    @GetMapping(value = "3")
    public Object test3() {
        RspDto errorCodes = localApi.getErrorCodes();
        return errorCodes;
    }
}

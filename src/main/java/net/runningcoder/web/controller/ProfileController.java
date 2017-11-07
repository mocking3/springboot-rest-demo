package net.runningcoder.web.controller;

import io.swagger.annotations.*;
import net.runningcoder.web.annotaion.auth.Authentication;
import net.runningcoder.web.dto.rsp.ProfileInfoDto;
import net.runningcoder.web.security.UserContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Api(tags = "个人中心接口", description = "无")
@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {

    @ApiOperation(value = "获取个人信息", notes = "需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "访问token", paramType = "header", required = true)
    })
    @Authentication
    @GetMapping
    @ResponseBody
    public ProfileInfoDto profileInfo(@ApiParam(hidden = true) @RequestAttribute UserContext userContext) {
        ProfileInfoDto profileInfoDto = new ProfileInfoDto();
        profileInfoDto.setName((String) userContext.get("name"));
        return profileInfoDto;
    }
}

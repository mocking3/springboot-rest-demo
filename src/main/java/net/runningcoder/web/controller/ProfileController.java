package net.runningcoder.web.controller;

import net.runningcoder.web.annotaion.auth.Authentication;
import net.runningcoder.web.dto.rsp.ProfileInfoDto;
import net.runningcoder.web.security.UserContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {

    @Authentication
    @GetMapping
    @ResponseBody
    public ProfileInfoDto profileInfo(@RequestAttribute UserContext userContext) {
        ProfileInfoDto profileInfoDto = new ProfileInfoDto();
        profileInfoDto.setName(userContext.getName());
        return profileInfoDto;
    }
}

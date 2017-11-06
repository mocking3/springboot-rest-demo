package net.runningcoder.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.runningcoder.web.annotaion.auth.Authorization;
import net.runningcoder.web.annotaion.version.Version;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@RestController
@RequestMapping(value = "/api/example")
public class ExampleController {

    @Version(1)
    @Authorization(value = {"OP_001"})
    @ResponseBody
    @GetMapping(value = "1")
    public Object test1() {
        JSONObject json = new JSONObject();
        json.put("result", true);
        return json;
    }

    @Authorization(value = "OP_002")
    @ResponseBody
    @GetMapping(value = "2")
    public Object test2() {
        JSONObject json = new JSONObject();
        json.put("result", true);
        return json;
    }
}

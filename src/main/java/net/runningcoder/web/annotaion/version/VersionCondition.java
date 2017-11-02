package net.runningcoder.web.annotaion.version;

import net.runningcoder.web.RestException;
import net.runningcoder.web.RspCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangmaocheng on 2017/2/4.
 */
public class VersionCondition implements RequestCondition<VersionCondition> {

    private int apiVersion;

    public VersionCondition(int apiVersion){
        this.apiVersion = apiVersion;
    }

    @Override
    public VersionCondition combine(VersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new VersionCondition(other.getApiVersion());
    }

    @Override
    public VersionCondition getMatchingCondition(HttpServletRequest request) {
        String version = request.getHeader("version");
        if(StringUtils.isBlank(version)){
            throw new RestException(RspCode.PARAMS_LOST);
        }
        if (version != null && version.trim().length() > 0) {
            try {
                Integer v = Integer.valueOf(version);
                if (v >= this.apiVersion) // 如果请求的版本号大于配置版本号， 则满足
                    return this;
            }catch (NumberFormatException e){
                throw new RestException(RspCode.PARAMS_NOT_VALID);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int compareTo(VersionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

}

package com.backend.utils.fastDepShiroJwtUtils;

import com.louislivi.fastdep.shirojwt.shiro.FastDepShiroJwtAuthorization;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FastDepShiroJwtConfig extends FastDepShiroJwtAuthorization {
    @Override
    public SimpleAuthorizationInfo getAuthorizationInfo(String userId) {
        Set<String> collect =  new HashSet<String>();
        collect.add("1");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(collect);
        return simpleAuthorizationInfo;
    }
}

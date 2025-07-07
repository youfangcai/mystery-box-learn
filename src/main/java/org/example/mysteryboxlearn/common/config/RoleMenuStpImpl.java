package org.example.mysteryboxlearn.common.config;

import cn.dev33.satoken.stp.StpInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoleMenuStpImpl implements StpInterface {


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return List.of();
    }
}

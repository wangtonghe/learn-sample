package com.wthfeng.learn.spring.autowire;

import com.wthfeng.learn.spring.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author wangtonghe
 * @since 2019/4/3 16:06
 */
@Repository(value = "userDao2")
public class UserDAOImpl implements UserDAO {


    @Override
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser("wthfeng");
        userInfo.setPassword("123456");
        return userInfo;
    }
}

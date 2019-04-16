package com.wthfeng.learn.spring.autowire;

import com.wthfeng.learn.spring.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;


/**
 * @author wangtonghe
 * @since 2019/4/3 16:05
 */
@Service(value = "userService")
public class UserService {


//    @Autowired
//    public UserService(@Qualifier(value = "userDao") UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }


    @Resource
    private UserDAO userDao;


    public UserInfo getUser() {
        return userDao.getUserInfo();
    }

    @PostConstruct
    public void init2() {
        System.out.println("user service init...xxxxxxxxxxxxx");
    }


    @PreDestroy
    public void destroy2() {
        System.out.println("user service destroy over xxxxxxx");
    }
}

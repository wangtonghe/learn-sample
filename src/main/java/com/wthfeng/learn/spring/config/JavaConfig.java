package com.wthfeng.learn.spring.config;

import com.wthfeng.learn.spring.autowire.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wangtonghe
 * @since 2019/4/3 17:22
 */
@Configuration
@Import(JavaConfig2.class)
public class JavaConfig {

    @Bean
    public UserService getUser() {
        return new UserService(getUserDAO());
    }

    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOImpl2();
    }


}

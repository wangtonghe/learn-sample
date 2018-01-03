package com.wthfeng.learn.guice;

import com.google.inject.AbstractModule;

/**
 * @author wangtonghe
 * @date 2017/10/20 13:09
 */
public class GuiceModuleImpl extends AbstractModule {

    @Override
    protected void configure() {

        // 连接绑定
        bind(UserService.class).annotatedWith(UserImpl.class).to(UserServiceImpl.class);

    }
}

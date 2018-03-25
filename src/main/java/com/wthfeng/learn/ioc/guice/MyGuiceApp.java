package com.wthfeng.learn.ioc.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author wangtonghe
 * @date 2017/10/20 13:11
 */
public class MyGuiceApp {

    @UserImpl
    @Inject
    private UserService userService;

    public void login(String name,String pwd){
        userService.login(name,pwd);
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GuiceModuleImpl());
        MyGuiceApp app = injector.getInstance(MyGuiceApp.class);
        app.login("wthfeng","123456");
    }
}

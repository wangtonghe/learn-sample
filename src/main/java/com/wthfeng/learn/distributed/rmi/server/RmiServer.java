package com.wthfeng.learn.distributed.rmi.server;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

/**
 * @author wangtonghe
 * @since 2019/2/28 19:30
 */
public class RmiServer {

    public static void main(String[] args) {

        try {

            PersonService personService = new PersonServiceImpl();

            LocateRegistry.createRegistry(6000);

            Context namingContext = new InitialContext();

            namingContext.rebind("rmi://127.0.0.1:6000/person-service", personService);

            System.out.println("服务注册成功");


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

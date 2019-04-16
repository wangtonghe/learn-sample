package com.wthfeng.learn.distributed.rmi.client;

import com.wthfeng.learn.distributed.rmi.entity.Person;
import com.wthfeng.learn.distributed.rmi.server.PersonService;

import java.rmi.Naming;

/**
 * @author wangtonghe
 * @since 2019/2/28 19:33
 */
public class RmiClient {

    public static void main(String[] args) {

        try {
            PersonService personService = (PersonService) Naming.lookup("rmi://127.0.0.1:6000/person-service");

            Person person = personService.getPerson("1");

            System.out.println(person);


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

package com.wthfeng.learn.distributed.rmi.server;

import com.wthfeng.learn.distributed.rmi.entity.Person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wangtonghe
 * @since 2019/2/28 19:23
 */
public class PersonServiceImpl extends UnicastRemoteObject implements PersonService {


    protected PersonServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Person getPerson(String id) throws RemoteException {

        Person person = new Person();
        person.setId(34);
        person.setAge(23);
        person.setName("hello");

        return person;
    }
}

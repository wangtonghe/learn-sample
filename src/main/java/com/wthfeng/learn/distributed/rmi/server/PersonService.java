package com.wthfeng.learn.distributed.rmi.server;

import com.wthfeng.learn.distributed.rmi.entity.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wangtonghe
 * @since 2019/2/28 19:20
 */
public interface PersonService extends Remote {

    Person getPerson(String id) throws RemoteException;


}

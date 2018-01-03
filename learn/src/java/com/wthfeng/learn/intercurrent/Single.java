package com.wthfeng.learn.intercurrent;

/**
 * @author wangtonghe
 * @date 2017/10/1 17:20
 */
public class Single {

    private volatile Single single;


    public Single getSingle() {
       if(single==null){
           synchronized (Single.class){
               if(single == null){
                   return new Single();
               }
           }
       }
       return single;
    }
}

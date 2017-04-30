package util;

import org.junit.Test;

/**
 * @author wangtonghe
 * @date 2017/3/11 10:27
 */
public class TestObserver {

    @Test
    public void test(){
        Observer observer1 = new ObserverImpl("观察者1");
        Observer observer2 = new ObserverImpl("观察者2");
        Subject subject = new NewsPaper();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notifyObservers("我消息更新了");
    }
}

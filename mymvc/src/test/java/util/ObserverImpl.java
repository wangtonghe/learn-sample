package util;

/**
 * @author wangtonghe
 * @date 2017/3/10 22:00
 */
public class ObserverImpl implements Observer {

    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    public void update(String msg) {
        System.out.println(name+"阅读消息:" + msg);

    }
}

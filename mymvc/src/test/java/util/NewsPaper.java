package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题实现类
 * @author wangtonghe
 * @date 2017/3/11 10:11
 */
public class NewsPaper implements Subject{

    //订阅者名单
    private List<Observer> observers = new ArrayList<>(0);


    //添加订阅者
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    //移除订阅者
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers(String msg) {
        for(Observer observer:observers){
            observer.update("有新报纸了");
        }
    }
}

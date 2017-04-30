package util;

/**
 * 主题类
 * @author wangtonghe
 * @date 2017/3/10 22:02
 */
public interface Subject {


     void addObserver(Observer observer);

     void removeObserver(Observer observer);

     void notifyObservers(String msg);

}

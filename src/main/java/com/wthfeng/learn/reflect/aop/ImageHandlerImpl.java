package com.wthfeng.learn.reflect.aop;

/**
 * @author wangtonghe
 * @since 2019/4/6 17:58
 */
public class ImageHandlerImpl implements ImageHandler {

    @Override
    public void loadImage() {
        System.out.println("执行图片加载工作");
    }
}

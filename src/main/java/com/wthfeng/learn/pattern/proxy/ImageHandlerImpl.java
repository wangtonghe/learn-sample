package com.wthfeng.learn.pattern.proxy;

import java.util.concurrent.TimeUnit;

/**
 * @author wangtonghe
 * @date 2017/5/21 15:41
 */
public class ImageHandlerImpl implements ImageHandler {
    @Override
    public void loadImage() {
        try {
            //用休眠2秒表示图片加载过程
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("图片加载完成。");
    }
}

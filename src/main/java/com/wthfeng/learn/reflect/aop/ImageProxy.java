package com.wthfeng.learn.reflect.aop;

/**
 * 图片代理类，代理图片加载
 *
 * @author wangtonghe
 * @since 2019/4/6 17:59
 */
public class ImageProxy implements ImageHandler {

    private ImageHandler imageHandler;

    public ImageProxy(ImageHandler imageHandler) {
        this.imageHandler = imageHandler;
    }

    @Override
    public void loadImage() {
        System.out.println("预处理工作");
        imageHandler.loadImage();
        System.out.println("执行清理工作");
    }
}

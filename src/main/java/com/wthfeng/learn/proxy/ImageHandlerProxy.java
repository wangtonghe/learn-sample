package com.wthfeng.learn.proxy;

/**
 * @author wangtonghe
 * @date 2017/5/21 15:48
 */
public class ImageHandlerProxy implements ImageHandler{
    private ImageHandler imageHandler;

    //将真实对象通过构造器传过来
    public ImageHandlerProxy(ImageHandler imageHandler) {
        this.imageHandler = imageHandler;
    }


    @Override
    public void loadImage() {
        //代理做一些预处理
        System.out.println("请等待，正在加载图片");
//        System.out.println("加载小图片");

        //调用真实对象方法
        imageHandler.loadImage();

        //可以做一些收尾工作
    }
}

package com.wthfeng.learn.mvc;

import com.blade.Blade;

/**
 * @author wangtonghe
 * @date 2017/11/25 09:40
 */
public class BladeMvc {

    public static void main(String[] args) {
        Blade.me().get("/",    (request, response) -> response.text("Hello World"))
                .start(BladeMvc.class, args);
    }

}

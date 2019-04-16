package com.wthfeng.learn.jvm;

/**
 * @author wangtonghe
 * @since 2019/3/7 16:26
 */
public class WebPage {

    private String url;

    private String content;

    private byte[] bytes = new byte[1024 * 50];


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

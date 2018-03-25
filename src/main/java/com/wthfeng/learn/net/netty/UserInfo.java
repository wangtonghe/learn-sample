package com.wthfeng.learn.net.netty;

import java.time.LocalDateTime;

/**
 * @author wangtonghe
 * @date 2017/11/19 12:34
 */
public class UserInfo {

    private String name;

    private String email;

    private LocalDateTime localDateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}

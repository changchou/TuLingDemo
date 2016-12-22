package com.zhang.tulingdemo;

/**
 * Created by Mr.Z on 2016/10/19 0019.
 */

public class ListData {

    public static final int SEND = 0;
    public static final int RECEIVE = 1;

    private String content;
    private int flag;
    private String time;

    public ListData(String content, int flag, String time) {
        this.content = content;
        this.flag = flag;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

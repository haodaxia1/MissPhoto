package com.example.administrator.missphoto;

/**
 * Created by lenovo on 2016/12/21.
 */
public class HomeRequest {
    private int id;//id
/*    private int num;//需求id*/
    private String title; //需求详情
    private String request; //需求详情
    private String phone;//联系电话
    private String time; //发布时间

    public HomeRequest(int id /*int num*/, String title, String request, String phone, String time) {
        this.id = id;
/*        this.num = num;*/
        this.title = title;
        this.request = request;
        this.phone = phone;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

/*    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}////
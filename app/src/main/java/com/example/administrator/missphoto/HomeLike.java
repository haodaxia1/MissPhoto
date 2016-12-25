package com.example.administrator.missphoto;

/**
 * Created by lenovo on 2016/12/21.
 */
public class HomeLike {
    private int id;
    private int pic;//展示作品
    private String name;//上榜人姓名

    public HomeLike(int id, int pic, String name) {
        this.id = id;
        this.pic = pic;
        this.name = name;

    }
//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

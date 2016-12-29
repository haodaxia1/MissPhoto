package com.example.administrator.missphoto;

import android.graphics.Bitmap;

/**
 * Created by 15530 on 2016/12/6.
 */

public class Camera {

    private Long id;
    private int headPortrait;   //头像
    private String name;    //昵称
    private Bitmap image;  //作品图片


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(int headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }



    public Camera(Long id, int headPortrait, String name, Bitmap image) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.name = name;
        this.image = image;
    }

}

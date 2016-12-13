package com.example.administrator.missphoto;

/**
 * Created by 15530 on 2016/12/6.
 */

public class Camera {
    private Long id;
    private int headPortrait;   //头像
    private String name;    //昵称
    private int image;  //作品图片
    private String comment; //评论

    public Camera(Long id, int headPortrait, String name, int image, String comment) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.name = name;
        this.image = image;
        this.comment = comment;
    }

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

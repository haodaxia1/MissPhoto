package com.example.administrator.missphoto;

/**
 * Created by 15530 on 2016/12/6.
 */

public class Camera {

    private Long id;
    private int headPortrait;   //头像
    private String name;    //昵称
    private int image;  //作品图片
    private int like;//作品点赞数
    private String userName; //用户名
    private String userComment; //用户评论

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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Camera(Long id, int headPortrait, String name, int image, int like, String userName, String userComment) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.name = name;
        this.image = image;
        this.like = like;

        this.userName = userName;
        this.userComment = userComment;
    }
}

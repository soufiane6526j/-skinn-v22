package com.ayoub.wallpaper;

public class ImageModel {
    private String Image,ttl,like,cmnt,download,desc;

    public ImageModel(String image, String ttl, String like, String cmnt, String download, String desc) {
        Image = image;
        this.ttl = ttl;
        this.like = like;
        this.cmnt = cmnt;
        this.download = download;
        this.desc = desc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getCmnt() {
        return cmnt;
    }

    public void setCmnt(String cmnt) {
        this.cmnt = cmnt;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

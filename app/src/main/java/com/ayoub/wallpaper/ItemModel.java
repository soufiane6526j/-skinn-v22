package com.ayoub.wallpaper;
public class ItemModel {
    private final String icon;
    private final String name;
    private final String AppUrl;

    public ItemModel(String str, String str2, String str3) {
        this.icon = str;
        this.AppUrl = str2;
        this.name = str3;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getAppUrl() {
        return this.AppUrl;
    }

    public String getName() {
        return this.name;
    }
}
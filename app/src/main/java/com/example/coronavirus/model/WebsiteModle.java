package com.example.coronavirus.model;

public class WebsiteModle {
    private String titleWeb;
    private String linkWeb;
    private String hotlineWeb;

    public WebsiteModle(String titleWeb, String linkWeb, String hotlineWeb) {
        this.titleWeb = titleWeb;
        this.linkWeb = linkWeb;
        this.hotlineWeb = hotlineWeb;
    }

    public WebsiteModle() {

    }

    public String getTitleWeb() {
        return titleWeb;
    }

    public void setTitleWeb(String titleWeb) {
        this.titleWeb = titleWeb;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public String getHotlineWeb() {
        return hotlineWeb;
    }

    public void setHotlineWeb(String hotlineWeb) {
        this.hotlineWeb = hotlineWeb;
    }
}

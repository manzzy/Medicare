package com.example.medicare.Models;

public class DashboardModel {
    private String dashTitle;
    private String dashDescription;
    private int dashImg;

    public DashboardModel(String dashTitle, String dashDescription, int dashImg) {
        this.dashTitle = dashTitle;
        this.dashDescription = dashDescription;
        this.dashImg = dashImg;
    }

    public String getDashTitle() {
        return dashTitle;
    }

    public void setDashTitle(String dashTitle) {
        this.dashTitle = dashTitle;
    }

    public String getDashDescription() {
        return dashDescription;
    }

    public void setDashDescription(String dashDescription) {
        this.dashDescription = dashDescription;
    }

    public int getDashImg() {
        return dashImg;
    }

    public void setDashImg(int dashImg) {
        this.dashImg = dashImg;
    }
}

package com.robotix_vssut.welcomeanimationapp;

/**
 * Created by Hp on 09-01-2017.
 */

public class UserDetails {

    private String name;
    private String mobile;
    private String profile;

    public UserDetails(){

    }

    public UserDetails(String name, String mobile, String profile) {
        this.name = name;
        this.mobile = mobile;
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

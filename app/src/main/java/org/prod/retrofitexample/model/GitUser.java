package org.prod.retrofitexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Neha on 5/19/17.
 */

public class GitUser {


    @SerializedName("login")
    @Expose
    private String name;

    @SerializedName("avatar_url")
    @Expose
    private String image_url;

    public GitUser() {
    }

    public GitUser(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

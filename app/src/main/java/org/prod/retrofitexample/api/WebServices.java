package org.prod.retrofitexample.api;

import org.prod.retrofitexample.model.GitUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Neha on 5/19/17.
 */

public interface WebServices {

    @GET("/users")
    Call<List<GitUser>> getUsers();
}

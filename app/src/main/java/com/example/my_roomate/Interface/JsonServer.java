package com.example.my_roomate.Interface;

import com.example.my_roomate.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonServer {

    //Traer la lista de usuarios registrados
    @GET("user")
    Call<List<User>> getUsers();

}

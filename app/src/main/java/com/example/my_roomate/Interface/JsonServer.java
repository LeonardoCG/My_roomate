package com.example.my_roomate.Interface;

import com.example.my_roomate.Model.User;
import com.example.my_roomate.Propuesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonServer {

    //Traer la lista de usuarios registrados
    @GET("user")
    Call<List<User>> getUsers();
    //Traer las propuestas de explorar
    @GET("proposal")
    Call<List<Propuesta>> getPropuestas();
}

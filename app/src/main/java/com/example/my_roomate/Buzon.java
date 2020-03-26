package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class Buzon implements Serializable {
    String _usuario, _descripcion;
    int _hora, _img;

    public Buzon(String usuario, String descripcion, int hora, int img)
    {
        _usuario = usuario;
        _descripcion = descripcion;
        _hora = hora;
    }

    public String get_usuario() {
        return _usuario;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public int get_hora() {
        return _hora;
    }


    public void set_usuario(String _usuario) {
        this._usuario = _usuario;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public void set_hora(int _hora) {
        this._hora = _hora;
    }
}

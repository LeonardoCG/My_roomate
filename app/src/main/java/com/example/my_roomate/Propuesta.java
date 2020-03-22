package com.example.my_roomate;

import java.io.Serializable;

public class Propuesta implements Serializable {
    String _title, _description, _localization;
    double _cost;
    int _fav;
    boolean _like;
    public Propuesta(String title, String description, String localization, double cost, int fav, boolean like){
        _title = title;
        _description = description;
        _localization = localization;
        _cost = cost;
        _fav = fav;
        _like = like;
    }

    public String get_title() {
        return _title;
    }

    public String get_description() {
        return _description;
    }

    public String get_localization() {
        return _localization;
    }

    public double get_cost() {
        return _cost;
    }

    public int get_fav() {
        return _fav;
    }

    public boolean is_like() {
        return _like;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_localization(String _localization) {
        this._localization = _localization;
    }

    public void set_cost(double _cost) {
        this._cost = _cost;
    }

    public void set_fav(int _fav) {
        this._fav = _fav;
    }

    public void set_like(boolean _like) {
        this._like = _like;
    }
}

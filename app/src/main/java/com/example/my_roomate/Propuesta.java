package com.example.my_roomate;


import java.io.Serializable;

public class Propuesta implements Serializable {
    String _title, _mindescription, _fulldescription, _localization, _cost, _username, _usernmamelocalization;
    int _id, _fav, _img, _userpicture;
    boolean _like;

    //propuesta short
    public Propuesta(String title, String description, String localization, String cost, int fav, boolean like, int img){
        _title = title;
        _mindescription = description;
        _localization = localization;
        _cost = cost;
        _fav = fav;
        _like = like;
        _img = img;
    }

    //propuestas de guardados
    public Propuesta(String username, String usernmamelocalization, String title, String description, String localization, String cost, int fav, boolean like, int img, int userpicture){
        _title = title;
        _mindescription = description;
        _localization = localization;
        _cost = cost;
        _fav = fav;
        _like = like;
        _img = img;
        _username = username;
        _usernmamelocalization = usernmamelocalization;
        _userpicture = userpicture;
    }
    //propuesta full

    public String get_title() {
        return _title;
    }

    public String get_description() {
        return _mindescription;
    }

    public String get_localization() {
        return _localization;
    }

    public String get_cost() {
        return _cost;
    }

    public int get_fav() {
        return _fav;
    }

    public boolean is_like() {
        return _like;
    }

    public int get_img() {
        return _img;
    }

    public String get_mindescription() {
        return _mindescription;
    }

    public String get_fulldescription() {
        return _fulldescription;
    }

    public String get_username() {
        return _username;
    }

    public String get_usernmamelocalization() {
        return _usernmamelocalization;
    }

    public int get_id() {
        return _id;
    }

    public int get_userpicture() {
        return _userpicture;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_description(String _description) {
        this._mindescription = _description;
    }

    public void set_localization(String _localization) {
        this._localization = _localization;
    }

    public void set_cost(String _cost) {
        this._cost = _cost;
    }

    public void set_fav(int _fav) {
        this._fav = _fav;
    }

    public void set_like(boolean _like) {
        this._like = _like;
    }

    public void set_img(int _img) {
        this._img = _img;
    }

    public void set_mindescription(String _mindescription) {
        this._mindescription = _mindescription;
    }

    public void set_fulldescription(String _fulldescription) {
        this._fulldescription = _fulldescription;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public void set_usernmamelocalization(String _usernmamelocalization) {
        this._usernmamelocalization = _usernmamelocalization;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_userpicture(int _userpicture) {
        this._userpicture = _userpicture;
    }
}

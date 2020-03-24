package com.example.my_roomate;


import java.io.Serializable;

public class Propuesta implements Serializable {
    String _title, _mindescription, _fulldescription, _localization, _cost, _username, _usernmamelocalization, _tipo_casa;
    int _id, _fav, _img, _userpicture, _num_camas, _numbanos, _num_sala, _num_comendor;
    boolean _like, _s_internet, _s_boiler, _s_aircondicionado, _o_terraza, _o_piscina;

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
    public Propuesta(String username, String usernmamelocalization, String title, String fulldescription, String mindescription, String tipo_casa, String localization, String cost, int fav, int img, int userpicture, int num_camas, int numbanos, int num_sala, int num_comendor, boolean s_internet, boolean s_boiler, boolean s_aircondicionado, boolean o_terraza, boolean o_piscina){
        _username = username;
        _usernmamelocalization = usernmamelocalization;
        _title = title;
        _fulldescription = fulldescription;
        _mindescription = mindescription;
        _tipo_casa = tipo_casa;
        _localization = localization;
        _cost = cost;
        _fav = fav;
        _img = img;
        _userpicture = userpicture;
        _num_camas = num_camas;
        _num_comendor = num_comendor;
        _num_sala = num_sala;
        _numbanos = numbanos;
        _s_internet = s_internet;
        _s_aircondicionado = s_aircondicionado;
        _s_boiler = s_boiler;
        _o_piscina = o_piscina;
        _o_terraza = o_terraza;

    }

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

    public String get_tipo_casa() {
        return _tipo_casa;
    }

    public int get_num_camas() {
        return _num_camas;
    }

    public int get_numbanos() {
        return _numbanos;
    }

    public int get_num_sala() {
        return _num_sala;
    }

    public int get_num_comendor() {
        return _num_comendor;
    }

    public boolean is_s_internet() {
        return _s_internet;
    }

    public boolean is_s_boiler() {
        return _s_boiler;
    }

    public boolean is_s_aircondicionado() {
        return _s_aircondicionado;
    }

    public boolean is_o_terraza() {
        return _o_terraza;
    }

    public boolean is_o_piscina() {
        return _o_piscina;
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

    public void set_tipo_casa(String _tipo_casa) {
        this._tipo_casa = _tipo_casa;
    }

    public void set_num_camas(int _num_camas) {
        this._num_camas = _num_camas;
    }

    public void set_numbanos(int _numbanos) {
        this._numbanos = _numbanos;
    }

    public void set_num_sala(int _num_sala) {
        this._num_sala = _num_sala;
    }

    public void set_num_comendor(int _num_comendor) {
        this._num_comendor = _num_comendor;
    }

    public void set_s_internet(boolean _s_internet) {
        this._s_internet = _s_internet;
    }

    public void set_s_boiler(boolean _s_boiler) {
        this._s_boiler = _s_boiler;
    }

    public void set_s_aircondicionado(boolean _s_aircondicionado) {
        this._s_aircondicionado = _s_aircondicionado;
    }

    public void set_o_terraza(boolean _o_terraza) {
        this._o_terraza = _o_terraza;
    }

    public void set_o_piscina(boolean _o_piscina) {
        this._o_piscina = _o_piscina;
    }
}

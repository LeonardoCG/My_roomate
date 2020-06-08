package com.example.my_roomate.Model;

public class Proposel {
    String id_proposal,id_user_favorite,id_user_create,title, brief,full_description,localization,cost,img_cover,ranking,date,type_house,numb_bed,numb_dinning_room,numb_living_room,numb_bathroom;
    boolean internet,air_conditional,boiler,pool,terrace;
    String[] img_slide;

    //propuesta versión cardview
    public Proposel(String id_user_favorite, String title, String brief, String localization, String cost, String img_cover, String ranking) {
        this.id_user_favorite = id_user_favorite;
        this.title = title;
        this.brief = brief;
        this.localization = localization;
        this.cost = cost;
        this.img_cover = img_cover;
        this.ranking = ranking;
    }
    //propuesta versión detalle

    public Proposel(String id_proposal, String id_user_create, String title, String full_description, String localization, String cost, String img_cover, String date, String type_house, String numb_bed, String numb_dinning_room, String numb_living_room, String numb_bathroom, boolean internet, boolean air_conditional, boolean boiler, boolean pool, boolean terrace, String[] img_slide) {
        this.id_proposal = id_proposal;
        this.id_user_create = id_user_create;
        this.title = title;
        this.full_description = full_description;
        this.localization = localization;
        this.cost = cost;
        this.img_cover = img_cover;
        this.date = date;
        this.type_house = type_house;
        this.numb_bed = numb_bed;
        this.numb_dinning_room = numb_dinning_room;
        this.numb_living_room = numb_living_room;
        this.numb_bathroom = numb_bathroom;
        this.internet = internet;
        this.air_conditional = air_conditional;
        this.boiler = boiler;
        this.pool = pool;
        this.terrace = terrace;
        this.img_slide = img_slide;
    }

    public String getId_proposal() {
        return id_proposal;
    }

    public String getId_user_favorite() {
        return id_user_favorite;
    }

    public String getId_user_create() {
        return id_user_create;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    public String getFull_description() {
        return full_description;
    }

    public String getLocalization() {
        return localization;
    }

    public String getCost() {
        return cost;
    }

    public String getImg_cover() {
        return img_cover;
    }

    public String getRanking() {
        return ranking;
    }

    public String getDate() {
        return date;
    }

    public String getType_house() {
        return type_house;
    }

    public String getNumb_bed() {
        return numb_bed;
    }

    public String getNumb_dinning_room() {
        return numb_dinning_room;
    }

    public String getNumb_living_room() {
        return numb_living_room;
    }

    public String getNumb_bathroom() {
        return numb_bathroom;
    }

    public boolean isInternet() {
        return internet;
    }

    public boolean isAir_conditional() {
        return air_conditional;
    }

    public boolean isBoiler() {
        return boiler;
    }

    public boolean isPool() {
        return pool;
    }

    public boolean isTerrace() {
        return terrace;
    }

    public String[] getImg_slide() {
        return img_slide;
    }
}

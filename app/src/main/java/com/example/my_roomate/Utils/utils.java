package com.example.my_roomate.Utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class utils {
    public static final int db_version = 1;
    public static final String dbName = "roomate-app";

    //Tablas

    //Users
    public static final String table_user   = "user";
    public static final String id_user   = "id_user";
    public static final String names_user   = "names";
    public static final String lastnames_user   = "last_names";
    public static final String address_user   = "address";
    public static final String ubication_user   = "ubication";
    public static final String phone_user   = "phone";
    public static final String bio_user   = "bio";
    public static final String curp_user   = "curp";
    public static final String email_user = "email";
    public static final String password_user = "password";
    public static final String photo_user = "photo_profile";

    public static final String create_user = String.format("CREATE TABLE %s (%s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s BLOB )"
        ,table_user,id_user,names_user,lastnames_user,address_user,ubication_user,phone_user,bio_user,curp_user,email_user,password_user,photo_user);

    //Category
    public static final String table_category   = "category";
    public static final String id_category   = "id_category";
    public static final String id_user_in_category   = "id_user";
    public static final String anime_category   = "anime_cartoons";
    public static final String travel_category   = "travel";
    public static final String music_category   = "music";
    public static final String books_category   = "books";
    public static final String politics_category   = "politics";
    public static final String tecnology_category   = "tecnology";
    public static final String science_category   = "science";
    public static final String crafting_category   = "crafting";

    public static final String create_category = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_category, id_category, id_user_in_category, anime_category, travel_category, music_category, books_category, politics_category, tecnology_category, science_category,crafting_category);

    //User Interesting
    public static final String table_user_interesting   = "user_interesting";
    public static final String id_interesting   = "id_interesting";
    public static final String id_user_in_interesting   = "id_user";
    public static final String id_category_in_interesting   = "id_category";

    public  static final String create_user_interesting = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_user_interesting, id_interesting, id_user_in_interesting, id_category_in_interesting);

    //Proposal
    public static final String table_proposal   = "proposal";
    public static final String id_proposal   = "id_proposal";
    public static final String id_user_in_proposal   = "id_user";
    public static final String title_proposal   = "title";
    public static final String brief_description_proposal   = "brief_description";
    public static final String localization_proposal   = "localization";
    public static final String cost_proposal   = "cost";
    public static final String cover_proposal   = "img_cover";
    public static final String ranking_proposal   = "ranking";
    public static final String date_proposal   = "date";

    public static final String create_proposal = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s BLOB, %s INTEGER, %s TEXT)"
            ,table_proposal, id_proposal, id_user_in_proposal, title_proposal, brief_description_proposal, localization_proposal, cost_proposal, cover_proposal, ranking_proposal,  date_proposal);

    //Proposal details
    public static final String table_proposal_detail   = "proposal_detail";
    public static final String id_proposal_detail   = "id_proposal_detail";
    public static final String id_proposal_in_proposal_detail   = "id_proposal";
    public static final String fulldescription_proposal_detail   = "full_description";
    public static final String type_house_proposal_detail   = "type_houser";

    public static final String create_proposal_detail = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s TEXT, %s TEXT)"
            ,table_proposal_detail, id_proposal_detail, id_proposal_in_proposal_detail, fulldescription_proposal_detail, type_house_proposal_detail);

    //Proposal img slide
    public static final String table_proposal_img_slice   = "proposal_image_slice";
    public static final String id_proposal_img_slice   = "id_proposal_img";
    public static final String id_proposal_detail_in_proposal_img_slice   = "id_proposal_detail";
    public static final String img_proposal_img_slice   = "img";

    public static final String create_proposal_img_slice = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s BLOB)"
            ,table_proposal_img_slice, id_proposal_img_slice, id_proposal_detail_in_proposal_img_slice, img_proposal_img_slice);

    //Rooms_detail
    public static final String table_rooms_detail   = "rooms_detail";
    public static final String id_room_detail   = "id_room";
    public static final String id_proposal_detail_in_rooms_detail   = "id_proposal_detail";
    public static final String numb_bed_room_detail   = "numb_bed";
    public static final String numb_dinning_room_room_detail   = "numb_dinning_room";
    public static final String numb_living_room_room_detail   = "numb_living_room";
    public static final String numb_bathroom_room_detail   = "numb_bathroom";

    public static final String create_rooms_detail = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_rooms_detail, id_room_detail, id_proposal_detail_in_rooms_detail, numb_bed_room_detail, numb_dinning_room_room_detail, numb_living_room_room_detail, numb_bathroom_room_detail);

    //Service Detail
    public static final String table_service_detail   = "service_detail";
    public static final String id_service_detail   = "id_service";
    public static final String id_proposal_detail_in_service_detail   = "id_proposal_detail";
    public static final String internet_service_detail   = "internet";
    public static final String air_conditional_service_detail   = "air_conditional";
    public static final String boiler_service_detail   = "boiler";

    public static final String create_service_detail = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_service_detail, id_service_detail, id_proposal_detail_in_service_detail, internet_service_detail, air_conditional_service_detail, boiler_service_detail);

    //Other service
    public static final String table_other_detail   = "other_detail";
    public static final String id_other_detail   = "id_other";
    public static final String id_proposal_detail_in_other_detail   = "id_proposal_detail";
    public static final String pool_other_detail   = "pool";
    public static final String terrace_other_detail   = "terrace";

    public static final String create_other_detail = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_other_detail, id_other_detail, id_proposal_detail_in_other_detail, pool_other_detail, terrace_other_detail);

    //Proposal Comments
    public static final String table_proposal_comment   = "proposal_comment";
    public static final String id_proposal_comment   = "id_proposal_comment";
    public static final String id_user_in_proposal_comment   = "id_user";
    public static final String comment_proposal_comment   = "comment";
    public static final String date_proposal_comment   = "date";

    public static final String create_proposal_comment = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s TEXT, %s TEXT)"
            ,table_proposal_comment, id_proposal_comment, id_user_in_proposal_comment, comment_proposal_comment, date_proposal_comment);

    //Proposal Favorite
    public static final String table_proposal_favorite   = "proposal_favorite";
    public static final String id_proposal_favorite   = "id_proposal_favorite";
    public static final String id_user_in_proposal_favorite   = "id_user";
    public static final String is_favorite_proposal_favorite   = "is_favorite";

    public static final String create_proposal_favorite = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_proposal_favorite, id_proposal_favorite, id_user_in_proposal_favorite, is_favorite_proposal_favorite);

    //Offer
    public static final String table_offer   = "offer";
    public static final String id_offer   = "id_offer";
    public static final String id_user_in_offer   = "id_user";
    public static final String id_proposal_in_offer   = "id_proposal";

    public static final String create_offer = String.format("CREATE TABLE %s (%s INTEGER, %s INTEGER, %s INTEGER)"
            ,table_offer, id_offer, id_user_in_offer, id_proposal_in_offer);

    //Obtener id siguiente
    public static int getLastID(String table, SQLiteOpenHelper conn){
        try {
            SQLiteDatabase db = conn.getReadableDatabase();

            String query = "SELECT id FROM "+table+" ORDER by id DESC";
            Cursor c = db.rawQuery(query,null);
            c.moveToFirst();
            return c.getShort(0);
        }
        catch (Exception ex){
            return 0;
        }
    }

}

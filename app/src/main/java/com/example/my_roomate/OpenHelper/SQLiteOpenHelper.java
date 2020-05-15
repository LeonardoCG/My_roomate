package com.example.my_roomate.OpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.my_roomate.Utils.utils;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {


    public SQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utils.create_user);
        db.execSQL(utils.create_category);
        db.execSQL(utils.create_proposal);
        db.execSQL(utils.create_user_interesting);
        db.execSQL(utils.create_proposal_detail);
        db.execSQL(utils.create_proposal_comment);
        db.execSQL(utils.create_proposal_favorite);
        db.execSQL(utils.create_proposal_img_slice);
        db.execSQL(utils.create_rooms_detail);
        db.execSQL(utils.create_service_detail);
        db.execSQL(utils.create_other_detail);
        db.execSQL(utils.create_offer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS user_interesting");
        db.execSQL("DROP TABLE IF EXISTS proposal");
        db.execSQL("DROP TABLE IF EXISTS proposal_detail");
        db.execSQL("DROP TABLE IF EXISTS proposal_image_slice");
        db.execSQL("DROP TABLE IF EXISTS rooms_detail");
        db.execSQL("DROP TABLE IF EXISTS service_detail");
        db.execSQL("DROP TABLE IF EXISTS other_detail");
        db.execSQL("DROP TABLE IF EXISTS proposal_comment");
        db.execSQL("DROP TABLE IF EXISTS proposal_favorite");
        db.execSQL("DROP TABLE IF EXISTS offer");
        onCreate(db);
    }
}

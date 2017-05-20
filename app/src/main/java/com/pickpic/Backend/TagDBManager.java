package com.pickpic.Backend;

/**
 * Created by Arsene holmes on 2017-05-05.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.pickpic.Item.TagTabListViewItem;

//DB를 총괄관리
public class TagDBManager {

    // DB관련 상수 선언
    public final static int NORMAL_TAG = 0;
    public final static int DATE_TAG = 1;
    public final static int DIRECTORY_TAG = 2;
    // DB관련 객체 선언
    private OpenHelper opener; // DB opener
    private SQLiteDatabase db; // DB controllers

    // 부가적인 객체들
    private Context context;

    // 생성자
    public TagDBManager(Context context) {
        this.context = context;
        this.opener = new OpenHelper(context, "PickPic.db", null, 1);
        db = opener.getWritableDatabase();
    }

    // Opener of DB and Table
    public class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory,
                          int version) {
            super(context, name, null, version);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            if (!db.isReadOnly()) {
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
        }
        // 생성된 DB가 없을 경우에 한번만 호출됨
        @Override
        public void onCreate(SQLiteDatabase sdb) {
            sdb.execSQL("CREATE TABLE IMAGES (path TEXT)");
            sdb.execSQL("CREATE TABLE IMAGE_TAG_RELATION " +
                    "(path TEXT REFERENCES IMAGES(path) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED," +
                    "tagValue TEXT, tagType INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
        }
    }

    public ArrayList<String> getAllImages() {
        String sql = "SELECT DISTINCT path FROM IMAGES;";
        Cursor a = db.rawQuery(sql, null);
        ArrayList<String> results = new ArrayList<String>();
        while (a.moveToNext()) {
            results.add(a.getString(0));
        }
        a.close();
        return results;
    }

    public void initTable() {
        db.execSQL("DROP TABLE IMAGE_TAG_RELATION");
        db.execSQL("DROP TABLE IMAGES");
        db.execSQL("CREATE TABLE IMAGES (path TEXT primary key)");
        db.execSQL("CREATE TABLE IMAGE_TAG_RELATION " +
                "(path TEXT REFERENCES IMAGES(path) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED," +
                "tagValue TEXT, tagType INTEGER)");
    }

    public ArrayList<String> getAllTags() {
        String sql = "SELECT DISTINCT tagValue, COUNT(*) AS tagCount FROM IMAGE_TAG_RELATION GROUP BY tagValue ORDER BY tagCount DESC;";
        Cursor a = db.rawQuery(sql, null);
        ArrayList<String> results = new ArrayList<String>();
        while (a.moveToNext()) {
            results.add(a.getString(0));
        }
        a.close();
        return results;
    }
    public ArrayList<TagTabListViewItem> getTagTabListViewItem(){
        String sql = "SELECT DISTINCT tagValue FROM IMAGE_TAG_RELATION where tagType = " + "\'" + TagDBManager.NORMAL_TAG + "\';";
        Cursor a = db.rawQuery(sql, null);
        ArrayList<TagTabListViewItem> results = new ArrayList<>();
        while (a.moveToNext()) {
            results.add(new TagTabListViewItem(a.getString(0)));
        }
        a.close();
        return results;
    }

        Cursor a = db.rawQuery(sql, null);
        while(a.moveToNext()){
            results.add(a.getString(0));
        }
        a.close();
        return results;
    }

    public ArrayList<String> getPathsByTags(ArrayList<String> tags){
        String sql = "SELECT * FROM IMAGE_TAG_RELATION where tagValue = \"" + tags.get(0) + "\";";
        ArrayList<String> results = new ArrayList<String>();

        Cursor a = db.rawQuery(sql, null);
        ArrayList<String> first = new ArrayList<>();
        while(a.moveToNext()){
            first.add(a.getString(0));
        }
        a.close();
        for(int i = 0; i<first.size(); i++){
            for(int j = 1; j<tags.size(); j++){
                sql = "SELECT * FROM IMAGE_TAG_RELATION where path = \"" + first.get(i) + "\" AND tagValue = \"" + tags.get(j) + "\";";
                Cursor b = db.rawQuery(sql,null);
                if(b.isAfterLast()){
                    break;
                }
                if(j == tags.size() -1){
                    results.add(first.get(i));
                }
                b.close();
            }
        }
        return results;
    }

    // 데이터 추가
    public void insertImage(String path) {
        db.execSQL("INSERT INTO IMAGES VALUES( \"" + path + "\")");
    }

    public void insertTag(String path, String tag, int tagType) {
        db.execSQL("INSERT INTO IMAGE_TAG_RELATION VALUES(" +
                "\"" + path + "\"" + ", " + "\"" + tag + "\"" + "," + "\"" + tagType + "\"" + ")");
    }

    public void removeImage(String path) {
        db.execSQL("DELETE FROM IMAGES WHERE path = \"" + path + "\"");
    }

    public void removeTag(String path, String tag) {
        db.execSQL("DELETE FROM IMAGE_TAG_RELATION WHERE path = " + path + "AND tagValue = " + "tag");
    }
/*
    // 데이터 갱신
    public void updateData(APinfo info, int index) {
        String sql = "update " + tableName + " set SSID = '" + info.getSSID()
                + "', capabilities = " + info.getCapabilities()
                + ", passwd = '" + info.getPasswd() + "' where id = " + index
                + ";";
        db.execSQL(sql);
    }

    // 데이터 삭제
    public void removeData(int index) {
        String sql = "delete from " + tableName + " where id = " + index + ";";
        db.execSQL(sql);
    }

    // 데이터 검색
    public APinfo selectData(int index) {
        String sql = "select * from " + tableName + " where id = " + index
                + ";";
        Cursor result = db.rawQuery(sql, null);

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if (result.moveToFirst()) {
            APinfo info = new APinfo(result.getInt(0), result.getString(1),
                    result.getInt(2), result.getString(3));
            result.close();
            return info;
        }
        result.close();
        return null;
    }

    // 데이터 전체 검색
    public ArrayList<apinfo> selectAll() {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        results.moveToFirst();
        ArrayList<apinfo> infos = new ArrayList<apinfo>();

        while (!results.isAfterLast()) {
            APinfo info = new APinfo(results.getInt(0), results.getString(1),
                    results.getInt(2), results.getString(3));
            infos.add(info);
            results.moveToNext();
        }
        results.close();
        return infos;
    }
    */
}
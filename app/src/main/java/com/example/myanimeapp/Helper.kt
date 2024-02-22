package com.example.myanimeapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Helper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE user (id INT PRIMARY KEY, login TEXT, password TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
     db!!.execSQL("DROP TABLE IF EXISTS user")
        onCreate(db)
    }
    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("password", user.password)

        val db = this.writableDatabase
        db.insert("user", null, values)

        db.close()

    }

    fun getUser(login: String, password: String): Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM user WHERE login = '$login' AND password = '$password'", null)
        return result.moveToFirst()

        db.close()
    }
}
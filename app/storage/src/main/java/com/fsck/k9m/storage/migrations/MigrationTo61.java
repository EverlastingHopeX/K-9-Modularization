package com.fsck.k9m.storage.migrations;


import android.database.sqlite.SQLiteDatabase;


class MigrationTo61 {
    public static void removeErrorsFolder(SQLiteDatabase db) {
        db.execSQL("DELETE FROM folders WHERE name = 'K9mail-errors'");
    }
}
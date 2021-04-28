package com.va.neoapp.util;

import android.content.Context;

import com.va.neoapp.custom.db.DBSupportUtil;
import com.va.neoapp.custom.db.core.TableDetails;

import java.util.ArrayList;


public class DBUtil extends DBSupportUtil {

    private static final int DB_VERSION_NUMBER = 1;
    private static DBUtil instance = null;

    private DBUtil(Context context) {
        super(context);
    }

    @Override
    protected ArrayList<TableDetails> getAllTableDetails(ArrayList<TableDetails> allTableDefinitions) {

        // add individual db model class.

       // allTableDefinitions.add(TableDetails.getTableDetails(ModelClassName.class));
       // allTableDefinitions.add(TableDetails.getTableDetails(ModelClassName.class));
       // allTableDefinitions.add(TableDetails.getTableDetails(ModelClassName.class));

        return allTableDefinitions;
    }

   /* public static DBUtil getWDInstance(Context context) {
        return getInstance(context);
    }*/

    public static DBUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtil(context);
        }
        return instance;
    }

    @Override
    protected String getDatabaseFileName() {
        return "AppDb";
    }

    @Override
    public int getDatabaseVersion() {
        return DB_VERSION_NUMBER;
    }

}
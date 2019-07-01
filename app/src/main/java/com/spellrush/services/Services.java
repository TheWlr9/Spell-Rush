package com.spellrush.services;

import com.spellrush.persistence.ILeaderboardPersistence;
import com.spellrush.persistence.stubs.LeaderboardPersistenceStub;
import com.spellrush.persistence.hsqldb.LeaderboardHSQLDB;

public class Services{
    private static ILeaderboardPersistence leaderboardPersistence = null;

    private static String databaseName = "spellrushDB";

    // synchronize = provides mutual exclusion for when calling the persistence service
    public static synchronized ILeaderboardPersistence getLeaderboardPersistence() {
        if(leaderboardPersistence == null){
            leaderboardPersistence = new LeaderboardHSQLDB(databaseName);
            //leaderboardPersistence = new LeaderboardPersistenceStub();
        }
        return leaderboardPersistence;
    } // end getLeaderboardPersistence()

    //FROM SAMPLE - Creates a new hsqldb instance
    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        databaseName = name;
    }

    public static String getDBPathName()
    {
        return databaseName;
    }
}

package com.spellrush.application;

import com.spellrush.persistence.ILeaderboardPersistence;
import com.spellrush.persistence.stubs.LeaderboardPersistenceStub;

public class Services{
    private static ILeaderboardPersistence leaderboardPersistence = null;

    public static synchronized ILeaderboardPersistence getLeaderboardPersistence() {
        if(leaderboardPersistence == null){
            leaderboardPersistence = new LeaderboardPersistenceStub();
        }
        return leaderboardPersistence;
    } // end getLeaderboardPersistence()
}

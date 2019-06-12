package com.spellrush.objects;

import com.spellrush.persistence.ILeaderboardPersistence;
import com.spellrush.persistence.stubs.LeaderboardPersistenceStub;

public class Services{
    private static ILeaderboardPersistence leaderboardPersistence = null;

    // synchronize = provides mutual exclusion for when calling the persistence service
    public static synchronized ILeaderboardPersistence getLeaderboardPersistence() {
        if(leaderboardPersistence == null){
            leaderboardPersistence = new LeaderboardPersistenceStub();
        }
        return leaderboardPersistence;
    } // end getLeaderboardPersistence()
}

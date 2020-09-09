package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Vocab.class}, version = 1)
public abstract class VocabDatabase extends RoomDatabase {

    public abstract VocabDao vocabDao();

    private static volatile VocabDatabase INSTANCE;

    public static VocabDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VocabDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VocabDatabase.class, "vocab")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

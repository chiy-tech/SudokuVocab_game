package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface VocabDao {
    @Insert
    void insert(Vocab word);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Vocab> words);

    @Query("DELETE FROM vocab")
    void deleteAll();

    @Query("SELECT * from vocab ORDER BY errorCount ASC")
    List<Vocab> getAllWords();

    @Query("SELECT * from vocab WHERE word = :word")
    Vocab getWord(String word);

    @Query("SELECT * from vocab WHERE groupName = :groupName ORDER BY errorCount ASC LIMIT :limit")
    List<Vocab> getGroupWords(String groupName, int limit);

    @Query("SELECT DISTINCT groupName from vocab")
    List<String> getAllGroupsName();

    @Update
    void update(Vocab word);

    @Update
    void update(List<Vocab> words);
}

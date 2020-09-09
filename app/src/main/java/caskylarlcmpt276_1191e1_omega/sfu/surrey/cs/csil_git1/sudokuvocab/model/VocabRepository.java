package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.controller.VocabApplication;

/**
 * Vocabulary data manipulation layer
 * Model layer
 */
public class VocabRepository {
    private VocabDao mWordDao;
    private static VocabRepository instance;

    public static synchronized VocabRepository getInstance(){
        if (instance == null){
            instance = new VocabRepository(VocabApplication.getApplication());
        }
        return instance;
    }

    private VocabRepository(Application application) {
        VocabDatabase db = VocabDatabase.getDatabase(application);
        mWordDao =  db.vocabDao();
    }

    public List<Vocab> queryByGroup(String groupName, int limit){
        return mWordDao.getGroupWords(groupName,limit);
    }

    public void insertBatch(List<Vocab> words){
        mWordDao.insertAll(words);
    }

    public List<String> getAllGroupsName(){
       return mWordDao.getAllGroupsName();
    }

    public void update(List<Vocab> words){
         mWordDao.update(words);
    }

}

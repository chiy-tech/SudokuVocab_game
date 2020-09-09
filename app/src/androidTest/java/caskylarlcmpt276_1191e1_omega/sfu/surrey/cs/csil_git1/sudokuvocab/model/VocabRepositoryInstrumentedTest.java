package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class VocabRepositoryInstrumentedTest {

    private VocabDatabase mDatabase;
    private VocabDao vocabDao;

    private static String[] ens = new String[]{"one","two","three"};
    private static String[] cns = new String[]{"一","二","三"};

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                VocabDatabase.class)
                .allowMainThreadQueries()
                .build();

        vocabDao = mDatabase.vocabDao();
    }

    @Test
    public void onInsertAndQuery(){
        String groupName = "test";
        vocabDao.insert(new Vocab(ens[0],Lang.EN,cns[0],Lang.CN,groupName));
        assertEquals(vocabDao.getWord(ens[0]).getFword(),cns[0]);
    }

    @Test
    public void onInsertBatch(){
        String groupName = "test_onInsertBatch";
        List< Vocab > list = new ArrayList<>();
        for (int i = 0; i < ens.length; i++) {
            list.add(new Vocab(ens[i],Lang.EN,cns[i],Lang.CN,groupName));
        }
        vocabDao.insertAll(list);
        assertEquals(vocabDao.getGroupWords(groupName,list.size()).size(),ens.length);
    }


    @Test
    public void onUpdate(){
        String after_update = "after_update";
        vocabDao.insert(new Vocab(ens[0],Lang.EN,cns[0],Lang.CN,"onUpdate_before"));
        assertNotEquals(vocabDao.getWord(ens[0]).getGroupName(),after_update);

        vocabDao.update(new Vocab(ens[0],Lang.EN,cns[0],Lang.CN,after_update));
        assertEquals(vocabDao.getWord(ens[0]).getGroupName(),after_update);
    }

    @Test
    public void onClear(){
        vocabDao.insert(new Vocab(ens[0],Lang.EN,cns[0],Lang.CN,"onClear"));
        assertNotNull(vocabDao.getAllGroupsName());
        vocabDao.deleteAll();
        assertTrue(vocabDao.getAllGroupsName().isEmpty());
    }


    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }
}

package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * vocabulary data class
 */
@Entity
public class Vocab {
    /**
     * native language word
     */
    @PrimaryKey
    @NonNull
    private String word;
    /**
     * native language type
     */
    public int nLang;
    /**
     * Foreign language word
     */
    private String fword;
    /**
     * Foreign language type
     */
    public int fLang;
    /**
     * sudoku game error times
     */
    private int errorCount;
    /**
     * The group to which the vocabulary belongs can be a chapter
     */
    private String groupName;


    @Ignore
    public Vocab(String word, Lang nativeLa, String sWord, Lang foreignLa,String groupName) {
        this.word = word;
        this.fword = sWord;
        this.fLang = foreignLa.code;
        this.nLang = nativeLa.code;
        this.groupName = groupName;
    }

    public Vocab(@NonNull String word, int nLang, String fword, int fLang, int errorCount, String groupName) {
        this.word = word;
        this.nLang = nLang;
        this.fword = fword;
        this.fLang = fLang;
        this.errorCount = errorCount;
        this.groupName = groupName;
    }

    public int getnLang() {
        return nLang;
    }

    public void setnLang(int nLang) {
        this.nLang = nLang;
    }

    public int getfLang() {
        return fLang;
    }

    public void setfLang(int fLang) {
        this.fLang = fLang;
    }

    public String getWord() {
        return word;
    }

    public String getNativeWord(Lang la){
        if (la.code == nLang){
            return word;
        }else {
            return fword;
        }
    }

    public String getForeignWord(Lang la){
        if (la.code == fLang){
            return fword;
        }else {
            return word;
        }
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFword() {
        return fword;
    }

    public void setFword(String fword) {
        this.fword = fword;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public void errorMaked(){
        this.errorCount++;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Vocab{" +
                "word='" + word + '\'' +
                ", fword='" + fword + '\'' +
                ", errorCount=" + errorCount +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}

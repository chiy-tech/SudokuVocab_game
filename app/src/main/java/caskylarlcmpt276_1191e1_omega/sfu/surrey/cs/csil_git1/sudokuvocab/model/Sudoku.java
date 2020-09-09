package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import android.text.TextUtils;

/**
 * sudoku data class
 */
public class Sudoku {
    private Vocab value;
    private int code;
    private boolean isShow;
    private Vocab choice;
    private int choiceCode;

    public Sudoku(Vocab value, int code, boolean isShow) {
        this.value = value;
        this.code = code;
        this.isShow = isShow;
        if (isShow) {
            this.choice = value;
            this.choiceCode = code;
        }else {
            this.choice=null;
            this.choiceCode = -1;
        }
    }

    public int getChoiceCode() {
        return choiceCode;
    }

    public void setChoiceCode(int choiceCode) {
        this.choiceCode = choiceCode;
    }

    public Vocab getValue() {
        return value;
    }

    public void setValue(Vocab value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public Vocab getChoice() {
        return choice;
    }
    public String getChoice(Lang lang) {
        return choice==null? "?":choice.getForeignWord(lang);
    }

    public void setChoice(Vocab choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return choiceCode == code;
    }
}

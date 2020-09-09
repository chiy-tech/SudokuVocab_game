package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import java.util.Locale;

/**
 * Language type
 */
public enum Lang {
    EN(Locale.ENGLISH,"English",0),
    CN(Locale.CHINESE,"Chinese",1);

    public Locale lo;
    public String description;
    public int code;
    Lang(Locale lo,String description,int code){
        this.lo = lo;
        this.description = description;
        this.code = code;
    }

    @Override
    public String toString() {
        return description;
    }

    public static Lang of(int code){
        for (Lang value : Lang.values()) {
            if (value.code == code){
                return value;
            }
        }
        return EN;
    }
}

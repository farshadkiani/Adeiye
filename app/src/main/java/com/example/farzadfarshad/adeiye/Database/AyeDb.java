package com.example.farzadfarshad.adeiye.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by FARZAD&FARSHAD on 13/12/2017.
 */

@Table(name = "AyeDbes")
public class AyeDb extends Model {
    @Column(name = "NameDoa")
    public String nameDoa;

    @Column(name = "Matn")
    public String matn;

    @Column(name = "Mani")
    public String mani;

    @Column(name = "DoaDb")
    public DoaDb doaDb;

    public String getMani() {
        return mani;
    }

    public String getMatn() {
        return matn;
    }
}
package com.example.farzadfarshad.adeiye.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by FARZAD&FARSHAD on 13/12/2017.
 */

@Table(name = "DoaDbes")
public class DoaDb extends Model {

    @Column(name = "Name")
    public String name;


    public List<AyeDb> AyeDbes(){
        return getMany(AyeDb.class , "DoaDb");
    }

    public String getName() {
        return name;
    }
}

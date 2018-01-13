package com.example.farzadfarshad.adeiye.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by FARZAD&FARSHAD on 12/01/2018.
 */

@Table(name = "OghatDbes")
public class OghatDb extends Model {
    @Column(name = "Fajr")
    public String fajr;

    @Column(name = "Sunrise")
    public String sunrise;

    @Column(name = "Dhuhr")
    public String dhuhr;

    @Column(name = "Asr")
    public String asr;

    @Column(name = "Sunset")
    public String sunset;

    @Column(name = "Maghreb")
    public String maghreb;

    @Column(name = "Isha")
    public String isha;

    @Column(name = "Imask")
    public String imask;

    @Column(name = "Midnight")
    public String midnight;

    @Column(name = "Month")
    public String month;

    @Column(name = "Day")
    public String day;

    @Column(name = "Province")
    public String province;

    @Column(name = "City")
    public String city;

    public String getAsr() {
        return asr;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public String getFajr() {
        return fajr;
    }

    public String getImask() {
        return imask;
    }

    public String getIsha() {
        return isha;
    }

    public String getMaghreb() {
        return maghreb;
    }

    public String getMidnight() {
        return midnight;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }
}
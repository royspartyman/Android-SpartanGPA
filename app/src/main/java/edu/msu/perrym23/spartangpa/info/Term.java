package edu.msu.perrym23.spartangpa.info;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by royspartyman on 12/27/16.
 */

public class Term extends RealmObject {

    private String season;
    private Integer year;
    private RealmList<Class> classes;
    private Double cumulativeGpa;
    private Double technicalGpa;

    public Term() {
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public RealmList<Class> getClasses() {
        return classes;
    }

    public void setClasses(RealmList<Class> classes) {
        this.classes = classes;
    }

    public Double getCumulativeGpa() {
        return cumulativeGpa;
    }

    public void setCumulativeGpa(Double cumulativeGpa) {
        this.cumulativeGpa = cumulativeGpa;
    }

    public Double getTechnicalGpa() {
        return technicalGpa;
    }

    public void setTechnicalGpa(Double technicalGpa) {
        this.technicalGpa = technicalGpa;
    }
}

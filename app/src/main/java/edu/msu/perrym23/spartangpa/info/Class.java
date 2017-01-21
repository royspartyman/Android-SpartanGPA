package edu.msu.perrym23.spartangpa.info;

import io.realm.RealmObject;

/**
 * Created by royspartyman on 12/27/16.
 */

public class Class extends RealmObject {

    private String name;
    private Double grade;
    private Double credit;

    public Class() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}

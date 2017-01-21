package edu.msu.perrym23.spartangpa.info;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by royspartyman on 12/27/16.
 */

public class Student extends RealmObject {

    private String firstName;
    private String lastName;
    private String major;
    private Double credits;
    private Double cumulativeGpa;
    private Double technicalGpa;
    private String year;
    private RealmList<Term> terms;

    public Student() {
    }

    public RealmList<Term> getTerms() {
        return terms;
    }

    public void setTerms(RealmList<Term> terms) {
        this.terms = terms;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void addTerm(Term term) {
        terms.add(term);
    }
}

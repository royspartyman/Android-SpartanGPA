package edu.msu.perrym23.spartangpa.utils;

import android.content.Context;

import java.util.Objects;

import edu.msu.perrym23.spartangpa.RecyclerViewAdapters.SemesterAdapter;
import edu.msu.perrym23.spartangpa.info.Student;
import edu.msu.perrym23.spartangpa.info.Term;
import io.realm.Realm;
import io.realm.RealmList;

public class RealmManager {

    private Realm realm;

    public RealmManager(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void createStudent(String firstName, String lastName, String major) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMajor(major);
        realm.beginTransaction();
        realm.copyToRealm(student);
        realm.commitTransaction();
    }

    public Student getStudent() {
        return realm.where(Student.class).findFirst();
    }

    public void createTerm(String season, Integer year) {
        Term term = new Term();
        term.setSeason(season);
        term.setYear(year);
        Student student = realm.where(Student.class).findFirst();
        realm.beginTransaction();
        student.addTerm(term);
        realm.copyToRealm(student);
        realm.commitTransaction();
    }

    public RealmList<Term> getTerms() {
        Student student = realm.where(Student.class).findFirst();
        return student.getTerms();
    }

    public boolean checkExistingTerm(String season, Integer year) {
        Student student = realm.where(Student.class).findFirst();
        RealmList<Term> terms = student.getTerms();

        for (Term term : terms) {
            if (Objects.equals(term.getYear(), year) && Objects.equals(term.getSeason(), season)) {
                return true;
            }
        }
        return false;
    }

}

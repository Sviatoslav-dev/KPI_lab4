package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {

    private String name;
    private ArrayList<Float> subject_marks;
    private float sum;
    private float session;
    private float first_add_session;
    private float second_add_session;

    public void sum_marks() {
        sum = 0;
        for (Float subject_mark : subject_marks) {
            sum += subject_mark;
        }
    }

    public String getName () {
        return name;
    }

    public void setName (String n) {
        name = n;
    }

    public ArrayList<Float> getSubjectMarks () {
        return subject_marks;
    }

    public void setSubjectMarks (ArrayList<Float> sm) {
        subject_marks = sm;
    }

    public float getSum () {
        return sum;
    }

    public void setSum (float s) {
        sum = s;
    }

    public float getSession () {
        return session;
    }

    public void setSession (float s) {
        session = s;
    }

    public float getFirstAddSession () {
        return first_add_session;
    }

    public void setFirstAddSession (float s) {
        first_add_session = s;
    }

    public float getSecondAddSession () {
        return second_add_session;
    }

    public void setSecondAddSession (float s) {
        second_add_session = s;
    }
}

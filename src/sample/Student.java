package sample;

import java.io.Serializable;
import java.util.ArrayList;


public class Student implements Serializable {

    private String username;
    private String name;
    private String group;
    private ArrayList<Subject> subjects;

    public Student () {
        subjects = new ArrayList<>();
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String un) {
        username = un;
    }

    public String getName () {
        return name;
    }

    public void setName (String n) {
        name = n;
    }

    public String getGroup () {
        return group;
    }

    public void setGroup (String gp) {
        group = gp;
    }

    public ArrayList<Subject> getSubjects () {
        return subjects;
    }

    public void setSubjects (ArrayList<Subject> sb) {
        subjects = sb;
    }
}


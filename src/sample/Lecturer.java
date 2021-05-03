package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {
    private String username;
    private String name;
    private ArrayList<String> subjects;
    private ArrayList<String> groups;

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

    public ArrayList<String> getSubjects () {
        return subjects;
    }

    public void setSubjects (ArrayList<String> sb) {
        subjects = sb;
    }

    public ArrayList<String> getGroups () {
        return groups;
    }

    public void setGroups (ArrayList<String> gp) {
        groups = gp;
    }
}


package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {
    String username;
    String name;
    String subject;
    ArrayList<String> groups;
}

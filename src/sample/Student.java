package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    String username;
    String name;
    String group;
    ArrayList<Subject> subjects;

    public class Subject {
        String name;
        ArrayList<Float> subject_marks;
        float sum;
        float session;
        float first_dopka;
        float second_dopka;

        public void sum_marks () {
            sum = 0;

            for (int i = 0; i < subject_marks.size(); i++) {
                sum += subject_marks.get(i);
            }
        }
    }
}

package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {

        String name;
        ArrayList<Float> subject_marks;
        float sum;
        float session;
        float first_dopka;
        float second_dopka;

        public void sum_marks() {
            sum = 0;
            for (int i = 0; i < subject_marks.size(); i++) {
                sum += subject_marks.get(i);
            }
        }

        //Subject () {
        //    session = -1;
        //    first_dopka = -1;
        //    second_dopka = -1;
        //}
}

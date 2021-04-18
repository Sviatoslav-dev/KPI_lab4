package sample;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    ArrayList<Account> accaunts;
    ArrayList<Student> students;
    ArrayList<Lecturer> lecturers;

    public DataBase () throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Accaunts.dat"));
        accaunts = (ArrayList<Account>)ois.readObject();

        ois = new ObjectInputStream(new FileInputStream("Students.dat"));
        students = (ArrayList<Student>)ois.readObject();

        ois = new ObjectInputStream(new FileInputStream("Lecturers.dat"));
        lecturers = (ArrayList<Lecturer>)ois.readObject();

        /*ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Accaunts.dat"));
        oos.writeObject(accaunts);

        oos = new ObjectOutputStream(new FileOutputStream("Students.dat"));
        oos.writeObject(students);

        oos = new ObjectOutputStream(new FileOutputStream("Lecturers.dat"));
        oos.writeObject(lecturers);*/
    }

    public void add_student () {

    }
}

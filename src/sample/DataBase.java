package sample;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    ArrayList<Account> accaunts = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Lecturer> lecturers = new ArrayList<>();

    public DataBase () throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Accaunts.dat"));
        accaunts = (ArrayList<Account>)ois.readObject();

        ois = new ObjectInputStream(new FileInputStream("Students.dat"));
        students = (ArrayList<Student>)ois.readObject();

        ois = new ObjectInputStream(new FileInputStream("Lecturers.dat"));
        lecturers = (ArrayList<Lecturer>)ois.readObject();

        //System.out.print(accaunts.get(0).username);

        /*Account decanat = new Account();
        decanat.password = "decanat123";
        decanat.username = "Decanat";
        decanat.type = "Decanat";

        accaunts.add(decanat);


        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Accaunts.dat"));
        oos.writeObject(accaunts);
        oos = new ObjectOutputStream(new FileOutputStream("Students.dat"));
        oos.writeObject(students);

        oos = new ObjectOutputStream(new FileOutputStream("Lecturers.dat"));
        oos.writeObject(lecturers);*/
    }

    public void save_accaunts () throws IOException {                                                 //Ці три функці для записування у файл
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Accaunts.dat"));  //нового акаунта, студента, чи викладача відповідно
        oos.writeObject(accaunts);
    }

    public void save_students () throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Students.dat"));
        oos.writeObject(students);
    }

    public void save_lecturers () throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Lecturers.dat"));
        oos.writeObject(lecturers);
    }


    public void add_student (String name, String group, String username, String password, ArrayList<String> subjects) {  //Додавання студента
        Account student = new Account();
        student.password = password;
        student.username = username;
        student.type = "Student";

        Student st = new Student();
        st.group = group;
        st.subjects = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
            Subject sub = new Subject();
            sub.name = subjects.get(i);
            st.subjects.add(sub);
        }
        st.name = name;
        st.username = username;

        accaunts.add(student);
        students.add(st);
    }

    public void add_lecturer (String name, String username, String password, ArrayList<String> groups, String subject) {      ////Додавання викладаче
        Account acc = new Account();
        acc.password = password;
        acc.username = username;
        acc.type = "Lecturer";

        Lecturer lec = new Lecturer();
        lec.groups = groups;
        lec.name = name;
        lec.subject = subject;
        lec.username = username;

        lecturers.add(lec);
        accaunts.add(acc);
    }

    public String log_in (String username, String password) {              //Для входу. Повертає тип акаунта, якщо акаунт відсутній повертає "Missing"
        int acc_id = -1;

        for (int i = 0; i < accaunts.size(); i++) {
            if (accaunts.get(i).username.equals(username) && accaunts.get(i).password.equals(password)) {
                acc_id = i;
            }
        }

        if (acc_id == -1) {
            return "Missing";
        } else {
            return accaunts.get(acc_id).type;
        }
    }

    int find_student_by_username(String username) {           //Повертає номер у списку
        int id = -1;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).username == username) {
                id = i;
            }
        }

        return id;
    }

    int add_mark (int student_id, String subject, float mark) {
        int res = -1;
        for (int i = 0; i < students.get(student_id).subjects.size(); i++) {
            if (students.get(student_id).subjects.get(i).name.equals(subject)) {
                students.get(student_id).subjects.get(i).subject_marks.add(mark);
                students.get(student_id).subjects.get(i).sum_marks();
                res = 0;
            }
        }

        return res;
    }

    int find_student_by_name(String name) {           //Повертає номер у списку
        int id = -1;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).name.equals(name)) {
                id = i;
            }
        }

        return id;
    }
}

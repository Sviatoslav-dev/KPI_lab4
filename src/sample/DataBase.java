package sample;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    public ArrayList<Account> accaunts;
    public ArrayList<Student> students;
    public ArrayList<Lecturer> lecturers;

    public DataBase () throws IOException, ClassNotFoundException {
        getData();
        //clearData ();
    }

    void getData () throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Accaunts.dat"));
        accaunts = (ArrayList<Account>)ois.readObject();

        ois = new ObjectInputStream(new FileInputStream("Students.dat"));
        students = (ArrayList<Student>)ois.readObject();

        ois = new ObjectInputStream(new FileInputStream("Lecturers.dat"));
        lecturers = (ArrayList<Lecturer>)ois.readObject();
    }

    void clearData () throws IOException {
        accaunts = new ArrayList<>();
        Account decanat = new Account();
        decanat.setPassword("decanat123");
        decanat.setUsername("Decanat");
        decanat.setType("Decanat");

        accaunts.add(decanat);


        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Accaunts.dat"));
        oos.writeObject(accaunts);
        oos = new ObjectOutputStream(new FileOutputStream("Students.dat"));
        oos.writeObject(students);

        oos = new ObjectOutputStream(new FileOutputStream("Lecturers.dat"));
        oos.writeObject(lecturers);
    }

    public void save_accaunts () throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Accaunts.dat"));
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


    public void add_student (String name, String group, String username, String password, ArrayList<String> subjects) {
        Account student = new Account();
        student.setPassword(password);
        student.setUsername(username);
        student.setType("Student");

        Student st = new Student();
        st.setGroup(group);
        st.setSubjects(new ArrayList<>());
        for (String subject : subjects) {
            Subject sub = new Subject();
            sub.setName(subject);
            st.getSubjects().add(sub);
        }
        st.setName(name);
        st.setUsername(username);

        accaunts.add(student);

        if (students != null)
            students.add(st);
        else {
            students = new ArrayList<>();
            students.add(st);
        }
    }

    public void add_lecturer (String name, String username, String password, ArrayList<String> groups, ArrayList<String> subjects) {
        Account acc = new Account();
        acc.setPassword(password);
        acc.setUsername(username);
        acc.setType("Lecturer");

        Lecturer lec = new Lecturer();
        lec.setGroups(groups);
        lec.setName(name);
        lec.setSubjects(subjects);
        lec.setUsername(username);

        if (lecturers != null) {
            lecturers.add(lec);
        } else {
            lecturers = new ArrayList<>();
            lecturers.add(lec);
        }
        accaunts.add(acc);
    }

    public String log_in (String username, String password) {
        int acc_id = -1;

        for (int i = 0; i < accaunts.size(); i++) {
            if (accaunts.get(i).getUsername().equals(username) && accaunts.get(i).getPassword().equals(password)) {
                acc_id = i;
            }
        }

        if (acc_id == -1) {
            return "Missing";
        } else {
            return accaunts.get(acc_id).getType();
        }
    }

    public int find_student_by_username(String username) {
        int id = -1;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUsername().equals(username)) {
                id = i;
            }
        }

        return id;
    }

    public int find_lectorer_by_username(String username) {
        int id = -1;

        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getUsername().equals(username)) {
                id = i;
            }
        }

        return id;
    }

    public void add_mark (int student_id, String subject, float mark) {
        for (int i = 0; i < students.get(student_id).getSubjects().size(); i++) {
            if (students.get(student_id).getSubjects().get(i).getName().equals(subject)) {
                if (students.get(student_id).getSubjects().get(i).getSubjectMarks() == null) {
                    students.get(student_id).getSubjects().get(i).setSubjectMarks(new ArrayList<>());
                }
                students.get(student_id).getSubjects().get(i).getSubjectMarks().add(mark);
                students.get(student_id).getSubjects().get(i).sum_marks();
            }
        }

    }

    public int find_student_by_name(String name) {
        int id = -1;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                id = i;
            }
        }

        return id;
    }

    public int subjects_id (int st_id, String name) {
        int res = -1;

        for (int i = 0; i < students.get(st_id).getSubjects().size(); i++) {
            if (students.get(st_id).getSubjects().get(i).getName().equals(name)) {
                res = i;
            }
        }
        return res;
    }

    public ArrayList<Integer> get_students_by_group_and_subject (String group, String subject) {
        ArrayList<Integer> students_id = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getGroup().equals(group) && subjects_id(i, subject) != -1) {
                students_id.add(i);
            }
        }

        return students_id;
    }

    public void transfer_in_other_group (int st_id, String group) {
        students.get(st_id).setGroup(group);
    }

    public int findAccauntByUsername (String username) {
        int id = -1;

        for (int i = 0; i < accaunts.size(); i++) {
            if (accaunts.get(i).getUsername().equals(username)) {
                id = i;
            }
        }

        return id;
    }

    public void DeleteStudent (int st_id) throws IOException {
        students.remove(st_id);
        accaunts.remove(findAccauntByUsername (students.get(st_id).getUsername()));
        save_students();
        save_accaunts();
    }
}

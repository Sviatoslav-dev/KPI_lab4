#include "student.h"
#include "tutor.h"
#include<iostream>
#include<fstream>
#include<vector>

using namespace std;

void write_tutor(vector <Tutor> &tutors){
  ifstream file("Tutors.csv");
  Tutor tutor;
  string str;
  while (!file.eof()) {
    file >> str;
    tutor.setName(str.substr(0, str.size()-1));

    file >> str;
    tutor.setPassword(str.substr(0, str.size()-1));
    tutors.push_back(tutor);
  }
}

void write_student(vector <Student> &students){

  ifstream file("Users.csv");
  Student student;

  string str;
  while (!file.eof()) {
    file >> str;
    // cout << str;
    student.setName(str.substr(0, str.size()-1));

    file >> str;
    student.setPassword(str.substr(0, str.size()-1));

    file >> str;
    student.setGroup(str.substr(0, str.size()-1));

    file >> str;
    student.setCourse(stoi(str.substr(0, str.size()-1)));

    for (size_t i = 0; i < 6; i++) {
      file >> str;
      // cout << str;
      student.setSubjects(str.substr(0, str.size()-1), i);
      str = "";

      file >> str;
      // cout << str;
      student.setMarks(stoi(str.substr(0, str.size()-1)), i);
      str = "";
      // cout << student.getSubjects(i) << "," << student.getMarks(i) << endl;
    }

    for (size_t i = 0; i < 3; i++) {
      file >> str;
      student.setSession(str.substr(0, str.size()-1), i);

      file >> str;
      student.setSessionMarks(stoi(str.substr(0, str.size()-1)), i);

      // cout << student.getSubjects(i) << " " << student.getMarks(i) << endl;
    }
    // cout << student.getName() << endl << student.getPassword() << endl;

    students.push_back(student);
  }
}

void print_info(vector <Student> students){
  for (size_t i = 0; i < students.size(); i++) {
    cout << students[i].getName() << endl;

    cout << students[i].getPassword() << endl;

    cout << students[i].getGroup() << endl;

    for (size_t j = 0; j < 6; j++) {
      cout << students[i].getSubjects(j) << ",";

      cout << students[i].getMarks(j) << endl;

    }

    for (size_t j = 0; j < 3; j++) {
      cout << students[i].getSession(j) << ",";

      cout << students[i].getSessionMarks(j) << endl;

      // cout << student.getSession(i) << " " << student.getSessionMarks(i) << endl;
    }

  }
}

void make_file(vector <Student> students){
  ofstream file("Users.csv");
  for (size_t i = 0; i < students.size(); i++) {

    file << students[i].getName() << ", ";

    file << students[i].getPassword() << ", ";

    file << students[i].getGroup() << ", ";

    file << students[i].getCourse() << ", ";

    for (size_t j = 0; j < 6; j++) {

      file << students[i].getSubjects(j) << ", ";

      file << students[i].getMarks(j) << ", ";

    }

    for (size_t j = 0; j < 3; j++) {
      if (j < 2) {
        file << students[i].getSession(j) << ", ";

        file << students[i].getSessionMarks(j) << ", ";
      }
      else{
        file << students[i].getSession(j) << ", ";

        file << students[i].getSessionMarks(j)<< ",";
      }

      // cout << student.getSession(i) << " " << student.getSessionMarks(i) << endl;
    }
    if (i < students.size()-1) {
      file << endl;
    }

  }
}


bool find_user(string login, string password, vector <Student> students, int &ind){
  bool ok = false;
  for (size_t i = 0; i < students.size(); i++) {
    if (login == students[i].getName() && password == students[i].getPassword()) {
      ok = true;
      ind = i;
    }
  }
  return ok;
}

void print_marks(Student student){
  for (size_t i = 0; i < 6; i++) {
    cout << student.getSubjects(i) << ": " << student.getMarks(i) << endl;
  }
}

void print_sessionMarks(Student student){
  for (size_t i = 0; i < 3; i++) {
    cout << student.getSession(i) << ": " << student.getSessionMarks(i) << endl;
  }
}

bool find_tutor(string login, string password, vector <Tutor> tutors, int &ind){
  bool ok = false;
  for (size_t i = 0; i < tutors.size(); i++) {
    if (login == tutors[i].getName() && password == tutors[i].getPassword()) {
      ok = true;
      ind = i;
    }
  }
  return ok;
}

int find_student(string name, vector <Student> students){
  for (size_t i = 0; i < students.size(); i++) {
    if (name == students[i].getName()) {
      return i;
    }
  }
  return -1;
}

void giveGrades(Student &student){
  string subject;
  int mark;
  char ch;

  do {
    for (size_t i = 0; i < 6; i++) {
      cout << student.getSubjects(i) << ": " << student.getMarks(i) << endl;
    }
    cout << "Виберіть предмет, по якому хочете виставити оцінки: "; cin >> subject;
    int ind = -1;
    for (size_t i = 0; i < 6; i++) {
      if (subject == student.getSubjects(i)) {
        ind = i;
      }
    }
    if (ind != -1) {
      cout << "Напишіть оцінку: "; cin >> mark;
      student.setMarks(mark, ind);
    }
    else{
      cout << "Такого предмета немає" << endl;
    }
    cout << "Бажаєте продовжитит виставляти оцінки?(y, n): "; cin >> ch;

  } while(ch != 'n');

}

void giveSessionGrades(Student &student){
  string subject;
  int mark;
  char ch;

  do {
    for (size_t i = 0; i < 3; i++) {
      cout << student.getSession(i) << ": " << student.getSessionMarks(i) << endl;
    }
    cout << "Виберіть предмет, по якому хочете виставити оцінки: "; cin >> subject;
    int ind = -1;
    for (size_t i = 0; i < 3; i++) {
      if (subject == student.getSession(i)) {
        ind = i;
      }
    }
    if (ind != -1) {
      cout << "Напишіть оцінку: "; cin >> mark;
      student.setSessionMarks(mark, ind);
    }
    else{
      cout << "Такого предмета немає" << endl;
    }
    cout << "Бажаєте продовжитит виставляти оцінки?(y, n): "; cin >> ch;

  } while(ch != 'n');
}

void make_File(vector <Tutor> tutors){
  ofstream file("Tutors.csv");
  for (size_t i = 0; i < tutors.size(); i++) {
    file << tutors[i].getName() << ", ";
    if (i < tutors.size()-1) {
      file << tutors[i].getPassword() << "," << endl;
    }
    else{
      file << tutors[i].getPassword() << ",";
    }
  }
}

void addTutor(vector <Tutor> &tutors){
  Tutor tutor;
  string name, password;
  cout << "Введіть ім'я нового викладача: "; cin >> name;
  cout << "Введіть його новий пароль: "; cin >> password;
  tutor.setName(name);
  tutor.setPassword(password);
  tutors.push_back(tutor);
}

void addStudent(vector <Student> &students){
  Student student;
  string name, password, group;
  cout << "Введіть ім'я нового студента: "; cin >> name;
  cout << "Введіть його новий пароль: "; cin >> password;
  cout << "Введіть його групу: "; cin >> group;

  student.setName(name);
  student.setPassword(password);
  student.setGroup(group);
  students.push_back(student);
}


int main(){
  string decPassword = "password";
  string decLogin = "decanat";
  vector <Student> students;
  vector <Tutor> tutors;
  write_student(students);
  write_tutor(tutors);

  string login, password;
  string str;
  int ind, diya, index;
  char ch;
  do {
    cout << "Введіть логін: "; cin >> login;
    cout << "Введіть пароль: "; cin >> password;


    // функционал для деканата
    if (login == decLogin && password == decPassword) {
      do {
        string studentName;
        cout << "Що ви хочете зробити?\n 1) переглянути оцінки студента\n 2) переглянути результати сесії студента\n 3) зареєструвати нового користувача\n 4) перевести студента\n 5) відрахувати студента\n 6) вийти" << endl;
        cin >> diya;
        if (diya == 1) {
          cout << "Введіть ім'я студента: "; cin >> studentName;
          int ind = find_student(studentName, students);
          print_marks(students[ind]);
        }

        else if (diya == 2) {
          cout << "Введіть ім'я студента: "; cin >> studentName;
          int ind = find_student(studentName, students);
          print_sessionMarks(students[ind]);
        }

        else if (diya == 3) {
          int who;
          do {
            cout << "Кого ви бажаєте додати?\n 1) викладача\n 2) студента\n";
            cin >> who;
            if (who == 1) {
              addTutor(tutors);
            }
            else if (who == 2) {
              addStudent(students);
            }
            else{
              cout << "будь ласка, введіть або \"1\" або \"2\"" << endl;
            }
          } while(!(who == 1 || who == 2));
        }

        else if (diya == 4) {
          int what;

          do {
            cout << "Що ви хочете зробити?\n 1) перевести студента на інший курс\n 2) перевести студента в іншу групу\n";
            cin >> what;
            if (what == 1) {
              cout << "Введіть ім'я студента, якого хочете перевести на інший курс: "; cin >> studentName;
              int ind = find_student(studentName, students);
              int course;
              cout << "Введіть номер курсу, на який хочете перевести студента: "; cin >> course;
              if (course >= 1 && course <= 7) {
                students[ind].setCourse(course);
              }
              else{
                cout << "Такого курсу не існує" << endl;
              }
            }
            else if (what == 2) {
              cout << "Введіть ім'я студента, якого хочете перевести в іншу групу: "; cin >> studentName;
              int ind = find_student(studentName, students);
              string group;
              cout << "Введіть назву групи, у яку хочете перевести студента: "; cin >> group;
              students[ind].setGroup(group);
            }
            else{
              cout << "будь ласка, введіть або \"1\" або \"2\"" << endl;
            }
          } while(!(what == 1 || what == 2));
        }

        if (diya == 5) {
          cout << "Введіть ім'я студента, якого хочете відрахувати: "; cin >> studentName;
          int ind = find_student(studentName, students);
          if (ind > 0 && ind < students.size()) {
            students.erase(students.begin() + ind);
          }
          else{
            cout << "Такого студента не існує" << endl;
          }
        }

        else if (diya == 6) {
          str = "exit";
        }

        cout << "Бажаєте вийти?(y, n): "; cin >> ch;
      } while(ch != 'y');
    }


    // функционал для студента
    else if (find_user(login, password, students, ind)) {
      do {
        cout << "ви студент!" << endl;
        // cout << ind << endl;
        cout << "Що ви хочете зробити?\n 1) переглянути оцінки\n 2) переглянути результати сесії\n 3) змінити пароль\n 4) вийти\n";
        cout << "Введіть цифру-номер дії: "; cin >> diya;
        if (diya == 1) {
          print_marks(students[ind]);
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }
        else if (diya == 2) {
          print_sessionMarks(students[ind]);
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }

        else if (diya == 3) {
          string pass;
            cout << "будь ласка, введіть новий пароль: ";
            cin >> pass;
            students[ind].setPassword(pass);
            cout << "Пароль успішно змінено" << endl;

            cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }

        else if (diya == 4) {
          str = "exit";
        }
      } while(diya != 4 && ch != 'y');
    }


    // функционал для преподователя
    else if (find_tutor(login, password, tutors, index)) {
      do {
        string studentName;
        cout << "Що ви хочете зробити?\n 1) переглянути оцінки\n 2) переглянути результати сесії\n 3) виставити оцінки\n 4) виставити результати сесії \n 5) змінити пароль\n 6) вийти\n";
        cin >> diya;


        if (diya == 1) {
          cout << "будь ласка, введіть ім'я студента: "; cin >> studentName;
          int ind = find_student(studentName, students);
          if (ind != -1) {
            print_marks(students[ind]);
          }
          else{
            cout << "Такого студента не існує" << endl;
          }
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }


        if (diya == 2) {
          cout << "будь ласка, введіть ім'я студента: "; cin >> studentName;
          int ind = find_student(studentName, students);
          if (ind != -1) {
            print_sessionMarks(students[ind]);
          }
          else{
            cout << "Такого студента не існує" << endl;
          }
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }
        if (diya == 3) {
          cout << "будь ласка, введіть ім'я студента, якому хочете виставити оцінки: "; cin >> studentName;
          int ind = find_student(studentName, students);
          giveGrades(students[ind]);
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }
        if (diya == 4) {
          cout << "будь ласка, введіть ім'я студента, якому хочете виставити результати сесії: "; cin >> studentName;
          int ind = find_student(studentName, students);
          giveSessionGrades(students[ind]);
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }
        if (diya == 5) {
          string newpassword;
          cout << "Введіть новий пароль: ";
          cin >> newpassword;
          cout << "Пароль успішно змінено" << endl;
          tutors[index].setPassword(newpassword);
          cout << "бажаєте вийти?(y, n): "; cin >> ch;
        }

        if (diya == 6) {
          str = "exit";
        }
      } while(diya != 6 && ch != 'y');

    }



    else{
        cout << "Неправильний логін або пароль" << endl;
    }
    make_file(students);
    make_File(tutors);

  } while(str != "exit" && ch != 'y');
  // print_info(students);
}

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
    cout << student.getSession(i) << ": " << setw (10) << student.getSessionMarks(i) << endl;
  }
}

bool find_tutor(string login, string password, vector <Tutor> tutors){
  bool ok = false;
  for (size_t i = 0; i < tutors.size(); i++) {
    if (login == tutors[i].getName() && password == tutors[i].getPassword()) {
      ok = true;
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

int main(){
  string decPassword = "password";
  string decLogin = "decanat";
  vector <Student> students;
  vector <Tutor> tutors;
  write_student(students);
  write_tutor(tutors);

  string login, password;
  string str;
  int ind, diya;
  do {
    cout << "Введіть логін: "; cin >> login;
    cout << "Введіть пароль: "; cin >> password;



    if (login == decLogin && password == decPassword) {
      cout << "Що ви хочете зробити?\n 1) переглянути оцінки студента\n 2) " << endl;
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
        }
        else if (diya == 2) {
          print_sessionMarks(students[ind]);
        }

        else if (diya == 3) {
          string pass;
            cout << "будь ласка, введіть новий пароль: ";
            cin >> pass;
            students[ind].setPassword(pass);

        }

        else if (diya == 4) {
          str = "exit";
        }
      } while(diya != 4);
    }


    // функционал для преподователя
    else if (find_tutor(login, password, tutors)) {
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
        }
        if (diya == 3) {
          /* code */
        }
        if (diya == 4) {
          /* code */
        }
        if (diya == 5) {
          /* code */
        }
        if (diya == 6) {
          str = "exit";
        }
      } while(diya != 6);

    }



    else{
        cout << "ви ніхто!" << endl;
    }
    make_file(students);

  } while(str != "exit");
  // print_info(students);
}

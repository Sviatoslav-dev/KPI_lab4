#include "student.h"
#include<iostream>
#include<fstream>
#include<vector>

using namespace std;


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

      // cout << student.getSession(i) << " " << student.getSessionMarks(i) << endl;
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

int main(){
  string decPassword = "password";
  string decLogin = "decanat";
  vector <Student> students;
  write_student(students);
  string login, password;
  string str;
  int ind, diya;
  do {
    cout << "Введіть логін: "; cin >> login;
    cout << "Введіть пароль: "; cin >> password;
    if (login == decLogin && password == decPassword) {
      cout << "ви деканат" << endl;
    }

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

    else{
        cout << "ви ніхто!" << endl;
    }
    make_file(students);

  } while(str != "exit");
  // print_info(students);
}

#pragma once

#include <iostream>
#include <string>
using namespace std;

class Student{
private:
  string name;
  string password;
  string group;
  int course;
  string subjects[6];
  int marks[6];
  string session[3];
  int sessionMarks[3];

public:
  Student(){
    course = 1;
    for (size_t i = 0; i < 6; i++) {
      marks[i] = 0;
    }
    for (size_t i = 0; i < 3; i++) {
      sessionMarks[i] = 0;
    }
    subjects[0] = "math";
    subjects[1] = "english";
    subjects[2] = "KPI";
    subjects[3] = "OP";
    subjects[4] = "ASD";
    subjects[5] = "ukr";
    session[0] = "OP";
    session[1] = "AK";
    session[2] = "math";
  }

  void setName(string str){
    name = str;
  }

  string getName(){
    return name;
  }

  void setPassword(string str){
    password = str;
  }

  string getPassword(){
    return password;
  }

  void setGroup(string str){
    group = str;
  }

  string getGroup(){
    return group;
  }

  void setCourse(int i){
    course = i;
  }

  int getCourse(){
    return course;
  }

  void setSubjects(string str, int i){
    subjects[i] = str;
  }

  string getSubjects(int i){
    return subjects[i];
  }

  void setMarks(int mark, int i){
    marks[i] = mark;
  }

  int getMarks(int i){
    return marks[i];
  }

  void setSession(string str, int i){
    session[i] = str;
  }

  string getSession(int i){
    return session[i];
  }

  void setSessionMarks(int mark, int i){
    sessionMarks[i] = mark;
  }

  int getSessionMarks(int i){
    return sessionMarks[i];
  }

};

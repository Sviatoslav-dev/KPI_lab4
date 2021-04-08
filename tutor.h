#pragma once

#include <iostream>
#include <string>
using namespace std;

class Tutor{
private:
  string name;
  string password;

public:
  Tutor(){ }

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
};

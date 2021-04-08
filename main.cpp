#include<iostream>
#include<fstream>
#include<vector>

using namespace std;

class User {
public:
    string name;
    string password;
    string type;
};

void input_users (vector<User> &users) {
    ifstream users_file;
    string line;

    users_file.open("Users.csv");
    while (!users_file.eof()) {
        getline(users_file, line);
        User u;
        for (int i = 0, k = 0; i < line.size(); i++) {
            if (line[i] != ',') {
                if (k == 0) {
                    u.name += line[i];
                } else if (k == 1) {
                    u.password += line[i];
                } else if (k == 2) {
                    u.type += line[i];
                }
            } else {
                k++;
            }
        }
        users.push_back(u);
    }
}

User login () {
    string log, pass;
    vector<User> users;

    while (true) {
        cout << "Input login:";
        getline(cin, log);
        cout << "Input password:";
        getline(cin, pass);

        for (int i = 0; i < users.size(); i++) {
            if (log == users[i].name && pass == users[i].password) {
                return users[i];
            }
        }

        cout << "Wrong login or password" << endl;
    }
}

int main () {
    login ();
}
#ifndef SAVEDEMPROIECTUL_USER_H
#define SAVEDEMPROIECTUL_USER_H

#include <iostream>
#include <string>

using namespace std;

class User {
private:
    int id;
    string name;
public:
    User();

//constructor cu parametri
    User(int id, string nume);

//construtor de copiere
    User(const User &user);

//destructor
    ~User();

//operator de assignare
    User &operator=(const User &user);

//operator de egalitate
    bool operator==(const User &user);

//operator de inegalitate
    bool operator!=(const User &user);

//setter pentru id
    void setId(int id);

//setter pentru nume
    void setName(string name);

//getter pentru id
    int getId() const;

//getter pentru nume
    string getName();

//operatorul de redirectare a iesirii
    friend ostream &operator<<(ostream &os, const User &user);
};


#endif

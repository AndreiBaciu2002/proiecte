#include "User.h"

#include <utility>

User::User() {
    this->id = 0;
    this->name = "";
}

User::User(int id, string nume) {
    this->id = id;
    this->name = std::move(nume);
}

User::User(const User &user) {
    this->id = user.id;
    this->name = user.name;
}

User::~User() {}

string User::getName() {
    return this->name;
}

int User::getId() const {
    return this->id;
}

void User::setName(string name1) {
    this->name = std::move(name1);
}

void User::setId(int id1) {
    this->id = id1;
}

bool User::operator!=(const User &user) {
    if (this->id != user.id && this->name != user.name)
        return true;
    return false;
}

User &User::operator=(const User &user) {
    if (this != &user) {
        this->name = user.name;
        this->id = user.id;
    }
    return *this;
}

bool User::operator==(const User &user) {
    if (this->id == user.id)
        return true;
    return false;
}

ostream &operator<<(ostream &os, const User &user) {
    os << "id-ul este: " << user.id << ", numele este: " << user.name << endl;
    return os;
}

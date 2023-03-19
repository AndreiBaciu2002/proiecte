#ifndef SAVEDEMPROIECTUL_USERREPO_H
#define SAVEDEMPROIECTUL_USERREPO_H

#include <vector>

using namespace std;

#include "List.h"
#include "User.h"

class UserRepo {
private:
    List<User> listUsers;
public:
    UserRepo();

    ~UserRepo() = default;

    void addElement(User &user);

    void removeElement(User &user);

    bool searchUser(const User& user);

    void getAllUsers();

    int getSize();
};
//dafault implicit constructor
inline UserRepo::UserRepo() = default;

//adaugam un user intr-o lista
inline void UserRepo::addElement(User &user) {
    this->listUsers.add(user);
}
//stergem un user din lista
inline void UserRepo::removeElement(User &user) {
    this->listUsers.remove(user);
}
//afisam toti userii din lista
inline void UserRepo::getAllUsers() {
    return this->listUsers.printElemente();
}
//returnam lungimea listei(numarul de elemente din lista)
inline int UserRepo::getSize() {
    return this->listUsers.getAllNod();
}
//returnam true daca gasim un user in lista, false daca nu
inline bool UserRepo::searchUser(const User& user) {
    return this->listUsers.search(user);
}



#endif

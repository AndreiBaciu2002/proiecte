#ifndef SAVEDEMPROIECTUL_SERVICE_H
#define SAVEDEMPROIECTUL_SERVICE_H

#include <utility>

#include "UserRepo.h"
#include "MessageRepo.h"
#include "FriendshipRepo.h"

class Service {
private:
    UserRepo userRepo;
    MessageRepo messageRepo;
    FriendshipRepo friendshipRepo;
public:
    Service();

    ~Service() = default;

    void addUser(int id, string name);

    void removeUser(int id);

    void gettAllUsers();

    void addFriendship(int idUser1, int idUser2);

    int getFrindshipsSize();

    void removeFriendship(int idUser1, int idUser2);

    vector<string> printFriendship();

    void addMessage(int idUser1, int idUser2, string textMessage);

    vector<string> printConversation(int idUser1, int idUser2);
};

inline Service::Service() = default;


inline void Service::addUser(int id, string name) {
    User u(id, std::move(name));
    if (!this->userRepo.searchUser(u)) {
        this->userRepo.addElement(u);
    } else {
        cout << "exista deja un user cu acest id" << endl;
    }
}

inline void Service::removeUser(int id) {
    User u(id, "");
    if (this->userRepo.searchUser(u)) {
        this->userRepo.removeElement(u);
    } else {
        cout << "nu avem un user cu id dat! " << endl;
    }
}

inline void Service::gettAllUsers() {
    return this->userRepo.getAllUsers();
}

inline void Service::addFriendship(int idUser1, int idUser2) {
    User u1(idUser1, "");
    User u2(idUser2, "");
    if (this->userRepo.searchUser(u1) && this->userRepo.searchUser(u2)) {
        Friendship friendship(idUser1, idUser2);
        if (!this->friendshipRepo.searchFriendship(friendship))
            this->friendshipRepo.addFriendship(friendship);
        else
            cout << "Acesti useri sunt deja prieteni" << endl;
    } else {
        cout << "nu exista acesti useri!" << endl;
    }
}

inline void Service::removeFriendship(int idUser1, int idUser2) {
    User u1(idUser1, "");
    User u2(idUser2, "");
    if (this->userRepo.searchUser(u1) && this->userRepo.searchUser(u2)) {
        Friendship friendship(idUser1, idUser2);
        if (this->friendshipRepo.searchFriendship(friendship))
            this->friendshipRepo.removeFriendship(friendship);
        else
            cout << "Nu exista o relatie de prietenie intre acesti doi useri" << endl;
    } else {
        cout << "nu exista acesti useri!" << endl;
    }
}

inline vector<string> Service::printFriendship() {
    vector<string> friendships;
    for (const Friendship &friendship: this->friendshipRepo.printFriendships())
        friendships.push_back(friendship.toString());
    return friendships;
}

inline int Service::getFrindshipsSize() {
    return this->friendshipRepo.getSize();
}

inline void Service::addMessage(int idUser1, int idUser2, string textMessage) {
    User u1(idUser1, "");
    User u2(idUser2, "");
    if (this->userRepo.searchUser(u1) && this->userRepo.searchUser(u2)) {
        if (this->friendshipRepo.searchFriendship(Friendship(idUser1, idUser2))) {
            Message message(idUser1, idUser2, std::move(textMessage));
            this->messageRepo.addMessage(message);
        }
    }
}

inline vector<string> Service::printConversation(int idUser1, int idUser2) {
    vector<string> conversation;
    User u1(idUser1, "");
    User u2(idUser2, "");
    if (this->userRepo.searchUser(u1) && this->userRepo.searchUser(u2)) {
        if (this->friendshipRepo.searchFriendship(Friendship(idUser1, idUser2))) {
            for (Message message: messageRepo.getConversation(idUser1, idUser2))
                conversation.push_back(message.toString());
        }
    } else {
        cout << "eroare la introducere useri! ";
    }
    return conversation;
}

#endif

#include "Friendship.h"

Friendship::Friendship() {}

Friendship::Friendship(int idUser1, int idUser2) {
    this->idUser1 = idUser1;
    this->idUser2 = idUser2;
}

Friendship::Friendship(const Friendship &friendship) {
    this->idUser1 = friendship.idUser1;
    this->idUser2 = friendship.idUser2;
}

Friendship::~Friendship() {}

int Friendship::getIdUser1() const {
    return this->idUser1;
}

int Friendship::getIdUser2() const {
    return this->idUser2;
}

void Friendship::setIdUser1(int idUseru1) {
    this->idUser1 = idUseru1;
}

void Friendship::setIdUser2(int idUseru2) {
    this->idUser2 = idUseru2;
}

Friendship &Friendship::operator=(const Friendship &friendship) {
    if (this != &friendship) {
        this->idUser1 = friendship.idUser1;
        this->idUser2 = friendship.idUser2;
    }
    return *this;
}

bool Friendship::operator==(const Friendship &friendship) {
    return (this->idUser1 == friendship.idUser1 && this->idUser2 == friendship.idUser2) ||
           (this->idUser1 == friendship.idUser2 && this->idUser2 == friendship.idUser1);
}

string Friendship::toString() const {
    int id1 = this->idUser1;
    int id2 = this->idUser2;
    return to_string(id1) + " -> " + to_string(id2) + ";";
}
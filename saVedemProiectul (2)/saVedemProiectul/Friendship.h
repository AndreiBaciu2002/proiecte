#ifndef SAVEDEMPROIECTUL_FRIENDSHIP_H
#define SAVEDEMPROIECTUL_FRIENDSHIP_H
#include <iostream>
using namespace std;

class Friendship {
private:
    int idUser1;
    int idUser2;
public:
    Friendship();

    Friendship(int idUser1, int idUser2);

    Friendship(const Friendship &friendship);

    ~Friendship();

    int getIdUser1() const;

    int getIdUser2() const;

    void setIdUser1(int idUser1);

    void setIdUser2(int idUser2);

    Friendship &operator=(const Friendship &friendship);

    bool operator==(const Friendship &friendship);

    string toString() const;
};


#endif

#ifndef SAVEDEMPROIECTUL_FRIENDSHIPREPO_H
#define SAVEDEMPROIECTUL_FRIENDSHIPREPO_H

#include "MultiMap.h"
#include "Friendship.h"
#include "User.h"
#include "UserRepo.h"

class FriendshipRepo {
private:
    Multimap<int, Friendship> friendships;
public:
    FriendshipRepo();

    ~FriendshipRepo() = default;

    void addFriendship(const Friendship &friendship);

    void removeFriendship(const Friendship &friendship);

    bool searchFriendship(const Friendship &friendship);

    int getSize();

    vector<Friendship> printFriendships();
};

inline FriendshipRepo::FriendshipRepo() = default;


inline void FriendshipRepo::addFriendship(const Friendship &friendship) {
    this->friendships.put(friendship.getIdUser1(), friendship);
    this->friendships.put(friendship.getIdUser2(), friendship);
}

inline void FriendshipRepo::removeFriendship(const Friendship &friendship) {
    this->friendships.removeKeyValue(friendship.getIdUser1(), friendship);
    this->friendships.removeKeyValue(friendship.getIdUser2(), friendship);
}

inline int FriendshipRepo::getSize() {
    return (this->friendships.getMultiMapSize()) / 2;
}

inline vector<Friendship> FriendshipRepo::printFriendships() {
    return this->friendships.getAllUnique();
}

inline bool FriendshipRepo::searchFriendship(const Friendship &friendship) {
    return this->friendships.search(friendship) != -1;
}

#endif

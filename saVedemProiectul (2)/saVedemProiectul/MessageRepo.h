#ifndef SAVEDEMPROIECTUL_MESSAGEREPO_H
#define SAVEDEMPROIECTUL_MESSAGEREPO_H

#include "MultiMap.h"
#include "Message.h"

class MessageRepo {
private:
    Multimap<int, Message> messages;
public:
    MessageRepo();

    ~MessageRepo() = default;

    void addMessage(const Message &message);

    vector<Message> getConversation(int userId1, int userId2);
};

inline MessageRepo::MessageRepo() = default;


inline void MessageRepo::addMessage(const Message &message) {
    int a = message.getIdDeparture(), b = message.getIdArrival(), key;
    key = (a < b ? a * 1000 + b : b * 1000 + a);
    this->messages.put(key, message);
}

inline vector<Message> MessageRepo::getConversation(int userId1, int userId2) {
    int a = userId1, b = userId2, key;
    key = (a < b ? a * 1000 + b : b * 1000 + a);
    return this->messages.getAllForKey(key);
}


#endif

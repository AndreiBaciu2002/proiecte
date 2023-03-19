#ifndef SAVEDEMPROIECTUL_MESSAGE_H
#define SAVEDEMPROIECTUL_MESSAGE_H
#include <vector>
using namespace std;
#include "User.h"
#include <string>

using namespace std;

class Message {
private:
    int idDeparture{};
    int idArrival{};
    string textMessage{};
public:
    Message();

    Message(int id1, int id2, string message);

    Message(const Message &message);

    ~Message();

    void setIdDeparture(int id1);

    void setIdArrival(int id2);

    void setTextMessage(string textMessage);

    int getIdDeparture() const;

    int getIdArrival() const;

    string getTextMessage();

    Message &operator=(const Message &message);

    bool operator==(const Message &message);

    string toString();
};


#endif

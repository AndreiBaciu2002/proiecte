#include "Message.h"

#include <utility>

Message::Message() {
    this->textMessage = " ";
}

Message::Message(int id1, int id2, string message) {
    this->idDeparture = id1;
    this->idArrival = id2;
    this->textMessage = std::move(message);
}

Message::~Message() = default;

Message::Message(const Message &message) {
    this->idDeparture = message.idDeparture;
    this->idArrival = message.idArrival;
    this->textMessage = message.textMessage;
}

void Message::setIdArrival(int id2) {
    this->idArrival = id2;
}

void Message::setIdDeparture(int id1) {
    this->idDeparture = id1;
}

void Message::setTextMessage(string textMessag) {
    this->textMessage = std::move(textMessag);
}

int Message::getIdArrival() const {
    return this->idArrival;
}

int Message::getIdDeparture() const {
    return this->idDeparture;
}

string Message::getTextMessage() {
    return this->textMessage;
}

Message &Message::operator=(const Message &message) {
    if (this != &message) {
        this->idArrival = message.idArrival;
        this->idDeparture = message.idDeparture;
        this->textMessage = message.textMessage;
    }
    return *this;
}

bool Message::operator==(const Message &message) {
    if (this->idDeparture == message.idDeparture && this->idArrival == message.idArrival &&
        this->textMessage == message.textMessage)
        return true;
    return false;
}

string Message::toString() {
    int id1 = this->idDeparture;
    int id2 = this->idArrival;
    string mess = this->textMessage;
    return to_string(id1) + "->" + to_string(id2) + " : " + mess;
}

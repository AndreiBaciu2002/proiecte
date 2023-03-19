#ifndef SAVEDEMPROIECTUL_USERINTERFACE_H
#define SAVEDEMPROIECTUL_USERINTERFACE_H

#include "Service.h"


class UI {
private:
    Service retea;
public:
    UI();

    explicit UI(Service &service) : retea(service) {};

    ~UI() = default;

    static void printMenu();

    int runMenu();
};

inline UI::UI() = default;

inline void UI::printMenu() {
    cout << "1. Adauga user" << endl;
    cout << "2. Sterge user" << endl;
    cout << "3. Afiseaza useri" << endl;
    cout << "4. Creeaza prietenie" << endl;
    cout << "5. Sterge prietenie" << endl;
    cout << "6. Afiseaza prieteniile" << endl;
    cout << "7. Creeaza mesaj" << endl;
    cout << "8. Afiseaza mesaje" << endl;
    cout << "0. Inchidere program" << endl;
}

inline int UI::runMenu() {
    while (true) {
        printMenu();
        int optiune;
        cout << "introduceti optiunea: ";
        cin >> optiune;
        cout << endl;
        switch (optiune) {
            case 1: {
                int idUser1;
                string name;
                cout << "dati id pentru user: ";
                cin >> idUser1;
                cout << "dati nume user: ";
                cin >> name;
                cout << endl;
                retea.addUser(idUser1, name);
                break;
            }
            case 2: {
                int idUser1;
                cout << "dati id pentru user: ";
                cin >> idUser1;
                cout << endl;
                retea.removeUser(idUser1);
                break;
            }
            case 3: {
                retea.gettAllUsers();
                cout << endl;
                break;
            }
            case 4: {
                int idUser1;
                int idUser2;
                cout << "dati id pentru user1: ";
                cin >> idUser1;
                cout << "dati id pentru user2: ";
                cin >> idUser2;
                cout << endl;
                retea.addFriendship(idUser1, idUser2);
                break;
            }
            case 5: {
                int idUser1;
                int idUser2;
                cout << "dati id pentru user1: ";
                cin >> idUser1;
                cout << "dati id pentru user2: ";
                cin >> idUser2;
                cout << endl;
                retea.removeFriendship(idUser1, idUser2);
                break;
            }
            case 6: {
                for (auto &&prietenie: retea.printFriendship())
                    cout << prietenie;
                cout << endl;
                break;
            }
            case 7: {
                int idUser1;
                int idUser2;
                cout << "dati id pentru user1: ";
                cin >> idUser1;
                cout << "dati id pentru user2: ";
                cin >> idUser2;
                string mess;
                cout << "dati un mesaj pe care doriti sa-l transmiteti: ";
                getline(std::cin >> std::ws, mess);
                retea.addMessage(idUser1, idUser2, mess);
                break;
            }
            case 8: {
                int idUser1;
                int idUser2;
                cout << "dati id pentru user1: ";
                cin >> idUser1;
                cout << "dati id pentru user2: ";
                cin >> idUser2;
                cout << endl;
                for (const basic_string<char> &f: retea.printConversation(idUser1, idUser2))
                    cout << f << endl;
                cout << endl;
                break;
            }
            case 0:
                return 0;
            default: {
                cout << "optiune invalida" << endl;
                break;
            }
        }


    }
}

#endif

#ifndef SAVEDEMPROIECTUL_FISIERTEST_H
#define SAVEDEMPROIECTUL_FISIERTEST_H
#include "List.h"
#include "MultiMap.h"
#include "Service.h"
#include "User.h"
#include "UserRepo.h"
#include "FriendshipRepo.h"
#include "MessageRepo.h"
#include "UserInterface.h"

void data(){
    Service service;
    User u1(1, "Andrei");
    User u2(2, "Alex");
    User u3(3, "Matei");
    User u4(4, "Ioan");
    User u5(5, "Pancu");
    User u6(6, "Ederson");
    User u7(7, "Cancelo");
    User u8(8, "Zinckenco");
    User u9(9, "Kevin");
    User u10(10, "Gundogan");
    User u11(11, "Jesus");
    User u12(12, "Haaland");
    User u13(13, "Laporte");
    User u14(14, "Messi");
    User u15(15, "Kloop");
    UserRepo userRepo;
    userRepo.addElement(u1);
    userRepo.addElement(u2);
    userRepo.addElement(u3);
    userRepo.addElement(u4);
    userRepo.addElement(u5);
    userRepo.addElement(u6);
    userRepo.addElement(u7);
    userRepo.addElement(u8);
    userRepo.addElement(u9);
    userRepo.addElement(u10);
    userRepo.addElement(u11);
    userRepo.addElement(u12);
    userRepo.addElement(u13);
    userRepo.addElement(u14);
    userRepo.addElement(u15);
    service.addUser(1, "Andrei");
    service.addUser(2, "Alex");
    service.addUser(3, "Matei");
    service.addUser(4, "Ioan");
    service.addUser(5, "Pancu");
    service.addUser(6, "Ederson");
    service.addUser(7, "Cancelo");
    service.addUser(8, "Zinckenco");
    service.addUser(9, "Kevin");
    service.addUser(10, "Gundogan");
    service.addUser(11, "Jesus");
    service.addUser(12, "Haaland");
    service.addUser(13, "Laporte");
    service.addUser(14, "Messi");
    service.addUser(15, "Kloop");
    service.addFriendship(1, 2);
    service.addFriendship(1, 3);
    service.addFriendship(1, 4);
    service.addFriendship(1, 5);
    service.addFriendship(1, 6);
    service.addFriendship(1, 7);
    service.addFriendship(1, 8);
    service.addFriendship(1, 9);
    service.addFriendship(3, 12);
    service.addFriendship(5, 6);
    service.addFriendship(11, 9);
    service.addFriendship(12, 10);
    service.addFriendship(5, 13);
    service.addFriendship(9, 6);
    service.addMessage(1, 2, "ce faci");
    service.addMessage(1,3, "salut");
    service.addMessage(1,4,"buna ziua");
    service.addMessage(1, 5, "felicatari pentru victorie");
    service.addMessage(1,6,"goooooooool");
    service.addMessage(1,7, "CFRvictorieeee");
    service.addMessage(1,8,"heeeeeei");
    service.addMessage(1,9,"daaaa");
    service.addMessage(3,12, "tu esti mai bun");
    service.addMessage(5,6,"corect");
    service.addMessage(11,9, "stadionul e plin");
    service.addMessage(12,10, "frumoasa vremea");
    UI u(service);
    u.runMenu();
}
#endif

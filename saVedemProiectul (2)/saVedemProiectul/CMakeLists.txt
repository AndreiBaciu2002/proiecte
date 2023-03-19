cmake_minimum_required(VERSION 3.21)
project(saVedemProiectul)

set(CMAKE_CXX_STANDARD 14)

add_executable(saVedemProiectul main.cpp User.cpp User.h Node.h List.h Friendship.h Friendship.cpp UserRepo.h
        Message.cpp Message.h MultiMap.h FriendshipRepo.h MessageRepo.h Service.h UserInterface.h fisierTest.h)

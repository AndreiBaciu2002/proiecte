#ifndef SAVEDEMPROIECTUL_LIST_H
#define SAVEDEMPROIECTUL_LIST_H

#include "Node.h"
#include <iostream>

using namespace std;

template<class T>
class List {
private:
    Node<T> *head;
public:
    List() {
        head = nullptr;
    }
//adaugam un element la lista
    void add(T elem) {
        if (head == nullptr) {
            head = new Node<T>(elem);
        } else {
            Node<T> *p = head;
            while (p->next != nullptr)
                p = p->next;
            p->next = new Node<T>(elem);
        }
    }
//stergem un element din lista
    T remove(T e) {
        Node<T> *nodeToDelete = nullptr;
        T infoToReturn;

        if (head != nullptr) {
            if (head->info == e) {
                nodeToDelete = head;
                head = head->next;
            } else {
                Node<T> *p = head->next;
                Node<T> *q = head;
                while (p != nullptr)
                    if (p->info == e) {
                        nodeToDelete = p;
                        p = nullptr;
                    } else {
                        p = p->next;
                        q = q->next;
                    }
                if (nodeToDelete != nullptr)
                    q->next = nodeToDelete->next;
            }
        }
        if (nodeToDelete != nullptr) {
            infoToReturn = nodeToDelete->info;
            delete nodeToDelete;
            return infoToReturn;
        }
        return T();
    }
//returnam true daca am gasit continutul unui element din lista, false daca nu
    bool search(T info) {
        auto *current = this->head;
        while (current != nullptr) {
            if (current->info == info) {
                return true;
            }
            current = current->next;
        }
        return false;
    }

//returnam numarul de noduri din lista
    int getAllNod() {
        Node<T> *node = head;
        int capacitatea = 0;
        while (node != nullptr) {
            capacitatea++;
            node = node->next;
        }
        return capacitatea;
    }
//afisam toate elementele din lista
    void printElemente() {
        if (head == nullptr) {
            cout << "lista este goala!" << endl;
        } else {
            Node<T> *element = head;
            cout << "lista contine: ";
            while (element != nullptr) {
                cout << element->info << ' ';
                element = element->next;
            }
        }
    }
};

#endif

#ifndef SAVEDEMPROIECTUL_MULTIMAP_H
#define SAVEDEMPROIECTUL_MULTIMAP_H
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

template<class Key, class Value>
class Multimap {
private:
    template<class K, class V>
    class MapNode {
    public:
        K key;
        V value;
        MapNode<K, V> *next;

        MapNode(K key, V value) : key(key), value(value) { this->next = nullptr; }
    };

    template<class V>
    class ListNode {
    public:
        V info;
        ListNode<V> *next;

        ListNode(V info, ListNode<V> *next) : info(info), next(next) {}

        explicit ListNode(V info) : info(info) { next = nullptr; }
    };

    int size{};
    MapNode<Key, ListNode<Value> *> *first;

    MapNode<Key, ListNode<Value> *> *searchAdress(Key k) {
        if (this->size == 0)
            return nullptr;
        MapNode<Key, ListNode<Value> *> *p = first;
        while (p->next != nullptr) {
            if (p->key == k)
                return p;
            p = p->next;
        }
        return p;
    }


public:
    Multimap() {
        this->first = nullptr;
        this->size = 0;
    }
//metoda de a add a unui element in multimap dupa cheie si valoare
    void put(Key key, Value value) {
        MapNode<Key, ListNode<Value> *> *poz = searchAdress(key);

        if (poz == nullptr) {
            this->first = new MapNode<Key, ListNode<Value> *>(key, new ListNode<Value>(value));
            this->size++;
        } else {
            if (poz->key == key) {  //cazul:exista in disctionar o pereche cu cheia egala cu key
                auto *node = new ListNode<Value>(value, nullptr);
                auto* current = poz->value;
                while(current->next!=nullptr){
                    current=current->next;
                }
                current->next = node;
                this->size++;
            } else {
                poz->next = new MapNode<Key, ListNode<Value> *>(key, new ListNode<Value>(value));
                this->size++;
            }
        }
    }
//metoda de stergere a unui element din multimap dupa cheie
    vector<Value> remove(Key key) {
        MapNode<Key, ListNode<Value> *> *poz = searchAdress(key);
        vector<Value> result;

        if (poz != nullptr && poz->key == key) {
            MapNode<Key, ListNode<Value> *> *p = first;
            if (poz == first) this->first = poz->next;
            else {
                while (p->next != poz) p = p->next;
                p->next = poz->next;
            }
            while (poz->value != nullptr) {
                ListNode<Value> *node = poz->value;
                result.push_back(node->info);
                poz->value = node->next;
                delete node;
            }
            this->size--;
            delete poz;
        }
        return result;
    }
//returnam un int, ce reprezinta numarul de elemente din multimap
    int getMultiMapSize() {
        return this->size;
    }
    //afisam toate elementele din multimap
    void printMultiMap(){
        auto* current = this->first;
        while(current!= nullptr){
            auto* localCurrent = current->value;
            while(localCurrent!= nullptr){
                cout<<current->key<<" "<<localCurrent->info<<endl;
                localCurrent = localCurrent->next;

            }
            current = current->next;
        }
    }
//stergem un element din lista dupa cheie si valoarea care il caracterizeaza
    void removeKeyValue(Key key, Value value){
        auto* current = this->first;
        if(current->key==key){
            int count=0;
            auto* localCurrent = current->value;
            while(localCurrent!= nullptr){
                count++;
                localCurrent=localCurrent->next;
            }
            if(count==1 && current->value->info == value){
                this->first=current->next;
                this->size--;
                return;
            }
            else{
                auto* previous = current->value;
                localCurrent = previous->next;
                if(previous->info==value){
                    current->value=localCurrent;
                    this->size--;
                    return;
                }
                while(localCurrent!= nullptr){
                    if(localCurrent->info==value){
                        previous->next=localCurrent->next;
                        this->size--;
                        return;
                    }
                    previous=localCurrent;
                    localCurrent=localCurrent->next;
                }
            }
        }

        auto* previous = current;
        current=current->next;
        while(current!= nullptr){
            auto * localCurrent = current->value;
            int count = 0;
            while(localCurrent!= nullptr){
                count++;
                localCurrent = localCurrent->next;
            }

            if(count==1 && current->value->info==value){
                previous->next = current->next;
                return;
            }
            auto * localPrevious = current->value;
            localCurrent=localPrevious->next;
            if(localPrevious->info==value){
                current->value=localCurrent;
                return;
            }
            while(localCurrent!=nullptr){
                if(localCurrent->info==value){
                    localPrevious->next=localCurrent->next;
                    return;
                }
                localPrevious=localCurrent;
                localCurrent=localCurrent->next;
            }
            previous=current;
            current=current->next;
        }

    }
//returnam toate valorile unui multimap
    vector<Value> getAllUnique(){
        vector<Value> uniqueValues;
        auto* current = this->first;
        while(current != nullptr){
            auto* localCurrent = current->value;
            while(localCurrent != nullptr){
                if(find(uniqueValues.begin(), uniqueValues.end(), localCurrent->info) == uniqueValues.end())
                    uniqueValues.push_back(localCurrent->info);
                localCurrent = localCurrent->next;
            }
            current = current->next;
        }
        return  uniqueValues;
    }
//returnam pozitia unei chei a  multimap-ului
    Key search(Value value){
        auto* current = this->first;
        while(current != nullptr){
            auto* localCurrent = current->value;
            while(localCurrent != nullptr){
                if(localCurrent->info == value)
                    return current->key;
                localCurrent = localCurrent->next;
            }
            current = current->next;
        }
        return -1;
    }
//returnam toate valorile unei chei pe care o dam ca parametru(multimap-ul avand mai multe valori la o cheie)
    vector<Value> getAllForKey(Key key){
        vector<Value> values;
        auto* current = this->first;
        while(current != nullptr){
            if(current->key == key){
                auto* localCurrent = current->value;
                while(localCurrent != nullptr){
                    values.push_back(localCurrent->info);
                    localCurrent = localCurrent->next;
                }
                break;
            }
            current = current->next;
        }
        return values;
    }
};
#endif

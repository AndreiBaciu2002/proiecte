package com.example.tematrei.service;

import com.example.tematrei.domain.User;
import com.example.tematrei.domain.validators.ValidationException;
import com.example.tematrei.repository.Repository;



public class UserService {
    private final Repository<Long, User> userRepository;

    public UserService(Repository<Long, User> userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        try {
            this.userRepository.save(user);
        } catch (ValidationException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Long id, String firstName, String lastName, String email, String password) {
        for (User user : this.userRepository.findAll()) {
            if (user.getId().equals(id)) {
                User user1 = new User(firstName, lastName,email, password);
                user1.setId(user.getId());
                this.userRepository.update(user1);
            }
        }
    }

    public void deleteUser(Long id) {
        try {
            this.userRepository.delete(id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public User findOne(Long x) {
        try {
            return this.userRepository.findOne(x);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }
}

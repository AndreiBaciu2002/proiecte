package com.example.tematrei;

import com.example.tematrei.domain.FriendRequest;
import com.example.tematrei.domain.Friendship;
import com.example.tematrei.domain.User;
import com.example.tematrei.domain.validators.FriendRequestValidator;
import com.example.tematrei.domain.validators.FriendshipValidator;
import com.example.tematrei.domain.validators.UserValidator;
import com.example.tematrei.repository.Repository;
import com.example.tematrei.repository.db.FriendRequestDbRepository;
import com.example.tematrei.repository.db.FriendshipsDbRepository;
import com.example.tematrei.repository.db.UsersDbRepository;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        String username = "postgres";
        String pasword = "postgre";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Repository<Long, Friendship> userRepository =
                new FriendshipsDbRepository(url, username, pasword, new FriendshipValidator());
        userRepository.findAll().forEach(System.out::println);
        Repository<Long, FriendRequest> friendRequestDbRepository = new FriendRequestDbRepository(url, username, pasword, new FriendRequestValidator());
        Repository<Long, User> userRepository3 =
                new UsersDbRepository(url, username, pasword, (FriendshipsDbRepository) userRepository, new UserValidator());
        userRepository3.findAll().forEach(x -> System.out.println(x));
        friendRequestDbRepository.findAll().forEach(x -> System.out.println(x));
    }
}



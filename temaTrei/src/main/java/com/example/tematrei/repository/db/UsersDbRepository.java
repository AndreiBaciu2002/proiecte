package com.example.tematrei.repository.db;

import com.example.tematrei.domain.Friendship;
import com.example.tematrei.domain.User;
import com.example.tematrei.domain.validators.Validator;
import com.example.tematrei.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDbRepository implements Repository<Long, User> {
    protected Map<Long, User> entities;
    private String url;
    private String username;
    private String password;

    private final Validator<User> validator;

    private final FriendshipsDbRepository friendshipsDbRepository;

    public UsersDbRepository(String url, String username, String password, FriendshipsDbRepository friendshipsDbRepository, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.friendshipsDbRepository = friendshipsDbRepository;
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public User save(User entity) {
        boolean exists = false;

        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");

        for (User user : findAll()) {
            if (entity.equals(user)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            throw new IllegalArgumentException("user already exists!");
        }
        validator.validate(entity);
        String sql = "insert into users (firstname, lastname, email, password1) values (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getPasswordHash());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User(entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPasswordHash());
        entities.put(entity.getId(), user);

        return null;
    }

    @Override
    public User delete(Long aLong) {
        if (aLong == null) {
            throw new IllegalArgumentException("deleted entity doesn't exist");
        }

        String sql = "delete from users where id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterable<Friendship> friendships = friendshipsDbRepository.findAll();
        for (Friendship friendship : friendships) {
            if (friendship.getId1().equals(aLong) || friendship.getId2().equals(aLong)) {
                friendshipsDbRepository.delete(friendship.getId());
            }
        }
        entities.remove(aLong);
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User findOne(Long aLong) {
        if (aLong == null)
            throw new IllegalArgumentException("ID must be not null");
        String sql = "select * from users where id = ? ";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String passwordHash = resultSet.getString("password1");

                return new User(firstName, lastName, email, passwordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users ";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String passwordHash = resultSet.getString("password1");
                User user = new User(firstName, lastName, email, passwordHash);
                user.setId(id);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}

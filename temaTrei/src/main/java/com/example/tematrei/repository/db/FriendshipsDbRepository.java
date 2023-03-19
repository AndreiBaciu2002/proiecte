package com.example.tematrei.repository.db;

import com.example.tematrei.domain.Friendship;
import com.example.tematrei.domain.validators.Validator;
import com.example.tematrei.repository.Repository;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FriendshipsDbRepository implements Repository<Long, Friendship> {

    private String url;
    private String username;
    private String password;

    private final Validator<Friendship> validator;

    public FriendshipsDbRepository(String url, String username, String password, Validator<Friendship> validator) {
        this.validator = validator;
        this.url = url;
        this.username = username;
        this.password = password;

    }

    @Override
    public Friendship save(Friendship entity) {
        boolean exists = false;
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");

        for (Friendship friendship : findAll()) {
            if (entity.equals(friendship) || (entity.getId1().equals(friendship.getId2()) && entity.getId2().equals(friendship.getId1()))) {
                exists = true;
                break;
            }
        }
        if (exists) {
            throw new IllegalArgumentException("friendship already exists!");
        }
        validator.validate(entity);
        String sql = "insert into friendship ( id1, id2, data) values (?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId1());
            preparedStatement.setLong(2, entity.getId2());
            java.sql.Date date1 = java.sql.Date.valueOf(entity.getFriendshipDate());
            preparedStatement.setDate(3, date1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Friendship delete(Long aLong) {
        if (aLong == null)
            throw new IllegalArgumentException("id must be not null");
        String sql = "delete from friendship where id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Friendship findOne(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id must be not null");

        String sql = "select * from friendship where id = ? ";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long friendOneId = resultSet.getLong("id1");
                Long friendTwoId = resultSet.getLong("id2");
                LocalDate friendshipDate1 = resultSet.getTimestamp("data").toLocalDateTime().toLocalDate();
                return new Friendship(friendOneId, friendTwoId, friendshipDate1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Friendship entity) {

    }

    @Override
    public Iterable<Friendship> findAll() {
        List<Friendship> friendships = new ArrayList<>();
        String sql = "select * from friendship ";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long friendOneId = resultSet.getLong("id1");
                Long friendTwoId = resultSet.getLong("id2");
                LocalDate friendshipDate1 = resultSet.getTimestamp("data").toLocalDateTime().toLocalDate();
                Friendship friendship = new Friendship(friendOneId, friendTwoId, friendshipDate1);
                friendship.setId(id);
                friendships.add(friendship);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

}

package com.example.tematrei.repository.db;

import com.example.tematrei.domain.FriendRequest;
import com.example.tematrei.domain.validators.Validator;
import com.example.tematrei.repository.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDbRepository implements Repository<Long, FriendRequest> {


    private String url;
    private String username;
    private String password;
    private final Validator<FriendRequest> validator;

    public FriendRequestDbRepository(String url, String username, String password, Validator<FriendRequest> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public FriendRequest save(FriendRequest entity) {
        boolean exists = false;
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");

        for (FriendRequest friendRequest : findAll()) {
            if (entity.equals(friendRequest) || (entity.getId1().equals(friendRequest.getId2()) && entity.getId2().equals(friendRequest.getId1()))) {
                exists = true;
                break;
            }
        }
        if (exists) {
            throw new IllegalArgumentException("friendship already exists!");
        }

        validator.validate(entity);
        String sql = "insert into friendrequest ( friend1id, friend2id, status, data) values (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, entity.getId1());
            preparedStatement.setLong(2, entity.getId2());
            preparedStatement.setString(3, String.valueOf(entity.getStatus()));
            java.sql.Date date1 = java.sql.Date.valueOf(entity.getDate());


            preparedStatement.setDate(4, date1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public FriendRequest delete(Long aLong) {
        if (aLong == null)
            throw new IllegalArgumentException("id must be not null");
        String sql = "delete from friendrequest where id = ?";
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
    public FriendRequest findOne(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id must be not null");

        String sql = "select * from friendrequest where id = ? ";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Long friendOneId = resultSet.getLong("friend1id");
            Long friendTwoId = resultSet.getLong("friend2id");
            String status = resultSet.getString("status");
            LocalDate date1 = resultSet.getTimestamp("data").toLocalDateTime().toLocalDate();;

            return new FriendRequest(friendOneId, friendTwoId, status, date1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(FriendRequest entity) {

    }

    @Override
    public Iterable<FriendRequest> findAll() {
        List<FriendRequest> friendships = new ArrayList<>();
        String sql = "select * from friendrequest ";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long friendOneId = resultSet.getLong("friend1id");
                Long friendTwoId = resultSet.getLong("friend2id");
                String status = resultSet.getString("status");
                LocalDate friendshipDate1 = resultSet.getTimestamp("data").toLocalDateTime().toLocalDate();


                FriendRequest friendship = new FriendRequest(friendOneId, friendTwoId, status, friendshipDate1);
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


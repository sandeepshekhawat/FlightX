package org.sandeep.dao;

import org.sandeep.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void signup(User user) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        try (Connection connection = DBConnection.getConnection() ){
            String query = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}


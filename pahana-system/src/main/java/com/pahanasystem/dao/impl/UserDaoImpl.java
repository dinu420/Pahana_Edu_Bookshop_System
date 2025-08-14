package com.pahanasystem.dao.impl;


import com.pahanasystem.dao.DaoException;
import com.pahanasystem.dao.UserDao;
import com.pahanasystem.model.User;
import com.pahanasystem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String INSERT =
        "INSERT INTO users(username,hashed_pwd,role) VALUES (?,?,?)";
    private static final String SELECT_ONE =
        "SELECT * FROM users WHERE username=?";
    private static final String DELETE =
    		"DELETE FROM users WHERE username = ?";

    @Override
    public void save(User u) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());  
            ps.setString(3, u.getRole());
            ps.executeUpdate();

        } catch (SQLException e) { throw new DaoException(e); }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ONE)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? Optional.of(map(rs)) : Optional.empty();

        } catch (SQLException e) { throw new DaoException(e); }
    }
    
    @Override
    public boolean deleteByUsername(String username) {
        try (Connection con = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE)) {

            ps.setString(1, username);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // returns true if at least one row was deleted

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    
    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT username, hashed_pwd, role FROM users WHERE role = ?";
        
        try (Connection conn = DBUtil.INSTANCE.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("username"), rs.getString("hashed_pwd"), rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    

    private User map(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("hashed_pwd"),
                rs.getString("role")
        );
    }
}

package com.pahanasystem.dao.impl;


import com.pahanasystem.dao.DaoException;
import com.pahanasystem.dao.UserDao;
import com.pahanasystem.model.User;
import com.pahanasystem.util.DBUtil;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String INSERT =
        "INSERT INTO users(username,hashed_pwd,role) VALUES (?,?,?)";
    private static final String SELECT_ONE =
        "SELECT * FROM users WHERE username=?";

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

    private User map(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("hashed_pwd"),
                rs.getString("role")
        );
    }
}

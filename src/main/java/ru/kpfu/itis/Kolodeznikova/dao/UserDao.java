package ru.kpfu.itis.Kolodeznikova.dao;

import ru.kpfu.itis.Kolodeznikova.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAll() throws SQLException;
    void save(User user) throws SQLException;
    User getById(Integer id);
    User getByLogin(String login) throws SQLException;
}

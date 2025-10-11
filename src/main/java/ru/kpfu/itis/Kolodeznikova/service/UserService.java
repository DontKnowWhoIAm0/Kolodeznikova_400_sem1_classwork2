package ru.kpfu.itis.Kolodeznikova.service;

import ru.kpfu.itis.Kolodeznikova.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<UserDto> getAll() throws SQLException;
    void registerUser(String name, String lastname, String login, String password) throws SQLException;
    boolean loginExists(String login) throws SQLException;
}

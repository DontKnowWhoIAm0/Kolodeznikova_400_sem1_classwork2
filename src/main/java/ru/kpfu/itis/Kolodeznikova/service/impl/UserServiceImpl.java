package ru.kpfu.itis.Kolodeznikova.service.impl;

import com.cloudinary.utils.ObjectUtils;
import ru.kpfu.itis.Kolodeznikova.dao.UserDao;
import ru.kpfu.itis.Kolodeznikova.dao.impl.UserDaoImpl;
import ru.kpfu.itis.Kolodeznikova.dto.UserDto;
import ru.kpfu.itis.Kolodeznikova.entity.User;
import ru.kpfu.itis.Kolodeznikova.service.UserService;
import ru.kpfu.itis.Kolodeznikova.util.CloudinaryUtil;
import ru.kpfu.itis.Kolodeznikova.util.PasswordUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() throws SQLException {
        return userDao.getAll().stream().map(u -> new UserDto(u.getName(), u.getLogin())).toList();
    }

    @Override
        public void registerUser(String name, String lastname, String login, String password, byte[] bytes) throws SQLException, IOException {
        String hashedPassword = PasswordUtil.encrypt(password);
        Map uploadResult = CloudinaryUtil.getInstance().uploader().upload(bytes, ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("secure_url");
        User user = new User(null, name, lastname, login, hashedPassword, imageUrl);
        userDao.save(user);
    }

    public boolean checkPasswordAndLogin(String login, String password) throws SQLException {
        User user = userDao.getByLogin(login);
        if (user == null) {
            return false;
        }
        return PasswordUtil.encrypt(password).equals(user.getPassword());
    }

    @Override
    public boolean loginExists(String login) throws SQLException {
        return userDao.getByLogin(login) != null;
    }
}

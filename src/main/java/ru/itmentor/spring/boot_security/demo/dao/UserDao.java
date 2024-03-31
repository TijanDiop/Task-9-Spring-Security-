package ru.itmentor.spring.boot_security.demo.dao;



import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    Optional<User> findByUsername(String name);

    void deleteUserById(Long id);

}

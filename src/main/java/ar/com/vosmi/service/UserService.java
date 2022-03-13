package ar.com.vosmi.service;

import ar.com.vosmi.domain.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void deleteUser(Long id);

    List<User> getUsers();

    User getUserById(Long id);
}

package homework.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import homework.entity.User;

@Repository
public interface UserMapper {
    User getUser(int id);
    List<User> getUserList();
    void insertNewUser(User user);
    void updateUser(User user);
    void deleteUser(int userid);
}
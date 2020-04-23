package homework.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import homework.entity.User;

@Repository
public interface UserMapper {
	User getUserByPk(int id);
    User getUserByNameAndAddress(String name,String address);
    List<User> getUserList();
    void insertNewUser(User user);
    void updateUser(User user);
    void deleteUser(int userid);
}
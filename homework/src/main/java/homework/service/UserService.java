package homework.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import homework.entity.User;
import homework.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/** 指定した値に一致するユーザー情報を返します。
	 * @param name 名前
	 * @param address アドレス
	 * @return User ユーザーのOptional型
	 **/
	public Optional<User> searchByNameAndAddress(String name,String address) {
		User user = userMapper.getUserByNameAndAddress(name, address);
		Optional<User> optUser = Optional.ofNullable(user);
		return optUser;
	}

	/** ユーザー情報を登録します。
	 * @param user ユーザー情報
	 **/
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) throws Exception {

    	userMapper.insertNewUser(user);
		System.out.println("登録しました");
    }


}

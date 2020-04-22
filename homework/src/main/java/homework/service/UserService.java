package homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import homework.entity.User;
import homework.form.LoginForm;
import homework.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertUser(LoginForm form) throws Exception {

    	User user = new User();
    	user.setAddress(form.getAddress());
    	user.setName(form.getName());

    	userMapper.insertNewUser(user);
    }


}

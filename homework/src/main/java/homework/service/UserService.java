package homework.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import homework.data.SessionData;
import homework.entity.User;
import homework.entity.UserSession;
import homework.mapper.UserMapper;
import homework.mapper.UserSessionMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserSessionMapper userSessionMapper;

    @Autowired
    SessionData sessionData;

	/** 指定したユーザーを認証し、登録されているセッション情報を取得します。
	 * @param session セッション情報がない場合に登録に使うセッションID
	 * @param user ユーザー
	 * @param model モデル
	 **/
	public void login (String session,User user,Model model) throws Exception{
		int id = user.getId();

		UserSession userSession = userSessionMapper.getUserSessionByPk(id);
		Optional<UserSession> optSession = Optional.ofNullable(userSession);

		// セッション情報がなければ登録
		if(!optSession.isPresent()){
			userSession = createUserSession(session,user.getId());
			insertUserSession(userSession);
		}

    	model.addAttribute("loginData", user);
    	sessionData.setId(userSession.getSessionId());
    	sessionData.setName(user.getName());
	}

	private UserSession createUserSession(String session,int id) {
		UserSession userSession = new UserSession();
		userSession.setUserId(id);
		userSession.setSessionId(session);
		return userSession;
	}

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

	/** ユーザーセッション情報を登録します。
	 * @param user ユーザー情報
	 **/
    @Transactional(rollbackFor = Exception.class)
    public void insertUserSession(UserSession session) throws Exception {

    	userSessionMapper.insertNewUserSession(session);
		System.out.println("登録しました");
    }


}

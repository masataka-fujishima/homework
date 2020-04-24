package homework.mapper;

import org.springframework.stereotype.Repository;

import homework.entity.UserSession;

@Repository
public interface UserSessionMapper {
	UserSession getUserSessionByPk(int userId);
	void insertNewUserSession(UserSession session);

}

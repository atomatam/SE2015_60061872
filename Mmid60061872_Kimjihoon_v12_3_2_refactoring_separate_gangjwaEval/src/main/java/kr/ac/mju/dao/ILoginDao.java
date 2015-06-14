package kr.ac.mju.dao;

import java.util.List;

import kr.ac.mju.exception.AlreadyExistUserIDException;
import kr.ac.mju.exception.NotCorrectUserIDException;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;

public interface ILoginDao {
	public List<User> getAllUser();
	public User getUserByUserID(UserInfo userInfor) throws NotCorrectUserIDException, AlreadyExistUserIDException;
	public void userEnroll(UserInfo user) throws AlreadyExistUserIDException;
	boolean isExistUserID(String userID) throws AlreadyExistUserIDException;
	public void deleteUser(User user);
	public List<UserInfo> getAllUserInfo();
	public UserInfo getUserInfoByUserID(UserInfo userInfo);
}

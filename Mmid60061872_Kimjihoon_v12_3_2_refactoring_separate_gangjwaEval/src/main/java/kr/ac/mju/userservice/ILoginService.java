package kr.ac.mju.userservice;

import java.util.List;

import kr.ac.mju.exception.AlreadyExistUserIDException;
import kr.ac.mju.exception.NotCorrectUserIDException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.exception.OverSizeInputException;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;

public interface ILoginService {

	public List<User> getAllUser();
	
	public User getUserByUserID(UserInfo userInfor) throws NotCorrectUserIDException, AlreadyExistUserIDException;
	
	public void userEnroll(UserInfo user) throws AlreadyExistUserIDException, NotInputDataException, OverSizeInputException;

	public void deleteUser(User user);

	public List<UserInfo> getAllUserInfo();

	public UserInfo getUserInfoByUserID(UserInfo stubUser);
}

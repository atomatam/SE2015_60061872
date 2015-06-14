package kr.ac.mju.userservice;

import java.util.List;

import kr.ac.mju.dao.ILoginDao;
import kr.ac.mju.exception.AlreadyExistUserIDException;
import kr.ac.mju.exception.NotCorrectUserIDException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.exception.OverSizeInputException;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CLoginService implements ILoginService {
	@Autowired
	private ILoginDao loginDao;

	public List<User> getAllUser() {
		return loginDao.getAllUser();
	}

	private boolean isCorrectSize(String target, int size) {
		if (target.length() > size)	
			return false;
		return true;
	}

	public User getUserByUserID(UserInfo userInfor)
			throws NotCorrectUserIDException, AlreadyExistUserIDException {
		if (!isCorrectSize(userInfor.getUsername(), 25))
			throw new NotCorrectUserIDException();
		return loginDao.getUserByUserID(userInfor);
	}

	@Override
	public List<UserInfo> getAllUserInfo() {
		return loginDao.getAllUserInfo();
	}
	
	public void userEnroll(UserInfo user) throws AlreadyExistUserIDException,
			NotInputDataException, OverSizeInputException {
		try {
			if (loginDao.isExistUserID(user.getUsername()))
				throw new AlreadyExistUserIDException();
			inputSizeValidator(user, 20);
			loginDao.userEnroll(user);
		} catch (DuplicateKeyException e) {
		}
	}

	@Override
	public void deleteUser(User user) {
		loginDao.deleteUser(user);
	}

	@Override
	public UserInfo getUserInfoByUserID(UserInfo userInfo) {
		return loginDao.getUserInfoByUserID(userInfo);
	}
	private void inputSizeValidator(UserInfo user, int size) 
			throws OverSizeInputException, NotInputDataException {
		if(user.getUsername().length() >= size 
				|| user.getPassword().length() >= size
				|| user.getUserRealName().length() >= size)
			throw new OverSizeInputException();
		if (user.getUsername().equals("") 
				|| user.getPassword().equals("")
				|| user.getUserRealName().equals("")) {
			throw new NotInputDataException();
		}
	}

	
}

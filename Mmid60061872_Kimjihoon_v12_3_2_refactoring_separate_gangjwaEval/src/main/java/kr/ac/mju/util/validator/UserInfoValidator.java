package kr.ac.mju.util.validator;

import kr.ac.mju.dao.ILoginDao;
import kr.ac.mju.exception.AlreadyExistUserIDException;
import kr.ac.mju.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserInfoValidator implements Validator {
	@Autowired
	private ILoginDao dao;
	@Override
	public boolean supports(Class<?> clazz) {
		return (UserInfo.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfo userInfo = (UserInfo)target;
		if(isError(userInfo.getUsername(), 20))
			errors.rejectValue("username", "NotCorrect.username");
		if(isError(userInfo.getPassword(), 20))
			errors.rejectValue("password", "NotCorrect.password");
		if(isError(userInfo.getUsername(), 20))
			errors.rejectValue("userRealName", "NotCorrect.userRealName");
		try {
			dao.userEnroll(userInfo);
		} catch (AlreadyExistUserIDException e) {
			errors.rejectValue("username", "AlreadyExistID.username");
		} 
	}
	
	private boolean isError(String value, int size) {
		if(value==null || value.trim().length()==0 || value.length() > size)
			return true;
		return false;
	}
}

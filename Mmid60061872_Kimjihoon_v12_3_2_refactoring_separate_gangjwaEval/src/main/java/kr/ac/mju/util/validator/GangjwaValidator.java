package kr.ac.mju.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mju.dao.IGangjwaDao;
import kr.ac.mju.exception.NotInputNumberException;
import kr.ac.mju.exception.OverSizeClassStudent;
import kr.ac.mju.exception.OverSizeInputException;
import kr.ac.mju.model.Gangjwa;

@Service
public class GangjwaValidator {
	@Autowired
	private IGangjwaDao dao;
	
	public void validate(Object target, int flag) throws OverSizeInputException, OverSizeClassStudent, NotInputNumberException {
		Gangjwa gangjwa = (Gangjwa)target;
		try {
			Integer.parseInt(gangjwa.getMaxStudent());
		} catch(NumberFormatException e) {
			throw new NotInputNumberException();
		}
		if(isError(gangjwa.getGangjwaID(), 20)) {
			throw new OverSizeInputException();
		}
		if(gangjwa.getMaxStudent().length() > 3) {
			throw new OverSizeInputException();
		}
		if(flag==1) {
			if(dao.getCurrentSugangStudent(gangjwa)+1>Integer.parseInt(gangjwa.getMaxStudent())) {
				throw new OverSizeClassStudent();
			}
		}
	}
	
	private boolean isError(String value, int size) {
		if(value==null || value.trim().length()==0 || value.length() > size)
			return true;
		return false;
	}
}

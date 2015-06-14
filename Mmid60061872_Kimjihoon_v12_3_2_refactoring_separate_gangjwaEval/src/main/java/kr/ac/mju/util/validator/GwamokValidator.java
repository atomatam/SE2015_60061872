package kr.ac.mju.util.validator;

import java.util.Calendar;

import kr.ac.mju.dao.IGangjwaDao;
import kr.ac.mju.model.Gwamok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class GwamokValidator implements Validator {
	@Autowired
	private IGangjwaDao dao;
	@Override
	public boolean supports(Class<?> clazz) {
		return Gwamok.class.isAssignableFrom(clazz);
	}

	@SuppressWarnings("static-access")
	@Override
	public void validate(Object target, Errors errors) {
		Gwamok gwamok = (Gwamok)target;
		if(isError(gwamok.getGwamokID(), 20)) {
			errors.rejectValue("gwamokID", "NotCorrect.gwamokID");
		}
		if(isError(gwamok.getGwamokName(), 20)) {
			errors.rejectValue("gwamokName", "NotCorrect.gwamokName");
		}
		if(isError(gwamok.getGwamokHakjeom(), 3)) {
			errors.rejectValue("gwamokHakjeom", "NotCorrect.gwamokHakjeom");
		}
		if(isError(gwamok.getYear(), 5)) {
			errors.rejectValue("year", "NotCorrect.year");
		}		
		if(isError(gwamok.getHaknyun(), 2)) {
			errors.rejectValue("haknyun", "NotCorrect.haknyun");
		}		
		if(isError(gwamok.getHakki(), 2)) {
			errors.rejectValue("hakki", "NotCorrect.hakki");
		}
		if(dao.isExistGwamokId(gwamok)) {
			errors.rejectValue("gwamokID", "Duplicate.gwamokID");
		}
		try {
			int hak = Integer.parseInt(gwamok.getGwamokHakjeom());
			if(hak<1 || hak>9)
				errors.rejectValue("gwamokHakjeom", "NotCorrect.gwamokHakjeom");
		} catch(NumberFormatException e) {
			errors.rejectValue("gwamokHakjeom", "NotNumberHakjeom.gwamokHakjeom");
		}
		try {
			int year = Integer.parseInt(gwamok.getYear());
			Calendar date = Calendar.getInstance();
			int now = date.get(date.YEAR);
			if((year > now) || (year < now-10)) {
				errors.rejectValue("year", "NotCorrectYear.year");
			}
		} catch(NumberFormatException e) {
			errors.rejectValue("year", "NotNumberHakjeom.year");
		}
		try {
			int temp = Integer.parseInt(gwamok.getHaknyun());
			if(temp<=0 || temp>4) {
				errors.rejectValue("haknyun", "NotCorrectHaknyun.haknyun");
			}
		} catch(NumberFormatException e) {
			errors.rejectValue("haknyun", "NotNumberHakjeom.haknyun");
		}
		try {
			int temp = Integer.parseInt(gwamok.getHakki());
			if(temp < 1 || 2 < temp) {
				errors.rejectValue("hakki", "NotCorrectHakki.hakki");
			}
		} catch(NumberFormatException e) {
			errors.rejectValue("hakki", "NotNumberHakjeom.hakki");
		}
	}
	
	private boolean isError(String value, int size) {
		if(value==null || value.trim().length()==0 || value.length() > size)
			return true;
		return false;
	}
}

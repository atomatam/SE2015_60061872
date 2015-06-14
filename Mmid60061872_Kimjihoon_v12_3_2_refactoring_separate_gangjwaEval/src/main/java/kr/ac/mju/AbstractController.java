package kr.ac.mju;

import kr.ac.mju.constants.CConstants.EErrorCodes;
import kr.ac.mju.model.User;
import kr.ac.mju.userservice.IGangjwaGwamokEnrollService;
import kr.ac.mju.userservice.IGangjwaManagementService;
import kr.ac.mju.userservice.ILoginService;
import kr.ac.mju.util.validator.GangjwaValidator;
import kr.ac.mju.util.validator.GwamokValidator;
import kr.ac.mju.util.validator.UserScoreValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class AbstractController {
	protected final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	protected IGangjwaGwamokEnrollService gangjwaService;
	@Autowired
	protected ILoginService loginService;
	@Autowired
	protected IGangjwaGwamokEnrollService enrollService;
	@Autowired
	protected IGangjwaManagementService gangjwaManagementService;
	@Autowired
	protected GwamokValidator gwamokValidator;
	@Autowired
	protected GangjwaValidator validator;
	@Autowired
	protected UserScoreValidator userScoreValidator;
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("userid");
	}
	
	protected void RedirectErrorHandling(RedirectAttributes redirectAttributes,String errorName) {
		EErrorCodes errorCode;
		errorCode = EErrorCodes.valueOf(errorName);
		redirectAttributes.addFlashAttribute("errorMessage", errorCode.getErrorMessage());
		logger.error("에러 코드:"+errorCode.getErrorCode()+" / "+ errorCode.toString());
	}
	
	protected User getUserPrincipal() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	protected void addModelMap(ModelMap model, Object... objects) {
		for(int i=0; i<objects.length; i=i+2) {
			model.addAttribute(objects[i].toString(), objects[i+1]);
		}
		
	}
}

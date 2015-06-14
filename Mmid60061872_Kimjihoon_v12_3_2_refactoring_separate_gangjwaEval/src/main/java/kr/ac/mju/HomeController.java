package kr.ac.mju;


import java.util.List;

import kr.ac.mju.model.User;
import kr.ac.mju.model.dto.GangjwaTableDto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userSession")
public class HomeController extends AbstractController {
	//메인 홈 겟으로 받기
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(ModelMap model) {
		int numberOfNewGangjwaEval=0;
		List<GangjwaTableDto> gangjwaList = enrollService.getAllGangjwaDescMaxStudent();//ROLE_ANONYMOUS
		if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			numberOfNewGangjwaEval = gangjwaManagementService.numberNewGangjwaEvalForGyosu(user);
		}
		model.addAttribute("gangjwaList", gangjwaList);
		if(numberOfNewGangjwaEval!=0)
			model.addAttribute("numberOfNewGangjwaEval", numberOfNewGangjwaEval);
		return "home";
	}
	
	@RequestMapping(value="/login.do", method = RequestMethod.GET) 
	public String login() {
		return "member/login";
	}
}

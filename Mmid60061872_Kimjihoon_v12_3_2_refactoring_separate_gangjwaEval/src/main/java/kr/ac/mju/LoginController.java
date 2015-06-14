package kr.ac.mju;

import kr.ac.mju.exception.AlreadyExistUserIDException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.exception.OverSizeInputException;
import kr.ac.mju.model.UserInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/loginController")
public class LoginController extends AbstractController {
	
	//회원 가입 폼 -> anonymous
	@RequestMapping(value = "/memberEnrollForm", method = RequestMethod.GET)
	public String memberEnrollForm() {
		return "memberenrollform";
	}
	//회원 가입 하기 -> anonymous
	@RequestMapping(value = "/memberEnroll", method = RequestMethod.POST)
	public String memberEnroll(@ModelAttribute("userInfo") UserInfo user, Model model,
			RedirectAttributes redirectAttributes) {
		String url = "redirect:/";
		try {
			loginService.userEnroll(user);
			redirectAttributes.addFlashAttribute("completeMsg", "회원 가입을 축하드립니다.");
		} catch (AlreadyExistUserIDException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
			url = "redirect:/loginController/memberEnrollForm";
		} catch (NotInputDataException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
			url = "redirect:/loginController/memberEnrollForm";
		} catch (OverSizeInputException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
			url = "redirect:/loginController/memberEnrollForm";
		}
		return url;
	}

}

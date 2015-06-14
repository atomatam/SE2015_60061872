package kr.ac.mju;

import java.io.IOException;
import java.util.List;

import kr.ac.mju.exception.AlreadyExistGwamokException;
import kr.ac.mju.exception.AlreadySugangShinchungException;
import kr.ac.mju.exception.DuplicateGangjwaIDException;
import kr.ac.mju.exception.NoExistGwamokException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.exception.NotInputNumberException;
import kr.ac.mju.exception.OverSizeClassStudent;
import kr.ac.mju.exception.OverSizeInputException;
import kr.ac.mju.exception.OverflowHakjeomException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.GangjwaScore;
import kr.ac.mju.model.Gwamok;
import kr.ac.mju.model.User;
import kr.ac.mju.model.dto.GangjwaTableDto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/Course")
public class CourseController extends AbstractController {
	
	//강좌와 과목 리스트 받기 -> 교수, 학생 권한
	@RequestMapping(value="/getGangjwaList", method=RequestMethod.GET)
	public String getGwamokList(ModelMap model) {
		addModelMap(model, "allGwamok", enrollService.getAllGwamok()
				, "allGangjwa", enrollService
				.getAllGangjwaTableByProfID(getUserPrincipal()));
		return "gwamokChoice";
	}
	
	//강좌 개설 시 정보 보기 -> 교수 권한
	@RequestMapping(value="/makeGangjwa", method=RequestMethod.POST)
	public String inputGwamokInfor(@ModelAttribute("gwamok") Gwamok gwamok) {
		return "inputGangjwaInfor";
	}
	
	//강좌 개설
	@RequestMapping(value="/setgangjwa", method=RequestMethod.POST)
	public String setGangjwa(Gangjwa gangjwa, 
			RedirectAttributes redirectAttributes) throws IOException{
		try {
			validator.validate(gangjwa, 0);
			enrollService.makeGangjwa(gangjwa);
			redirectAttributes.addFlashAttribute("madeGangjwa", gangjwa);
			logger.info("강좌 개설이 완료되었습니다.");
		} catch (NoExistGwamokException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (NotInputDataException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (DuplicateGangjwaIDException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (OverSizeInputException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch(OverSizeClassStudent e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (NotInputNumberException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		}
		return "redirect:/Course/getGangjwaList";
	}
	
	//수강 신청 홈 페이지
	@RequestMapping(value="/sugagnShinchunghome", method=RequestMethod.GET)
	public String sugangShinchungForm(ModelMap model) {
		List<GangjwaTableDto> sugangList = 
				enrollService.getEnableSugangGangjwaList(getUserPrincipal());
		List<GangjwaTableDto> alreadySugangList = 
				enrollService.getSugangListByUser(getUserPrincipal());
		int total = totalSugangHakjeom(alreadySugangList);
		addModelMap(model,"sugangList", sugangList, "alreadySugangList", alreadySugangList,
				"totalHakjeom", total);
		return "sugangList";
	}
	private int totalSugangHakjeom(List<GangjwaTableDto> alreadySugangList) {
		int result = 0;
		for(GangjwaTableDto gangjwa : alreadySugangList) {
			result += Integer.parseInt(gangjwa.getGwamokHakjeom());
		}
		return result;
	}
	
	//수강 신청 바로하기
	@RequestMapping(value="/directSugangshinchung", method=RequestMethod.POST)
	public String directSugangshinchung(@ModelAttribute("gangjwa") Gangjwa gangjwa, 
			RedirectAttributes redirectAttributes) {
		List<GangjwaTableDto> alreadySugangList = 
				enrollService.getSugangListByUser(getUserPrincipal());
		int total = totalSugangHakjeom(alreadySugangList);
		sugangShinchung(gangjwa, redirectAttributes, total);
		
		return "redirect:/Course/sugagnShinchunghome";
	}
	
	//수강 신청 하는 페이지
	@RequestMapping(value="/sugangshinchung", method=RequestMethod.POST)
	public String sugangShinchung(@ModelAttribute("gangjwa") Gangjwa gangjwa, 
			RedirectAttributes redirectAttributes
			, @RequestParam("totalHakjeom") int totalHakjeom) {
		try {
			validator.validate(gangjwa, 1);
			if(totalHakjeom+Integer.parseInt(gangjwa.getGwamokHakjeom())<20) {
				enrollService.sugangShinchung((User)SecurityContextHolder
						.getContext().getAuthentication().getPrincipal(), gangjwa);
			} else {
				RedirectErrorHandling(redirectAttributes, 
						(new OverflowHakjeomException()).getErrorName());
			}
		} catch (AlreadySugangShinchungException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (OverSizeInputException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (OverSizeClassStudent e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		} catch (NotInputNumberException e) {
			RedirectErrorHandling(redirectAttributes, e.getErrorName());
		}
		return "redirect:/Course/sugagnShinchunghome";
	}
	
	@RequestMapping(value="/sugangdraw", method=RequestMethod.POST)
	public String sugangDraw(@ModelAttribute("gangjwa") Gangjwa gangjwa, 
			RedirectAttributes redirectAttributes) {
		enrollService.sugangWithdraw(getUserPrincipal(), gangjwa);
		return "redirect:/Course/sugagnShinchunghome";
	}
	
	@RequestMapping(value="/makegwamokForm", method=RequestMethod.GET)
	public String makeGwamokForm(ModelMap model) {
		List<Gwamok> allGwmaokList = enrollService.getAllGwamok();
		Gwamok gwamok = new Gwamok();
		addModelMap(model, "gwamok", gwamok, "allGwamokList", allGwmaokList);
		return "makegwamokform";
	}
	
	@RequestMapping(value="/makegwamokForm", method=RequestMethod.POST)
	public String makeGwamok(@ModelAttribute("gwamok") Gwamok gwamok
			, BindingResult result, RedirectAttributes redirectAttributes
			,ModelMap model) throws AlreadyExistGwamokException {
		gwamokValidator.validate(gwamok, result);
		addModelMap(model, "allGwamokList", enrollService.getAllGwamok());
		if(result.hasErrors()) { 
			return "makegwamokform"; 
		}
		redirectAttributes.addFlashAttribute("madeGwamok", gwamok);
		enrollService.makeGwamok(gwamok);
		return "redirect:/Course/makegwamokForm";
	}
	
	@RequestMapping(value="/gangjwaInfoForm", method=RequestMethod.GET)
	public String setScoreFormInSelectGangjwa(ModelMap model) {
		addModelMap(model, "enrolledGangjwa", enrollService
				.getAllGangjwaTableByProfID(getUserPrincipal()),"numberGangjwa"
				,enrollService.getAllGangjwaTableByProfID(getUserPrincipal()).size()
				,"totalHakjeom", getTotalHakjeom(enrollService
						.getAllGangjwaTableByProfID(getUserPrincipal())));
		return "gangjwaInfoForm";
	}
	private int getTotalHakjeom(List<Gangjwa> gangjwas) {
		int totalHakjeom = 0;
		for(Gangjwa gangjwa : gangjwas) {
			totalHakjeom += Integer.parseInt(gangjwa.getGwamokHakjeom());
		}
		return totalHakjeom;
	}
	
	@RequestMapping(value="/detailGangjwaInfoForm", method=RequestMethod.GET)
	public String detailGangjwaInfoForm(String gangjwaID, ModelMap model) {
		Gangjwa stubGangjwa = new Gangjwa();
		stubGangjwa.setGangjwaID(gangjwaID);
		addModelMap(model, "UserScore"
				, enrollService.getUserScoreTableDtoList(stubGangjwa), "gangjwaID", gangjwaID);
		return "detailGangjwaInfoForm";
	}
	
	@RequestMapping(value="/setScore", method=RequestMethod.POST)
	public String setScore(@ModelAttribute GangjwaScore gangjwaScore, ModelMap model) {
		String ErrorMsg = null;
		if(userScoreValidator.isError(gangjwaScore)) {
			ErrorMsg = "학점을 올바르게 입력해 주십시오.";
		} else {
			enrollService.setGangjwaScore(gangjwaScore);
		}
		Gangjwa stubGangjwa = new Gangjwa();
		stubGangjwa.setGangjwaID(gangjwaScore.getGangjwaID());
		addModelMap(model, "UserScore", enrollService.getUserScoreTableDtoList(stubGangjwa)
				, "gangjwaID", gangjwaScore.getGangjwaID(), "ErrorMsg", ErrorMsg);
		return "detailGangjwaInfoForm";
	}
	
	@RequestMapping(value="/showMyScore", method=RequestMethod.GET)
	public String showMyScore(ModelMap model) {
		List<GangjwaTableDto> gangjwaScoreTable = enrollService.getGangjwaScoreTableByUser(
				(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		addModelMap(model, "gangjwaScoreTable", gangjwaScoreTable);
		return "showMyScore";
	}
	
	@RequestMapping(value="/popularGangjwa", method=RequestMethod.GET)
	public String popularGangjwa(ModelMap model) {
		List<GangjwaTableDto> gangjwaList = enrollService.getAllGangjwaDescMaxStudent();
		model.addAttribute("gangjwaList", gangjwaList);
		return "popularGangjwaDetails";
	}
	
	@RequestMapping(value="/gangjwaEvalForm", method=RequestMethod.GET)
	public String writingGangjwaEvaluationHome(ModelMap model) {
		List<GangjwaEvaluation> list = gangjwaManagementService.getGangjwaEvalByStudentID(getUserPrincipal());
		model.addAttribute("gangjwaList", enrollService.getSugangListByUser(getUserPrincipal()));
		model.addAttribute("gangjwaEvalList", list);
		return "gangjwaEvalForm";
	}
	
	@RequestMapping(value="/gangjwaEval", method=RequestMethod.POST)
	public String gangjwaEval(@ModelAttribute GangjwaEvaluation gangjwaEval) {
		gangjwaEval.setUserName(getUserPrincipal().getUsername());
		gangjwaManagementService.writingGangjwaEval(gangjwaEval);
		return "redirect:/Course/gangjwaEvalForm";
	}
	
	@RequestMapping(value="/showGangjwaEvalForm", method=RequestMethod.GET)
	public String showGangjwaEvalForm(ModelMap model) {
		model.addAttribute("gangjwaList", enrollService.getAllGangjwaTableByProfID(getUserPrincipal()));
		return "showGangjwaEvalForm";
	}	
	
	@RequestMapping(value="/showGangjwaEval", method=RequestMethod.POST)
	public String showGangjwaEval(@ModelAttribute Gangjwa gangjwa, ModelMap model) {
		List<GangjwaEvaluation> gangjwaEval = gangjwaManagementService.getGangjwaEvalByGangjwa(gangjwa);
		model.addAttribute("gangjwaEval", gangjwaEval);
		return "showGangjwaEval";
	}	
	
	@RequestMapping(value="/detalEvaluationShow", method=RequestMethod.GET)
	public String detalEvaluationShow(@ModelAttribute GangjwaEvaluation eval, ModelMap model) {
		GangjwaEvaluation gangjwaEval = gangjwaManagementService.getGangjwaEvalByUsernameAndGangjwaID(eval);
		model.addAttribute("gangjwaEval", gangjwaEval);
		return "detalEvaluationShow";
	}	
}

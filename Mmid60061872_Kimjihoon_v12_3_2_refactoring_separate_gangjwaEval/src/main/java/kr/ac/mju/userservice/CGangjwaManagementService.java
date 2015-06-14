package kr.ac.mju.userservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mju.dao.IGangjwaEvalDao;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.User;

@Service
public class CGangjwaManagementService implements IGangjwaManagementService {
	@Autowired
	private IGangjwaEvalDao gangjwaDao;
	
	@Override
	public List<GangjwaEvaluation> getGangjwaEvalByStudentID(User user) {
		return gangjwaDao.getGangjwaEvalByStudentID(user);
	}

	@Override
	public void writingGangjwaEval(GangjwaEvaluation gangjwaEval) {
		if(gangjwaEval.getIsRead()==null)
			gangjwaEval.setIsRead("0");
		if(gangjwaEval.getDate()==null) {
			SimpleDateFormat fommatter = new SimpleDateFormat("yy.MM.dd HH:mm:ss", Locale.KOREA);
			Date currentTime = new Date();
			String dTime = fommatter.format(currentTime);
			gangjwaEval.setDate(dTime);
		}
		gangjwaDao.writingGangjwaEval(gangjwaEval);
	}

	@Override
	public List<GangjwaEvaluation> getGangjwaEvalByGangjwa(Gangjwa gangjwa) {
		return gangjwaDao.getGangjwaEvalByGangjwa(gangjwa);
	}

	@Override
	public GangjwaEvaluation getGangjwaEvalByUsernameAndGangjwaID(
			GangjwaEvaluation eval) {
		return gangjwaDao.getGangjwaEvalByUsernameAndGangjwaID(eval);
	}

	@Override
	public int numberNewGangjwaEvalForGyosu(User user) {
		return gangjwaDao.numberNewGangjwaEvalForGyosu(user);
	}	
	
}

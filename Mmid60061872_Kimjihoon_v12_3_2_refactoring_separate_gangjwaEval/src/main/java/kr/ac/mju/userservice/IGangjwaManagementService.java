package kr.ac.mju.userservice;

import java.util.List;

import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.User;

public interface IGangjwaManagementService {
	public List<GangjwaEvaluation> getGangjwaEvalByStudentID(User userPrincipal);
	public List<GangjwaEvaluation> getGangjwaEvalByGangjwa(Gangjwa gangjwa);
	public GangjwaEvaluation getGangjwaEvalByUsernameAndGangjwaID(GangjwaEvaluation eval);
	public void writingGangjwaEval(GangjwaEvaluation gangjwaEval);
	public int numberNewGangjwaEvalForGyosu(User principal);
}

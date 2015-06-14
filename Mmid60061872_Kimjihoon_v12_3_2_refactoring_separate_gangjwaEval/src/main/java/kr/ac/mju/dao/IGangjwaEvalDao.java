package kr.ac.mju.dao;

import java.util.List;

import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.User;

public interface IGangjwaEvalDao {
	public GangjwaEvaluation getGangjwaEvalByUsernameAndGangjwaID(GangjwaEvaluation eval);
	public List<GangjwaEvaluation> getGangjwaEvalByStudentID(User user);
	public List<GangjwaEvaluation> getGangjwaEvalByGangjwa(Gangjwa gangjwa);
	public void writingGangjwaEval(GangjwaEvaluation gangjwaEval);
	public void checkGangjwaEvalByEval(GangjwaEvaluation eval);
	public int numberNewGangjwaEvalForGyosu(User user);
}

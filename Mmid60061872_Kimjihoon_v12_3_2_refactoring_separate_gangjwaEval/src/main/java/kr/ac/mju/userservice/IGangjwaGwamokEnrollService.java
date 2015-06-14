package kr.ac.mju.userservice;

import java.util.List;

import kr.ac.mju.exception.AlreadyExistGwamokException;
import kr.ac.mju.exception.AlreadySugangShinchungException;
import kr.ac.mju.exception.DuplicateGangjwaIDException;
import kr.ac.mju.exception.NoEixstGangjwaException;
import kr.ac.mju.exception.NoExistGwamokException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.GangjwaScore;
import kr.ac.mju.model.Gwamok;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;
import kr.ac.mju.model.dto.GangjwaTableDto;
import kr.ac.mju.model.dto.UserScoreTableDto;

public interface IGangjwaGwamokEnrollService {
	
	public List<Gwamok> getAllGwamok();
	public void makeGwamok(Gwamok gwamok) throws AlreadyExistGwamokException;
	
	public Gangjwa getGangjwaByGangjwa(Gangjwa gangjwa) throws NoEixstGangjwaException;
	public List<Gangjwa> getAllGangjwaTableByProfID(User prof);
	public List<Gangjwa> getAllGangjwa();
	public void makeGangjwa(Gangjwa gangjwa) throws NotInputDataException, DuplicateGangjwaIDException, NoExistGwamokException;
	public void sugangShinchung(User user1, Gangjwa gangjwa1) throws AlreadySugangShinchungException;
	public void sugangWithdraw(User user, Gangjwa gangjwa);
	public void deleteGangjwa(Gangjwa gangjwa);
	
	public List<GangjwaTableDto> getEnableSugangGangjwaList(User user);
	public List<GangjwaTableDto> getSugangListByUser(User user);
	public List<GangjwaTableDto> getGangjwaListByUser(User user);
	public List<GangjwaTableDto> getGangjwaScoreTableByUser(User user);
	public List<GangjwaTableDto> getAllGangjwaDescMaxStudent();
	
	public List<GangjwaScore> getGangjwaScoreListByGangjwa(Gangjwa stubGangjwa);
	public List<GangjwaScore> getGangjwaScoreByUser(User user);
	public void setGangjwaScore(GangjwaScore gangjwaScore);

	public List<UserScoreTableDto> getUserScoreTableDtoList(Gangjwa stubGangjwa);
	
	public List<UserInfo> getSugangShinchungUserList(Gangjwa gangjwa1);
}

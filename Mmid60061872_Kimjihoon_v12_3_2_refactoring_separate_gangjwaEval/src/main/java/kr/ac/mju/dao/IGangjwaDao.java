package kr.ac.mju.dao;

import java.util.List;

import kr.ac.mju.exception.AlreadyExistGwamokException;
import kr.ac.mju.exception.DuplicateGangjwaIDException;
import kr.ac.mju.exception.NoEixstGangjwaException;
import kr.ac.mju.exception.NoExistGwamokException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.GangjwaScore;
import kr.ac.mju.model.Gwamok;
import kr.ac.mju.model.SugangTable;
import kr.ac.mju.model.User;
import kr.ac.mju.model.dto.GangjwaTableDto;

public interface IGangjwaDao {
	public List<Gwamok> getAllGwamok();
	public boolean isExistGwamokId(Gwamok gwamok);
	public void makeGwamok(Gwamok gwamok) throws AlreadyExistGwamokException;
	
	public Gangjwa getGangjwaByGangjwa(Gangjwa gangjwa) throws NoEixstGangjwaException;
	public List<Gangjwa> getAllGangjwaTableByProfID(User prof);
	public List<Gangjwa> getAllGangjwa();
	public void makeGangjwa(Gangjwa gangjwa) throws NoExistGwamokException, NotInputDataException, DuplicateGangjwaIDException;
	public void sugangShinchung(User user, Gangjwa gangjwa1);
	public void deleteGangjwa(Gangjwa gangjwa);
	public int getCurrentSugangStudent(Gangjwa gangjwa);
	
	public List<SugangTable> getAllSugangTable();
	public List<SugangTable> getSomeOneUserSugangList(User user);
	
	public List<GangjwaTableDto> getAllGangjwaTable();
	public List<GangjwaTableDto> getSugangListByUser(User user);
	public List<GangjwaTableDto> getAllGangjwaDescMaxStudent();
	public List<GangjwaTableDto> getGangjwaListByUser(User user);

	public void withdrawSugang(User user, Gangjwa gangjwa);

	public List<GangjwaScore> getGangjwaScoreListByGangjwa(Gangjwa gangjwa);
	public List<GangjwaScore> getGangjwaScoreByUser(User user);
	public void setGangjwaScore(GangjwaScore gangjwaScore);

}

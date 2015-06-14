package kr.ac.mju.userservice;

import java.util.ArrayList;
import java.util.List;

import kr.ac.mju.dao.IGangjwaDao;
import kr.ac.mju.exception.AlreadyExistGwamokException;
import kr.ac.mju.exception.AlreadySugangShinchungException;
import kr.ac.mju.exception.DuplicateGangjwaIDException;
import kr.ac.mju.exception.NoEixstGangjwaException;
import kr.ac.mju.exception.NoExistGwamokException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaScore;
import kr.ac.mju.model.Gwamok;
import kr.ac.mju.model.SugangTable;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;
import kr.ac.mju.model.dto.GangjwaTableDto;
import kr.ac.mju.model.dto.UserScoreTableDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CGangjwaGwamokEnrollService implements IGangjwaGwamokEnrollService {
	@Autowired
	private IGangjwaDao gangjwaDao;
	@Autowired
	private ILoginService loginService;

	@Override
	public void makeGangjwa(Gangjwa gangjwa) throws NoExistGwamokException,
			NotInputDataException, DuplicateGangjwaIDException {
		gangjwaDao.makeGangjwa(gangjwa);
	}
	
	@Override
	public void makeGwamok(Gwamok gwamok) throws AlreadyExistGwamokException {
		gangjwaDao.makeGwamok(gwamok);
	}
	
	@Override
	public Gangjwa getGangjwaByGangjwa(Gangjwa gangjwa)
			throws NoEixstGangjwaException {
		return gangjwaDao.getGangjwaByGangjwa(gangjwa);
	}

	@Override
	public List<Gangjwa> getAllGangjwa() {
		return gangjwaDao.getAllGangjwa();
	}

	@Override
	public List<Gwamok> getAllGwamok() {
		return gangjwaDao.getAllGwamok();
	}

	@Override
	public void deleteGangjwa(Gangjwa gangjwa) {
		gangjwaDao.deleteGangjwa(gangjwa);
	}

	@Override
	public void sugangShinchung(User user, Gangjwa gangjwa)
			throws AlreadySugangShinchungException {
		gangjwaDao.sugangShinchung(user, gangjwa);
	}

	@Override
	public void setGangjwaScore(GangjwaScore gangjwaScore) {
		gangjwaDao.setGangjwaScore(gangjwaScore);
	}

	@Override
	public List<GangjwaTableDto> getGangjwaScoreTableByUser(User user) {
		List<GangjwaTableDto> userScoreTable = getGangjwaListByUser(user);
		List<GangjwaScore> gangjwaScore = getGangjwaScoreByUser(user);
		return sumGangjwaTableDtoAndGangjwaScore(userScoreTable, gangjwaScore);
	}
	private List<GangjwaTableDto> sumGangjwaTableDtoAndGangjwaScore(
			List<GangjwaTableDto> userScoreTable, List<GangjwaScore> gangjwaScores) {
		List<GangjwaTableDto> result = new ArrayList<GangjwaTableDto>();
		for(GangjwaTableDto gangjwaTable : userScoreTable) {
			gangjwaTable.setScore("unwritten");
			for(GangjwaScore gangjwaScore : gangjwaScores) {
				if(gangjwaTable.getGangjwaID().equals(gangjwaScore.getGangjwaID())) {
					gangjwaTable.setScore(gangjwaScore.getScore());
				}
			}
			result.add(gangjwaTable);
		}
		return result;
	}
	
	@Override
	public List<GangjwaScore> getGangjwaScoreByUser(User user) {
		return gangjwaDao.getGangjwaScoreByUser(user);
	}

	@Override
	public List<GangjwaTableDto> getGangjwaListByUser(User user) {
		return gangjwaDao.getGangjwaListByUser(user);
	}
	
	@Override
	public List<UserScoreTableDto> getUserScoreTableDtoList(Gangjwa gangjwa) {
		List<UserInfo> userInforList = getSugangShinchungUserList(gangjwa);
		List<GangjwaScore> gangjwaScoreList = getGangjwaScoreListByGangjwa(gangjwa);
		return sumUserInforListAndGangjwaScoreList(userInforList, gangjwaScoreList);
	}
	
	private List<UserScoreTableDto> sumUserInforListAndGangjwaScoreList(
			List<UserInfo> userInforList, List<GangjwaScore> gangjwaScoreList) {
		List<UserScoreTableDto> result = new ArrayList<UserScoreTableDto>();
		for(UserInfo user : userInforList) {
			UserScoreTableDto temp = new UserScoreTableDto();
			temp.setScore("0");
			temp.setUsername(user.getUsername());
			temp.setUserRealName(user.getUserRealName());
			for(GangjwaScore gs : gangjwaScoreList) {
				if(user.getUsername().equals(gs.getUsername())) {
					if(gs.getScore() == null)
						gs.setScore("0");
					temp.setScore(gs.getScore());
					break;
				}
			}
			result.add(temp);
		}
		return result;
	}	

	@Override
	public List<GangjwaScore> getGangjwaScoreListByGangjwa(Gangjwa gangjwa) {
		return gangjwaDao.getGangjwaScoreListByGangjwa(gangjwa);
	}

	@Override
	public List<UserInfo> getSugangShinchungUserList(Gangjwa gangjwa) {
		List<UserInfo> userList = loginService.getAllUserInfo();
		List<SugangTable> sugangList = gangjwaDao.getAllSugangTable();
		List<String> userIDList = getUserIDListFromSugangTable(gangjwa, sugangList);
		return filterUserInfoByUserID(userIDList, userList);
	}
	private List<UserInfo> filterUserInfoByUserID(List<String> userIDList,
			List<UserInfo> userList) {
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		for(UserInfo userInfo : userList) {
			String userInfoID = userInfo.getUsername();
			for(String userID : userIDList) 
				if(userInfoID.equals(userID)) {
					userInfoList.add(userInfo);
					break;
				}
		}
		return userInfoList;
	}
	private List<String> getUserIDListFromSugangTable(Gangjwa gangjwa,
			List<SugangTable> sugangList) {
		List<String> userIDs = new ArrayList<String>();
		for(SugangTable sugang : sugangList) {
			if(sugang.getGangjwaID().equals(gangjwa.getGangjwaID()))
				userIDs.add(sugang.getUserID());
		}
		return userIDs;
	}

	@Override
	public List<GangjwaTableDto> getEnableSugangGangjwaList(User user) {
		List<GangjwaTableDto> allGangjwa = gangjwaDao.getAllGangjwaTable();
		List<SugangTable> alreadySugangList = getAlreadSugangGangjwaList(user);
		return excludeAlreadyGangjwaList(alreadySugangList, allGangjwa);
	}

	private List<SugangTable> getAlreadSugangGangjwaList(User user) {
		List<SugangTable> allList = gangjwaDao.getAllSugangTable();
		List<SugangTable> result = new ArrayList<SugangTable>();

		for (SugangTable su : allList) {
			if (su.getUserID().equals(user.getUsername()))
				result.add(su);
		}
		return result;
	}

	private List<GangjwaTableDto> excludeAlreadyGangjwaList(
			List<SugangTable> alreadyGangjwa, List<GangjwaTableDto> allGangjwa) {
		boolean addGangjwa;
		List<GangjwaTableDto> result = new ArrayList<GangjwaTableDto>();
		for (GangjwaTableDto all : allGangjwa) {
			addGangjwa = true;
			for (SugangTable already : alreadyGangjwa) {
				if (already.getGangjwaID().equals(all.getGangjwaID())) {
					addGangjwa = false;
					break;
				}
			}
			if (addGangjwa) {
				result.add(all);
			}
		}
		return result;
	}

	@Override
	public List<GangjwaTableDto> getSugangListByUser(User user) {
		return gangjwaDao.getSugangListByUser(user);
	}

	@Override
	public void sugangWithdraw(User user, Gangjwa gangjwa) {
		gangjwaDao.withdrawSugang(user, gangjwa);
	}

	@Override
	public List<Gangjwa> getAllGangjwaTableByProfID(User prof) {
		return gangjwaDao.getAllGangjwaTableByProfID(prof);
	}

	@Override
	public List<GangjwaTableDto> getAllGangjwaDescMaxStudent() {
		List<GangjwaTableDto> result = gangjwaDao.getAllGangjwaDescMaxStudent();
		if(isLastGangjwaCurrentStudentSameLastBeforeGangjwaCurrentStudent(result))
			return pupularGangjwaFilter(result);
		result.remove(10);
		return result;
	}
	private List<GangjwaTableDto> pupularGangjwaFilter(List<GangjwaTableDto> gangjwaList) {
		return samePopularRemoveFilter(gangjwaList);
	}
	private List<GangjwaTableDto> samePopularRemoveFilter(List<GangjwaTableDto> gangjwaList) {
		List<GangjwaTableDto> result = new ArrayList<GangjwaTableDto>();
		String lastGangjwaStudent = gangjwaList.get((gangjwaList.size())-1).getCurrentStudent();
		for(GangjwaTableDto gangjwa:gangjwaList) {
			if(!gangjwa.getCurrentStudent().equals(lastGangjwaStudent)) 
				result.add(gangjwa);
		}
		return result;
	}
	private boolean isLastGangjwaCurrentStudentSameLastBeforeGangjwaCurrentStudent(List<GangjwaTableDto> gangjwaList) {
		if( gangjwaList.get((gangjwaList.size())-1).getCurrentStudent()
				.equals(gangjwaList.get((gangjwaList.size())-2).getCurrentStudent()))
			return true;
		return false;
	}


}

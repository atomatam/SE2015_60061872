package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import kr.ac.mju.exception.AlreadySugangShinchungException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;
import kr.ac.mju.model.dto.GangjwaTableDto;
import kr.ac.mju.userservice.IGangjwaGwamokEnrollService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class SugangTestClass {
	@Autowired
	IGangjwaGwamokEnrollService sugangService;
	User user1;
	Gangjwa gangjwa1;
	
	@Before
	public void setUp() {
//		user1 = new User();
//		user1.setUserID("1");
//		user1.setUserCode("oneman");
//		user1.setUserName("김길동");
//		user1.setUserPassword("1");
		gangjwa1 = new Gangjwa();
		gangjwa1.setGangjwaID("11");
		gangjwa1.setGwamokID("1");
		gangjwa1.setGwamokHakjeom("3");
		gangjwa1.setGwamokName("softwareEng");
		gangjwa1.setMaxStudent("30");
	}
	
	@Test
	public void sugangShincungTest() throws AlreadySugangShinchungException {
		sugangService.sugangShinchung(user1, gangjwa1);
//		assertThat(isContainID(users, user1.getUserID()), is(true));
		sugangService.sugangWithdraw(user1, gangjwa1);
	}

	@Test
	public void checkSugangShinchungTest() {
		List<GangjwaTableDto> sugangList = sugangService.getEnableSugangGangjwaList(user1);
		List<GangjwaTableDto> alreadySugangList = sugangService.getSugangListByUser(user1);
		assertThat(sugangList.size(), is(5));
		assertThat(alreadySugangList.size(), is(0));
	}
	
	public boolean isContainID(List<User> users, String userID) {
		return false;
	}
}

//package test;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import java.util.List;
//
//import kr.ac.mju.constants.CConstants.EUserType;
//import kr.ac.mju.exception.AlreadyExistUserIDException;
//import kr.ac.mju.exception.NotCorrectUserIDException;
//import kr.ac.mju.exception.NotCorrectUserPasswordException;
//import kr.ac.mju.exception.NotInputDataException;
//import kr.ac.mju.model.User;
//import kr.ac.mju.userservice.ILoginService;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "/test-applicationContext.xml")
//public class UserTestClass {
//	@Autowired
//	private ILoginService loginService;
//
//	private User user1, user2;
//	private User userInfor1, userInfor2;
//
//	@Before
//	public void setUp() {
//		user1 = new User();
//		user2 = new User();
//		userInfor1 = new User();
//		userInfor2 = new User();
//
//		user1.setUserID("1");
////		user1.setUserName("oneman");
//		user1.setUserPassword("1");
////		user1.setUserCode("2");
//		user2.setUserID("2");
////		user2.setUserName("twoman");
//		user2.setUserPassword("2");
////		user2.setUserCode("2");
//
//		userInfor1.setUserID("1");
//		userInfor1.setUserPassword("1");
//		userInfor2.setUserID("2");
//		userInfor2.setUserPassword("2");
//	}
//
//	@Test
//	public void userGetAllTest() {
//		List<User> users = loginService.getAllUser();
//		assertThat(users.size(), is(6));
//	}
//
//	@Test
//	public void userLoginTest() throws NotCorrectUserIDException,
//			NotCorrectUserPasswordException {
//		User user1 = loginService.login(userInfor1);
//		assertThat(user1.getUserID(), is(userInfor1.getUserID()));
//		assertThat(user1.getUserPassword(), is(userInfor1.getUserPassword()));
//		User user2 = loginService.login(userInfor2);
//		assertThat(user2.getUserID(), is(userInfor2.getUserID()));
//		assertThat(user2.getUserPassword(), is(userInfor2.getUserPassword()));
//	}
//
//	@Test(expected = NotCorrectUserIDException.class)
//	public void userIDLoginFailureTest() throws NotCorrectUserIDException,
//			NotCorrectUserPasswordException {
//		userInfor1.setUserID("unknownID");
//		userInfor1.setUserPassword("unknownPassword");
//		loginService.login(userInfor1);
//	}
//
//	@Test(expected = NotCorrectUserPasswordException.class)
//	public void userPasswordLoginFailureTest() throws NotCorrectUserIDException,
//			NotCorrectUserPasswordException {
//		userInfor1.setUserID("1");
//		userInfor1.setUserPassword("unknownPassword");
//		loginService.login(userInfor1);
//	}
//
//	@Test(expected = AlreadyExistUserIDException.class)
//	public void FailureUserEnrollTest() throws AlreadyExistUserIDException,
//			NotInputDataException, NotCorrectUserIDException {
//		loginService.userEnroll(user1);
//	}
//	
//	@Test
//	public void userEnrollTest() throws AlreadyExistUserIDException, NotCorrectUserIDException, NotInputDataException {
//		User user = new User();
//		user.setUserID("10");
//		user.setUserPassword("10");
////		user.setUserName("tenman");
////		user.setUserCode(String.valueOf(EUserType.STUDENT.getUserCode()));
//		loginService.userEnroll(user);
//		loginService.deleteUser(user);
//	}
//}

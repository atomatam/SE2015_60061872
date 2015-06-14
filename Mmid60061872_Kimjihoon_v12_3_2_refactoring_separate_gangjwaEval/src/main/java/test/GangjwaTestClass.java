package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import kr.ac.mju.exception.DuplicateGangjwaIDException;
import kr.ac.mju.exception.NoEixstGangjwaException;
import kr.ac.mju.exception.NoExistGwamokException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.userservice.IGangjwaGwamokEnrollService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class GangjwaTestClass {
	@Autowired
	private IGangjwaGwamokEnrollService sugangService;
	private Gangjwa gangjwa1, gangjwa2;
	private Gangjwa unEnrolledGangjwa1;
	@Before
	public void setUp() {
		gangjwa1 = new Gangjwa();
		gangjwa2 = new Gangjwa();
	}
	@Test
	public void makeGangjwaTest() 
			throws NoExistGwamokException, NotInputDataException, 
			DuplicateGangjwaIDException, NoEixstGangjwaException {
		gangjwa1.setGwamokID("2");
		gangjwa1.setGwamokName("softwareEngineering");
		gangjwa1.setGangjwaID("999");
		gangjwa1.setMaxStudent("33");
		gangjwa1.setGwamokHakjeom("3");
		sugangService.makeGangjwa(gangjwa1);
		Gangjwa gangjwa = sugangService.getGangjwaByGangjwa(gangjwa1);
		assertThat(gangjwa.getGwamokID(), is("2"));
		assertThat(gangjwa.getGangjwaID(), is("999"));
		sugangService.deleteGangjwa(gangjwa1);
	}
	@Test(expected=DuplicateGangjwaIDException.class)
	public void duplicateGangjwaID() throws NoExistGwamokException, NotInputDataException, DuplicateGangjwaIDException {
		gangjwa1.setGwamokID("1");
		gangjwa1.setGwamokName("java");
		gangjwa1.setGangjwaID("11");
		gangjwa1.setMaxStudent("33");
		gangjwa1.setGwamokHakjeom("3");
		sugangService.makeGangjwa(gangjwa1);
	}
	
}

package kr.ac.mju.constants;

public class CConstants {
	public static enum EErrorCodes {
		NoExistUserID(1, "아이디가 올바르지 않습니다"), 
		NoExistUserPassword(2,"비밀번호가 올바르지 않습니다"), 
		AlreadyExistUserID(3,"이미 존재하는 아이디입니다"), 
		NotInputData(4,"정보를 입력해 주세요"), 
		NoExistGwamok(5,"해당하는 과목이 존재하지 않습니다"), 
		AlreadyExistGwamok(6,"이미 존재하는 과목입니다"),
		DuplicateGangjwaID(7,"이미 존재하는 강좌 ID 입니다"), 
		NoExistGangjwa(8, "해당 강좌가 존재하지 않습니다"), 
		AlreadySugangShinchung(9, "이미 수강신청을 하셨습니다"),
		OverSize(10, "입력을 초과하셨습니다"),
		OverSizeClassStudent(11, "최대 수강 인원을 초과했습니다."),
		NotCorrectInput(12, "최대 수강 인원에는 숫자를 입력해주세요"),
		OverflowHakjeom(13, "최대 학점을 초과하셨습니다.(최대 19학점)");

		private int errorCode;
		private String errorMessage;

		private EErrorCodes(int errorCode, String errorMessage) {
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		}

		public int getErrorCode() {
			return this.errorCode;
		}

		public String getErrorMessage() {
			return this.errorMessage;
		}
	}

	public static enum ELogers {
		REQUESTLOGIN("로그인 요청"), SUCCESSLOGIN("로그인 성공"), FAILURELOGIN("로그인 실패"), SUCCESSLOGOUT(
				"로그아웃 성공");

		private String loger;

		private ELogers(String loger) {
			this.loger = loger;
		}

		public String getLogers() {
			return this.loger;
		}
	}

	public static enum EUserType {
		STUDENT("0"), GYOSU("1");
		private String userCode;

		private EUserType(String userCode) {
			this.userCode = userCode;
		}

		public String getUserCode() {
			return userCode;
		}
	}
	


	
	
	
	//User
	public static String USERID = "userid";
	public static String USERPASSWORD = "userpassword";
	public static String USERNAME = "username";
	public static String USERCODE = "usercode";
	public static String GETALLUSER = "select * from user order by userid";
	public static String GETUSERBYID = "select * from user where userid=?";
	public static String INSERTUSER = "insert into user values (?,?,1,?)";
	public static String DELETEUSERBYID = "delete from user where userid=?";
	//Gwamok
	public static String GWAMOKID = "gwamokid";
	public static String GWAMOKNAME = "gwamokname";
	public static String GWAMOKHAKJEOM = "gwamokhakjeom";
	public static String SELECTALLGWAMOK = "select * from gwamok";
	public static String GETGWAMOKBYID = "select * from gwamok where gwamokid=?";
	public static String INSERTGWAMOK = "insert into gwamok values (?,?,?,?,?,?)";
	public static String SELECTGWAMOKBYGWAMOK = "select * from gwamok where gwamokid=?";
	public static String GETCURRENTGANGJWASTUDENT = "select currentstudent from gangjwa where gangjwaid=?";
	public static String GANGJWACURRENTSTUDENTMINUS = "update gangjwa set currentstudent=currentstudent-1 where gangjwaid=?";
	public static String GANGJWACURRENTSTUDENTPLUS = "update gangjwa set currentstudent=currentstudent+1 where gangjwaid=?";
	//Gangjwa
	public static String GANGJWAID = "gangjwaid";
	public static String MAXSTUDENT = "maxstudent";
	public static String GETALLGANGJWA = "select * from gangjwa, gwamok where gangjwa.gwamokid = gwamok.gwamokid";
	public static String INSERTGANGJWA = "insert into gangjwa values(?,?,0,?,?)";
	public static String GETGANGJWABYID = "select * from gwamok, gangjwa where gwamok.gwamokid = gangjwa.gwamokid and gangjwaid=?";
	public static String DELETEGANGJWABYID = "delete from gangjwa where gangjwaid=?";
	//SugangTable
	public static String INSERTSUGANGTABLE = "insert sugangtable values(?,?)";
	public static String DELETESUGANGTABLEBYID = "delete from sugangtable where gangjwaid=? and userid=?";
	public static String SELECTSUGANGTABLE = "select * from sugangtable";
	public static String GETSUGANGTABLEBYUSERID = "select * from sugangtable where userid=?";
	//GangjwaTable
	public static String GETGANGJWATABLEDTOBYUSER = "select sugangtable.gangjwaid, gwamok.gwamokname, user.username, gwamok.year, gwamok.hakki, gwamok.haknyun, gwamok.gwamokhakjeom, gangjwa.maxstudent from sugangtable left join gangjwa on sugangtable.gangjwaid=gangjwa.gangjwaid left join gwamok on gangjwa.gwamokid=gwamok.gwamokid left join user on gangjwa.profid=user.userid where sugangtable.userid=?";
	public static String GETALLGANGJWATABLEBYPROFID = "select * from gangjwa, gwamok where gwamok.gwamokid = gangjwa.gwamokid and profid = ?";
	//GangjwaEvaluation
	public static String DELETEGANGJWAEVAL = "delete from gangjwa_evaluation where userid=? and gangjwaid=?";
	//GangjwaScore
	public static String DELETESUGANGSCORE = "delete from gangjwa_score where userid=? and gangjwaid=?";
	public static String GETGANGJWASCORE = "select * from gangjwa_score where userid=?";
	public static String ADDGANGJWASCORE = "insert into gangjwa_score values(?,?,?)";
	public static String UPDATEGANGJWASCORE = "update gangjwa_score set jeomsu=? where gangjwaid=? and userid=?";
	//Complex
	public static String GETSUGANGLISTBYUSERID = "select * from gangjwa, sugangtable, gwamok where gangjwa.gangjwaid = sugangtable.gangjwaid and gangjwa.gwamokid = gwamok.gwamokid and userid=?";
	public static String GETALLGANGJWATABLE = "select * from gangjwa, gwamok, user where gangjwa.gwamokid = gwamok.gwamokid and gangjwa.profid = user.userid";
	public static String GETSUGANGLISTBYUSER = "select gwamok.gwamokid, gwamok.gwamokname, gangjwa.profid, gwamok.gwamokhakjeom, gangjwa.currentstudent, gangjwa.maxstudent, gwamok.haknyun, gwamok.year, gwamok.hakki, gangjwa.gangjwaid, user.username from sugangtable left join gangjwa on sugangtable.gangjwaid=gangjwa.gangjwaid left join gwamok on gangjwa.gwamokid=gwamok.gwamokid left join user on gangjwa.profid=user.userid where sugangtable.userid=?";
	public static String GETPOPULARTOP11 = "select * from gangjwa, gwamok, user where gangjwa.gwamokid=gwamok.gwamokid and gangjwa.profid=user.userid order by currentstudent desc, gangjwaid asc limit 11";

}//과목아이디	과목이름	학점	현재 수강 인원	최대 수강 인원	학년	    개설 년도	개설 학기	강좌번호	담당 교수	
//select gwamok.gwamokid, gwamok.gwamokname, gwamok.gwamokhakjeom, gangjwa.currentstudent, gangjwa.maxstudent, gwamok.haknyun, gwamok.year, gwamok.hakki, gangjwa.gangjwaid, user.username from sugangtable left join gangjwa on sugangtable.gangjwaid=gangjwa.gangjwaid left join gwamok on gangjwa.gwamokid=gwamok.gwamokid left join user on gangjwa.profid=user.userid where sugangtable.userid=?

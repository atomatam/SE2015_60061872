package kr.ac.mju.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaEvaluation;
import kr.ac.mju.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CGangjwaEvalDao implements IGangjwaEvalDao {
	private JdbcTemplate jdbcTempleate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTempleate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<GangjwaEvaluation> getGangjwaEvalByStudentID(User user) {
		return this.jdbcTempleate.query("select * from gangjwa_evaluation where userid=?"
				, new Object[] {user.getUsername()}, gangjwaEvalRowMapper);
	}
	
	@Override
	public void writingGangjwaEval(GangjwaEvaluation gangjwaEval) {
		try {
			this.jdbcTempleate.update("insert into gangjwa_evaluation values(?,?,?,?,?)", 
					gangjwaEval.getUserName(), gangjwaEval.getGangjwaID()
					, gangjwaEval.getGangjwaEval(), gangjwaEval.getIsRead(),gangjwaEval.getDate());
		} catch(DuplicateKeyException e) {
			this.jdbcTempleate.update("update gangjwa_evaluation set gangjwaeval=?,date=?,isread='0' where gangjwaid=? and userid=?"
					,gangjwaEval.getGangjwaEval(), gangjwaEval.getDate(), gangjwaEval.getGangjwaID(), gangjwaEval.getUserName());
		}
	}

	@Override
	public List<GangjwaEvaluation> getGangjwaEvalByGangjwa(Gangjwa gangjwa) {
		return this.jdbcTempleate.query("select * from gangjwa_evaluation where gangjwaid=?", 
				new Object[] {gangjwa.getGangjwaID()}, gangjwaEvalRowMapper);
	}

	@Override
	public GangjwaEvaluation getGangjwaEvalByUsernameAndGangjwaID(
			GangjwaEvaluation eval) {
		checkGangjwaEvalByEval(eval);
		return this.jdbcTempleate.queryForObject("select * from gangjwa_evaluation where gangjwaid=? and userid=?"
				, new Object[] {eval.getGangjwaID(), eval.getUserName()}, gangjwaEvalRowMapper);
	}
	
	@Override
	public void checkGangjwaEvalByEval(GangjwaEvaluation eval) {
		this.jdbcTempleate.update("update gangjwa_evaluation set isread='1' where gangjwaid=? and userid=?"
				,  eval.getGangjwaID(), eval.getUserName());
	}
	
	@Override
	public int numberNewGangjwaEvalForGyosu(User user) {
		int flag = this.jdbcTempleate.queryForInt("select count(isread) from gangjwa_evaluation left join gangjwa on gangjwa_evaluation.gangjwaid=gangjwa.gangjwaid where gangjwa.profid=? and isread='0'"
				,user.getUsername());
		return flag;//;
	}
	private RowMapper<GangjwaEvaluation> gangjwaEvalRowMapper = new RowMapper<GangjwaEvaluation>() {
		public GangjwaEvaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
			GangjwaEvaluation gangjwaEval = new GangjwaEvaluation();
			gangjwaEval.setGangjwaID(rs.getString("gangjwaid"));
			gangjwaEval.setUserName(rs.getString("userid"));
			gangjwaEval.setGangjwaEval(rs.getString("gangjwaeval"));
			gangjwaEval.setIsRead(rs.getString("isread"));
			gangjwaEval.setDate(rs.getString("date"));
			return gangjwaEval;
		}
	};
}

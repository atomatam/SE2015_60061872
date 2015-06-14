package kr.ac.mju.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.ac.mju.constants.CConstants;
import kr.ac.mju.exception.AlreadyExistGwamokException;
import kr.ac.mju.exception.DuplicateGangjwaIDException;
import kr.ac.mju.exception.NoEixstGangjwaException;
import kr.ac.mju.exception.NoExistGwamokException;
import kr.ac.mju.exception.NotInputDataException;
import kr.ac.mju.model.Gangjwa;
import kr.ac.mju.model.GangjwaScore;
import kr.ac.mju.model.Gwamok;
import kr.ac.mju.model.SugangTable;
import kr.ac.mju.model.User;
import kr.ac.mju.model.dto.GangjwaTableDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CGangjwaDao implements IGangjwaDao {
	private JdbcTemplate jdbcTempleate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTempleate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Gwamok> getAllGwamok() {
		return this.jdbcTempleate.query(CConstants.SELECTALLGWAMOK,
				this.gwamokRowMapper);
	}
	
	@Override
	public List<Gangjwa> getAllGangjwa() {
		return this.jdbcTempleate.query(CConstants.GETALLGANGJWA,
				this.gangjwaRowMapper);
	}
	
	@Override
	public List<GangjwaTableDto> getAllGangjwaTable() {
		return this.jdbcTempleate.query(CConstants.GETALLGANGJWATABLE,
				this.gangjwaTableRowMapper);
	}
	
	@Override
	public void makeGangjwa(Gangjwa gangjwa) throws NoExistGwamokException,
			NotInputDataException, DuplicateGangjwaIDException {
		isContainNullvalue(gangjwa);
		try {
			getGwamokByGwamokID(gangjwa);
			makeGangjwaByGangjwaID(gangjwa);
		} catch (EmptyResultDataAccessException e) {
			throw new NoExistGwamokException();
		} catch (DuplicateKeyException e) {
			throw new DuplicateGangjwaIDException();
		}
	}
	private void makeGangjwaByGangjwaID(Gangjwa gangjwa) {
		User prof = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.jdbcTempleate.update(CConstants.INSERTGANGJWA,
				gangjwa.getGangjwaID(), gangjwa.getMaxStudent(),
				gangjwa.getGwamokID(), prof.getUsername());
	}
	private Gwamok getGwamokByGwamokID(Gwamok gwamok) {
		return this.jdbcTempleate.queryForObject(CConstants.GETGWAMOKBYID,
				new Object[] { gwamok.getGwamokID() }, gwamokRowMapper);
	}
	
	@Override
	public void makeGwamok(Gwamok gwamok) throws AlreadyExistGwamokException {
		try {
			this.jdbcTempleate.update(CConstants.INSERTGWAMOK,
					gwamok.getGwamokID(), gwamok.getGwamokName(),
					gwamok.getGwamokHakjeom(), gwamok.getHaknyun(),
					gwamok.getYear(), gwamok.getHakki());
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			throw new AlreadyExistGwamokException();
		}
	}
	
	@Override
	public Gangjwa getGangjwaByGangjwa(Gangjwa gangjwa)
			throws NoEixstGangjwaException {
		try {
			return this.jdbcTempleate.queryForObject(CConstants.GETGANGJWABYID,
					new Object[] { gangjwa.getGangjwaID() }, gangjwaRowMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new NoEixstGangjwaException();
		}
	}
	
	@Override
	public void deleteGangjwa(Gangjwa gangjwa) {
		this.jdbcTempleate.update(CConstants.DELETEGANGJWABYID,
				gangjwa.getGangjwaID());
	}
	
	@Override
	public void sugangShinchung(User user, Gangjwa gangjwa) {
		this.jdbcTempleate.update(CConstants.INSERTSUGANGTABLE,
				gangjwa.getGangjwaID(), user.getUsername());
		this.jdbcTempleate.update(CConstants
				.GANGJWACURRENTSTUDENTPLUS, gangjwa.getGangjwaID());
	}

	@Override
	public List<GangjwaTableDto> getGangjwaListByUser(User user) {
		return this.jdbcTempleate.query(CConstants.GETGANGJWATABLEDTOBYUSER,
				new Object[] { user.getUsername() }, gangjwaScoreTableRowMapper);
	}

	@Override
	public List<GangjwaScore> getGangjwaScoreByUser(User user) {
		return this.jdbcTempleate.query(CConstants.GETGANGJWASCORE,
				new Object[] { user.getUsername() }, gangjwaScoreRowMapper);
	}	
	
	@Override
	public List<SugangTable> getAllSugangTable() {
		return this.jdbcTempleate.query(CConstants.SELECTSUGANGTABLE,
				this.sugangTableRowMapper);
	}
	private void isContainNullvalue(Gangjwa gangjwa)
			throws NotInputDataException {
		if (gangjwa.getGangjwaID() == null
				|| gangjwa.getGangjwaID() == ""
				|| gangjwa.getMaxStudent() == null)
			throw new NotInputDataException();
	}
	
	@Override
	public List<GangjwaTableDto> getSugangListByUser(User user) {
		return this.jdbcTempleate.query(CConstants.GETSUGANGLISTBYUSER,
				new Object[] { user.getUsername() }, gangjwaTableRowMapper);
	}

	@Override
	public void setGangjwaScore(GangjwaScore gangjwaScore) {
		try {
			this.jdbcTempleate.update(CConstants.ADDGANGJWASCORE, 
					gangjwaScore.getUsername(), gangjwaScore.getGangjwaID(), gangjwaScore.getScore());
		}catch(DuplicateKeyException e) {
			this.jdbcTempleate.update(CConstants.UPDATEGANGJWASCORE 
					, gangjwaScore.getScore(), gangjwaScore.getGangjwaID(), gangjwaScore.getUsername());
		}
	}
	
	@Override
	public List<GangjwaScore> getGangjwaScoreListByGangjwa(Gangjwa gangjwa) {
		return this.jdbcTempleate.query("select * from gangjwa_score where gangjwaid=?", 
				new Object[] { gangjwa.getGangjwaID() }, gangjwaScoreRowMapper);
	}

	@Override
	public void withdrawSugang(User user, Gangjwa gangjwa) {
		this.jdbcTempleate.update(
				CConstants.DELETESUGANGTABLEBYID,
				gangjwa.getGangjwaID(), user.getUsername());
		this.jdbcTempleate.update(
				CConstants.GANGJWACURRENTSTUDENTMINUS, gangjwa.getGangjwaID());
		this.jdbcTempleate.update(CConstants.DELETESUGANGSCORE,
				user.getUsername(), gangjwa.getGangjwaID());
		this.jdbcTempleate.update(CConstants.DELETEGANGJWAEVAL, 
				user.getUsername(), gangjwa.getGangjwaID());
	}

	@Override
	public List<SugangTable> getSomeOneUserSugangList(User user) {
		return this.jdbcTempleate.query(CConstants.GETSUGANGTABLEBYUSERID,
				new Object[] { user.getUsername() }, this.sugangTableRowMapper);
	}

	@Override
	public boolean isExistGwamokId(Gwamok gwamok) {
		try {
			@SuppressWarnings("unused")
			Gwamok tempGwamok = this.jdbcTempleate.queryForObject(
					CConstants.SELECTGWAMOKBYGWAMOK
					, new Object[] { gwamok.getGwamokID() }, gwamokRowMapper);
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public int getCurrentSugangStudent(Gangjwa gangjwa) {
		return this.jdbcTempleate.queryForInt(
				CConstants.GETCURRENTGANGJWASTUDENT, gangjwa.getGangjwaID());
	}

	@Override
	public List<Gangjwa> getAllGangjwaTableByProfID(User prof) {
		return this.jdbcTempleate.query(CConstants.GETALLGANGJWATABLEBYPROFID
				,new Object[] { prof.getUsername() }, gangjwaRowMapper);
	}

	@Override
	public List<GangjwaTableDto> getAllGangjwaDescMaxStudent() {
		return this.jdbcTempleate.query(CConstants.GETPOPULARTOP11, 
				gangjwaTableRowMapper);
	}

	private RowMapper<Gwamok> gwamokRowMapper = new RowMapper<Gwamok>() {
		public Gwamok mapRow(ResultSet rs, int rowNum) throws SQLException {
			Gwamok gwamok = new Gwamok();
			gwamok.setGwamokID(rs.getString(CConstants.GWAMOKID));
			gwamok.setGwamokName(rs.getString(CConstants.GWAMOKNAME));
			gwamok.setGwamokHakjeom(rs.getString(CConstants.GWAMOKHAKJEOM));
			gwamok.setHaknyun(rs.getString("haknyun"));
			gwamok.setYear(rs.getString("year"));
			gwamok.setHakki(rs.getString("hakki"));
			return gwamok;
		}
	};
	private RowMapper<Gangjwa> gangjwaRowMapper = new RowMapper<Gangjwa>() {
		public Gangjwa mapRow(ResultSet rs, int rowNum) throws SQLException {
			Gangjwa gangjwa = new Gangjwa();
			gangjwa.setGangjwaID(rs.getString(CConstants.GANGJWAID));
			gangjwa.setMaxStudent(rs.getString(CConstants.MAXSTUDENT));
			gangjwa.setGwamokID(rs.getString(CConstants.GWAMOKID));
			gangjwa.setGwamokName(rs.getString(CConstants.GWAMOKNAME));
			gangjwa.setCurrentStudent(rs.getString("currentstudent"));
			gangjwa.setGwamokHakjeom(rs.getString(CConstants.GWAMOKHAKJEOM));
			gangjwa.setProfID(rs.getString("profid"));
			gangjwa.setHaknyun(rs.getString("haknyun"));
			gangjwa.setYear(rs.getString("year"));
			gangjwa.setHakki(rs.getString("hakki"));
			return gangjwa;
		}
	};
	private RowMapper<SugangTable> sugangTableRowMapper = new RowMapper<SugangTable>() {
		public SugangTable mapRow(ResultSet rs, int rowNum) throws SQLException {
			SugangTable sugangTable = new SugangTable();
			sugangTable.setGangjwaID(rs.getString(CConstants.GANGJWAID));
			sugangTable.setUserID(rs.getString(CConstants.USERID));
			return sugangTable;
		}
	};
	private RowMapper<GangjwaTableDto> gangjwaTableRowMapper = new RowMapper<GangjwaTableDto>() {
		public GangjwaTableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			GangjwaTableDto gangjwaTable = new GangjwaTableDto();
			gangjwaTable.setGangjwaID(rs.getString(CConstants.GANGJWAID));
			gangjwaTable.setMaxStudent(rs.getString(CConstants.MAXSTUDENT));
			gangjwaTable.setGwamokID(rs.getString(CConstants.GWAMOKID));
			gangjwaTable.setGwamokName(rs.getString(CConstants.GWAMOKNAME));
			gangjwaTable.setCurrentStudent(rs.getString("currentstudent"));
			gangjwaTable.setGwamokHakjeom(rs.getString(CConstants.GWAMOKHAKJEOM));
			gangjwaTable.setProfID(rs.getString("profid"));
			gangjwaTable.setProfname(rs.getString("username"));
			gangjwaTable.setHaknyun(rs.getString("haknyun"));
			gangjwaTable.setYear(rs.getString("year"));
			gangjwaTable.setHakki(rs.getString("hakki"));
			return gangjwaTable;
		}
	};
	private RowMapper<GangjwaTableDto> gangjwaScoreTableRowMapper = new RowMapper<GangjwaTableDto>() {
		public GangjwaTableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			GangjwaTableDto gangjwaTable = new GangjwaTableDto();
			gangjwaTable.setGangjwaID(rs.getString(CConstants.GANGJWAID));
			gangjwaTable.setMaxStudent(rs.getString(CConstants.MAXSTUDENT));
			gangjwaTable.setGwamokName(rs.getString(CConstants.GWAMOKNAME));
			gangjwaTable.setGwamokHakjeom(rs.getString(CConstants.GWAMOKHAKJEOM));
			gangjwaTable.setProfname(rs.getString("username"));
			gangjwaTable.setHaknyun(rs.getString("haknyun"));
			gangjwaTable.setYear(rs.getString("year"));
			gangjwaTable.setHakki(rs.getString("hakki"));
			return gangjwaTable;
		}
	};
	private RowMapper<GangjwaScore> gangjwaScoreRowMapper = new RowMapper<GangjwaScore>() {
		public GangjwaScore mapRow(ResultSet rs, int rowNum) throws SQLException {
			GangjwaScore gangjwaTable = new GangjwaScore();
			gangjwaTable.setGangjwaID(rs.getString("gangjwaid"));
			gangjwaTable.setUsername(rs.getString("userid"));
			gangjwaTable.setScore(rs.getString("jeomsu"));
			return gangjwaTable;
		}
	};

}

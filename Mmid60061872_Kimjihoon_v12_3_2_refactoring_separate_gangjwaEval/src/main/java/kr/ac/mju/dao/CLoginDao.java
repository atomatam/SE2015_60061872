package kr.ac.mju.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.ac.mju.constants.CConstants;
import kr.ac.mju.exception.AlreadyExistUserIDException;
import kr.ac.mju.exception.NotCorrectUserIDException;
import kr.ac.mju.model.User;
import kr.ac.mju.model.UserInfo;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class CLoginDao extends JdbcDaoImpl implements ILoginDao {
	//확장 포인트
	@Override
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
		@SuppressWarnings("unused")
		String returnUsername = userFromUserQuery.getUsername();
		if(!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}
		return new User(username, userFromUserQuery.getPassword(), 
				userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities, 
				((User)userFromUserQuery).getUserRealName());
	}
	
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(),
				new String[] { username }, new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String userid = rs.getString("userid");
						String password = rs.getString("userpassword");
						String username = rs.getString("username");
						return new User(userid, password, true, true, true, true,  
								AuthorityUtils.NO_AUTHORITIES, username);
					}
				});
	}

	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(),
				new String[] { username }, new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String roleName = getRolePrefix() + rs.getString(1);
						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

	@Override//groupAuthoritiesByUsernameQuery라는 것이 기본 쿼리문임.
	protected List<GrantedAuthority> loadGroupAuthorities(String username) {
		return getJdbcTemplate().query("select authorityid from groups, groups_authority, "
				+ "groups_member where groups.groupid=groups_authority.groupid and groups.groupid"
				+ " = groups_member.groupid and userid=?",
				new String[] { username }, new RowMapper<GrantedAuthority>() {
			public GrantedAuthority mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				String roleName = getRolePrefix() + rs.getString(1);
				return new SimpleGrantedAuthority(roleName);
			}
		});
	}
	
	private RowMapper<User> userRowMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			String username = rs.getString(CConstants.USERID);
			String userPassword= rs.getString(CConstants.USERPASSWORD);
			String userRealName = rs.getString(CConstants.USERNAME);
			return new User(username, userPassword, true, false, false, false, null, userRealName);                
		}
	};	
	private RowMapper<UserInfo> userInfoRowMapper = new RowMapper<UserInfo>() {
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			String username = rs.getString(CConstants.USERID);
			String userRealName = rs.getString(CConstants.USERNAME);
			return new UserInfo(username, userRealName);                
		}
	};
	
	public List<User> getAllUser() {
		return getJdbcTemplate().query(CConstants.GETALLUSER, userRowMapper);
	}
	@Override
	public List<UserInfo> getAllUserInfo() {
		return getJdbcTemplate().query("select userid, username from user", userInfoRowMapper);
	}
	
	public User getUserByUserID(UserInfo userInfor)
			throws NotCorrectUserIDException, AlreadyExistUserIDException {
		try {
			String userId = userInfor.getUsername();
			return (User) loadUserByUsername(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new NotCorrectUserIDException();
		} catch(DuplicateKeyException e) {
			throw new AlreadyExistUserIDException();
		}
	}
	
	@Override
	public UserInfo getUserInfoByUserID(UserInfo userInfo) {
		return getJdbcTemplate().queryForObject("select userid, username from user where userid=?", 
				new Object[] { userInfo.getUsername() }, userInfoRowMapper);
	}

	public void userEnroll(UserInfo user) throws AlreadyExistUserIDException {
		getJdbcTemplate().update(CConstants.INSERTUSER, user.getUsername(),
				user.getPassword(), user.getUserRealName());
		getJdbcTemplate().update("insert into groups_member values(?, ?)",user.getUserCode() ,user.getUsername());
		Object authorityName = getJdbcTemplate().queryForObject(
				"select authorityid from authority where authority_code=?",
				new Object[] { user.getUserCode() }, String.class);
		getJdbcTemplate().update("insert into user_authority values(? ,?)", user.getUsername(), (String)authorityName);
	}

	public boolean isExistUserID(String userId) 
			throws AlreadyExistUserIDException {
		try {
			getUserByUserID(changeUserIdToUserInfor(userId));
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (NotCorrectUserIDException e) {
			return false;
		} catch(UsernameNotFoundException e) { 
			return false;
		}
		return true;
	}

	private UserInfo changeUserIdToUserInfor(String userId) {
		UserInfo userInfor = new UserInfo();
		userInfor.setUsername(userId);
		return userInfor;
	}

	public void deleteUser(User user) {
		getJdbcTemplate().update(CConstants.DELETEUSERBYID, user.getUsername());
	}

}

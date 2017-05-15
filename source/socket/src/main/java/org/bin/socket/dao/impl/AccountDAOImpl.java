package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.AccountDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.Users;
import org.bin.socket.enums.UserStatus;
import org.springframework.stereotype.Repository;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Repository("accountDAO")
public class AccountDAOImpl extends BaseDAO implements AccountDAO{

	private final static String ADD_USERS = "addUsers"; 
	
	private final static String UPDATE_USERS = "updateUsers"; 

	private final static String GET_USERS = "getUsers"; 
	
	private final static String GET_USERS_LIST = "getUsersList";
	
	private final static String UPDATE_STATUS_LASTLOGIN_TIME = "updateStatusAndLastLoginTime";
	
	private final static String UPDATE_PASSWORD = "updatePassword";
	private final static String UPDATE_ACTIVE = "updateAccountActive";

	public List<Users> findUsersLocal(){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForList(GET_USERS_LIST,filters);
	}
	
	public Page<Users> findUsersByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_USERS_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addUsersLocal(Users users) {
		myBatisDAO.insert(ADD_USERS, users);
		return users.getId();
	}

    public Users findUsersByIdLocal(long id) {
     	return (Users)myBatisDAO.findForObject(GET_USERS, id);
    }
    
    public int updateUsersLocal(Users users) {
    	return myBatisDAO.update(UPDATE_USERS, users) ; 
    }

	@Override
	public Users findUsersByAccountLocal(String account) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("account", account);
		return (Users)myBatisDAO.findForObject(GET_USERS_LIST,filters);
	}

	@Override
	public int updateStatusAndLastLoginTime(String account, UserStatus status) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("account", account);
		filters.put("status", status);
		return myBatisDAO.update(UPDATE_STATUS_LASTLOGIN_TIME, filters);
	}

	@Override
	public int updatePwd(String account, String newPassword) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("password", newPassword);
		filters.put("account", account);
		return myBatisDAO.update(UPDATE_PASSWORD, filters);
	}

	@Override
	public int updateActive(String account) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("account", account);
		return myBatisDAO.update(UPDATE_ACTIVE, filters);
	}

}

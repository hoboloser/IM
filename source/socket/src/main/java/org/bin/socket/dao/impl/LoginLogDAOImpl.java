package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.LoginLogDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.dao.MyBatisDAO;
import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("loginLogDAO")
public class LoginLogDAOImpl extends BaseDAO implements LoginLogDAO {

	private final static String ADD_LOGINLOG = "addLoginLog"; 
	
	private final static String UPDATE_LOGINLOG = "updateLoginLog"; 

	private final static String GET_LOGINLOG = "getLoginLog"; 

	private final static String GET_LOGINLOG_LIST = "getLoginLogList";
	
	public List<LoginLog> findLoginLogLocal(){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForList(GET_LOGINLOG_LIST,filters);
	}
	
	public Page<LoginLog> findLoginLogByPageLocal(String account,int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("account", account);
		return myBatisDAO.findForPage(GET_LOGINLOG_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addLoginLogLocal(LoginLog loginLog) {
		myBatisDAO.insert(ADD_LOGINLOG, loginLog);
		return loginLog.getId();
	}

    public LoginLog findLoginLogByIdLocal(long id) {
     	return (LoginLog)myBatisDAO.findForObject(GET_LOGINLOG, id);
    }
    
    public void updateLoginLogLocal(LoginLog loginLog) {
    	myBatisDAO.update(UPDATE_LOGINLOG, loginLog) ; 
    }
    
}

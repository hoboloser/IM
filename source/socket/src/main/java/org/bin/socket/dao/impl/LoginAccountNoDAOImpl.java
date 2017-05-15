package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.LoginAccountNoDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.LoginAccountNo;
import org.springframework.stereotype.Repository;

import com.zhicall.care.mybatis.dao.MyBatisDAO;
import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Repository("loginAccountNoDAO")
public class LoginAccountNoDAOImpl extends BaseDAO implements LoginAccountNoDAO {

	private final static String ADD_LOGINACCOUNTNO = "addLoginAccountNo"; 
	
	private final static String UPDATE_LOGINACCOUNTNO = "updateLoginAccountNo"; 

	private final static String GET_LOGINACCOUNTNO = "getLoginAccountNo"; 

	private final static String GET_LOGINACCOUNTNO_LIST = "getLoginAccountNoList";
	
	public List<LoginAccountNo> findLoginAccountNoLocal(){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForList(GET_LOGINACCOUNTNO_LIST,filters);
	}
	
	public Page<LoginAccountNo> findLoginAccountNoByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_LOGINACCOUNTNO_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addLoginAccountNoLocal(LoginAccountNo loginAccountNo) {
		myBatisDAO.insert(ADD_LOGINACCOUNTNO, loginAccountNo);
		return loginAccountNo.getId();
	}

    public LoginAccountNo findLoginAccountNoByIdLocal(long id) {
     	return (LoginAccountNo)myBatisDAO.findForObject(GET_LOGINACCOUNTNO, id);
    }
    
    public void updateLoginAccountNoLocal(LoginAccountNo loginAccountNo) {
    	myBatisDAO.update(UPDATE_LOGINACCOUNTNO, loginAccountNo) ; 
    }
         
}

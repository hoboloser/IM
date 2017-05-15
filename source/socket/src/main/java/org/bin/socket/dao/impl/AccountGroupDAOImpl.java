package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.AccountGroupDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.AccountGroup;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("accountGroupDAO")
public class AccountGroupDAOImpl extends BaseDAO implements AccountGroupDAO {

	private final static String ADD_ACCOUNTGROUP = "addAccountGroup"; 
	
	private final static String UPDATE_ACCOUNTGROUP = "updateAccountGroup"; 

	private final static String GET_ACCOUNTGROUP = "getAccountGroup"; 

	private final static String GET_ACCOUNTGROUP_LIST = "getAccountGroupList";
	
	public List<AccountGroup> findAccountGroupLocal(String account){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("account", account);
		return myBatisDAO.findForList(GET_ACCOUNTGROUP_LIST,filters);
	}
	
	public Page<AccountGroup> findAccountGroupByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_ACCOUNTGROUP_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addAccountGroupLocal(AccountGroup accountGroup) {
		myBatisDAO.insert(ADD_ACCOUNTGROUP, accountGroup);
		return accountGroup.getId();
	}

    public AccountGroup findAccountGroupByIdLocal(long id) {
     	return (AccountGroup)myBatisDAO.findForObject(GET_ACCOUNTGROUP, id);
    }
    
    public void updateAccountGroupLocal(AccountGroup accountGroup) {
    	myBatisDAO.update(UPDATE_ACCOUNTGROUP, accountGroup) ; 
    }
    
}

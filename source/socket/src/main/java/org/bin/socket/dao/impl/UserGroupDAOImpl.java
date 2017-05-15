package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.UserGroupDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.UserGroup;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("userGroupDAO")
public class UserGroupDAOImpl extends BaseDAO implements UserGroupDAO {

	private final static String ADD_USERGROUP = "addUserGroup"; 
	
	private final static String UPDATE_USERGROUP = "updateUserGroup"; 

	private final static String GET_USERGROUP = "getUserGroupById";

	private final static String GET_USERGROUP_LIST = "getUserGroupList";
	
	public List<UserGroup> findUserGroupLocal(String name,String account){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("groupName", name);
		filters.put("account", account);
		return myBatisDAO.findForList(GET_USERGROUP_LIST,filters);
	}
	
	public Page<UserGroup> findUserGroupByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_USERGROUP_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addUserGroupLocal(UserGroup userGroup) {
		myBatisDAO.insert(ADD_USERGROUP, userGroup);
		return userGroup.getId();
	}

    public UserGroup findUserGroupByIdLocal(long id) {
     	return (UserGroup)myBatisDAO.findForObject(GET_USERGROUP, id);
    }
    
    public void updateUserGroupLocal(UserGroup userGroup) {
    	myBatisDAO.update(UPDATE_USERGROUP, userGroup) ; 
    }
}

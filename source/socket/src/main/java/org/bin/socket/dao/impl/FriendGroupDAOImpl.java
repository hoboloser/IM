package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.FriendGroupDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.FriendGroup;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("friendGroupDAO")
public class FriendGroupDAOImpl extends BaseDAO implements FriendGroupDAO {

	private final static String ADD_FRIENDGROUP = "addFriendGroup"; 
	
	private final static String UPDATE_FRIENDGROUP = "updateFriendGroup"; 

	private final static String GET_FRIENDGROUP = "getFriendGroup"; 

	private final static String GET_FRIENDGROUP_LIST = "getFriendGroupList";
	
	public List<FriendGroup> findFriendGroupLocal(String name,String account){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("fgroupName", name);
		filters.put("account", account);
		return myBatisDAO.findForList(GET_FRIENDGROUP_LIST,filters);
	}
	
	public Page<FriendGroup> findFriendGroupByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_FRIENDGROUP_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addFriendGroupLocal(FriendGroup friendGroup) {
		myBatisDAO.insert(ADD_FRIENDGROUP, friendGroup);
		return friendGroup.getId();
	}

    public FriendGroup findFriendGroupByIdLocal(long id) {
     	return (FriendGroup)myBatisDAO.findForObject(GET_FRIENDGROUP, id);
    }
    
    public void updateFriendGroupLocal(FriendGroup friendGroup) {
    	myBatisDAO.update(UPDATE_FRIENDGROUP, friendGroup) ; 
    }

	@Override
	public FriendGroup findFriendGroupByGroupIdLocal(String groupId) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("fgroupId", groupId);
		return (FriendGroup)myBatisDAO.findForObject(GET_FRIENDGROUP_LIST,filters);
	}
    
}

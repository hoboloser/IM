package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.FriendGroupMemberDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.FriendGroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.dao.MyBatisDAO;
import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("friendGroupMemberDAO")
public class FriendGroupMemberDAOImpl extends BaseDAO implements FriendGroupMemberDAO {

	private final static String ADD_FRIENDGROUPMEMBER = "addFriendGroupMember"; 
	
	private final static String UPDATE_FRIENDGROUPMEMBER = "updateFriendGroupMember"; 

	private final static String GET_FRIENDGROUPMEMBER = "getFriendGroupMember"; 

	private final static String GET_FRIENDGROUPMEMBER_LIST = "getFriendGroupMemberList";
	
	public List<FriendGroupMember> findFriendGroupMemberLocal(String groupAccount){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("fgroupId", groupAccount);
		return myBatisDAO.findForList(GET_FRIENDGROUPMEMBER_LIST,filters);
	}
	
	public Page<FriendGroupMember> findFriendGroupMemberByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_FRIENDGROUPMEMBER_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addFriendGroupMemberLocal(FriendGroupMember friendGroupMember) {
		myBatisDAO.insert(ADD_FRIENDGROUPMEMBER, friendGroupMember);
		return friendGroupMember.getId();
	}

    public FriendGroupMember findFriendGroupMemberByIdLocal(long id) {
     	return (FriendGroupMember)myBatisDAO.findForObject(GET_FRIENDGROUPMEMBER, id);
    }
    
    public void updateFriendGroupMemberLocal(FriendGroupMember friendGroupMember) {
    	myBatisDAO.update(UPDATE_FRIENDGROUPMEMBER, friendGroupMember) ; 
    }
    
}

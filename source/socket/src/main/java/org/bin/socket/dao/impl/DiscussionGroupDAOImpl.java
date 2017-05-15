package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.DiscussionGroupDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.DiscussionGroup;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("discussionGroupDAO")
public class DiscussionGroupDAOImpl extends BaseDAO implements DiscussionGroupDAO {

	private final static String ADD_DISCUSSIONGROUP = "addDiscussionGroup"; 
	
	private final static String UPDATE_DISCUSSIONGROUP = "updateDiscussionGroup"; 

	private final static String GET_DISCUSSIONGROUP = "getDiscussionGroup"; 

	private final static String GET_DISCUSSIONGROUP_LIST = "getDiscussionGroupList";
	
	public List<DiscussionGroup> findDiscussionGroupLocal(String name,String account){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("dgroupName", name);
		filters.put("account", account);
		return myBatisDAO.findForList(GET_DISCUSSIONGROUP_LIST,filters);
	}
	
	public Page<DiscussionGroup> findDiscussionGroupByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_DISCUSSIONGROUP_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addDiscussionGroupLocal(DiscussionGroup discussionGroup) {
		myBatisDAO.insert(ADD_DISCUSSIONGROUP, discussionGroup);
		return discussionGroup.getId();
	}

    public DiscussionGroup findDiscussionGroupByIdLocal(long id) {
     	return (DiscussionGroup)myBatisDAO.findForObject(GET_DISCUSSIONGROUP, id);
    }
    
    public void updateDiscussionGroupLocal(DiscussionGroup discussionGroup) {
    	myBatisDAO.update(UPDATE_DISCUSSIONGROUP, discussionGroup) ; 
    }
    
}

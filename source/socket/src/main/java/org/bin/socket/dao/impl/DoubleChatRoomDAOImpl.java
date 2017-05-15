package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.DoubleChatRoomDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.DoubleChatRoom;
import org.springframework.stereotype.Repository;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Repository("doubleChatRoomDAO")
public class DoubleChatRoomDAOImpl extends BaseDAO implements DoubleChatRoomDAO{

	private final static String ADD_DOUBLECHATROOM = "addDoubleChatRoom";
	
	private final static String UPDATE_DOUBLECHATROOM = "updateDoubleChatRoom";

	private final static String GET_DOUBLECHATROOM = "getDoubleChatRoom"; 

	private final static String GET_DOUBLECHATROOM_LIST = "getDoubleChatRoomList";
	
	public List<DoubleChatRoom> findDoubleChatRoomLocal(String groupId,String type){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("groupId", groupId);
		filters.put("type", type);
		return myBatisDAO.findForList(GET_DOUBLECHATROOM_LIST,filters);
	}
	
	public Page<DoubleChatRoom> findDoubleChatRoomByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_DOUBLECHATROOM_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addDoubleChatRoomLocal(DoubleChatRoom doubleChatRoom) {
		myBatisDAO.insert(ADD_DOUBLECHATROOM, doubleChatRoom);
		return doubleChatRoom.getId();
	}

    public DoubleChatRoom findDoubleChatRoomByIdLocal(long id) {
     	return (DoubleChatRoom)myBatisDAO.findForObject(GET_DOUBLECHATROOM, id);
    }
    
    public void updateDoubleChatRoomLocal(DoubleChatRoom doubleChatRoom) {
    	myBatisDAO.update(UPDATE_DOUBLECHATROOM, doubleChatRoom) ; 
    }
}

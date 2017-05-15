package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.ChatRoomDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.ChatRoom;
import org.springframework.stereotype.Repository;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Repository("chatRoomDAO")
public class ChatRoomDAOImpl extends BaseDAO implements ChatRoomDAO{
	private final static String ADD_CHATROOM = "addChatRoom"; 
	
	private final static String UPDATE_CHATROOM = "updateChatRoom"; 

	private final static String GET_CHATROOM = "getChatRoomById";

	private final static String GET_CHATROOM_LIST = "getChatRoomList";
	

	public List<ChatRoom> findChatRoomLocal(String to,String from){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("to", to);
		filters.put("from", from);
		return myBatisDAO.findForList(GET_CHATROOM_LIST,filters);
	}
	
	public Page<ChatRoom> findChatRoomByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_CHATROOM_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addChatRoomLocal(ChatRoom chatRoom) {
		myBatisDAO.insert(ADD_CHATROOM, chatRoom);
		return chatRoom.getId();
	}

    public ChatRoom findChatRoomByIdLocal(long id) {
     	return (ChatRoom)myBatisDAO.findForObject(GET_CHATROOM, id);
    }
    
    public void updateChatRoomLocal(ChatRoom chatRoom) {
    	myBatisDAO.update(UPDATE_CHATROOM, chatRoom) ; 
    }

}

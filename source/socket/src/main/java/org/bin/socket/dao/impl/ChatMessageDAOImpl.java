package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.ChatMessageDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.ChatMessage;
import org.bin.socket.enums.ChatType;
import org.bin.socket.enums.ReadType;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("chatMessageDAO")
public class ChatMessageDAOImpl extends BaseDAO implements ChatMessageDAO {

	private final static String ADD_CHATMESSAGE = "addChatMessage"; 
	
	private final static String UPDATE_CHATMESSAGE = "updateChatMessage"; 
	
	private final static String UPDATE_CHATMESSAGE_UNREAD = "updateUnReadMsg"; 

	private final static String GET_CHATMESSAGE = "getChatMessage"; 

	private final static String GET_CHATMESSAGE_LIST = "getChatMessageList";
	
	public List<ChatMessage> findChatMessageLocal(String from,String to,Long roomId,ChatType type,ReadType isRead){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("from", from);
		filters.put("to", to);
		filters.put("roomId", roomId);
		filters.put("type", type);
		filters.put("isRead", isRead);
		return myBatisDAO.findForList(GET_CHATMESSAGE_LIST,filters);
	}
	
	public Page<ChatMessage> findChatMessageByPageLocal(String from,String to,Long roomId,ChatType type,int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("from", from);
		filters.put("to", to);
		filters.put("roomId", roomId);
		filters.put("type", type);
		return myBatisDAO.findForPage(GET_CHATMESSAGE_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addChatMessageLocal(ChatMessage chatMessage) {
		myBatisDAO.insert(ADD_CHATMESSAGE, chatMessage);
		return chatMessage.getId();
	}

    public ChatMessage findChatMessageByIdLocal(long id) {
     	return (ChatMessage)myBatisDAO.findForObject(GET_CHATMESSAGE, id);
    }
    
    public void updateChatMessageLocal(ChatMessage chatMessage) {
    	myBatisDAO.update(UPDATE_CHATMESSAGE, chatMessage) ; 
    }

	@Override
	public void updateUnReadMessage(String from, String to, ReadType isRead) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("from", from);
		filters.put("to", to);
		filters.put("isRead", isRead);
		myBatisDAO.update(UPDATE_CHATMESSAGE_UNREAD, filters);
	}
    
}

package org.bin.socket.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.entity.ChatMessage;
import org.bin.socket.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhicall.care.mybatis.dao.MyBatisDAO;
import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {

	private final static String ADD_CHATMESSAGE = "addChatMessage"; // ����
	
	private final static String UPDATE_CHATMESSAGE = "updateChatMessage"; // ����

	private final static String GET_CHATMESSAGE = "getChatMessage"; // ��������

	private final static String GET_CHATMESSAGE_LIST = "getChatMessageList";// �б����
	
	@Autowired
 	private MyBatisDAO myBatisDAO;
	
	/****************** ���ؽӿ� **************************/
	public List<ChatMessage> findChatMessageLocal(){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForList(GET_CHATMESSAGE_LIST,filters);
	}
	
	public Page<ChatMessage> findChatMessageByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
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
    
}

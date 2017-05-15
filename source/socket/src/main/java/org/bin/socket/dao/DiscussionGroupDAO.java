package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.DiscussionGroup;

import com.zhicall.care.mybatis.page.Page;

public interface DiscussionGroupDAO {

	public List<DiscussionGroup> findDiscussionGroupLocal(String name,String account);
	
	public Page<DiscussionGroup> findDiscussionGroupByPageLocal(int pageNum,int pageSize);
	
	public Long addDiscussionGroupLocal(DiscussionGroup discussionGroup); 

    public DiscussionGroup findDiscussionGroupByIdLocal(long id);
    
    public void updateDiscussionGroupLocal(DiscussionGroup discussionGroup);
    
}

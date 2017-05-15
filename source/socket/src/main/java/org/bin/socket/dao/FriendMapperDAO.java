package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.FriendMapper;

import com.zhicall.care.mybatis.page.Page;

public interface FriendMapperDAO {

	public List<FriendMapper> findFriendMapperLocal(String account,String applyAccount);
	
	public Page<FriendMapper> findFriendMapperByPageLocal(int pageNum,int pageSize);
	
	public Long addFriendMapperLocal(FriendMapper friendMapper); 

    public FriendMapper findFriendMapperByIdLocal(long id);
    
    public void updateFriendMapperLocal(FriendMapper friendMapper);
    
    public int deleteFrientMapperById(long id);
    
}

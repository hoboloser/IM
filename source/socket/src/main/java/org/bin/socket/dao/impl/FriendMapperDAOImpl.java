package org.bin.socket.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bin.socket.dao.FriendMapperDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.entity.FriendMapper;
import org.bin.socket.enums.ValidFlag;
import org.springframework.stereotype.Repository;

import com.zhicall.care.cache.service.DataPopulateService;
import com.zhicall.care.cache.service.ZHPDataCacheService;
import com.zhicall.care.mybatis.dao.MyBatisDAO;
import com.zhicall.care.mybatis.page.Page;
import com.zhicall.care.mybatis.page.PageRequest;

@Repository("friendMapperDAO")
public class FriendMapperDAOImpl extends BaseDAO implements FriendMapperDAO {

	private final static String ADD_FRIENDMAPPER = "addFriendMapper"; 
	
	private final static String UPDATE_FRIENDMAPPER = "updateFriendMapper"; 

	private final static String GET_FRIENDMAPPER = "getFriendMapper";
	private final static String DELETE_FRIENDMAPPER = "deleteFriendMapperById";

	private final static String GET_FRIENDMAPPER_LIST = "getFriendMapperList";
	

	public List<FriendMapper> findFriendMapperLocal(String account,String applyAccount){
		Map<String, Object> filters = new HashMap<String, Object>() ;
		filters.put("account", account);
		filters.put("validFlag", ValidFlag.ENABLE);
		filters.put("friendAccount", applyAccount);
		return myBatisDAO.findForList(GET_FRIENDMAPPER_LIST,filters);
	}
	
	public Page<FriendMapper> findFriendMapperByPageLocal(int pageNum,int pageSize) {
		Map<String, Object> filters = new HashMap<String, Object>() ;
		return myBatisDAO.findForPage(GET_FRIENDMAPPER_LIST, new PageRequest(pageNum, pageSize, filters));
	}
	
	public Long addFriendMapperLocal(FriendMapper friendMapper) {
		myBatisDAO.insert(ADD_FRIENDMAPPER, friendMapper);
		return friendMapper.getId();
	}

    public FriendMapper findFriendMapperByIdLocal(long id) {
     	return (FriendMapper)myBatisDAO.findForObject(GET_FRIENDMAPPER, id);
    }
    
    public void updateFriendMapperLocal(FriendMapper friendMapper) {
    	myBatisDAO.update(UPDATE_FRIENDMAPPER, friendMapper) ; 
    }

	@Override
	public int deleteFrientMapperById(long id) {
		return myBatisDAO.delete(DELETE_FRIENDMAPPER, id);
	}
    
}

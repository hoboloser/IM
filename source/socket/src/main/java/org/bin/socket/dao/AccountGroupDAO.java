package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.AccountGroup;

import com.zhicall.care.mybatis.page.Page;

public interface AccountGroupDAO {

	public List<AccountGroup> findAccountGroupLocal(String account);
	
	public Page<AccountGroup> findAccountGroupByPageLocal(int pageNum,int pageSize);
	
	public Long addAccountGroupLocal(AccountGroup accountGroup); 

    public AccountGroup findAccountGroupByIdLocal(long id);
    
    public void updateAccountGroupLocal(AccountGroup accountGroup);
    
}

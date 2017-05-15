package org.bin.socket.dao;

import java.util.List;

import org.bin.socket.entity.LoginAccountNo;

import com.zhicall.care.mybatis.page.Page;

public interface LoginAccountNoDAO {

	public List<LoginAccountNo> findLoginAccountNoLocal();
	
	public Page<LoginAccountNo> findLoginAccountNoByPageLocal(int pageNum,int pageSize);
	
	public Long addLoginAccountNoLocal(LoginAccountNo loginAccountNo); 

    public LoginAccountNo findLoginAccountNoByIdLocal(long id);
    
    public void updateLoginAccountNoLocal(LoginAccountNo loginAccountNo);
    
}

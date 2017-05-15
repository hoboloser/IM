package org.bin.socket.dao.impl;

import org.bin.socket.dao.HelloDAO;
import org.bin.socket.dao.base.BaseDAO;
import org.bin.socket.dao.base.SqlMapperID;
import org.bin.socket.entity.HelloMan;
import org.springframework.stereotype.Repository;

@Repository("helloDAO")
public class HelloDAOImpl extends BaseDAO<HelloMan> implements HelloDAO {

	@Override
	public void insert(HelloMan helloMan) {
		super.insert(helloMan, SqlMapperID.HelloMapperID.class);
	}

}

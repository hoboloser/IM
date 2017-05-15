package org.bin.socket.dao.base;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhicall.care.mybatis.dao.MyBatisDAO;
@SuppressWarnings("rawtypes")
public abstract class BaseDAO<T> {
	
	@Autowired
	public MyBatisDAO myBatisDAO;
	
	public int insert(T t,Class clazz) {
		return myBatisDAO.insert(getSqlMapper(ActionEnum.INSERT,clazz), t);
	}
	
	public int update(T t,Class clazz) {
		return myBatisDAO.update(getSqlMapper(ActionEnum.UPDATE,clazz), t);
	}
	
	public int reduce(Long id,Class clazz) {
		return myBatisDAO.update(getSqlMapper(ActionEnum.UPDATEREDUCE,clazz), id);
	}
	
	public int delete(T t,Class clazz) {
		if(null == t){
			myBatisDAO.delete(getSqlMapper(ActionEnum.DELETE,clazz));
		}
		return myBatisDAO.delete(getSqlMapper(ActionEnum.DELETE,clazz), t);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> queryAllForList(Class clazz) {
		
		return myBatisDAO.findForList(getSqlMapper(ActionEnum.QUERYALL,clazz));
	}
	
	@SuppressWarnings("unchecked")
	public T queryById(Long id,Class clazz){
		if(null == id){
			return (T)myBatisDAO.findForObject(getSqlMapper(ActionEnum.QUERYBYID,clazz));
		}
		return (T)myBatisDAO.findForObject(getSqlMapper(ActionEnum.QUERYBYID,clazz), id);
	}
	
	private String getSqlMapper(ActionEnum aenum,Class clazz){
		Field field;
		String sqlMapper = null;
		try {
			field = clazz.getField(aenum.getName());
			
			sqlMapper = (String)field.get(clazz);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return sqlMapper;
	}
}

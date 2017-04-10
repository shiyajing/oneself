package com.oneself.cloud.provider.dbcroe.base;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.oneself.cloud.provider.dbcroe.exception.ServiceException;

public interface IDefaultService {
	
	public final static String BEAN_ID = "defaultService";

	/**
	 * 向数据库插入一条数据
	 * 
	 * @param s
	 *            mybatis中的namespace。id
	 * @param aobj
	 *            传入的参数
	 * @return 成功结果
	 * @throws ServiceException
	 */
	public int insert(String s, Object aobj) throws ServiceException;

	/**
	 * 从数据库中删除记录
	 * 
	 * @param s
	 *            mybatis中的namespace。id
	 * @param aobj
	 *            传入的参数
	 * @return 删除条数
	 * @throws ServiceException
	 */
	public int delete(String s, Object aobj) throws ServiceException;

	/**
	 * 更新数据库中记录
	 * 
	 * @param s
	 *            mybatis中的namespace。id
	 * @param aobj
	 *            传入的参数
	 * @return 成功条数
	 * @throws ServiceException
	 */
	public int update(String s, Object aobj) throws ServiceException;

	/**
	 * 从数据库中查询记录
	 * 
	 * @param s
	 *            mybatis中的namespace。id
	 * @return 符合查询条件的结果集
	 * @throws ServiceException
	 */
	public <E> List<E> selectList(String s) throws ServiceException;

	/**
	 * 从数据库中查询记录
	 * 
	 * @param s
	 *            mybatis中的namespace。id
	 * @param obj
	 *            传入的参数
	 * @return 符合查询条件的结果集
	 * @throws ServiceException
	 */
	public <E> List<E> selectList(String s, Object obj) throws ServiceException;

	/**
	 * 从数据库中查询记录
	 * 
	 * @param s
	 *            mybatis中的namespace。id
	 * @param obj
	 *            传入的参数
	 * @param offset
	 *            分页起始条数
	 * @param limit
	 *            查询记录数
	 * @return 符合查询条件的结果集
	 * @throws ServiceException
	 */
	public <E> List<E> selectList(String s, Object obj, int offset, int limit) throws ServiceException;

	/**
	 * 从数据库中查询唯一记录
	 * 
	 * @param statementName
	 *            mybatis中的namespace。id
	 * @param param
	 *            传入参数
	 * @return 符合查询条件的结果
	 * @throws ServiceException
	 */
	public <T> T selectOne(String statementName, Object param) throws ServiceException;

	/**
	 * 获得可以操作mybatis的session
	 * 
	 * @param isBatch
	 *            是否批处理
	 * @return SqlSession
	 */
	public SqlSession getSqlSession(boolean isBatch);

	/**
	 * 常用的查询类方法
	 * 
	 * @param queryStatement
	 *            查询语句
	 * @param countStatement
	 *            统计记录条数语句
	 * @param param
	 *            传入参数
	 * @param offset
	 *            分页起始数
	 * @param limit
	 *            查询最大条数
	 * @return Object[]，第一项为结果集，第二项为记录数
	 * @throws ServiceException
	 */
	Object[] getCommonPaged(String queryStatement, String countStatement, Object param, int offset, int limit)
			throws ServiceException;

}

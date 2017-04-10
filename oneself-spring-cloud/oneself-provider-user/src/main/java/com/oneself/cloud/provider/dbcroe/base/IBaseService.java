package com.oneself.cloud.provider.dbcroe.base;

import java.util.List;
import java.util.Map;

import com.oneself.cloud.provider.dbcroe.exception.ServiceException;

public interface IBaseService<T> {

	public final static String BEAN_ID = "baseSerivce";

	/**
	 * 新增,只支持当前对应的VO
	 * 
	 * @param entity
	 *            实体类
	 * @return
	 * @throws ServiceException
	 */
	public Integer insert(T entity) throws ServiceException;

	/**
	 * 新增,只支持当前对应的VO,也可以自传当前的SQLMAP对应的ID，也能支持完整路径
	 * 
	 * @param entity
	 * @param sqlMapId
	 * @return
	 * @throws ServiceException
	 */
	public Integer insert(T entity, String sqlMapId) throws ServiceException;

	/**
	 * 新增,支持map参数传入，自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param map
	 * @param sqlMapId
	 * @return
	 * @throws ServiceException
	 */
	public Integer insert(Map<String, Object> map, String sqlMapId) throws ServiceException;

	/**
	 * 删除,只支持当前对应的VO,也可以自传当前的SQLMAP对应的ID，也能支持完整路径
	 * 
	 * @param entity
	 *            实体类
	 * @return
	 * @throws ServiceException
	 */
	public Integer delete(T entity) throws ServiceException;

	/**
	 * 删除,只支持当前对应的VO,也可以自传当前的SQLMAP对应的ID，也能支持完整路径
	 * 
	 * @param entity
	 * @param sqlMapId
	 * @return
	 * @throws ServiceException
	 */
	public Integer delete(T entity, String sqlMapId) throws ServiceException;

	/**
	 * 删除,支持map参数传入，自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param map
	 * @param sqlMapId
	 * @return
	 * @throws ServiceException
	 */
	public Integer delete(Map<String, Object> map, String sqlMapId) throws ServiceException;

	/**
	 * 修改,只支持当前对应的VO
	 * 
	 * @param entity
	 *            实体类
	 * @return
	 * @throws ServiceException
	 */
	public Integer update(T entity) throws ServiceException;

	/**
	 * 修改,只支持当前对应的VO,也可以自传当前的SQLMAP对应的ID，也能支持完整路径
	 * 
	 * @param entity
	 * @param sqlMapId
	 * @return
	 * @throws ServiceException
	 */
	public Integer update(T entity, String sqlMapId) throws ServiceException;

	/**
	 * 修改,支持map参数传入，自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param map
	 * @param sqlMapId
	 * @return
	 * @throws ServiceException
	 */
	public Integer update(Map<String, Object> map, String sqlMapId) throws ServiceException;
	
	/**
	 * 查询,只支持当前对应的VO
	 * @param entity
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public T getOne(Class<T> entity, Map<String, Object> map) throws ServiceException;
	
	/**
	 * 查询,只支持当前对应的VO,key与value相对应
	 * @param entity
	 * @param key
	 * @param value
	 * @return
	 * @throws ServiceException
	 */
	public T getOne(Class<T> entity,String key,Object value) throws ServiceException;
	
	/**
	 * 查询,只支持当前对应的VO,key与Value用逗号隔开
	 * @param entity
	 * @param key
	 * @param value
	 * @return
	 * @throws ServiceException
	 */
	public T getOne(Class<T> entity,String[] keyValue) throws ServiceException;
	
	/**
	 * 查询,只支持当前对应的VO
	 * 
	 * @param entity
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public List<T> find(Class<T> entity, Map<String, Object> map) throws ServiceException;

	/**
	 * 查询,针对当前的VO,自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param entity
	 * @param sqlMapId
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public List<T> find(Class<T> entity, String sqlMapId, Map<String, Object> map) throws ServiceException;

	/**
	 * 查询,自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param sqlMapId
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public List<?> find(String sqlMapId, Map<String, Object> map) throws ServiceException;

	/**
	 * 总记录个数,只支持当前对应的VO
	 * 
	 * @param entity
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public Integer count(Class<T> entity, Map<String, Object> map) throws ServiceException;

	/**
	 * 总记录个数,针对当前的VO,自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param entity
	 * @param sqlMapId
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public Integer count(Class<T> entity, String sqlMapId, Map<String, Object> map) throws ServiceException;

	/**
	 * 总记录个数,自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param sqlMapId
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public Integer count(String sqlMapId, Map<String, Object> map) throws ServiceException;

	/**
	 * 分页查询,针对当前的VO,自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param entity
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public Object[] getPageList(Class<T> entity, Map<String, Object> map) throws ServiceException;

	/**
	 * 分页查询，支持
	 * 
	 * @param entity
	 * @param sqlMapId
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public Object[] getPageList(Class<T> entity, String querySqlMapId, String countSqlMapId, Map<String, Object> map)
			throws ServiceException;

	/**
	 * 分页查询,自定义sqlMapId,完整路径和单个路径
	 * 
	 * @param sqlMapId
	 * @param map
	 * @return
	 * @throws ServiceException
	 */
	public Object[] getPageList(String querySqlMapId, String countSqlMapId, Map<String, Object> map)
			throws ServiceException;
	
	/**
	 * 判断对象是否存在
	 * @param paramClass
	 * @param paramMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean isObjectVo(Class<T> paramClass, Map<String, Object> paramMap) throws ServiceException;
}

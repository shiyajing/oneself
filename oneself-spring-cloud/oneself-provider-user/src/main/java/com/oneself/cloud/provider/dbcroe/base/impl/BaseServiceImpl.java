package com.oneself.cloud.provider.dbcroe.base.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneself.cloud.provider.dbcroe.base.IBaseService;
import com.oneself.cloud.provider.dbcroe.base.IDefaultService;
import com.oneself.cloud.provider.dbcroe.exception.ServiceException;

@Service(IBaseService.BEAN_ID)
@Transactional
public class BaseServiceImpl<T> implements IBaseService<T> {

	private static final String _MOST_START = "com.oneself.cloud.";
	private static final String _INSERT = ".insert";
	private static final String _MODEL = ".model";
	private static final String _VO = "VO";
	private static final String _QUERY = ".query";
	private static final String _UPDATE = ".update";
	private static final String _DELETE = ".delete";
	private static final String _COUNT = ".count";
	private static final String _SPOT = ".";

	@Autowired
	@Qualifier("defaultService")
	private IDefaultService service = null;

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	private Collection<String> getMappedStatementNames() {
		Configuration conf = sqlSessionFactory.getConfiguration();
		Collection<String> maps = conf.getMappedStatementNames();
		return maps;
	}

	private String getNampSpace(String sqlMapId) {
		return nampSpace(getMappedStatementNames(), sqlMapId);
	}

	public String getNampSpace(T entity, String type) {
		String vo = entity.getClass().getName().split(_MODEL)[1].split(_VO)[0];
		return nampSpace(getMappedStatementNames(), vo + type);
	}

	public String getNampSpace(Class<T> entity, String type) {
		String vo = entity.getName().split(_MODEL)[1].split(_VO)[0];
		return nampSpace(getMappedStatementNames(), vo + type);
	}

	private String nampSpace(Collection<String> statementNames, String type) throws ServiceException {
		String nampSpace = "";
		int num = 0;
		for (String statement : statementNames) {
			if (statement.startsWith(_MOST_START) && statement.endsWith(type)) {
				num++;
				nampSpace = statement;
				break;
			}
		}
		if (num == 0 || num > 1) {
			throw new ServiceException("找不到相对应的sqlMapId,请检查sqlmap文件是否存在!!!");
		}
		return nampSpace;
	}

	public Integer insert(T entity) throws ServiceException {
		return service.insert(getNampSpace(entity, _INSERT), entity);
	}

	public Integer insert(T entity, String sqlMapId) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.insert(getNampSpace(sqlMapId), entity);
		} else {
			return service.insert(getNampSpace(entity, _SPOT + sqlMapId), entity);
		}
	}

	public Integer insert(Map<String, Object> map, String sqlMapId) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.insert(getNampSpace(sqlMapId), map);
		} else {
			return service.insert(getNampSpace(_SPOT + sqlMapId), map);
		}
	}

	public Integer delete(T entity) throws ServiceException {
		return service.delete(getNampSpace(entity, _DELETE), entity);
	}

	public Integer delete(T entity, String sqlMapId) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.delete(getNampSpace(sqlMapId), entity);
		} else {
			return service.delete(getNampSpace(entity, _SPOT + sqlMapId), entity);
		}
	}

	public Integer delete(Map<String, Object> map, String sqlMapId) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.delete(getNampSpace(sqlMapId), map);
		} else {
			return service.delete(getNampSpace(_SPOT + sqlMapId), map);
		}
	}

	public Integer update(T entity) throws ServiceException {
		return service.update(getNampSpace(entity, _UPDATE), entity);
	}

	public Integer update(T entity, String sqlMapId) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.update(getNampSpace(sqlMapId), entity);
		} else {
			return service.update(getNampSpace(entity, _SPOT + sqlMapId), entity);
		}
	}

	public Integer update(Map<String, Object> map, String sqlMapId) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.update(getNampSpace(sqlMapId), map);
		} else {
			return service.update(getNampSpace(_SPOT + sqlMapId), map);
		}
	}
	
	@Override
	public T getOne(Class<T> entity, Map<String, Object> map) throws ServiceException {
		// TODO Auto-generated method stub
		return service.selectOne(getNampSpace(entity, _QUERY), map);
	}
	
	@Override
	public T getOne(Class<T> entity, String key, Object value) throws ServiceException {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(key, value);
		return service.selectOne(getNampSpace(entity, _QUERY), map);
	}
	
	@Override
	public T getOne(Class<T> entity, String[] keyValue) throws ServiceException {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		for (int i = 0; i < keyValue.length; i++) {
			String str=keyValue[i];
			String[] nstr=str.split(",");
			map.put(nstr[0], nstr[1]);
		}
		return service.selectOne(getNampSpace(entity, _QUERY), map);
	}

	public List<T> find(Class<T> entity, Map<String, Object> map) throws ServiceException {
		return service.selectList(getNampSpace(entity, _QUERY), map);
	}

	public List<T> find(Class<T> entity, String sqlMapId, Map<String, Object> map) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.selectList(getNampSpace(entity, sqlMapId), map);
		} else {
			return service.selectList(getNampSpace(entity, _SPOT + sqlMapId), map);
		}
	}

	public List<?> find(String sqlMapId, Map<String, Object> map) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.selectList(getNampSpace(sqlMapId), map);
		} else {
			return service.selectList(getNampSpace(_SPOT + sqlMapId), map);
		}
	}

	public Integer count(Class<T> entity, Map<String, Object> map) throws ServiceException {
		return service.selectOne(getNampSpace(entity, _COUNT), map);
	}

	public Integer count(Class<T> entity, String sqlMapId, Map<String, Object> map) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.selectOne(getNampSpace(entity, _COUNT), map);
		} else {
			return service.selectOne(getNampSpace(entity, _SPOT + _COUNT), map);
		}
	}

	public Integer count(String sqlMapId, Map<String, Object> map) throws ServiceException {
		if (sqlMapId.indexOf(_SPOT) != -1) {
			return service.selectOne(getNampSpace(sqlMapId), map);
		} else {
			return service.selectOne(getNampSpace(_SPOT + sqlMapId), map);
		}
	}

	public Object[] getPageList(Class<T> entity, Map<String, Object> map) throws ServiceException {
		return service.getCommonPaged(getNampSpace(entity, _QUERY), getNampSpace(entity, _COUNT), map,
				(Integer) map.get("limit"), (Integer) map.get("offset"));
	}

	public Object[] getPageList(Class<T> entity, String querySqlMapId, String countSqlMapId, Map<String, Object> map)
			throws ServiceException {
		if (querySqlMapId.indexOf(_SPOT) != -1 && countSqlMapId.indexOf(_SPOT) != -1) {
			return service.getCommonPaged(getNampSpace(entity, querySqlMapId), getNampSpace(entity, countSqlMapId), map,
					(Integer) map.get("limit"), (Integer) map.get("offset"));
		} else {
			return service.getCommonPaged(getNampSpace(entity, _SPOT + querySqlMapId),
					getNampSpace(entity, _SPOT + countSqlMapId), map, (Integer) map.get("limit"),
					(Integer) map.get("offset"));
		}
	}

	public Object[] getPageList(String querySqlMapId, String countSqlMapId, Map<String, Object> map)
			throws ServiceException {
		if (querySqlMapId.indexOf(_SPOT) != -1 && countSqlMapId.indexOf(_SPOT) != -1) {
			return service.getCommonPaged(getNampSpace(querySqlMapId), getNampSpace(countSqlMapId), map,
					(Integer) map.get("limit"), (Integer) map.get("offset"));
		} else {
			return service.getCommonPaged(getNampSpace(_SPOT + querySqlMapId), getNampSpace(_SPOT + countSqlMapId), map,
					(Integer) map.get("limit"), (Integer) map.get("offset"));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isObjectVo(Class<T> entity, Map<String, Object> param) throws ServiceException {
		Map map = new HashMap();
		for (Map.Entry entry : param.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		List list = find(entity, map);
		if ((list != null) && (list.size() > 0)) {
			return true;
		}
		return false;
	}
}

package com.oneself.cloud.provider.dbcroe.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.oneself.cloud.provider.dbcroe.base.IDefaultService;
import com.oneself.cloud.provider.dbcroe.exception.ServiceException;

public class DefaultService implements IDefaultService {

	private SqlSession session = null;

	private SqlSession batchSqlSession = null;

	/**
	 * 仅限于select可以使用fallback
	 */
	private IDefaultService fallback = null;

	private List<Class<Throwable>> fallbackExceptions = null;

	public int insert(String statementName, Object param) throws ServiceException {
		try {
			return session.insert(statementName, param);
		} catch (Exception e) {
			throw new ServiceException("插入失败", e);
		}
	}

	public void batchInsert(String statementName, Object param) throws ServiceException {
		try {
			batchSqlSession.insert(statementName, param);
		} catch (Exception e) {
			throw new ServiceException("插入失败", e);
		}
	}

	public int delete(String statementName, Object param) throws ServiceException {
		try {
			return session.delete(statementName, param);
		} catch (Exception e) {
			throw new ServiceException("删除失败", e);
		}
	}

	public void batchDelete(String statementName, Object param) throws ServiceException {
		try {
			batchSqlSession.delete(statementName, param);
		} catch (Exception e) {
			throw new ServiceException("删除失败", e);
		}
	}

	public int update(String statementName, Object param) throws ServiceException {
		try {
			return session.update(statementName, param);
		} catch (Exception e) {
			throw new ServiceException("更新失败", e);
		}
	}

	public void batchUpdate(String statementName, Object param) throws ServiceException {
		try {
			batchSqlSession.update(statementName, param);
		} catch (Exception e) {
			throw new ServiceException("更新失败", e);
		}
	}

	public <E> List<E> selectList(String statementName) throws ServiceException {
		try {
			return session.selectList(statementName);
		} catch (Exception e) {
			if (fallbackExceptions != null) {
				for (Class<Throwable> c : fallbackExceptions) {
					if (c.isAssignableFrom(e.getClass())) {
						return fallback.selectList(statementName);
					}
				}
			}
			throw new ServiceException("查询失败", e);
		}
	}

	public <E> List<E> selectList(String statementName, Object param, int offset, int limit) throws ServiceException {
		try {
			if (limit < 0) {
				return selectList(statementName, param);
			}
			return session.selectList(statementName, param, new RowBounds(offset, limit));

		} catch (Exception e) {
			if (fallbackExceptions != null) {
				for (Class<Throwable> c : fallbackExceptions) {
					if (c.isAssignableFrom(e.getClass())) {
						return fallback.selectList(statementName, param, offset, limit);
					}
				}
			}
			throw new ServiceException("查询失败", e);
		}
	}

	@Override
	public <E> List<E> selectList(String statementName, Object param) throws ServiceException {
		try {
			return session.selectList(statementName, param);
		} catch (Exception e) {
			if (fallbackExceptions != null) {
				for (Class<Throwable> c : fallbackExceptions) {
					if (c.isAssignableFrom(e.getClass())) {
						return fallback.selectList(statementName, param);
					}
				}
			}
			throw new ServiceException("查询失败", e);
		}
	}

	@Override
	public <T> T selectOne(String statementName, Object param) throws ServiceException {
		try {
			return session.selectOne(statementName, param);
		} catch (Exception e) {
			if (fallbackExceptions != null) {
				for (Class<Throwable> c : fallbackExceptions) {
					if (c.isAssignableFrom(e.getClass())) {
						return fallback.selectOne(statementName, param);
					}
				}
			}
			throw new ServiceException("查询失败", e);
		}
	}

	@Override
	public Object[] getCommonPaged(String queryStatement, String countStatement, Object param, int offset, int limit)
			throws ServiceException {
		try {
			Object[] obj = new Object[2];
			obj[0] = this.selectList(queryStatement, param, offset, limit);
			obj[1] = this.selectOne(countStatement, param);
			return obj;
		} catch (Exception e) {
			if (fallbackExceptions != null) {
				for (Class<Throwable> c : fallbackExceptions) {
					if (c.isAssignableFrom(e.getClass())) {
						return fallback.getCommonPaged(queryStatement, countStatement, param, offset, limit);
					}
				}
			}
			throw new ServiceException("查询失败", e);
		}
	}

	@Override
	public SqlSession getSqlSession(boolean isBatch) {
		return isBatch ? this.batchSqlSession : session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	public void setBatchSqlSession(SqlSession batchSqlSession) {
		this.batchSqlSession = batchSqlSession;
	}

	public void setFallback(IDefaultService fallback) {
		this.fallback = fallback;
	}

	public void setFallbackExceptions(List<String> fallbackExceptions) {
		this.fallbackExceptions = new ArrayList<Class<Throwable>>();
		for (String ex : fallbackExceptions) {
			try {
				@SuppressWarnings("unchecked")
				Class<Throwable> clazz = (Class<Throwable>) Class.forName(ex);
				this.fallbackExceptions.add(clazz);
			} catch (Exception e) {
				// ingore
			}
		}
	}

}

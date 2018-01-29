package com.oneself.cloud.provider.user.service;

import java.util.List;

import com.oneself.cloud.provider.dbcroe.base.IBaseService;
import com.oneself.cloud.provider.dbcroe.exception.ServiceException;
import com.oneself.cloud.provider.user.model.SgSysStaffVO;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月10日下午1:56:54
 */
public interface IUserInfoService extends IBaseService<SgSysStaffVO>{
	
	public final static String BEAN_ID = "userInfoService";
	
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public List<SgSysStaffVO> queryUser(String name)throws ServiceException;
	
	/**
	 * 插入用户
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	public SgSysStaffVO saveUser(SgSysStaffVO vo)throws ServiceException;
}

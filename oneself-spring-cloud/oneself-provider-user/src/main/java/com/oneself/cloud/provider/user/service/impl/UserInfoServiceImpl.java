package com.oneself.cloud.provider.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneself.cloud.provider.dbcroe.base.impl.BaseServiceImpl;
import com.oneself.cloud.provider.dbcroe.exception.ServiceException;
import com.oneself.cloud.provider.user.model.ShUserInfoVO;
import com.oneself.cloud.provider.user.service.IUserInfoService;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月10日下午1:58:30
 */
@Service(IUserInfoService.BEAN_ID)
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<ShUserInfoVO> implements IUserInfoService{

	/* (non-Javadoc)
	 * @see com.oneself.cloud.provider.user.service.IUserInfoService#queryUser(java.lang.String)
	 */
	@Override
	public List<ShUserInfoVO> queryUser(String name) throws ServiceException {
		Map<String, Object> map=new HashMap<String, Object>();
		// TODO Auto-generated method stub
		map.put("userName", name);
		List<ShUserInfoVO> list=this.find(ShUserInfoVO.class,map);
		if (list.size()>0) {
			return list;
		}
		return null;
	}
	
	@Override
	public ShUserInfoVO saveUser(ShUserInfoVO vo)throws ServiceException{
		Integer num=this.insert(vo);
		if (num>0) {
			return vo;
		}
		return null;
	}

}

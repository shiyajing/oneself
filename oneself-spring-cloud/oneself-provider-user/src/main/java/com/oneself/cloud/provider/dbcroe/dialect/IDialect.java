package com.oneself.cloud.provider.dbcroe.dialect;

public interface IDialect {
	
	boolean isPaged();
	
	String getPagedString(String sql, boolean flag);

    String getPagedString(String sql, int offset, int limit);
}


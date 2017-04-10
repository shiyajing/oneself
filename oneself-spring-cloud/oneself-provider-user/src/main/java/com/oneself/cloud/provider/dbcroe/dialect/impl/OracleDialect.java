package com.oneself.cloud.provider.dbcroe.dialect.impl;

import com.oneself.cloud.provider.dbcroe.dialect.IDialect;

public class OracleDialect implements IDialect {
	private String ORACLE_PAGEDSQL_PATTERN = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (@_z_#) A WHERE ROWNUM <= @_x_#) WHERE RN >= @_y_#";

	public boolean isPaged() {
		return true;
	}

	public String getPagedString(String sql, boolean flag) {
		return null;
	}

	public String getPagedString(String sql, int offset, int limit) {
		offset++;
		int endset = (offset + limit) - 1;
		String rs = ORACLE_PAGEDSQL_PATTERN
				.replaceFirst("@_y_#",
						(new StringBuilder()).append(offset).toString())
				.replaceFirst("@_x_#",
						new StringBuilder().append(endset).toString())
				.replaceFirst("@_z_#", sql);
		return rs;
	}

}
package com.oneself.cloud.provider.dbcroe.dialect.impl;

import com.oneself.cloud.provider.dbcroe.dialect.IDialect;

public class MysqlDialect implements IDialect {
	protected static final String SQL_END_DELIMITER = ";";
	public MysqlDialect(){}

    public String getPagedString(String sql, boolean hasOffset){
        return (new StringBuilder(sql.length() + 20)).append(trim(sql)).append(hasOffset ? " limit ?,?" : " limit ?").append(";").toString();
    }

    public String getPagedString(String sql, int offset, int limit){
        sql = trim(sql);
        StringBuilder sb = new StringBuilder(sql.length() + 20);
        sb.append(sql);
        if(offset > 0)
            sb.append(" limit ").append(offset).append(',').append(limit).append(SQL_END_DELIMITER);
        else
            sb.append(" limit ").append(limit).append(SQL_END_DELIMITER);
        return sb.toString();
    }

    public boolean isPaged(){
        return true;
    }

    private String trim(String sql){
        sql = sql.trim();
        if(sql.endsWith(";"))
            sql = sql.substring(0, sql.length() - 1 - ";".length());
        return sql;
    }

}

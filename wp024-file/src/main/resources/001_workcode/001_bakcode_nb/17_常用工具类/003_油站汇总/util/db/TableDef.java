package com.catt.common.util.db;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表定义
 * 
 * @author 纪建宏
 *
 */
@XStreamAlias("o:Table")
public class TableDef {

	/**
	 * 表名称
	 */
	@XStreamAlias("a:Name")
	private String name;
	/**
	 * 数据库表名
	 */
	@XStreamAlias("a:Code")
	private String code;
	/**
	 * 表注释
	 */
	@XStreamAlias("a:Comment")
	private String comment;
	/**
	 * 字段定义列表
	 */
	@XStreamAlias("c:Columns")
	private List<com.catt.common.util.db.ColumnDef> columns = new ArrayList<com.catt.common.util.db.ColumnDef>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<com.catt.common.util.db.ColumnDef> getColumns() {
		return columns;
	}

	public void setColumns(List<com.catt.common.util.db.ColumnDef> columns) {
		this.columns = columns;
	}

}

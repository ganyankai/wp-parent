package com.catt.common.util.excel;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel2007实体类，代表一个Excel2007文件
 * 
 * @author 纪建宏
 * 
 */
public class XSSFExcelBean extends com.catt.common.util.excel.ExcelBean {

	/**
	 * 创建一个2007版本的Excel对象
	 */
	public XSSFExcelBean() {
		workbook = new XSSFWorkbook();
	}

	/**
	 * 创建一个2007版本的Excel对象
	 * 
	 * @param workbook
	 *            指定poi工作薄对象
	 */
	public XSSFExcelBean(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	@Override
	public int getMaxRows() {
		return SpreadsheetVersion.EXCEL2007.getMaxRows();
	}

	@Override
	public int getMaxColumns() {
		return SpreadsheetVersion.EXCEL2007.getMaxColumns();
	}

}

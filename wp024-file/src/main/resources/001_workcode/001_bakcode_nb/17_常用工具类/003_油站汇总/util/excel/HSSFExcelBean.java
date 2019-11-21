package com.catt.common.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;

/**
 * Excel2003实体类，代表一个Excel2003文件
 * 
 * @author 纪建宏
 * 
 */
public class HSSFExcelBean extends com.catt.common.util.excel.ExcelBean {

	/**
	 * 创建一个2003版本的Excel对象
	 */
	public HSSFExcelBean() {
		workbook = new HSSFWorkbook();
	}

	/**
	 * 创建一个2003版本的Excel对象
	 * 
	 * @param workbook
	 *            指定poi工作薄对象
	 */
	public HSSFExcelBean(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	@Override
	public int getMaxRows() {
		return SpreadsheetVersion.EXCEL97.getMaxRows();
	}

	@Override
	public int getMaxColumns() {
		return SpreadsheetVersion.EXCEL97.getMaxColumns();
	}

}

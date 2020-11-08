package cn.itcast.poi.entity.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 使用poi创建excel
 */
public class PoiTest02 {

    public static void main(String[] args) throws Exception {
        //1.创建工作簿
        Workbook wb = new XSSFWorkbook();   //2007版本

        //2.创建表单sheet
        Sheet sheet = wb.createSheet("test");
        //创建行对象 参数:索引
        Row row = sheet.createRow(2);
        //创建单元格对象
        Cell cell = row.createCell(2);
        //向单元格中写入文件流
        cell.setCellValue("传智播客");



        //3.文件流
        FileOutputStream fos = new FileOutputStream("C:\\Users\\admin\\Desktop\\test\\t01\\test1.xlsx");
        
        //4.写入文件
        wb.write(fos);
        fos.close();
    }

}

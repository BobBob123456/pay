package com.pay.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Excel 操作类
 * @author bob
 *
 */
public class ExcelUtil {
	/**
	 * 读取xlsx文件数据
	 * @param is
	 * @return
	 */
	public static List<String> readerXlsx(InputStream is) {
		List<String> list=null;
		XSSFWorkbook xssfWorkbook;
		try {
			xssfWorkbook = new XSSFWorkbook(is);
			// 获取第一个工作薄
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			if (xssfSheet == null) {
				return null;
			}
			if(xssfSheet.getLastRowNum()>2){
				list=new ArrayList<String>();
			}
			// 获取当前工作薄的每一行
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					String bank =getXlsxValue( xssfRow.getCell(0));
					String card=getXlsxValue( xssfRow.getCell(1));
					String name=getXlsxValue( xssfRow.getCell(2));
					String money=getXlsxValue( xssfRow.getCell(3));
					System.out.println(bank+","+card+","+name+","+money);
					list.add(bank+","+card+","+name+","+money);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 读取xls文件数据
	 * @param is
	 * @return
	 */
	public static List<String> readerXls(InputStream is) {
		List<String> list=null;
		HSSFWorkbook hssfWorkbook;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
			// 获取第一个工作薄
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			if (hssfSheet == null) {
				return null;
			}
			if(hssfSheet.getLastRowNum()>2){
				list=new ArrayList<String>();
			}
			// 获取当前工作薄的每一行
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					String bank =getXlsValue(hssfRow.getCell(0));
					String card=getXlsValue(hssfRow.getCell(1));
					String name=getXlsValue(hssfRow.getCell(2));
					String money=getXlsValue(hssfRow.getCell(3));
					list.add(bank+","+card+","+name+","+money);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 转换数据格式
	public static String getXlsxValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	// 转换数据格式
	public static String getXlsValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

}

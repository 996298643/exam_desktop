package com.css.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {

	public static List<String> importColumn(File file) {
		List<String> list = new ArrayList<String>();
		POIFSFileSystem fs;
		try {
			fs = new POIFSFileSystem(new FileInputStream(file));
			Workbook wb = new HSSFWorkbook(fs);
			Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
			Iterator<Row> rite = sheet.rowIterator();
			while(rite.hasNext()) {
				Row row = rite.next();
				Cell cell = row.getCell(0);
				String value = getCellValue(cell);
				list.add(value);
				if (StrKit.isBlank(value)) {
					continue;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String genExcelTemplate(List<String> list) {
		String result = "";
		try {
			Workbook wb = new HSSFWorkbook();
			CellStyle cs = wb.createCellStyle();
			cs.setAlignment(CellStyle.ALIGN_CENTER);
			cs.setBorderBottom((short) 1);
			Font font = wb.createFont();
			font.setBold(true);
			font.setFontName("宋体");
			font.setFontHeight((short) 240);
			cs.setFont(font);

			CreationHelper createHelper = wb.getCreationHelper();
			Sheet sheet1 = wb.createSheet("模版");

			Row row = sheet1.createRow((short) 0);
			int i = 0;
			for (String str : list) {
				sheet1.setColumnWidth(i, 600 * length(str));
				Cell cell = row.createCell(i);
				cell.setCellStyle(cs);
				cell.setCellValue(createHelper.createRichTextString(str));
				i++;
			}
			result = System.getProperty("java.io.tmpdir") + File.separator + CoreUtil.uuid32() + ".xls";
			FileOutputStream fos = new FileOutputStream(result);
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}
	
	public static String getCellValue(Cell cell) {
		String val;
		if (cell == null) {
			val = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			val = cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			val = cell.getBooleanCellValue() ? "true" : "false";
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			val = new BigDecimal(cell.getNumericCellValue()).toPlainString();
		} else {
			val = "";
		}
		return val;
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}
}

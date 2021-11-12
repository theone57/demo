//package com.lin.demo.utils;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.view.document.AbstractExcelView;
//
//import javax.mail.internet.MimeUtility;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Paul on 2016/08/17.
// */
//public class ViewExcel extends AbstractExcelView {
//
//	public final static String FILE_NAME = "fileName";
//	public final static String TITLE = "title";
//	public final static String DATE = "date";
//	public final static String CELL_TITLE = "cellTitle";
//	public final static String DATA_LIST = "dataList";
//	public final static String TOTAL = "total";
//	public final static String FIRST_TITLE = "firstTile";
//	public final static String FIRST_DATE = "firstDate";
//	public final static String TYPE = "type";
//
////	@Override
//	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// 文件名
//		String fileName = null;
//		// 标题
//		String title = null;
//		// 统计日期
//		String date = null;
//
//		String type = null;
//		// 表头标题
//		List<String> cellTitle = null;
//		// 数据
//		List<List<String>> dataList = null;
//		//统计表头标题
//		List<String> firstTitle = null;
//		//统计表头数据
//		List<String> firstDate = null;
//
//		if (model.containsKey(FILE_NAME))
//			fileName = String.valueOf(model.get(FILE_NAME));
//		if (model.containsKey(TITLE))
//			title = String.valueOf(model.get(TITLE));
//		if (model.containsKey(DATE))
//			date = String.valueOf(model.get(DATE));
//		if (model.containsKey(CELL_TITLE))
//			cellTitle = (List<String>) model.get(CELL_TITLE);
//		if (model.containsKey(DATA_LIST))
//			dataList = (List<List<String>>) model.get(DATA_LIST);
//		if (model.containsKey(FIRST_TITLE))
//			firstTitle = (List<String>) model.get(FIRST_TITLE);
//		if (model.containsKey(FIRST_DATE))
//			firstDate = (List<String>) model.get(FIRST_DATE);
//		if (model.containsKey(TYPE))
//            type = String.valueOf(model.get(TYPE));
//		HSSFSheet sheet = null;
//		HSSFRow sheetRow = null;
//		int titleSpace = 1;
//		int dataSpace = 5;
//        HSSFCellStyle cellTitleStyle = workbook.createCellStyle();
////        cellTitleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
////        cellTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		if ("2".equals(type)) {
//            if (cellTitle != null && cellTitle.size() > 0) {
//                sheet = workbook.createSheet(title);
//                sheet.setDefaultColumnWidth(12);
//                sheet.setColumnWidth(2, 18 * 256);
//                sheet.setColumnWidth(3, 20 * 256);
//
//                sheet.setDefaultRowHeightInPoints(21.75F);
//                HSSFCell cell = getCell(sheet, 1, 1);
//                HSSFCellStyle cellStyle = workbook.createCellStyle();
//                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//
//                HSSFFont font = workbook.createFont();
//                font.setFontName("宋体");
//                font.setFontHeightInPoints((short) 14);
//                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//                cellStyle.setFont(font);
//                setText(cell, title);
//                cell.setCellStyle(cellStyle);
//                for (int i = 0; i < cellTitle.size(); i++) {
//                    if (i == 0) {
//                        sheet.addMergedRegion(new CellRangeAddress((short) 1, (short) 2, (short) 1, (short) 1));
//                    } else if (i == 1) {
//                        sheet.addMergedRegion(new CellRangeAddress((short) 1, (short) 1, (short) 2, (short) 4));
//                    } else if (i == 2) {
//                        sheet.addMergedRegion(new CellRangeAddress((short) 1, (short) 1, (short) 5, (short) 7));
//                    } else if (i == 3) {
//                        sheet.addMergedRegion(new CellRangeAddress((short) 1, (short) 1, (short) 8, (short) 10));
//                    } else if (i == 4) {
//                        sheet.addMergedRegion(new CellRangeAddress((short) 1, (short) 1, (short) 11, (short) 13));
//                    } else if (i == 5) {
//                        sheet.addMergedRegion(new CellRangeAddress((short) 1, (short) 1, (short) 14, (short) 16));
//                    }
//                }
//                dataSpace = 3;
//                sheetRow = sheet.createRow(1);
//            } else {
//                titleSpace = 0;
//                dataSpace = 1;
//                sheet = workbook.createSheet();
//                sheetRow = sheet.createRow(0);
//            }
//		} else {
//			titleSpace = 0;
//			dataSpace = 1;
//			sheet = workbook.createSheet();
//			sheetRow = sheet.createRow(0);
//		}
//
//		// 循环表头
//		if (firstTitle != null && firstTitle.size() > 0) {
//			for (int i = 0; i < firstTitle.size(); i++) {
//                HSSFCell dataCell = sheetRow.createCell(i + titleSpace);
//                dataCell.setCellStyle(cellTitleStyle);
//                dataCell.setCellValue(firstTitle.get(i));
//			}
//            sheetRow = sheet.createRow(2);
//		}
//		// 循环表头
//		if (cellTitle != null && cellTitle.size() > 0) {
//		    int a = 0;
//			for (int i = 0; i < cellTitle.size(); i++) {
//			    if ("2".equals(type)) {
//			        a = i + 1;
//                }
//                HSSFCell dataCell = sheetRow.createCell(a + titleSpace);
//                dataCell.setCellStyle(cellTitleStyle);
//                dataCell.setCellValue(cellTitle.get(i));
//                a++;
//			}
//		}
//		// 循环数据
//		if (dataList != null && dataList.size() > 0) {
//			for (int i = 0; i < dataList.size(); i++) {
//				HSSFRow dataRow = sheet.createRow(i + dataSpace);
//				List<String> dataCellList = dataList.get(i);
//				int index = 0;
//				for (String data : dataCellList) {
//					HSSFCell dataCell = dataRow.createCell(index + titleSpace);
//					dataCell.setCellStyle(cellTitleStyle);
//					dataCell.setCellValue(data);
//					index++;
//				}
//			}
//		}
//
//		// 文件名
//		String filename = fileName + ".xls";// 设置下载时客户端Excel的名称
//		filename = encodeFilename(filename, request);// 处理中文文件名
//		response.setContentType("application/vnd.ms-excel");
//		response.setHeader("Content-disposition", "attachment;filename=" + filename);
//		OutputStream ouputStream = response.getOutputStream();
//		workbook.write(ouputStream);
//		ouputStream.flush();
//		ouputStream.close();
//	}
//
//	public static String encodeFilename(String filename, HttpServletRequest request) {
//		/**
//		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
//		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
//		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
//		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
//		 */
//		String agent = request.getHeader("USER-AGENT");
//		try {
//			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
//				String newFileName = URLEncoder.encode(filename, "UTF-8");
//				newFileName = StringUtils.replace(newFileName, "+", "%20");
//				if (newFileName.length() > 150) {
//					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
//					newFileName = StringUtils.replace(newFileName, " ", "%20");
//				}
//				return newFileName;
//			}
//			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
//				return MimeUtility.encodeText(filename, "UTF-8", "B");
//			return filename;
//		} catch (Exception ex) {
//			return filename;
//		}
//	}
//}
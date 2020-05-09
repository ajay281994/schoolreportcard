package test.tom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import test.com.studentobj;

public class readxcel {

	List<studentobj> studentlist = new LinkedList<studentobj>();

	Map<String, List<Double>> subjectMarkmap = new LinkedHashMap<String, List<Double>>();
	Map<Integer, String> keyvalue = new LinkedHashMap<Integer, String>();
	List<String> subjectlist = new LinkedList<String>();

	public List<String> getSubjectlist() {
		return subjectlist;
	}

	public void read() throws InvalidFormatException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(new File("D:\\result\\11A.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> itrow = sheet.iterator();

		while (itrow.hasNext()) {
			Row row = itrow.next();
			if (row.getRowNum() == 0) {
				getsubjectlist(row);
			} else {

				studentobj studentobj = new studentobj();
				studentobj.setRoll_no(Math.round((row.getCell(0).getNumericCellValue())) + "");
				studentobj.setName(row.getCell(1).toString().trim());
				studentobj.setClass_name(row.getCell(2).toString().trim());

				Iterator<Cell> itcell = row.iterator();
				Map<String, Double> subjectmap = new HashMap<String, Double>();
				studentobj.setSubjectmap(subjectmap);
				while (itcell.hasNext()) {

					Cell cell = itcell.next();

					if (cell.getColumnIndex() > 2) {
						subjectmap.put(keyvalue.get(cell.getColumnIndex()), cell.getNumericCellValue());
						List<Double> subjectlist;
						if (subjectMarkmap.get(keyvalue.get(cell.getColumnIndex())) == null) {
							subjectlist = new ArrayList<Double>();
							subjectMarkmap.put(keyvalue.get(cell.getColumnIndex()), subjectlist);
						} else {
							subjectlist = subjectMarkmap.get(keyvalue.get(cell.getColumnIndex()));
						}
						subjectlist.add(cell.getNumericCellValue());
					}
				}
//				System.out.println(subjectmap);
				studentlist.add(studentobj);
			} // System.out.println();
		}
		// System.out.println(subjectMarkmap);
	}

	public void getsubjectlist(Row row) {
		Iterator<Cell> itcell = row.iterator();
		while (itcell.hasNext()) {
			Cell cell = itcell.next();
			keyvalue.put(cell.getColumnIndex(), cell.toString().trim());
			if (cell.getColumnIndex() > 2) {
				subjectlist.add(cell.toString().trim());
			}
		}
		System.out.println(keyvalue);
		System.out.println(subjectlist);
	}

	public List<studentobj> getStudentlist() {
		return studentlist;
	}

	public void setStudentlist(List<studentobj> studentlist) {
		this.studentlist = studentlist;
	}

	public Map<String, List<Double>> getSubjectMarkmap() {
		return subjectMarkmap;
	}

	public void setSubjectMarkmap(Map<String, List<Double>> subjectMarkmap) {
		this.subjectMarkmap = subjectMarkmap;
	}
}

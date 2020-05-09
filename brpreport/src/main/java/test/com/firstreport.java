package test.com;

import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import test.tom.readxcel;

public class firstreport {
	private static DecimalFormat df2 = new DecimalFormat("#");

	public static void main(String[] args) throws InvalidFormatException, JRException, IOException {
		firstreport firs = new firstreport();
		firs.callfirst();
	}

	public void callfirst() throws JRException, InvalidFormatException, IOException {

		List<studentobj> studentlist = null;
		Map<String, List<Double>> subjectMarkmap = null;
		Map<String, Double> subjectAvgMap = null;
		Map<String, Double> subjectTopMap = null;
		List<String> subjectlistmain = null;
		
		Image image = ImageIO.read(new File("D:\\test\\schoollog.jpeg"));

		// read excel and get studentObjectlist and subjectmarks
		readxcel readxcell = new readxcel();
		readxcell.read();
		studentlist = readxcell.getStudentlist();

		// System.out.println(studentlist);
		subjectMarkmap = readxcell.getSubjectMarkmap();

		// get subjectobj using subjetmap
		subjectlistmain = readxcell.getSubjectlist();
		System.out.println(subjectlistmain);
		subjectAvgMap = getsubjectavg(subjectMarkmap);
		subjectTopMap = getsubjecttop(subjectMarkmap);

		String fileSource = "C:\\Users\\Ajay\\JaspersoftWorkspace\\MyReports\\brpatel.jrxml";

		JasperReport jrReport = JasperCompileManager.compileReport(fileSource);
		LinkedList<student> datalist = new LinkedList<student>();
		for (studentobj stuobj : studentlist) {
			student stu = new student();
			stu.setName(stuobj.getName());
			System.out.println(stuobj.getName());
			stu.setRoll_no(stuobj.getRoll_no());
			stu.setClass_name(stuobj.getClass_name());
			stu.setAttendance("A:0 P:0 T:0");
			List<subjectobj> subjectlist = new LinkedList<subjectobj>();
			List<tabeldata> tabeldataslist = new LinkedList<tabeldata>();
			List<tabeldata> skillsubjectlist = new LinkedList<tabeldata>();
			Map<String, Double> subjectmap = new LinkedHashMap<String, Double>(stuobj.getSubjectmap());
			double sum = 0;
			Integer seno = 0;
			Integer divcount = 0;
			double pmarks = 0;
			Long totalpmarks = 0l;
			Long totalgmarks = 0l;
			for (String subjname : subjectlistmain) {
				double marks = subjectmap.get(subjname);
				if (!(subjname.matches("Physical education") || subjname.matches("Drawing")
						|| subjname.matches("Computer") || subjname.matches("CT"))) {
					if (marks != 0) {
						divcount += 1;
					}
					sum += marks;
				}

			}
			long persentagetotal = Math.round((sum * 100) / (120 * divcount));
			System.out.println(persentagetotal);
			if ((persentagetotal - 33) > 15) {
				pmarks = 15;
			} else if ((persentagetotal - 33) > 0) {
				pmarks = persentagetotal - 33;
			}
			
			System.out.println(pmarks);
			sum = 0;
			divcount = 0;
			persentagetotal = 0;
			for (String subjname : subjectlistmain) {
				System.out.println(subjname);
				double marks = subjectmap.get(subjname);
				// System.out.println(subjname);
				// System.out.println(subjname.matches("Physical education"));
				if (subjname.matches("Physical education") || subjname.matches("Drawing")
						|| subjname.matches("Computer") || subjname.matches("CT")) {

					tabeldata skillsubject = new tabeldata();
					skillsubject.setSubjectName(subjname);
					skillsubject.setSubjectgrade(getgrade((marks * 100) / 20));
					skillsubjectlist.add(skillsubject);
				} else {

					subjectobj subobj = new subjectobj();
					seno += 1;
					subobj.setSubject_avg(Math.round(subjectAvgMap.get(subjname)));
					subobj.setSubject_topper(Math.round(subjectTopMap.get(subjname)));
					subobj.setSubject_name(subjname);

					tabeldata tabeldata = new tabeldata();
					tabeldata.setSeno(seno + "");
					tabeldata.setSubjectName(subjname);
					tabeldata.setGmarks("-");
					tabeldata.setPmarks("-");
					Double a = 0d;
					if (marks != 0) {
						a = (marks * 100 / 120);
					}
					tabeldata.setSubjectmark(Math.round(a) + "/100");
					sum += Math.round(a);
					if (a < 33) {
						double need = 33 - Math.round(a);
						if (pmarks == 0) {
							tabeldata.setGmarks(df2.format(need));
							totalgmarks += Math.round(need);
						} else if (pmarks == need) {
							System.out.println("========");
							tabeldata.setPmarks(df2.format(need));
							System.out.println(pmarks);
							totalpmarks += Math.round(need);
							System.out.println(totalgmarks);
							pmarks = 0d;
						} else if (pmarks < need) {
							tabeldata.setPmarks(df2.format(pmarks));
							System.out.println(tabeldata.getPmarks());
							tabeldata.setGmarks(df2.format(need - pmarks));
							totalpmarks += Math.round(pmarks);
							totalgmarks += Math.round(need - pmarks);
							pmarks = 0;
						} else if (need < pmarks) {
							tabeldata.setPmarks(df2.format(need));
							totalpmarks += Math.round(need);
							pmarks -= need;
						}
						a = 33d;
					}
					tabeldata.setSubjectpercent(df2.format(a));
					tabeldata.setSubjectgrade(getgrade(a));
					subobj.setSubject_achive(Math.round(a));
					persentagetotal += Math.round(a);
					divcount += 1;
					tabeldataslist.add(tabeldata);
					subjectlist.add(subobj);
				}
			}
			System.out.println(subjectlist);
			stu.setTotal_pmarks(totalpmarks + "");
			stu.setTotal_gmarks(totalgmarks + " ");
			stu.setTotal_marks(df2.format(sum) + "/" + 100 * divcount);
			stu.setTotalmarksinint((int) Math.round(sum));
			stu.setTotal_percentage(persentagetotal + " ");
			System.out.println("Finalgrade:" + persentagetotal / seno);
			stu.setFinalgrade(getgrade(persentagetotal / seno)+" / "+persentagetotal / seno+" %");
			stu.setSubjectlist(subjectlist);
			stu.setTabeldataslist(tabeldataslist);
			stu.setSkillsubject(skillsubjectlist);
			stu.setTeacher_Comment("teacher comment");
			stu.setUrl(image);
			datalist.add(stu);
		}
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datalist);

		List<student> datalistclon = new LinkedList<student>();
	    datalistclon = (List<student>) datalist;
		Collections.sort(datalistclon, new Comparator<student>() {
		    public int compare(student a, student b) {
		        return b.getTotalmarksinint() - a.getTotalmarksinint();
		    }
		});
		int rank = 0;
		Map<String,String> studentrank = new HashMap<String, String>();
		
		for (student student : datalistclon) {
			rank+=1;
			studentrank.put(student.getRoll_no(),rank+"");
			System.out.println("|"+student.getRoll_no()+"||"+rank+"|");
		}

		Map<String, Object> pathemap = new HashMap<String, Object>();

		pathemap.put("school_name", "Shree B R Patel High School,Koydam");
		pathemap.put("school_address", "Koydam,Virpur,Mahisagr(388260)");
		pathemap.put("School_logo", "D:\\test\\schoollog.jpg");
		pathemap.put("principal_signature", "D:\\test\\PrincipalSignature.jpg");
		pathemap.put("class_teacher_signature", "D:\\result\\vupandaya.jpeg");
		pathemap.put("grade_boundry", "A:99-95;A+:90-85;B:75-85;B+:60-75;C:60-45;C+:35-45;F:35-0");
		pathemap.put("student_rank",studentrank);

		try {
			JasperPrint jprint = JasperFillManager.fillReport(jrReport, pathemap, dataSource);

			File file = new File("D:\\result\\11A.pdf");
			OutputStream output = new FileOutputStream(file);
			JasperExportManager.exportReportToPdfStream(jprint, output);
			Desktop desktop = Desktop.getDesktop();
			if (file.exists()) {
				desktop.open(file);
			}

		} catch (JRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getgrade(double m) {
		if (m >= 91) {
			return "A+";
		} else if (m >= 81 && m < 90) {
			return "A";
		} else if (m >= 71 && m < 80) {
			return "B";
		} else if (m >= 61 && m < 70) {
			return "B+";
		} else if (m >= 51 && m < 60) {
			return "C+";
		} else if (m >= 41 && m < 50) {
			return "C";
		} else if (m >= 33 && m < 40) {
			return "D";
		} else {
			return "E";
		}
	}

	private Map<String, Double> getsubjecttop(Map<String, List<Double>> subjectMarkmap) {
		Map<String, Double> toplist = null;

		if (subjectMarkmap != null) {

			toplist = new HashMap<String, Double>();
			for (String subjectName : subjectMarkmap.keySet()) {
				subjectMarkmap.get(subjectName);

				toplist.put(subjectName, ((Collections.max(subjectMarkmap.get(subjectName)) * 100) / 120));
			}

		}
		// System.out.println(toplist);
		return toplist;

	}

	public Map<String, Double> getsubjectavg(Map<String, List<Double>> subjectMarkmap) {
		Map<String, Double> avglist = null;

		if (subjectMarkmap != null) {

			avglist = new HashMap<String, Double>();
			for (String subjectName : subjectMarkmap.keySet()) {
				// System.out.println(subjectName + " : " + subjectMarkmap.get(subjectName));
				double sum = 0;
				for (double i : subjectMarkmap.get(subjectName)) {

					sum += (i * 100) / 120;
				}
				double average = sum / subjectMarkmap.get(subjectName).size();
				avglist.put(subjectName, average);
			}

		}
		System.out.println("avglist:" + avglist);
		return avglist;

	}

}

package test.com;

import java.awt.Image;
import java.util.List;

public class student {

	private String name;
	private String roll_no;
	private String class_name;
	private String attendance;
	private String teacher_Comment;
	private String total_percentage;
	private String total_marks;
	private String total_pmarks;
	private String total_gmarks;
    private String finalgrade;
	private List<subjectobj> subjectlist;
	private List<tabeldata> tabeldataslist;
	private List<tabeldata> skillsubject;
	private Image url;
	private Integer totalmarksinint;

	public String getTotal_pmarks() {
		return total_pmarks;
	}

	public void setTotal_pmarks(String total_pmarks) {
		this.total_pmarks = total_pmarks;
	}

	public String getTotal_gmarks() {
		return total_gmarks;
	}

	public void setTotal_gmarks(String total_gmarks) {
		this.total_gmarks = total_gmarks;
	}

	public Image getUrl() {
		return url;
	}

	public void setUrl(Image url) {
		this.url = url;
	}

	public List<subjectobj> getSubjectlist() {
		return subjectlist;
	}

	public void setSubjectlist(List<subjectobj> subjectlist) {
		this.subjectlist = subjectlist;
	}

	public List<tabeldata> getTabeldataslist() {
		return tabeldataslist;
	}

	public void setTabeldataslist(List<tabeldata> tabeldataslist) {
		this.tabeldataslist = tabeldataslist;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getTeacher_Comment() {
		return teacher_Comment;
	}

	public void setTeacher_Comment(String teacher_Comment) {
		this.teacher_Comment = teacher_Comment;
	}

	public String getTotal_percentage() {
		return total_percentage;
	}

	public void setTotal_percentage(String total_percentage) {
		this.total_percentage = total_percentage;
	}

	public String getTotal_marks() {
		return total_marks;
	}

	public void setTotal_marks(String total_marks) {
		this.total_marks = total_marks;
	}

	public String getFinalgrade() {
		return finalgrade;
	}

	public void setFinalgrade(String finalgrade) {
		this.finalgrade = finalgrade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public List<tabeldata> getSkillsubject() {
		return skillsubject;
	}

	public void setSkillsubject(List<tabeldata> skillsubject) {
		this.skillsubject = skillsubject;
	}

	public Integer getTotalmarksinint() {
		return totalmarksinint;
	}

	public void setTotalmarksinint(Integer totalmarksinint) {
		this.totalmarksinint = totalmarksinint;
	}

}

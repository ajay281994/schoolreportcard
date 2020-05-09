package test.com;

import java.util.Map;

public class studentobj {
	private String name;
	private String roll_no;
	private String class_name;
	private Map<String, Double> subjectmap;

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

	public Map<String, Double> getSubjectmap() {
		return subjectmap;
	}

	public void setSubjectmap(Map<String, Double> subjectmap) {
		this.subjectmap = subjectmap;
	}
}

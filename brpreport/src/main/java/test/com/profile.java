package test.com;

import java.util.List;

public class profile {

	private String name;
	
	private String profileId;
	
	private String orglogo;
	private String pr_sign;

	private List<studentobj> tablelist;
	

	public String getPr_sign() {
		return pr_sign;
	}

	public void setPr_sign(String pr_sign) {
		this.pr_sign = pr_sign;
	}

	public String getOrglogo() {
		return orglogo;
	}

	public void setOrglogo(String orglogo) {
		this.orglogo = orglogo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public List<studentobj> getTablelist() {
		return tablelist;
	}

	public void setTablelist(List<studentobj> tablelist) {
		this.tablelist = tablelist;
	}


}

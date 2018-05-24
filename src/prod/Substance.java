import java.util.ArrayList;

public class Substance {
	
	private ArrayList<Synonym> synList;

	private String recordID;
	private int nodeID;
	private String casCode;
	private String euIndexCode;
	private String einecs_elincs_code;

	private boolean duty_to_declare;
	private boolean isUnwanted;
	private boolean isProhibited;
	private boolean isReach;
	private boolean isDeleted;
	private boolean isHidden;

	public Substance(String recordID, int nodeID, String casCode, String euIndexCode, String einecs_elincs_code,
			boolean duty_to_declare, boolean isUnwanted, boolean isProhibited, boolean isReach, boolean isDeleted,
			boolean isHidden) {
		this.recordID = recordID;
		this.nodeID = nodeID;
		this.casCode = casCode;
		this.euIndexCode = euIndexCode;
		this.einecs_elincs_code = einecs_elincs_code;
		this.duty_to_declare = duty_to_declare;
		this.isUnwanted = isUnwanted;
		this.isProhibited = isProhibited;
		this.isReach = isReach;
		this.isDeleted = isDeleted;
		this.isHidden = isHidden;

	}

	public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeid(int nodeID) {
		this.nodeID = nodeID;
	}

	public String getCasCode() {
		return casCode;
	}

	public void setCasCode(String casCode) {
		this.casCode = casCode;
	}

	public String getEuIndexCode() {
		return euIndexCode;
	}

	public void setEuIndexCode(String euIndexCode) {
		this.euIndexCode = euIndexCode;
	}

	public String getEinecs_elincs_code() {
		return einecs_elincs_code;
	}

	public void setEinecs_elincs_code(String einecs_elincs_code) {
		this.einecs_elincs_code = einecs_elincs_code;
	}

	public boolean isDuty_to_declare() {
		return duty_to_declare;
	}

	public void setDuty_to_declare(boolean duty_to_declare) {
		this.duty_to_declare = duty_to_declare;
	}

	public boolean isUnwanted() {
		return isUnwanted;
	}

	public void setUnwanted(boolean isUnwanted) {
		this.isUnwanted = isUnwanted;
	}

	public boolean isProhibited() {
		return isProhibited;
	}

	public void setProhibited(boolean isProhibited) {
		this.isProhibited = isProhibited;
	}

	public boolean isReach() {
		return isReach;
	}

	public void setReach(boolean isReach) {
		this.isReach = isReach;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public ArrayList<Synonym> getSyn() {
		return synList;
	}

	public void setSynList(ArrayList<Synonym> synList) {
		this.synList = synList;
	}

	@Override
	public String toString() {
		return casCode + ", " + synList.get(0);
	}

}

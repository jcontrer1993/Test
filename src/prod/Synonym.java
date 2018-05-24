
public class Synonym {

	private String recordID;
	private int nodeID;
	private int synID;
	private String isoLanguage;
	private String synName;

	public Synonym(String recordID, int nodeID, int synID, String isoLanguage, String synName) {
		super();
		this.recordID = recordID;
		this.nodeID = nodeID;
		this.synID = synID;
		this.isoLanguage = isoLanguage;
		this.synName = synName;
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

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public int getSynID() {
		return synID;
	}

	public void setSynID(int synID) {
		this.synID = synID;
	}

	public String getIsoLanguage() {
		return isoLanguage;
	}

	public void setIsoLanguage(String isoLanguage) {
		this.isoLanguage = isoLanguage;
	}

	public String getSynName() {
		return synName;
	}

	public void setSynName(String synName) {
		this.synName = synName;
	}

	@Override
	public String toString() {
		return synName;
	}

}

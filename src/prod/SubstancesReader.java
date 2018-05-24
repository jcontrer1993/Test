import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * File Reader for text data containing Substances
 * 
 * @author contreras
 *
 */
public class SubstancesReader {

	// constants sizes
	private final int R_ATTRIBUTE_LENGTH_WITH_ISUNWANTED = 11;
	private final int R_ATTRIBUTE_LENGTH_WITHOUT_ISUNWANTED = 10;
	private final int RN_ATTRIBUTE_LENGTH = 5;

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

	private String actualLangCode = (Locale.getDefault().toString().equals("de_DE")) ? "DE" : "EN";

	// arrayList to store all substances
	private ArrayList<Substance> storedSubList = new ArrayList<>();
	// arrayList to store all synonyms
	private ArrayList<Synonym> storedSynList = new ArrayList<>();

	// specifications for each attribute length
	private int[] each_R_attribute_length_criteria_with_isUwanted = { 1, 18, 20, 20, 20, 1, 2, 1, 1, 1, 1 };
	private int[] each_R_attribute_length_criteria_without_isUnwanted = { 1, 18, 20, 20, 20, 1, 1, 1, 1, 1 };
	private int[] each_RN_attribute_length_criteria = { 2, 18, 4, 2, 250 };

	/**
	 * Get method for SubList containing Substances
	 * 
	 * @return subList
	 */
	public ArrayList<Substance> getStoredSubList() {
		return storedSubList;
	}

	/**
	 * Get method for SynList containing Synonyms
	 * 
	 * @return subList
	 */
	public ArrayList<Synonym> getStoredSynList() {
		return storedSynList;
	}

	/**
	 * Check if R line meets requirements. requirement: number of attributes and
	 * length of each attribute should match values from specification document
	 */
	public boolean checkSpecificationSubstance(String[] line) {
		if (line[0].equals("R") && (line.length == R_ATTRIBUTE_LENGTH_WITHOUT_ISUNWANTED
				|| line.length == R_ATTRIBUTE_LENGTH_WITH_ISUNWANTED)) {
			int len = 0;
			int[] criteria;
			if (line.length == R_ATTRIBUTE_LENGTH_WITHOUT_ISUNWANTED) {
				len = R_ATTRIBUTE_LENGTH_WITHOUT_ISUNWANTED;
				criteria = each_R_attribute_length_criteria_without_isUnwanted;
			} else {
				len = R_ATTRIBUTE_LENGTH_WITH_ISUNWANTED;
				criteria = each_R_attribute_length_criteria_with_isUwanted;
			}

			// check each attribute length for R line
			for (int i = 0; i < len; i++) {
				if (line[i].length() > criteria[i]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}

	}

	/***
	 * Check if RN line meets requirements. requirement: number of attributes and
	 * length f each attribute should match values from specification document.
	 */
	public boolean checkSpecificationSynonym(String[] line) {
		if (line[0].equals("RN") && line.length == RN_ATTRIBUTE_LENGTH) {
			int len = line.length;
			int[] criteria = each_RN_attribute_length_criteria;
			for (int i = 0; i < len; i++) {
				if (line[i].length() > criteria[i]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reads lines from textfile, check if every line(Substance) meets the
	 * requirement. Add Substances in to an List as Objects.
	 * 
	 * @param file
	 */
	public void readList(File file) {

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), "Windows-1252"))) {
			/**
			 * ignore first line because its the header and 3 other following lines.
			 */
			for (int i = 0; i < 4; i++) {
				reader.readLine();
			}

			String line = reader.readLine();
			while (line != null) {
				/** split line into 11 fields/attributes */
				String[] arr = line.split("\t");
	
				if (checkSpecificationSubstance(arr)) {
					recordID = arr[0];
					nodeID = Integer.parseInt(arr[1]);
					casCode = arr[2];
					euIndexCode = arr[3];
					einecs_elincs_code = arr[4];
					duty_to_declare = (Integer.parseInt(arr[5]) == 1);
					isUnwanted = (Integer.parseInt(arr[6]) == 1);
					isProhibited = (Integer.parseInt(arr[7]) == 1);
					isReach = (Integer.parseInt(arr[8]) == 1);
					isDeleted = (Integer.parseInt(arr[9]) == 1);
					isHidden = (Integer.parseInt(arr[10]) == 1);

					storedSubList.add(new Substance(recordID, nodeID, casCode, euIndexCode, einecs_elincs_code,
							duty_to_declare, isUnwanted, isProhibited, isReach, isDeleted, isHidden));

					line = reader.readLine();
					arr = line.split("\t");
				} else if (checkSpecificationSynonym(arr)) {
					storedSynList.clear();
					while (arr[0].equals("RN")) {
						if (actualLangCode.equals(arr[3])) {
							storedSynList.add(new Synonym(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]),
									arr[3], arr[4]));

						}

						line = reader.readLine();
						if (line != null) {
							arr = line.split("\t");
						} else {
							break;
						}
					}
					
				} else {
					line = reader.readLine();
				}
			

			}
			
			
			ArrayList<Synonym> list = new ArrayList<>();
			for(int i = 0; i < storedSubList.size(); i++) {
				Substance sub = storedSubList.get(i);
				int subNodeID = storedSubList.get(i).getNodeID();
				for(Synonym syn : storedSynList) {
					if(subNodeID == syn.getSynID()) {
						list.add(syn);
					}			
				}
				sub.setSynList(list);
				storedSubList.set(i, sub);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

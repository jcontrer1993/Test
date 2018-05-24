import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Test;

/**
 * Test class for SubstanceReader
 * 
 * @author contreras
 */
public class SubstanceReaderTest {

	@Test
	public void test() {

		long startTime = System.currentTimeMillis();

		SubstancesReader reader = new SubstancesReader();

		File file = new File("C:\\opt\\JDEV\\workspace\\test1\\MVI_Einstellungstest\\samples\\Substances.dat");

		reader.readList(file);
		System.out.println("after read");
		// test the first 10 substances
		ArrayList<Substance> actualList = reader.getStoredSubList();
		ArrayList<String> expectedListDE = new ArrayList<>();
		ArrayList<String> expectedListEN = new ArrayList<>();

		// Expected Substances in german language
		expectedListDE.add("118725-24-9, (1,3-Dioxo-2H-benz(de)isochinolin-2-ylpropyl)hexadecyldimethylammoni...");
		expectedListDE.add(
				"118725-25-0, 1H-Benz(de)isochinolin-2(3H)-propanaminium, N-hexadecyl-N.N-dimethyl-1,3-dioxo-,bromid");
		expectedListDE.add("86608-70-0, (2-(1,3-Dioxolan-2-yl)ethyl)triphenylphosphoniumbromid");
		expectedListDE.add("111-15-9, 2-Ethoxyethylacetat");
		expectedListDE.add("111-15-9, Aethylenglykolaetheracetat");

		expectedListDE.add("103-09-3, 2-Ethylhexylacetat");
		expectedListDE.add("110-49-6, 2-Methoxyethylacetat");
		expectedListDE.add("2532-43-6, (3-Methyl-1H-pyrazol-5-yl)-N,N-dimethyl-carbamat");
		expectedListDE.add("95154-01-1, (Benzothiazol-2-ylthio)bernsteinsäure");
		expectedListDE.add(
				"-, (C16oderC18-n-Alkyl)(C16oderC18-n-alkyl)ammonium-2-((C16oderC18-n-alkyl)(C16oderC18-n-alkyl)carbamoyl)benzolsulfonat");
		expectedListDE.add("85491-26-5, (Chlormethyl)bis(4-fluorphenyl)methylsilan");
		expectedListDE.add("-, (Chlorphenyl)(chlortolyl)methan, isomerengemisch");

		// Expected Substances in english language
		expectedListEN.add("118725-24-9, (1,3-Dioxo-2H-benz(de)isoquinolin-2-ylpropyl)hexadecyldimethylammoni...");
		expectedListEN.add(
				"118725-25-0, 1H-Benz(de)isochinoline-2(3H)-propanaminium, N-hexadecyl-N.N-dimethyl-1,3-dioxo-,bromide");
		expectedListEN.add("86608-70-0, (2-(1,3-Dioxolan-2-yl)ethyl)triphenylphosphonium bromide");
		expectedListEN.add("111-15-9, 2-Ethoxyethyl acetate");
		expectedListEN.add("111-15-9, Acetic acid, 2-ethoxyethyl ester");

		expectedListEN.add("103-09-3, 2-Ethylhexyl acetate");
		expectedListEN.add("110-49-6, 2-Methoxyethyl acetate");
		expectedListEN.add("2532-43-6, 3-Methylpyrazol-5-yl-dimethylcarbamate");
		expectedListEN.add("2532-43-6, Monometilan");
		expectedListEN.add("95154-01-1, (Benzothiazol-2-ylthio)succinic acid");
		expectedListEN.add(
				"-, (C16orC18-n-alkyl)(C16orC18-n-alkyl)ammonium 2-((C16orC18-n-alkyl)(C16orC18-n-alkyl)carbamoyl)benzenesulfonate");

		ArrayList<String> expectedList = (Locale.getDefault().toString().equals("de_DE")) ? expectedListDE
				: expectedListEN;



		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i), actualList.get(i).toString());
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime + " ms");

	}
}

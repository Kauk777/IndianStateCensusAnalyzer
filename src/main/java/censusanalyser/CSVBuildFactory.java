package censusanalyser;

public class CSVBuildFactory {

	public static ICSVBuilder createCsvFile() {
		return new openCSVBuilder();
	}
	
}

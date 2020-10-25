package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;


public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData.txt";
	private static final String INDIA_CENSUS_CSV_DELIMITER_FILE_PATH = "./src/test/resources/IndiaStateCensusDataDelimiterIncorrect.csv";
	private static final String INDIA_CENSUS_CSV_WRONG_HEADER_FILE_PATH = "./src/test/resources/IndiaStateCensusDataWrongHeader.csv";
	private static final String INDIA_STATECODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String INDIA_STATECODE_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
	private static final String INDIA_STATECODE_WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode.txt";
	private static final String INDIA_STATECODE_CSV_DELIMITER_FILE_PATH = "./src/test/resources/IndiaStateCodeDelimiterIncorrect.csv";
	private static final String INDIA_STATECODE_CSV_WRONG_HEADER_FILE_PATH = "./src/test/resources/IndiaStateCodeWrongHeader.csv";
	private static final String AREA_CENSUS_FILE="./src/test/resources/IndiaCensusArea.json";
	private static final String POPULATION_CENSUS_FILE="./src/test/resources/IndiaCensusPopulation.json";

	@Test
	public void givenIndianCensusCSVFile_ShouldReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}
	
	@Test
	public void givenIndianCensusCommonsCSVFile_ShouldReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusDataCommonsCsv(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaCensusCSVFile_WithWrongFilePath_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusCSVFile_WithWrongFileType_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusCSVFile_WithDelimiterIncorrect_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_DELIMITER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusCSVFile_WithWrongHeaderName_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianStateCsv_ShouldReturnExactCount() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfStateCode = censusAnalyser.loadIndiaStateCode(INDIA_STATECODE_CSV_FILE_PATH);
			Assert.assertEquals(37, numOfStateCode);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIndiaStateCodeCensusCSVFile_WithWrongFilePath_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(INDIA_STATECODE_WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	
	@Test
	public void givenIndiaStateCodeCensusCSVFile_WithWrongFileType_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(INDIA_STATECODE_WRONG_CSV_FILE_TYPE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	
	@Test
	public void givenIndiaStateCodeCensusCSVFile_WithDelimiterIncorrect_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(INDIA_STATECODE_CSV_DELIMITER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM, e.type);
		}
	}
	
	@Test
	public void givenIndiaStateCodeCensusCSVFile_WithWrongHeaderName_ShouldThrowCustomException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(INDIA_STATECODE_CSV_WRONG_HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM, e.type);
		}
	}
	
	@Test
	public void givenIndiaCensusData_WhenSorted_ShouldReturnResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortedCensusData = censusAnalyser.getStateWiseSortedData("state",false);
			IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
			Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIndiaStateCodeData_WhenSorted_ShouldReturnResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaStateCode(INDIA_STATECODE_CSV_FILE_PATH);
			String sortedStateCodeData = censusAnalyser.getStateCodeSortedData();
			IndiaStateCodeCSV[] censusCSV=new Gson().fromJson(sortedStateCodeData,IndiaStateCodeCSV[].class);
			Assert.assertEquals("AD", censusCSV[0].stateCode);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIndiaCensusData_WhenSortedByPoulation_ShouldReturnResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortedCensusData = censusAnalyser.getStateWiseSortedData("population",true);
			censusAnalyser.writingJsonFile(sortedCensusData, POPULATION_CENSUS_FILE);
			IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
			Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIndiaCensusData_WhenSortedByPopulationDensity_ShouldReturnResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortedCensusData = censusAnalyser.getStateWiseSortedData("densityPerSqKm",true);
			IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
			Assert.assertEquals("Bihar", censusCSV[0].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIndiaCensusData_WhenSortedByArea_ShouldReturnResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortedCensusData = censusAnalyser.getStateWiseSortedData("areaInSqKm",true);
			censusAnalyser.writingJsonFile(sortedCensusData, AREA_CENSUS_FILE);
			IndiaCensusCSV[] censusCSV=new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
			Assert.assertEquals("Rajasthan", censusCSV[0].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
}

package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
}

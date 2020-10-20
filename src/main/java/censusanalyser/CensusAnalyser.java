package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<IndiaCensusCSV> censusCSVIterator = this.getCsvIterator(reader, IndiaCensusCSV.class);
			Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM);
		}
	}

	public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<IndiaStateCodeCSV> stateCSVIterator = this.getCsvIterator(reader, IndiaStateCodeCSV.class);
			Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCSVIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM);
		}
	}

	private <E> Iterator<E> getCsvIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}

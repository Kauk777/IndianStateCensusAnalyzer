package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

import CSVBuilder.*;

public class CensusAnalyser {
	
	List<IndiaCensusCSV> censusCSVList = null;
			
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder=CSVBuildFactory.createCsvFile();
			censusCSVList = csvBuilder.getCsvList(reader, IndiaCensusCSV.class);
			return censusCSVList.size();
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
			ICSVBuilder csvBuilder=CSVBuildFactory.createCsvFile();
			Iterator<IndiaStateCodeCSV> stateCSVIterator = csvBuilder.getCsvIterator(reader, IndiaStateCodeCSV.class);
			return this.getCount(stateCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM);
		}
	}
	
	public int loadIndiaCensusDataCommonsCsv(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder=CSVBuildFactory.createCommonsCsvFile();
			Iterator<IndiaCensusCSV> censusCSVIterator=csvBuilder.getCsvIterator(reader, IndiaCensusCSV.class);
			return this.getCount(censusCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM);
		}
	}
	
	private <E> int getCount(Iterator<E> csvIterator) {
		Iterable<E> csvIterable = () -> csvIterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}

	public String getStateWiseSortedData() throws CensusAnalyserException {
		if(censusCSVList==null || censusCSVList.size()==0) {
			throw new CensusAnalyserException("No census data",
					CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<IndiaCensusCSV> censusComparator=Comparator.comparing(census->census.state);
		this.sort(censusComparator);
		String sortedStateData=new Gson().toJson(censusCSVList);
		return sortedStateData;
		
	}

	private void sort(Comparator<IndiaCensusCSV> censusComparator) {
		for(int i=0;i<censusCSVList.size()-1;i++) {
			for(int j=0;j<censusCSVList.size()-1;j++) {
				IndiaCensusCSV census1=censusCSVList.get(j);
				IndiaCensusCSV census2=censusCSVList.get(j+1);
				if(censusComparator.compare(census1, census2)>0) {
					censusCSVList.set(j, census2);
					censusCSVList.set(j+1, census1);
				}
				
			}
		}
	}
}

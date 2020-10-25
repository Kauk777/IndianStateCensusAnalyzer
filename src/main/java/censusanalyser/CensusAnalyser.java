package censusanalyser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

import CSVBuilder.*;

public class CensusAnalyser {
	
	List<IndiaCensusCSV> censusCSVList=null;
	List<IndiaStateCodeCSV> stateCodeList = null;
			
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder=CSVBuildFactory.createCsvFile();
			stateCodeList = csvBuilder.getCsvList(reader, IndiaStateCodeCSV.class);
			return stateCodeList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (Exception e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_DELIMITER_HEADER_PROBLEM);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	public void writingJsonFile(String gson, String filePath) {
		try(FileWriter writer=new FileWriter(filePath);) {
			writer.write(gson);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getStateWiseSortedData(String fieldName, boolean reverse) throws CensusAnalyserException {
		if(censusCSVList==null || censusCSVList.size()==0) {
			throw new CensusAnalyserException("No census data",
					CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator myComparator=sortByField(fieldName);
		if(reverse)
		Collections.sort(censusCSVList, myComparator.reversed());
		else
		Collections.sort(censusCSVList, myComparator);
		String sortedStateData=new Gson().toJson(censusCSVList);
		return sortedStateData;
	}
	
	public String getStateCodeSortedData() throws CensusAnalyserException {
		if(stateCodeList==null || stateCodeList.size()==0) {
			throw new CensusAnalyserException("No census data",
					CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<IndiaStateCodeCSV> censusComparator=Comparator.comparing(census->census.stateCode);
		this.sortStateCode(censusComparator);
		String sortedStateData=new Gson().toJson(stateCodeList);
		return sortedStateData;
		
	}

	private void sortStateCode(Comparator<IndiaStateCodeCSV> censusComparator) {
		for(int i=0;i<stateCodeList.size()-1;i++) {
			for(int j=0;j<stateCodeList.size()-1;j++) {
				IndiaStateCodeCSV census1= stateCodeList.get(j);
				IndiaStateCodeCSV census2= stateCodeList.get(j+1);
				if(censusComparator.compare(census1, census2)>0) {
					stateCodeList.set(j, census2);
					stateCodeList.set(j+1, census1);
				}
			}
		}
		
	}

	public Comparator<IndiaCensusCSV> sortByField(String fieldName) {
		Comparator<IndiaCensusCSV> comparator=new Comparator<IndiaCensusCSV>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public int compare(IndiaCensusCSV obj1, IndiaCensusCSV obj2) {
				try {
					Field field = IndiaCensusCSV.class.getDeclaredField(fieldName);
					field.setAccessible(true);
					Comparable field1=(Comparable) field.get(obj1);
					Comparable field2=(Comparable) field.get(obj2);
					return field1.compareTo(field2);
				} catch (Exception e) {
					// if filedName is not proper then return 0
					e.printStackTrace();
					return 0;
				} 
			}
		};
	return comparator;
	}
	
}

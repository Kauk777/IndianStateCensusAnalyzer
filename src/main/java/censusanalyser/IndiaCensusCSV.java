package censusanalyser;

import java.util.Comparator;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV implements Comparable<IndiaCensusCSV> {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getAreaInSqKm() {
		return areaInSqKm;
	}

	public void setAreaInSqKm(int areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}

	public int getDensityPerSqKm() {
		return densityPerSqKm;
	}

	public void setDensityPerSqKm(int densityPerSqKm) {
		this.densityPerSqKm = densityPerSqKm;
	}

	@Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
	
	@Override
	public int compareTo(IndiaCensusCSV stateCensus) {
		return this.state.compareTo(stateCensus.state);
	}
	
	class IndiaCensusComparator implements Comparator<IndiaCensusCSV> {
		public int compare(IndiaCensusCSV obj1, IndiaCensusCSV obj2) {
			return obj1.state.compareTo(obj2.state);
		}
	}  
}

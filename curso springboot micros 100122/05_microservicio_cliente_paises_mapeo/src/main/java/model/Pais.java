package model;

import java.util.List;
import java.util.Map;

public class Pais {
	    private NombrePais name;
	    private List<String> capital;
	    private Map<String, String> languages;
	    private double area;
	    private String flag;
	    private long population;

	    public Pais(NombrePais name, List<String> capital, Map<String, String> languages, double area, String flag,
				long population) {
			super();
			this.name = name;
			this.capital = capital;
			this.languages = languages;
			this.area = area;
			this.flag = flag;
			this.population = population;
		}
		public Pais() {
			super();
			// TODO Auto-generated constructor stub
		}
		// Getters y setters
	    public NombrePais getName() { return name; }
	    public void setName(NombrePais name) { this.name = name; }

	    public List<String> getCapital() { return capital; }
	    public void setCapital(List<String> capital) { this.capital = capital; }

	    public Map<String, String> getLanguages() { return languages; }
	    public void setLanguages(Map<String, String> languages) { this.languages = languages; }

	    public double getArea() { return area; }
	    public void setArea(double area) { this.area = area; }

	    public String getFlag() { return flag; }
	    public void setFlag(String flag) { this.flag = flag; }

	    public long getPopulation() { return population; }
	    public void setPopulation(long population) { this.population = population; }
}

/*
[
   {
      "name":{
         "common":"Ireland",
         "official":"Republic of Ireland"
      },
      "capital":[
         "Dublin",
         "Dublin2"
      ],
      "languages":{
         "eng":"English",
         "gle":"Irish"
      },
      "area":70273.0,
      "flag":"🇮🇪",
      "population":4994724
   },
   ...
]



*/
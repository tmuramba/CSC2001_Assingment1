public class PlaceNameEntry {
    private int Id;
    private String placename;
    private String province;
    private int population;
    private String municipality;
    PlaceNameEntry(int Id, String placename,String municipality, String province,int population){
        this.Id = Id;
        this.placename = placename;
        this.population = population;
        this.province = province;
        this.municipality = municipality;
    }

    public int getId(){
        return Id;
    }
    public String getPlacename(){
        return placename;
    }
    public String getProvince(){
        return province;
    }
    public int getPopulation(){
        return population;
    }
    public String getMunicipality(){return municipality;}
}

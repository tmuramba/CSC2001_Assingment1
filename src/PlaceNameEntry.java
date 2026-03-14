public class PlaceNameEntry {
    private int Id;
    private String placename;
    private String province;
    private int population;
    PlaceNameEntry(int Id, String placename, String province,int population){
        this.Id = Id;
        this.placename = placename;
        this.population = population;
        this.province = province;
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
}

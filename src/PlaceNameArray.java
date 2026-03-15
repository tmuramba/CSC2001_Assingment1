import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PlaceNameArray {
    
    String DataPath;
    Integer n;
    private ArrayList<ArrayList<String>> records = new ArrayList<>();
    
    PlaceNameArray(String Path, Integer n){
        this.DataPath = Path;
        this.n = n;
    }

    public class Data{
        Boolean found;
        int count;
        PlaceNameEntry entry;

        Data(Boolean found, int count, PlaceNameEntry entry){
            this.found = found;
            this.count = count;
            this.entry = entry;
        }
    }

    public Data findElement(ArrayList<ArrayList<String>> nestedList, String target) {
        int count = 0;

        for (ArrayList<String> innerList : nestedList) {
            if (innerList.get(1).equalsIgnoreCase(target)) {
                PlaceNameEntry entry = new PlaceNameEntry(Integer.parseInt(innerList.get(0)),innerList.get(1),innerList.get(2),innerList.get(3),Integer.parseInt(innerList.get(4)));
                return new Data(true, count, entry);
            }
            count++;
        }

        return new Data(false, count, null);
    }

    public void readCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader(DataPath))) {
            System.out.println("sucess");
            String line;
            int i = 0;
            if(n != 0){
                while (i < n){
                    if (i == 0){
                        br.readLine();
                        i = 1;
                    }
                    line = br.readLine();
                    String[] parts = line.split(",");
                    if(records.isEmpty()) {
                        ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                        records.add(row);
                    }else {
                        if(findElement(records, parts[1]).found == false){
                            ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                            records.add(row);
                        }

                    }
                    i++;
                }
            }else {
                if (i == 0){
                    br.readLine();
                    i = 1;
                }
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if(records.isEmpty()) {
                        ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                        records.add(row);
                    }else {
                        if(findElement(records, parts[1]).found == false){
                            ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                            records.add(row);
                        }

                    }
                }
            }
        }
        catch (Exception e) {
        }
    }

    public void findPlaceName(String placename){
        Data record = findElement(records,placename);
        if(record.found){
            System.out.println("Place name found");
            System.out.println("Id: " + Integer.toString(record.entry.getId()));
            System.out.println("Place Name: " + record.entry.getPlacename());
            System.out.println("Municipality: " + record.entry.getMunicipality());
            System.out.println("Province: " + record.entry.getProvince());
            System.out.println("Population: " + Integer.toString(record.entry.getPopulation()));
            System.out.println("Number of comparisons: " + Integer.toString(record.count));
        }else {
            System.out.println("Placename not found");
            System.out.println("Would you like to try again");
        }

    }




    // Getter for data
    public int getData() {
        return records.size();
    }

}

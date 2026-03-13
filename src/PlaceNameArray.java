import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PlaceNameArray {
    
    String DataPath;
    public Integer n;
    private ArrayList<ArrayList<String>> records = new ArrayList<>();
    
    PlaceNameArray(String Path, Integer n){
        this.DataPath = Path;
        this.n = n;
    }

    public class Data{
        Boolean found;
        int count;
        ArrayList record;

        Data(Boolean found, int count, ArrayList record){
            this.found = found;
            this.count = count;
            this.record = record;
        }
    }

    public Data findElement(ArrayList<ArrayList<String>> nestedList, String target) {
        int count = 0;

        for (ArrayList<String> innerList : nestedList) {
            if (innerList.get(1).equals(target)) {
                return new Data(true, count, innerList);
            }
            count++;
        }

        return new Data(false, count, null);
    }

    public void readCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader(DataPath))) {
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
            e.printStackTrace();
        }
    }

    // Getter for data
    public int getData() {
        return records.size();
    }

}

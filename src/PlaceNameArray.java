import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PlaceNameArray {
    
    String DataPath;
    public Integer n;
    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    
    PlaceNameArray(String Path, Integer n){
        this.DataPath = Path;
        this.n = n;
    }

    public static boolean findElement(ArrayList<ArrayList<String>> nestedList, String target) {
        for (ArrayList<String> innerList : nestedList) {
            for (String element : innerList) {
                if (element.equals(target)) {
                    return true; // Element found
                }
            }
        }
        return false; // Element not found after checking all lists
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
                    if(data.isEmpty()) {
                        ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                        data.add(row);
                    }else {
                        if(findElement(data, parts[1]) == false){
                            ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                            data.add(row);
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
                    if(data.isEmpty()) {
                        ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                        data.add(row);
                    }else {
                        if(findElement(data, parts[1]) == false){
                            ArrayList<String> row = new ArrayList<>(Arrays.asList(parts));
                            data.add(row);
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
        return data.size();
    }

}

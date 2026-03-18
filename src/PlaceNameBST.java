import java.io.BufferedReader;
import java.io.FileReader;

public class PlaceNameBST {
    String DataPath;
    Integer n;
    private Node root;
    private int size = 0;

    PlaceNameBST(String Path, Integer n){
        this.DataPath = Path;
        this.n = n;
    }

    private static class Node {
        PlaceNameEntry entry;
        Node left;
        Node right;

        Node(PlaceNameEntry entry){
            this.entry = entry;
        }
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

    private boolean insertEntry(PlaceNameEntry entry) {
        if (root == null) {
            root = new Node(entry);
            size++;
            return true;
        }

        Node current = root;
        while (true) {
            int i = entry.getPlacename().compareToIgnoreCase(current.entry.getPlacename());
            if (i == 0) {
                // Duplicate place name, do not insert
                return false;
            }
            if (i < 0) {
                if (current.left == null) {
                    current.left = new Node(entry);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(entry);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Data findElement(String target) {
        Node current = root;
        int count = 0;

        while (current != null) {
            count++;
            int i = target.compareToIgnoreCase(current.entry.getPlacename());
            if (i == 0) {
                return new Data(true, count, current.entry);
            }
            if (i < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
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
                    PlaceNameEntry entry = new PlaceNameEntry(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                    insertEntry(entry);
                    i++;
                }
            }else {
                if (i == 0){
                    br.readLine();
                    i = 1;
                }
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    PlaceNameEntry entry = new PlaceNameEntry(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                    insertEntry(entry);
                }
            }
        }
        catch (Exception e) {
        }
    }

    public void findPlaceName(String placename){
        Data record = findElement(placename);
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
        return size;
    }

}

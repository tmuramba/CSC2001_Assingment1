// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        PlaceNameArray placeNameArray = new PlaceNameArray("SAPlaceNames.csv", 300);
        placeNameArray.readCSV();
        System.out.println(placeNameArray.getData());
        placeNameArray.findPlaceName("Kliprand");
    }
}
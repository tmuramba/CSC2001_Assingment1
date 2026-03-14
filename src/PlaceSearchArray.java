import java.util.Scanner;

public class PlaceSearchArray {

    public static void main(String[] args){
        PlaceNameArray placeNameArray;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Welcome");
            System.out.print("Please enter the path of the csv file you wish to load and the number of records you wish to load: ");
            String read = sc.nextLine();
            String[] path_n = read.split(" ");
            try {
                placeNameArray = new PlaceNameArray(path_n[0], Integer.parseInt(path_n[1]));
                System.out.println("Please enter the place name you wish to find?");
                String placename = sc.nextLine();

            }catch (Exception e){
                System.out.println("Please enter correct file path");
            }

        }
    }
}

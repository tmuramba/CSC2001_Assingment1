import java.util.Scanner;

public class PlaceSearchBST {
    public static void main(String[] args){
        PlaceNameBST placeNameBST;
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Welcome please choose one of the following options:");
            System.out.println("1. load data records");
            System.out.println("2. Exit");
            String input = sc.nextLine();
            if(Integer.parseInt(input.trim()) == 1){
                while (true) {
                    System.out.println("Please enter file path of records and number of records to load or type exit to leave:");
                    String path = sc.nextLine();
                    if(path.equalsIgnoreCase("exit")){
                        System.out.println("Thank you Goodbye :) ");
                        System.exit(0);
                    }else {
                        String[] path_n = path.split(" ");
                        try {
                            placeNameBST = new PlaceNameBST(path_n[0], Integer.parseInt(path_n[1]));
                            placeNameBST.readCSV();
                            System.out.println("Load sucesfull");
                            while (true) {
                                System.out.println("Please enter the place name you would like to find or press exit to quit");
                                String placeName = sc.nextLine();
                                if(placeName.equalsIgnoreCase("exit")){
                                    System.out.println("Thank you bye :) ");
                                    System.exit(0);
                                }else {
                                    placeNameBST.findPlaceName(placeName.trim());
                                }

                            }
                        }catch (Exception e){
                            System.out.println("File not found.");
                        }
                    }
                }
            } else if (Integer.parseInt(input.trim()) == 2) {
                System.out.println("Thank you bye :) ");
                System.exit(0);
            }else {
                System.out.println("Please enter one of the options");
                System.out.println();
            }
        }
    }
}

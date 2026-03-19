import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;

public class PlaceExperiment {


    public static void main(String[] arrgs){

        int[] n ={1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        String[][] results = new String[10][5];
        int arr_avg = 0;
        int bst_asis = 0;
        int sorted_bst = 0;
        int optimal_bst= 0 ;
        try (BufferedReader br = new BufferedReader(new FileReader("SearchQueries.txt"))){

            for (int i = 0; i < n.length; i++){
                String lineQ;
                PlaceNameArray pna = new PlaceNameArray("SAPlaceNames.csv", n[i]);
                while (br.readLine() != null){
                    PlaceNameArray.Data record = pna.findPlaceName("Kliprand");
                    System.out.println(record.found);
                    arr_avg += record.count;
                    br.readLine();

                }
                BufferedReader br1 = new BufferedReader(new FileReader("SearchQueries.txt"));
                PlaceNameBST pnb = new PlaceNameBST("SAPlaceNames.csv", n[i]);
                while (br1.readLine() != null){
                    PlaceNameBST.Data record = pnb.findPlaceName(br1.readLine());
                    bst_asis += record.count;
                }
                BufferedReader br2 = new BufferedReader(new FileReader("SAPlaceNames.csv"));
                br2.readLine();
                String[][] line = new String[n[i]][5];
                int j = 0;
                while (j < n[i]){
                    String[] temp = br2.readLine().split(",");
                    line[j][0] = temp[0];
                    line[j][1] = temp[1];
                    line[j][2] = temp[2];
                    line[j][3] = temp[3];
                    line[j][4] = temp[4];
                    j += 1;
                }
                Arrays.sort(line, Comparator.comparing(row -> row[1]));

                try ( BufferedWriter myWriter =  new BufferedWriter(new FileWriter("SortedAZ.txt"))){
                    int k = 0;
                    while (k < line.length){
                        myWriter.write(line[k][0] + ","+ line[k][1] + "," + line[k][2] + "," + line[k][3] + "," + line[k][4]);
                        myWriter.newLine();
                        k += 1;
                    }
                    BufferedReader br3 = new BufferedReader(new FileReader("SearchQueries.txt"));
                    while (br3.readLine() != null){
                        PlaceNameBST pnb1 = new PlaceNameBST("SortedAZ.txt", i);
                        PlaceNameBST.Data record = pnb1.findPlaceName(br3.readLine());
                        sorted_bst += record.count;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                try (BufferedWriter myWriter2 = new BufferedWriter(new FileWriter("Optimal.txt"))) {

                    BufferedReader br4 = new BufferedReader(new FileReader("SAPlaceNames.csv"));
                    br4.readLine(); // skip header

                    String[][] line2 = new String[n[i]][5];

                    int l = 0;
                    while (l < n[i]) {
                        String[] temp = br4.readLine().split(",");
                        for (int c = 0; c < 5; c++) {
                            line2[l][c] = temp[c];
                        }
                        l++;
                    }

                    BufferedReader br5 = new BufferedReader(new FileReader("SAPlaceNamesOptimal.txt"));
                    String optimalLine;
                    while ((optimalLine = br5.readLine()) != null) {
                        String searchName = optimalLine.trim();
                        for (int m = 0; m < line2.length; m++) {
                            if (line2[m][1].equals(searchName)) {
                                myWriter2.write(line2[m][0] + "," + line2[m][1] + "," + line2[m][2] + "," + line2[m][3] + "," + line2[m][4]);
                                myWriter2.newLine();
                                break;
                            }
                        }
                    }
                    BufferedReader br6 = new BufferedReader(new FileReader("SearchQueries.txt"));
                    while ((lineQ = br.readLine()) != null){
                        PlaceNameBST pnb1 = new PlaceNameBST("SortedAZ.txt", i);
                        PlaceNameBST.Data record = pnb1.findPlaceName(lineQ);
                        sorted_bst += record.count;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(arr_avg / 50);
        System.out.println(bst_asis / 50);
        System.out.println(sorted_bst / 50);
        System.out.println(optimal_bst / 50);
    }
}

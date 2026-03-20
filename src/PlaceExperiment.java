import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PlaceExperiment {


    public static void main(String[] arrgs){

        int[] n ={1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        String[][] results = new String[10][5];

        try {

            System.out.println("N          Array           BST(as-is)           BST(sorted)       BST(optimal)");
            //loop to move through array n
            for (int i = 0; i < n.length; i++){
                float arr_avg = 0;
                float bst_asis = 0;
                float sorted_bst = 0;
                float optimal_bst= 0 ;
                String lineQ;
                BufferedReader br = new BufferedReader(new FileReader("SearchQueries.txt"));
                PlaceNameArray pna = new PlaceNameArray("SAPlaceNames.csv", n[i]);
                pna.readCSV();
                //calling place name array
                while ((lineQ = br.readLine()) != null){
                    PlaceNameArray.Data record = pna.findPlaceName(lineQ);
                    arr_avg += record.count;

                }
                BufferedReader br1 = new BufferedReader(new FileReader("SearchQueries.txt"));
                PlaceNameBST pnb = new PlaceNameBST("SAPlaceNames.csv", n[i]);
                pnb.readCSV();
                // calling place name bst
                while ((lineQ = br1.readLine()) != null){
                    PlaceNameBST.Data record = pnb.findPlaceName(lineQ);
                    bst_asis += record.count;
                }
                BufferedReader br2 = new BufferedReader(new FileReader("SAPlaceNames.csv"));
                br2.readLine();
                String[][] line = new String[n[i]][5];
                int j = 0;
                // reading place names csv and storing into a array
                while (j < n[i]){
                    String[] temp = br2.readLine().split(",");
                    line[j][0] = temp[0];
                    line[j][1] = temp[1];
                    line[j][2] = temp[2];
                    line[j][3] = temp[3];
                    line[j][4] = temp[4];
                    j += 1;
                }
                //sorting the array
                Arrays.sort(line, Comparator.comparing(row -> row[1]));

                try ( BufferedWriter myWriter =  new BufferedWriter(new FileWriter("SortedAZ.txt"))){
                    int k = 0;
                    // writing to txt file because place name bst accepts files only
                    while (k < line.length){
                        myWriter.write(line[k][0] + ","+ line[k][1] + "," + line[k][2] + "," + line[k][3] + "," + line[k][4]);
                        myWriter.newLine();
                        k += 1;
                    }
                    BufferedReader br3 = new BufferedReader(new FileReader("SearchQueries.txt"));
                    PlaceNameBST pnb1 = new PlaceNameBST("SortedAZ.txt", n[i]);
                    pnb1.readCSV();
                    // calling pace name bst
                    while ((lineQ = br3.readLine()) != null){
                        PlaceNameBST.Data record = pnb1.findPlaceName(lineQ);
                        sorted_bst += record.count;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                // write to a file because not place name search does not work with out  id, municipality etc
                try (BufferedWriter myWriter2 = new BufferedWriter(new FileWriter("Optimal.txt"))) {

                    BufferedReader br4 = new BufferedReader(new FileReader("SAPlaceNames.csv"));
                    br4.readLine(); // skip header

                    String[][] line2 = new String[14062][5];

                    int l = 0;
                    // reading all of the place names csv
                    while (l < 14062) {
                        String[] temp = br4.readLine().split(",");
                        for (int c = 0; c < 5; c++) {
                            line2[l][c] = temp[c];
                        }
                        l++;
                    }
                    Arrays.sort(line2, Comparator.comparing(row -> row[1]));
                    BufferedReader br5 = new BufferedReader(new FileReader("SAPlaceNamesOptimal.txt"));
                    String optimalLine;
                    ArrayList<ArrayList<String>> lines = new ArrayList<>();
                    while ((optimalLine = br5.readLine()) != null) {
                        String searchName = optimalLine.trim();
                        int initial = 0;
                        for (int m = 0; m < line2.length; m++) {
                            if (line2[m][1].equals(searchName) && initial == 0) {
                                myWriter2.write(line2[m][0] + "," + line2[m][1] + "," + line2[m][2] + "," + line2[m][3] + "," + line2[m][4]);
                                myWriter2.newLine();
                                initial = 1;
                            }// to remove duplicates
                            else if (line2[m][1].equals(searchName) && line2[m -1][1].equals(line2[m][1]) == false){
                                myWriter2.write(line2[m][0] + "," + line2[m][1] + "," + line2[m][2] + "," + line2[m][3] + "," + line2[m][4]);
                                myWriter2.newLine();
                            }
                        }
                    }
                    BufferedReader br6 = new BufferedReader(new FileReader("SearchQueries.txt"));
                    PlaceNameBST pnb1 = new PlaceNameBST("Optimal.txt", n[i]);
                    pnb1.readCSV();
                    while ((lineQ = br6.readLine()) != null){
                        PlaceNameBST.Data record = pnb1.findPlaceName(lineQ);
                        optimal_bst += record.count;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(n[i] + "      " +  arr_avg/50 + "              " + bst_asis / 50+ "               " + sorted_bst / 50+ "             " + optimal_bst/50);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

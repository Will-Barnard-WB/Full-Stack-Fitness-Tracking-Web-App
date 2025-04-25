import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DataReader dr = new DataReader("D7LA3958.csv");
        ArrayList<ArrayList<String>> myArray = dr.readCSV();
        splitCSVtoDatabase sCtD = new splitCSVtoDatabase();
        sCtD.sendSplits(myArray);
    }
}

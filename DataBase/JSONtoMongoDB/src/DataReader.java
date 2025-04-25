import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DataReader
{
    private ArrayList<ArrayList<String>> data;
    private String filename;

    public DataReader(String filename)
    {
        this.filename = filename;
        data = readCSV();

    }

    public ArrayList<ArrayList<String>> readCSV()
    {

        String[] temp = {"timestamp","position_lat","position_long","distance","altitude","speed","cadence","enhanced_altitude","enhanced_speed"};
        ArrayList<String> types = new ArrayList<>();
        ArrayList<ArrayList<String>> allData = new ArrayList<>();
        for(int i = 0; i < temp.length; i++)
        {
            types.add(temp[i]);
            allData.add(new ArrayList<>());
        }
        try
        {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(filename));
            for(int i = 0; i<29; i++)
            {
                line = br.readLine();
            }
            while(line != null)
            {

                String[] data = line.split(",");
                if(Objects.equals(data[0], "Data") && Objects.equals(data[1], "12"))
                {

                    for(int i = 3; i<data.length; i+=3)
                    {
                        if(types.contains(data[i]))
                        {
                            allData.get(types.indexOf(data[i])).add(data[i+1]);
                        }
                    }
                }
                line = br.readLine();

            }


        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return allData;
    }

    public ArrayList<Double> calculateSplits(ArrayList<ArrayList<String>> data)
    {
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Double> speeds = new ArrayList<>();
        ArrayList<Double> kmSpeeds = new ArrayList<>();
        for(int i = 0; i < data.get(0).size(); i++)
        {
            distances.add(Double.parseDouble(data.get(3).get(i).replace("\"","")));
            speeds.add(Double.parseDouble(data.get(5).get(i).replace("\"","")));
        }
        double sum = 0;
        int count = 1;
        double prevDis = 0;
        for(int i = 0; i < distances.size(); i++)
        {
            if(distances.get(i)/1000 >= count)
            {
                sum += speeds.get(i)*((distances.get(i)-prevDis)/1000);
                prevDis = distances.get(i);
                count++;
                kmSpeeds.add(1000/(sum*60));
                sum = 0;
            }
            else
            {
                sum += speeds.get(i)*((distances.get(i)-prevDis)/1000);
                prevDis = distances.get(i);
            }
        }
        return kmSpeeds;
    }
}

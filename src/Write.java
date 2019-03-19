import java.io.BufferedWriter;
import java.io.FileWriter;
import com.csvreader.CsvWriter;

public class Write {
    private static final String PATH = "report.csv";
    public static void main(String[] args) {
        BufferedWriter bw;
        String[] array = {"aa", "bb", "cc", "dd"};
        try {
            bw = new BufferedWriter(new FileWriter(PATH, true));
            CsvWriter out = new CsvWriter(bw, ',');
            out.writeRecord(array);
            out.flush();
            bw.flush();
            out.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

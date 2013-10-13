package imran.demo.hackday;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NumbersQueue {

    private static final String DATA_DIR = "D:\\Temp\\number_dists\\";
//    private static final String DATA_DIR = "/mnt/hgfs/workspace/hack-day-demo/number_dists/";

    public static List<Long> readNumbers(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(DATA_DIR+file));

        List<Long> lines = new ArrayList<Long>();

        String line = null;

        while ((line = in.readLine()) != null) {
            lines.add(new Long(line));
        }

        in.close();

        return lines;
    }
}

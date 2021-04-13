package fr.iscgroup.isc.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author emoqu on 10/03/2021
 * @project ISC
 */
public class CSVUtils {
    public static final String OUTPUTDATA_HEADER[] = new String[]{"size", "distance", "duration",
            "errorRate", "effectiveDistance", "X", "Y"};

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void writeInFile(ArrayList<String[]> data, String filename) throws IOException {
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    /*public ArrayList<String[]> fromOutputData(String[] header, ArrayList<OutputData> data){
        ArrayList<String[]> res = new ArrayList<>();
        for (OutputData datum : data)
            res.add(datum.toStringArray());
        return res;
    }*/
}

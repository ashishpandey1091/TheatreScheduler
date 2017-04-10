/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theatreseating;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashish
 */
public class TheatreSeating {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        URL url = TheatreSeating.class.getResource("dataFile.txt");
        File file = null;

        Map<Integer, ArrayList<Integer>> theatreLayoutFromFile = new HashMap<>();
        Map<String, Integer> ticketRequestsFromFile = new LinkedHashMap<>();
        
        SeatScheduler seatScheduler = new SeatScheduler();
       
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException ex) {
            System.out.println("Exception occured:");
            System.err.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Exception occured:");
            System.err.println(ex.getMessage());
        }

        String line = null;
        FileReader files = null;
        try {
            files = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheatreSeating.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(files);
        int key = 0;
        boolean hasNonAlpha = false;
        while (true) {
            try {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    hasNonAlpha = line.matches(".*[a-zA-Z]+.*");
                }
                key++;
            } catch (IOException ex) {
                Logger.getLogger(TheatreSeating.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (line == null) {
                break;
            } else if (!line.isEmpty() && hasNonAlpha == false) {
                ArrayList<Integer> value = parseIntegerFromLine(line);
                theatreLayoutFromFile.put(key, value);
            } else {
                String[] line_parts = line.split(" ");
                if (line_parts.length > 1) {
                    StringBuilder desc = new StringBuilder(line_parts[0]);
                    for (int i = 2; i < line_parts.length; i++) {
                        desc.append(line_parts[i]);
                    }
                    ticketRequestsFromFile.put(desc.toString(), new Integer(line_parts[1]));
                }
            }
        }
        theatreLayoutFromFile.entrySet().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());
        });
        ticketRequestsFromFile.entrySet().forEach((entry) -> {
            System.out.println("Key : " + entry.getKey() + " value : " + entry.getValue());
        });
        seatScheduler.setTheatreLayout(theatreLayoutFromFile);
        seatScheduler.setTicketRequests(ticketRequestsFromFile);
//        System.out.println(seatScheduler.getTheatreLayout());
//        System.out.println(seatScheduler.getTicketRequests());
          System.out.println(seatScheduler.schedule());
       
    }

    private static ArrayList<Integer> parseIntegerFromLine(String line) {
        ArrayList<Integer> integerList = new ArrayList<>();
        String[] splitted = line.split("\\s+");
        for (String s : splitted) {
            //System.out.println("Compute Line : "+s);
            integerList.add(Integer.parseInt(s));
        }
        return integerList;
    }

}

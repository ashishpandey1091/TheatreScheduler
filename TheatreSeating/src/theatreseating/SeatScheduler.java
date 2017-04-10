/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theatreseating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ashish
 */
public class SeatScheduler {

    Map<Integer, ArrayList<Integer>> theatreLayout = new HashMap<>();
    Map<String, Integer> ticketRequests = new LinkedHashMap<>();

    public SeatScheduler() {
    }

    public void setTheatreLayout(Map<Integer, ArrayList<Integer>> theatreLayout) {
        this.theatreLayout = theatreLayout;
    }

    public void setTicketRequests(Map<String, Integer> ticketRequests) {
        this.ticketRequests = ticketRequests;
    }

    public String schedule() {
       // System.out.println(theatreLayout);
        //System.out.println(ticketRequests);
        int allotted =0;
        int sum  = 0;
        
        
        for (Map.Entry<String, Integer> entry : ticketRequests.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            ArrayList<String> result = new ArrayList<>();
            for (Map.Entry<Integer, ArrayList<Integer>>  entries : theatreLayout.entrySet())
            {
                Integer bookingKey = entries.getKey();
                //System.out.println("The key is " +entries.getKey());           
                    
                for(Integer bookingValue: entries.getValue())
                {       
                    sum+=bookingValue;
                    System.out.println("Sum is" + sum);
                    if(bookingValue <= value){
                        System.out.println("Matched " + bookingValue.intValue() +  " " + key + " " + bookingKey);
                    }
                    
                }
                
            }
           // System.out.println("Sum is" + sum);
    // now work with key and value...
            //System.out.println(key+ " " + value);
}
        return ticketRequests.keySet().toString();
    }
    public Map<Integer, ArrayList<Integer>> getTheatreLayout() {
        return theatreLayout;
    }

    public Map<String, Integer> getTicketRequests() {
        return ticketRequests;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}

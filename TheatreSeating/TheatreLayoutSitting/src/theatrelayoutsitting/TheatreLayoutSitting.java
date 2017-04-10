/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theatrelayoutsitting;

/**
 *
 * @author Ashish
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class TheatreLayoutSitting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        URL url = TheatreLayoutSitting.class.getResource("dataFile.txt");
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException ex) {
            System.out.println("Exception occured:");
            System.err.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Exception occured:");
            System.err.println(ex.getMessage());
        }
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheatreLayoutSitting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ArrayList<String> seating = new ArrayList<String>();
        
         ArrayList<int[]> seating = new ArrayList<int[]>();
         ArrayList<String> requestedName = new ArrayList<String>();
         ArrayList<Integer> requestedTicket = new ArrayList<Integer>();
         ArrayList<String> outputList = new ArrayList<String>();
         
        while(true)
        {
            String nextLine = sc.nextLine();
           
            //If empty line is found
            if ( nextLine.equals("") ) {
                nextLine = sc.nextLine();
                while(nextLine!=null) {
                    if (nextLine.isEmpty()) { 
                        break;
                    }

                    String nameTicketRequest[] = nextLine.split(" ");                        
                    if(nameTicketRequest.length==2) {
                        requestedName.add(nameTicketRequest[0]);
                        requestedTicket.add(Integer.parseInt(nameTicketRequest[1]));
                    } 
                    nextLine = sc.nextLine();
                }
               break;
            } 
            //Parsing the theatre layout
            seating.add(Stream.of(nextLine.split(" ")).mapToInt(token -> Integer.parseInt(token)).toArray());               
        }
        
        
    int highestSectionSeats = 0;
    int sumOfAvailableSeats = 0;  
    
//Find highest number of seats and total number of seats in the theatre layout
    for (int[] array : seating) {
        for (int value : array) {
            sumOfAvailableSeats+=value;
            if (value > highestSectionSeats) {
                highestSectionSeats = value;
            }
        }
    }
    
    
    int [] storePrevious = new int[3];
        
    int availableSeats;
    int requestedSeats;
    boolean foundExactSeats;
    for(int requestNumber = 0; requestNumber<requestedTicket.size();requestNumber++) {
        requestedSeats = requestedTicket.get(requestNumber);
        
        //seats
        storePrevious[0] = seating.get(0)[0];

        //row number
        storePrevious[1] = 0;

        //section number
        storePrevious[2] = 0;
        
        //
        foundExactSeats = false;
                
        for(int row = 0; row < seating.size(); row++){
            
            for( int section = 0; section < seating.get(row).length; section++) {                
                
                availableSeats = seating.get(row)[section];                 
                
                if(availableSeats<=requestedSeats && availableSeats>0)
                {
                    if(availableSeats==requestedSeats)
                    {
                          storePrevious[0] = requestedSeats;
                          storePrevious[1] = row;
                          storePrevious[2] = section; 
                        break;
                        
                    }
                    else{
                        if(storePrevious[0]<requestedSeats)
                        {
                            
                        }
                    }
                } 
                }
            
            if(highestSectionSeats<requestedSeats && sumOfAvailableSeats<requestedSeats)                
                {
                    System.out.println(requestedName.get(requestNumber)+" Sorry, we can't handle your party.");
                    break;
                }
                else if(highestSectionSeats<requestedSeats && sumOfAvailableSeats>=requestedSeats)
                {
                    System.out.println(requestedName.get(requestNumber)+" Call to split party.");
                    break;
                }
                else
                {    
                            System.out.println(requestedName.get(requestNumber)+" Row "+(storePrevious[1]+1)+" Section "+(storePrevious[2]+1));
                            int seatsLeft = seating.get(storePrevious[1])[storePrevious[2]] - requestedSeats;
                            
                            for(int i = 0; i < seating.get(storePrevious[1]).length; i++)
                            {
                                
                            }
                            //int []updateSeats =  
                            //seating.set(row, )
                            //        seating.set(storePrevious[1], storePrevious)
                            //seating.get(storePrevious[1])[storePrevious[2]] = seating.get(storePrevious[1])[storePrevious[2]] - requestedSeats;
                            sumOfAvailableSeats -= requestedSeats;
                            break;
                    
            }
            
        }
    }
  
      
    }
    
}

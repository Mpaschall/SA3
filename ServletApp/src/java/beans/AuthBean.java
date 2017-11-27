package beans;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xdark
 */
public class AuthBean implements Serializable{
    
    //variables
    private String first;
    private String last;
    private String address;
    private String number;
    private String sec;
    private java.util.Date exp;
   
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    //getters and setters
    public void setLast(String last) {   
        this.last = last;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public java.util.Date getExp() {
        return exp;
    }

    public void setExp(java.util.Date exp) {
        this.exp = exp;
    }
    
    //read from file, and check user input
    public boolean checkEntry(String first, String last, String address, 
            String number, String sec, java.util.Date exp){
        String[] values = null;
        String data;
        String fileName = "test.csv";
        File file = new File(fileName);
    
        
        try(Scanner inputStream = new Scanner(file)) {
            inputStream.next();
            while(inputStream.hasNext()){
                data = inputStream.next();
                if(data == first + ", " + last + ", " + address + ", " + number + 
                        ", " + sec + ", " + exp){
                    return true;
                }
                else{
                }
            }
        }
         catch (FileNotFoundException ex) {
            Logger.getLogger(AuthBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //default constructor
    public AuthBean(){

    }

    
}

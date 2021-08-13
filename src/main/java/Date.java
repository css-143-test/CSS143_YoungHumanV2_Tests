
import java.io.Serializable;

public class Date implements Comparable<Object>, Cloneable, Serializable{

    private int month; 
    private int day;    
    private int year; 
    
    public Date(int month, int day, int year){
        //write your code here
    }

    public Date(Date other){
        //write your code here
    }
    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getYear() {
        return this.year;
    }

    public void setMonth(int month) {
        //write your code here
    }

    public void setDay(int day) {
        //write your code here
    }

    public void setYear(int year) {
        //write your code here
    }

    public boolean equals(Object otherObject){
        //write your code here
        return true;
    }

    public String toString(){
        //write your code here
        return "";
    }

    @Override
    public int compareTo(Object other) {
      //write your code here
        return -1;
    }
    
    @Override
    public Date clone() {
        return null;
    }
}


import java.io.Serializable;

public class Weight implements Comparable<Object>, Cloneable, Serializable{
    private int lbs;
    private int oz;

    public Weight(int lbs){
        //write your code here
    }


    public Weight(int lbs, int oz){
        //write your code here
    }

    public Weight(Weight other){
        //write your code here
    }

    public int getOunces() {
        //write your code here
        return 0;
    }

    public int getPounds() {
        //write your code here
        return 0;
    }
    
    public void setPounds(int lbs) {
        //write your code here
    }

    public void setOunces(int oz) {
        //write your code here
    }

    public double getWeight(){
        //write your code here
        return 0;
    }
    
    public void setWeight(int lbs, int oz){
        //write your code here
    }

    public void add(int lbs){
        //write your code here
    }

    public void add(int lbs, int oz){
        //write your code here
    }

    public void add(Weight other){
        //write your code here
    }

    public boolean equals(Object o){
        //write your code here
        return false;
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
    public Weight clone() {
        //write your code here
        return null;
    }

}

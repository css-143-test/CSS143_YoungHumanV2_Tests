
import java.io.Serializable;

public class YoungHuman implements Comparable<Object>, Cloneable, Serializable{
    private Weight currentWeight;
    private Date birthDate;
    private Date lastCheckUpDate;
    private String firstName;
    private String lastName;

    public YoungHuman(Weight weight, Date birthDate, String firstName, String lastName){
        //write your code here
    }

    public YoungHuman(YoungHuman toCopy){
        //write your code here
    }

    public Date getBirthDate() {
        //write your code here
        return null;
    }

    public Date getLastCheckUpDate(){
        //write your code here
        return null;
    }

    public String getName(){
        //write your code here
        return "";
    }

    public boolean hasHadCheckUp(){
        //write your code here
        return true;
    }

    public void setCheckUp(Date dateOfCheckUp){
        //write your code here
    }

    public void unsetCheckUpDate(){
        //write your code here
    }

    public void setBirthDate(Date birthDate){
        //write your code here
    }

    public void setWeight(Weight amount){
        //write your code here
    }

    public Weight getWeight(){
        return null;
    }

    public void setName(String first, String last){
        //write your code here
    }

    public String toString(){
        //write your code here
        return "";
    }

    public boolean equals(YoungHuman toCompare){
        //write your code here
        return false;
    }
    
    @Override
    public int compareTo(Object other) {
      //write your code here
        return -1;
    }
    
    @Override
    public YoungHuman clone() {
        //write your code here
        return null;
    }
}

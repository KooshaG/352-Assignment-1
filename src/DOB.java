// ----------------------------------------------------------------
// Assignment 1
// Written by: Adamo Orsini (40174716) and Koosha Gholipour (40176826)
// ----------------------------------------------------------------
import java.util.concurrent.ThreadLocalRandom;

public class DOB {
    protected int day;
    protected int month;
    protected int year;

    private static final DOB SENIOR = new DOB(21,5,1956);

    public DOB(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DOB(DOB copy){
        this(copy.day, copy.month, copy.year);
    }
    public static DOB randomDOB(){
        return new DOB(ThreadLocalRandom.current().nextInt(1,31),ThreadLocalRandom.current().nextInt(1,13),ThreadLocalRandom.current().nextInt(1920,2020));
    }

    public boolean isSenior(){
        return SENIOR.isYounger(this);
    }
        
 
    public boolean isYounger(DOB other){
        if(this.year>other.year) return true;
        if(this.year==other.year&&this.month>other.month) return true;
        if(this.year==other.year&&this.month==other.month&&this.day>other.day) return true;
        return false;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String toString(){
        return(day+"-"+month+"-"+year);
    }
}

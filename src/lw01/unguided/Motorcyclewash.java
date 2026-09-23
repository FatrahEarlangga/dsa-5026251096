package lw01.unguided;

public class Motorcyclewash extends WashService implements Billable {
    public Motorcyclewash(String id, int days) {
        super(id,days);
    }
    public int calculateCharge(){
        return (getDays() * 15000) + 5000;
    }
    public String label(){
        return "Motorcycle Wash";
    }
} 
    


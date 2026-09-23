package lw01.unguided;

public abstract class WashService {
    private String id;
    private int days;

protected WashService(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }
        this.id = id;
        this.days = days;

    }
    public String getId() {
        return id;
    }

    public int getDays(){
        return days;
    }    

    public abstract int calculateCharge();

    public int calculateCharge(int items) {
        if (items <= 0) {
            throw new IllegalArgumentException("Items must be positive.");
        }
        return items * calculateCharge();
    }

    public String label(){
        return "Service";
    }

    public String summary() {
        return  getId() + " | " + label() + " | " + calculateCharge();
    }

}

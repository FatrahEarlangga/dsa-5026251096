package lw01.unguided;

public class CarWash extends WashService {
private int units;
    public CarWash(String id, int days, int units) {
        super(id, days);
        this.units = units;
    }

    @Override
    public int calculateCharge() {
        if (getDays() <= 3) {
            return (getDays() * 35000 + 15000 ) * units;
        } else {
            return ((3 * 35000) + ((getDays() - 3) * 25000)) * units;
        }
    }
    public String label() {
        return "Car Wash";
    }

}
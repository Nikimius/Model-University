package Builder;

public class Main {
    public static void main(String[] args) {
        SportCar sportCar = new SportCar.Builder("Audi").setColor("green").build();
        System.out.println(sportCar.getName());
        System.out.println(sportCar.getColor());
        System.out.println(sportCar.getMaxSpeed());
    }
}

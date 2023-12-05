public class Beard extends Service {
    public Beard(String name, int duration, double price) {
        super(name, duration, price);
    }

    @Override
    public String description() {
        return "Barba completa";
    }
}

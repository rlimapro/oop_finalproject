public class HairAndBeard extends Service {
    public HairAndBeard(String name, int duration, double price) {
        super(name, duration, price);
    }

    @Override
    public String description() {
        return "Corte de cabelo e barba";
    }
}

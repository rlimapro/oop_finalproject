public class Hair extends Service {
    public Hair(String name, int duration, double price) {
        super(name, duration, price);
    }

    @Override
    public String description() {
        return "Corte de cabelo usando máquina, tesoura ou ambos";
    }
}

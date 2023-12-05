import java.text.DecimalFormat;

public abstract class Service {
    private String name;
    private int duration;
    private double price;
    private int feedback = -1;

    public Service(String name, int duration, double price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public int getFeedback() { return feedback; }

    public void setFeedback(int feedback) { this.feedback = feedback; }

    public abstract String description();

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "[ " + getName() + " : " + getDuration() + " minutos : R$" + df.format(getPrice()) + " ]";
    }
}

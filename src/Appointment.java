import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Appointment {
    private BarberShop barberShop;
    private LocalDateTime date;
    private Service service;
    private Status status;

    public Appointment(BarberShop barberShop, LocalDateTime date, Service service, Status status) {
        this.barberShop = barberShop;
        this.date       = date;
        this.service    = service;
        this.status     = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Service getService() {
        return service;
    }

    public BarberShop getBarberShop() {
        return barberShop;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment that)) return false;
        return Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String str = "";

        str += date.format(fmt) + " : " + getBarberShop().getName() + " [" + status.toString() + "]\n";
        str += service.toString();

        return str;
    }
}

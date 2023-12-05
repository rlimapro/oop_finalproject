import java.util.ArrayList;
import java.util.List;

public class BarberShop extends User {
    private String address;
    private WorkingDay workingDay;
    private List<Service> services = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public BarberShop(String name, String email, String password, String phone, String address, WorkingDay workingDay) {
        super(name, email, password, phone);
        this.address    = address;
        this.workingDay = workingDay;
    }

    public List<Service> getServices() {
        return services;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void showServices() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < services.size(); i++) {
            sb.append(i + 1).append(" - ").append(services.get(i).toString());
            if(i < services.size() - 1) { sb.append("\n"); }
        }
        System.out.println(sb.toString());
    }

    public void showServicesHours() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < workingDay.getServiceHours().size(); i++) {
            sb.append(String.format("[%2d - ", i + 1)).append(workingDay.getServiceHours().get(i)).append("]").append(" ");
            if((i + 1) % 3 == 0) { sb.append("\n"); }
        }
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return getName();
    }
}

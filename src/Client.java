import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Client extends User {
    private List<Appointment> appointments = new ArrayList<>();
    private Map<LocalDateTime, Service> historic = new TreeMap<>();

    public Client(String name, String email, String password, String phone) {
        super(name, email, password, phone);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public boolean scheduleAppointment(Appointment appointment) {
        if(!appointments.isEmpty() && appointments.contains(appointment)) {
            System.out.println("\nVocê já possui um agendamento neste horário!");
            return false;
        }
        appointments.add(appointment);
        return true;
    }

    public void cancelAppointment(int index) {
        appointments.get(index).changeStatus(Status.CANCELADO);
        appointments.remove(index);
    }

    public void doService(int index, int feedback) {
        appointments.get(index).changeStatus(Status.COMPLETADO);
        appointments.get(index).getService().setFeedback(feedback);
        historic.put(appointments.get(index).getDate(), appointments.get(index).getService());
        appointments.remove(index);
    }

    public void showAppointmentList() {
        StringBuilder sb = new StringBuilder("\n(AGENDA)\n");

        if(appointments.isEmpty()) {
            sb.append("[Vazia]");
            System.out.println(sb.toString());
            return;
        }

        int i = 0;
        for(Appointment a : appointments) {
            sb.append(a).append("\n");
            if(i < appointments.size() - 1)
                sb.append("\n");
            i++;
        }
        System.out.print(sb.toString());
    }

    public void showHistoric() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder("\n(HISTÓRICO)\n");

        if(historic.isEmpty()) {
            sb.append("[Vazio]");
            System.out.println(sb.toString());
            return;
        }

        int i = 0;
        for(Map.Entry<LocalDateTime, Service> entry : historic.entrySet()) {
            sb.append(entry.getKey().format(fmt)).append(" : ")
                    .append(entry.getValue().toString())
                    .append(" Avaliação: ").append(entry.getValue().getFeedback()).append("/5");

            if(i < historic.size() - 1) { sb.append("\n"); }
            i++;
        }
        System.out.println(sb);
    }
}

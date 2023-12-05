import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingDay {
    private String workingStart;
    private String workingEnd;
    private List<LocalTime> serviceHours = new ArrayList<>();

    public WorkingDay(String workingStart, String workingEnd) {
        this.workingStart = workingStart;
        this.workingEnd = workingEnd;
        calculateServiceHours();
    }

    public String getWorkingStart() {
        return workingStart;
    }

    public String getWorkingEnd() {
        return workingEnd;
    }

    public List<LocalTime> getServiceHours() {
        return serviceHours;
    }

    public void calculateServiceHours() {
        var hourEnd   = workingEnd.split(":");
        var hourStart = workingStart.split(":");
        LocalTime start = LocalTime.of(Integer.parseInt(hourStart[0]), Integer.parseInt(hourStart[1])); // 08:40
        LocalTime end   = LocalTime.of(Integer.parseInt(hourEnd[0]), Integer.parseInt(hourEnd[1])); // 18:40

        serviceHours.add(start); // [08:40]
        LocalTime aux;
        int minutes = 60;

        do {
            aux = start.plusMinutes(minutes);
            serviceHours.add(aux); // [08:40, 09:40, ... , 18:40]
            minutes += 60;
        } while (aux.isBefore(end));
    }

    @Override
    public String toString() {
        return "Atende das " + getWorkingStart() + " às " + getWorkingEnd();
    }
}

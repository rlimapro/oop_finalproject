import utils.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientLogic {
    static Scanner sc = new Scanner(System.in);
    private static Client client;
    private static List<BarberShop> barberShops = new ArrayList<>();
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void createBarberShops() {
        // Criando barbearias
        BarberShop b1 = new BarberShop("Corte fino barbearia", "cortefino@gmail.com", "123", "(88)99332-1133", "Rua Sem fim", new WorkingDay("08:40", "18:40"));
        BarberShop b2 = new BarberShop("Johns Barbearia", "johns@gmail.com", "123", "(88)99343-1533", "Rua Sem começo", new WorkingDay("08:40", "18:40"));
        BarberShop b3 = new BarberShop("Thiago Barber", "thiago@gmail.com", "123", "(88)99132-1163", "Rua Sem saída", new WorkingDay("08:40", "18:40"));

        // Adicionando serviços às barbearias
        b1.addService(new Hair("Corte de cabelo adulto", 40, 20.00));
        b1.addService(new Hair("Corte de cabelo infantil", 45, 25.00));

        b2.addService(new Hair("Corte de cabelo adulto", 40, 25.00));
        b2.addService(new Beard("Barba modelagem", 35, 20.00));
        b2.addService(new HairAndBeard("Corte de cabelo + barba modelagem", 75, 45.00));

        b3.addService(new Hair("Corte de cabelo adulto", 40, 30.00));
        b3.addService(new Hair("Corte de cabelo infantil", 40, 35.00));
        b3.addService(new Beard("Barba modelagem", 40, 25.00));
        b3.addService(new Beard("Barboterapia", 40, 30.00));
        b3.addService(new HairAndBeard("Corte de cabelo + Barba modelagem", 80, 55.00));
        b3.addService(new HairAndBeard("Corte de cabelo + Barboterapia", 80, 60.00));

        // Adicionando barbearias à lista de barbearias presentes no app
        barberShops.add(b1);
        barberShops.add(b2);
        barberShops.add(b3);
    }

    public static void register() {
        System.out.println("(CADASTRO)");
        System.out.print("Insira seu nome: ");
        String name = sc.nextLine();
        String email = Util.readEmail("Insira seu email: ");
        System.out.print("Insira sua senha: ");
        String password = sc.nextLine();
        String phone = Util.readPhoneNumber("Insira seu telefone (xx)xxxxx-xxxx : ");

        client = new Client(name, email, password, phone);
        System.out.println("Cadastro realizado com sucesso!");
    }

    public static void menu() {
        label:
        while(true) {
            System.out.println("\n(MENU)");
            System.out.println("[1] - Agendar");
            System.out.println("[2] - Minha agenda");
            System.out.println("[3] - Histórico");
            System.out.println("[4] - Sair");

            int input = Util.readInt(">>", 4);

            if(input == 1) {
                System.out.println("\nEscolha uma barbearia ou veja todas detalhadamente:");
                showBarberShops();
                System.out.println(barberShops.size() + 1 + " - Mostrar todas");

                int barberInput = Util.readInt(">>", barberShops.size() + 1);

                int aux = -1;
                if(barberInput == 4) {
                    showEverything();
                    aux = Util.readInt("\nEscolha uma das barbearias: ", barberShops.size());
                }

                if(aux != -1) { barberInput = aux; }

                LocalDate date = Util.readDate("\nInsira a data do agendamento (dd/mm/aaaa): ");

                System.out.println("\nEscolha um dos serviços:");
                barberShops.get(barberInput - 1).showServices();
                int serviceInput = Util.readInt(">>", barberShops.get(barberInput - 1).getServices().size());

                System.out.println("\nEscolha um dos horários abaixo:");
                barberShops.get(barberInput - 1).showServicesHours();
                int serviceHourInput = Util.readInt(">>", barberShops.get(barberInput - 1).getWorkingDay().getServiceHours().size());
                LocalTime hour = barberShops.get(barberInput - 1).getWorkingDay().getServiceHours().get(serviceHourInput - 1);

                // Associando a data do serviço com a hora do serviço em um tipo LocalDateTime
                LocalDateTime dateHourService = date.atTime(hour);

                BarberShop barberShop = barberShops.get(barberInput - 1);
                if( client.scheduleAppointment(new Appointment(
                        barberShop,
                        dateHourService,
                        barberShop.getServices().get(serviceInput - 1),
                        Status.PENDENTE
                    ))
                ) { System.out.println("\nAgendamento realizado com sucesso!"); }
            }
            else if(input == 2) {
                client.showAppointmentList();

                if(!client.getAppointments().isEmpty()) {
                    System.out.println("\nDigite 1 para cancelar algum agendamento.");
                    System.out.println("Digite 2 para realizar um serviço agendado.");
                    System.out.println("Digite 3 para continuar.");

                    int userChoose = Util.readInt(">>", 3);
                    if (userChoose == 1) {
                        System.out.println("\nDigite o número do agendamento que deseja concelar:");

                        int i = 0;
                        for(Appointment a : client.getAppointments()) {
                            System.out.println(i+1 + " - " + a.getDate().format(fmt) + " : " + a.getService().toString());
                            i++;
                        }

                        int cancelIndex = Util.readInt(">>", client.getAppointments().size());
                        System.out.print("Tem certeza disso (y/n): ");
                        char sure = sc.next().charAt(0);
                        sc.nextLine();

                        if(sure == 'y') {
                            client.cancelAppointment(cancelIndex - 1);
                            System.out.println("Agendamento cancelado com sucesso!");
                        }
                    }
                    if(userChoose == 2) {
                        System.out.println("\nDigite o número do serviço que foi realizado:");

                        int i = 0;
                        for(Appointment a : client.getAppointments()) {
                            System.out.println(i+1 + " - " + a.getDate().format(fmt) + " : " + a.getService().toString());
                            i++;
                        }

                        int doInput = Util.readInt(">>", client.getAppointments().size());
                        int rating = Util.readInt("Avalie o serviço (1-5): ", 5);

                        client.doService(doInput - 1, rating);
                        System.out.println("Serviço avaliado com sucesso!");
                    }
                }
                else
                    anythingToContinue();
            }
            else if(input == 3) {
                client.showHistoric();
                anythingToContinue();
            }
            else
                break label;
        }
    }

    public static void showEverything() {
        StringBuilder sb = new StringBuilder("\n");
        int i = 0;

        for(BarberShop b : barberShops) {
            sb.append(i+1).append(" - ").append(b.getName()).append(" - ").append(b.getWorkingDay().toString()).append("\n");
            for(Service s : b.getServices()) {
                sb.append(s).append("\n");
            }
            if(i < barberShops.size() - 1) { sb.append("\n"); }
            i++;
        }

        System.out.print(sb.toString());
    }

    public static void showBarberShops() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < barberShops.size(); i++) {
            sb.append(i + 1).append(" - ").append(barberShops.get(i));
            if(i < barberShops.size() - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void anythingToContinue() {
        System.out.print("\nDigite qualquer tecla e aperte enter para continuar...");
        sc.nextLine();
    }
}

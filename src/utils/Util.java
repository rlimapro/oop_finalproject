package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;
 
public class Util {
    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int readInt(String prompt, int userChoices) {
        int input;
        do {
            try {
                System.out.print(prompt); // >>
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                input = -1;
                System.out.println("Insira um inteiro válido!");
            }
        } while(input <= 0 || input > userChoices);
        return input;
    }

    public static String readPhoneNumber(String prompt) {
        boolean aux = false;
        String phoneNumber;
        String regex = "\\(\\d{2}\\)\\d{5}-\\d{4}"; // (xx)xxxxx-xxxx
        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.print(prompt);
            phoneNumber = sc.nextLine();
            Matcher matcher = pattern.matcher(phoneNumber);

            if(matcher.matches())
                aux = true;
            else
                System.out.println("Insira o telefone no formato indicado!");
        } while(!aux);

        return phoneNumber;
    }

    public static String readEmail(String prompt) {
        boolean aux = false;
        String email;
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);

        do {
            System.out.print(prompt);
            email = sc.nextLine();
            Matcher matcher = pattern.matcher(email);

            if(matcher.matches())
                aux = true;
            else
                System.out.println("Insira um email válido!");
        } while(!aux);

        return email;
    }

    public static LocalDate readDate(String prompt) {
        LocalDate date;

        do{
            try {
                System.out.print(prompt);
                date = LocalDate.parse(sc.nextLine(), fmt);

                while(date.isBefore(LocalDate.now())) {
                    System.out.println("\nVocê não pode escolher uma data do passado.");
                    System.out.println("1 - Inserir data de agendamento novamente");
                    System.out.println("2 - Sair");

                    int input = readInt(">>", 2);
                    if (input == 1) {
                        System.out.print(prompt);
                        date = LocalDate.parse(sc.nextLine(), fmt);
                    }
                    else if(input == 2)
                        exit(0);
                }
            } catch (Exception e) {
                date = null;
                System.out.println("Insira a data no formato indicado!");
            }
        } while (date == null);

        return date;
    }

}

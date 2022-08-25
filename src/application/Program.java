package application;

import model.Contract;
import model.Installment;
import services.ContractService;
import services.OnlinePaymentService;
import services.PayPalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //para eu ler uma data formatada
        Locale.setDefault(Locale.US); //para ler os pontos quando for digitar os números
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract data ");
        System.out.println("Number: ");
        Integer number = sc.nextInt();
        System.out.println("Date: (dd/MM/yyyy):  ");
        Date date = sdf.parse(sc.next());
        System.out.println("Contract value: ");
        double contractTotalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, contractTotalValue);

        System.out.println("Enter number of installments: ");
        int n = sc.nextInt();

        ContractService contractService = new ContractService(new PayPalService());
        contractService.processContract(contract,n);

        System.out.println("Installments: ");
            for (Installment it: contract.getInstallments()) {
                System.out.println(it); //o toString vai fazer isso imprimir melhor
            }

    sc.close();
    }

}

package services;

import model.Contract;
import model.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService  {
    private OnlinePaymentService onlinePaymentService; //declarar com o tipo da interface. -> inversão de controle/injeção de dependência

   public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
         }
    public void processContract(Contract contract, int months){
        double basicQuota = contract.getTotalValue()/months;

        for(int i=1 ; i<=months; i++){
            double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota,i);
            double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
            Date dueDate = addmonth(contract.getDate(), i);
            contract.getInstallments().add(new Installment(dueDate, fullQuota));
        }
    }
    //função auxiliar
    private Date addmonth(Date date, int n){
        //p adc meses a uma data no java, tem que o usar o Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);

        return calendar.getTime();
    }
    }


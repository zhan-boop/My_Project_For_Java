import java.util.Scanner;

public class Retirement2 {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);//需要终端写入时需要引入Scanner类
        System.out.print("How much money will you contribute every year?");
        double payment=in.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate=in.nextDouble();

        double balance=0;
        int year=0;

        String input;

        do{
            //当用户选择不退休，则更新用户薪资
            balance+=payment;
            double interest=balance*interestRate/100;
            balance+=interest;
            year++;

            //输出目前的薪资
            System.out.printf("After year %d,your balance is %,.2f%n",year,balance);
            System.out.print("Ready to retire?(Y/N)");
            input=in.next();
        }while(input.equalsIgnoreCase("N"));

    }
}

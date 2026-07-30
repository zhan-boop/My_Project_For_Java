import java.util.Scanner;

public class Retirement{
    public static void main(String[] args){
        //构造一个与标准输入流System.in相关联的Scanner对象，方便后续进行输入
        Scanner in=new Scanner(System.in);
        System.out.print("How much do you need to retire?");
        //输入目标goal，输入可退休工资
        double goal=in.nextDouble();
        //输入每年可以存储的金钱，即payment
        System.out.print("How much will you contribute every year?");
        double payment=in.nextDouble();
        //输入年增长比例
        System.out.print("Interest rate in %:");
        double interestRate=in.nextDouble();

        double balance=0;
        int years=0;
        //循环语句while的利用
        while(balance<goal){
            balance+=payment;
            double interest=balance*interestRate/100;
            balance+=interest;
            years++;
        }
        System.out.println("You can retire in "+ years + "years.");
    }
}
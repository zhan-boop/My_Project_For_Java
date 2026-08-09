package test1;
import java.util.Scanner;

class  InsufficientException extends Exception{
    public InsufficientException(String msg){
        super(msg);
    }
}

class BankAccount{
    private double balance;
    public BankAccount(double salary) {
        balance=salary;
    }
    public void deposit(double money){
        balance=balance+money;
    }
    public void withdraw(double money)throws InsufficientException{
        if(money>balance){
            throw new InsufficientException("需要资金为"+money+"当前余额为"+balance);
        }
        else {balance-=money;}
    }
    public double getBalance(){return balance;}
}

public class BankAccount_test {
    public static void main(String[] args){
        BankAccount account=new BankAccount(10000);
        System.out.println("原来的余额为: "+account.getBalance());
        try {
            account.withdraw(500);
            System.out.println("剩余的余额为: "+account.getBalance());
        }catch(InsufficientException e){
            System.out.println(e.getMessage());
        }finally{
            System.out.println("   [记录] 本次操作已保存");
        }
        Scanner in=new Scanner(System.in);
        System.out.println("需要存入多少钱？");
        int money=in.nextInt();
        account.deposit(money);
        System.out.println("存钱后余下的额度为"+account.getBalance());

        try{
            account.withdraw(90000);
        }catch(InsufficientException e){
            System.out.println(e.getMessage());
        }finally{
            System.out.println(" [记录]本次操作已保存");
        }
        System.out.println("\n最终余额: ¥" + account.getBalance());
        System.out.println("程序正常结束");  // 异常被捕获，程序没崩溃
    }
}

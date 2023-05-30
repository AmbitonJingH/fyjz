import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Goods g = new Goods();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入单价：");
        g.setMoney(input.nextInt());
        System.out.println("请输入数量：");
        g.setAmount(input.nextInt());
        System.out.println("请输入金额：");
        g.setPay(input.nextInt());
        System.out.println("应收金额为："+g.allMoney()+",找零为："+g.pay_Back());
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");

    }
}

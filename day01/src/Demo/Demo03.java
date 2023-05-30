package Demo;

public class Demo03 {


    public static void main(String[] args) {
        /*byte,char,short转换整型int
        以上三种类型实际存储的数据都是整型,在实际使用中遵循如下规则:
        int直接量可以赋值给byte,char,short只要不超过表示的范围,
        byte,char,short三种类型参与运算时,先一律转换成整型int再进行计算
        short类型:占用2个字节 范围-32768~~32767
        byte类型:占用1个字节 范围-127~~128*/

        char a='a';
        char b='b';
        char ch=(char)(a+b);
        System.out.println(ch);

        short g=30;
        short f=79;
        short sh=(short)(g+f);

        byte p=60;
        byte y=20;
        byte by=(byte)(p+y);
        System.out.println(ch+" "+sh+" "+by);


    }

}

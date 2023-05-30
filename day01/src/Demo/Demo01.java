package Demo;

public class Demo01 {
    /*
     * 1.变量不能使用"关键字"
     * 2.只能由字母,数字,_和$符组成
     * 3.不能数字开头
     */
    public static void main(String[] args){
        //1.声明并初始化
        int a=6;//声明一个变量a并且赋值为6
        a=7;
        System.out.println(a);
        //2.先声明后初始化
        int b;
        b=10;//先声明一个变量b,在给变量b赋值为10
        System.out.println(b);//10
        //3.声明多个变量,在给变量赋值
        int c,d;
        c=11;
        d=12;
        System.out.println(c);
        System.out.println(d);

        int e,f=19;// System.out.println(e);//声名,并没有初始化
        //4.int整型范围(占4个字节,取值范围-2^31 2^31-1 -2147483648~2147483647)
        int n=2074637463;
        //5.long长整型(占8个字节,取值范围:-2^63 2^63-1)
        long n1=207666666664L;
        //6.double双精确浮点型(占8个字节,取值范围:-1.7e+308 ~ 1.7e+308)精确到16位
        double dou=78.89;
        //7.float单精确浮点型(占4个字节,取值范围: -3.4e+38 ~ 3.4e+38); 精确到7位
        float f1=78.89f;

        //8.byte:字节型:(占1字节,取值范围:-127 ~~ 128)
        byte bt=78;
        //9.boolean:布尔类型:true为真,false为假,1个字节
        boolean bo=true;
        /* * 10.char:字符型(占用2个字节) * 采用Unicode编码格式,每个字符都有一个对应的码,表现得形式是char,
        * * 其实本质就事int类型(0~~65535),它所对应得ASCII编码例如 * a--97 A--65
        * * 字符直接量必须放在单引号中并且只有一个.
        * * 特殊字符需要通过"\"来转义,例如输出字符\ char ch3='\\';
        * */

        char ch='a';
        System.out.println(ch);//a
        int y=ch;
        System.out.println(y);//97
        char y1='\\';
        System.out.println(y1);

        //11.short:短整数型:(占2字节,取值范围:-2^15 ~~ 2^15-1,-32768至32767)
        short s=7812;

    }

}

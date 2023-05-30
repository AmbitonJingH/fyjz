public class Goods {
    private int money;
    private int amount;
    private int pay;
    private float payback;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public float getPayback() {
        return payback;
    }

    public void setPayback(float payback) {
        this.payback = payback;
    }

    public int allMoney(){
//        this.amount=amount;
//        this.money = money;
        int zongJinE = this.money*this.amount;
        return zongJinE;
    }
    public float pay_Back(){
        this.payback = this.pay - this.money*this.amount;
        return payback;
    }
}

public class ShoppingMall {
    // Name: Gustavo Galvao e Silva
    // Fun fact: My favorite animal are gibbons (the monkey)
    public static void main(String[] args) {
        int cash = 100;
        double taxRate = 1.13;
        double subtotal = 58.62;
        String name = "Gustavo";
        double change = cash - subtotal * taxRate;
        double changeTrunc = (int)(change * 100) / 100.0;

        System.out.println(name + " has $" + changeTrunc + " dollars remaining!\n" + name + " started with $" + cash + " dollars!");
    }
}

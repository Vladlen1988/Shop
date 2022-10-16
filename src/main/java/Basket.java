import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {
    protected String[] products;
    protected int[] prices;
    protected int[] totalProducts;
    protected int sumProducts = 0;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.totalProducts = new int[products.length];
    }

    private Basket(String[] products, int[] prices, int[] totalProducts) {
        this.products = products;
        this.prices = prices;
        this.totalProducts = totalProducts;
    }

    public void addToCart(int productNum, int amount) {
        totalProducts[productNum] += amount;
        sumProducts += (amount * prices[productNum]);
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        for (int i = 0; i < totalProducts.length; i++) {
            if (totalProducts[i] != 0) {
                System.out.println(" " + products[i] + " " + totalProducts[i] + " шт "
                        + prices[i] + " руб/шт," + (totalProducts[i] * prices[i]) + " руб. в сумме");
            }
        }
        System.out.println("Итого: " + sumProducts + " руб.");
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String product : products) {
                out.print(product + " ");
            }
            out.println();

            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(textFile))) {
            String[] product = scanner.nextLine().split(" ");
            int[] price = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            int[] totalProduct = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            return new Basket(product, price, totalProduct);
        }
    }
}




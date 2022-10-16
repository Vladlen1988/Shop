import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Basket basket;

        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт.");
        }

        File basketFile = new File("basket.txt");
        if (basketFile.exists()) {
            basket = Basket.loadFromTxtFile(basketFile);
        }else {
            basket = new Basket(products, prices);
        }

        while (true) {
            System.out.println("Выберите номер товара и количество или введите end для завершения");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                basket.printCart();
                break;
            }
            String[] choice = input.split(" ");
            int productNumber = Integer.parseInt(choice[0]) - 1;
            int productCount = Integer.parseInt(choice[1]);
            basket.addToCart(productNumber, productCount);
        }
        basket.saveTxt(basketFile);
    }
}

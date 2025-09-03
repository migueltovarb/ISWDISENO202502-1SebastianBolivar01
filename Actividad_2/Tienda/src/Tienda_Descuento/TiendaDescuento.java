package Tienda_Descuento;
import java.util.Scanner;

public class TiendaDescuento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int productCount;
        double[] prices = new double[100]; // Vector to store prices
        String[] names = new String[100];
        int[] types = new int[100];
        double total = 0;

        // Input number of products
        System.out.print("Ingrese el número de productos a comprar (mínimo 1): ");
        productCount = scanner.nextInt();
        while (productCount < 1) {
            System.out.print("El número debe ser al menos 1. Intente de nuevo: ");
            productCount = scanner.nextInt();
        }

        // Input product details
        for (int i = 0; i < productCount; i++) {
            scanner.nextLine(); // Clear buffer
            System.out.print("Producto " + (i + 1) + " - Nombre: ");
            names[i] = scanner.nextLine();
            System.out.print("Tipo (1: ropa, 2: tecnología, 3: alimentos): ");
            types[i] = scanner.nextInt();
            System.out.print("Precio: $");
            prices[i] = scanner.nextDouble();

            // Apply specific discount based on product type
            switch (types[i]) {
                case 1: // Ropa
                    prices[i] *= 0.90; // 10% discount
                    break;
                case 2: // Tecnología
                    prices[i] *= 0.85; // 15% discount
                    break;
                case 3: // Alimentos
                    prices[i] *= 0.95; // 5% discount
                    break;
                default:
                    System.out.println("Tipo no válido, sin descuento aplicado.");
            }
            total += prices[i];
        }

        // Additional discount if total exceeds $500
        double finalTotal = total;
        if (total > 500) {
            finalTotal *= 0.90; // 10% additional discount
        }

        // Display results
        double savings = total - finalTotal;
        System.out.printf("Total sin descuento: $%.2f%n", total);
        System.out.printf("Total con descuento: $%.2f%n", finalTotal);
        System.out.printf("Ahorro: $%.2f%n", savings);

        scanner.close();
    }
}
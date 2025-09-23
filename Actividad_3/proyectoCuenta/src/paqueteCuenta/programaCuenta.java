package paqueteCuenta;

public class programaCuenta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cuenta acc1 = new cuenta("101", "Alice", 500);
        cuenta acc2 = new cuenta("102", "Bob", 300);
        System.out.println(acc1);
        System.out.println(acc2);

        acc1.credit(200);
        acc1.debit(100);
        acc1.transferTo(acc2, 250);

        System.out.println("After operations:");
        System.out.println(acc1);
        System.out.println(acc2);

        acc1.debit(1000);
    }
}

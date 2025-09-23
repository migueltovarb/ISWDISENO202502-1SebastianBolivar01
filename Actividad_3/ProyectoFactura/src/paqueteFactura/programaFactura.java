package paqueteFactura;

public class programaFactura {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        factura item = new factura("A1", "Laptop", 2, 850.50);
        System.out.println(item);
        System.out.println("Total: " + item.getTotal());
        item.setQty(3);
        item.setUnitPrice(800.00);
        System.out.println("Updated Item: " + item);
        System.out.println("New Total: " + item.getTotal());
    }
}

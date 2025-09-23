package paqueteLibro;

public class programaLibro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Autor author = new Autor("Gabriel García Márquez",
                "gabo@example.com", 'm');
		libro book = new libro("Cien Años de Soledad", author, 49.99, 10);
		System.out.println(book);
		
		book.setPrice(39.99);
		book.setQty(12);
		System.out.println("Updated Book: " + book);
	}
}
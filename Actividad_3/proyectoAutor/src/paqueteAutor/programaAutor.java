package paqueteAutor;

public class programaAutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        autor autor = new autor("Gabriel García Márquez",
                "gabo@example.com", 'm');
		System.out.println(autor);
		autor.setEmail("ggmarquez@correo.com");
		System.out.println("Updated Author: " + autor);
	}
}
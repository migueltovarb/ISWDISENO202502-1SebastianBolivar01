package paqueteCirculo;

public class ProgramaCirculo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		circulo miCirculo=new circulo();
		double area=miCirculo.getArea();
		System.out.println("area: "+ area);
		miCirculo.setRadio(300);
		area=miCirculo.getArea();
		
		System.out.println("area: "+area);
		
		circulo miSegundoCirculo=new circulo(400);
		area=miSegundoCirculo.getArea();
		System.out.println("area: "+ area);
		
		double perimetro=miSegundoCirculo.getPerimetro();
		System.out.println("Perimetro: "+ perimetro);
		
		System.out.println(miSegundoCirculo);

	}

}

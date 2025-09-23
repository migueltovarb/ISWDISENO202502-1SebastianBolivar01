package paqueteEmpleado;

public class programaEmpleado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        empleado emp = new empleado(1, "Juan", "Perez", 3000);
        System.out.println(emp);
        System.out.println("Name: " + emp.getName());
        System.out.println("Annual salary: " + emp.getAnnualSalary());
        emp.raiseSalary(10);
        System.out.println("New salary after 10% raise: " + emp.getSalary());
    }
}

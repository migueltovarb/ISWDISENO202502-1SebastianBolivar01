package asistencia;
import java.util.Scanner;

public class Asistencia_Estudiantes {
	public static final int DIAS_SEMANA = 5;
	public static final int NUM_ESTUDIANTES = 4;
	public static char[][] asistencia = new char [NUM_ESTUDIANTES][DIAS_SEMANA];
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			mostrarMenu();
			System.out.print("Seleccione opcion 1-4");
			opcion = scanner.nextInt();
			
			switch (opcion) {
				case 1:
					registrarAsistencia();
					break;
				case 2:
					mostrarResumen();
					break;
				case 3:
					System.out.println("Volviendo a Resgistrar");
					break;
				case 4:
					System.out.println("Saliendo del programa");
					break;
				default:
					System.out.println("Opcion no valida");
						
			}
		} while (opcion != 4);
	}
	
	private static void mostrarMenu() {
		System.out.println("1. Ver asistencia indivudual");
		System.out.println("2. Ver resumen general");
		System.out.println("3. Volver a registrar");
		System.out.println("4. Salir");
	}
	
	private static void registrarAsistencia() {
		for (int estudiante = 0; estudiante < NUM_ESTUDIANTES; estudiante++) {
			System.out.println("Estudiante #" + (estudiante + 1) );
			for (int dia = 0; dia < DIAS_SEMANA; dia++) {
				char valor;
				do {
					System.out.print("Dia" + (dia+1) + "(P/A)");
					valor = Character.toUpperCase(scanner.next().charAt(0));
					if (valor != 'P' && valor != 'A') {
						System.out.println("El valor ingrasado no es valido");
					}				
				} while (valor != 'P' && valor != 'A');
                asistencia[estudiante][dia] = valor;
			}
		}
		System.out.println("Asistencia registrada exitosamente.");
	}
		
	 private static void mostrarResumen() {
	        int[] totalPorDia = new int[DIAS_SEMANA];
	        int[] ausenciasPorEstudiante = new int[NUM_ESTUDIANTES];
	        int maxAusencias = 0;
	        int diaMaxAusencias = 0;

	        for (int dia = 0; dia < DIAS_SEMANA; dia++) {
	            for (int estudiante = 0; estudiante < NUM_ESTUDIANTES; estudiante++) {
	                if (asistencia[estudiante][dia] == 'P') {
	                    totalPorDia[dia]++;
	                } else {
	                    ausenciasPorEstudiante[estudiante]++;
	                }
	            }
	            if ((NUM_ESTUDIANTES - totalPorDia[dia]) > maxAusencias) {
	                maxAusencias = NUM_ESTUDIANTES - totalPorDia[dia];
	                diaMaxAusencias = dia;
	            }
	        }

	        System.out.println("\n--- Resumen de Asistencia ---");
	        System.out.println("Total de asistencias por día:");
	        for (int dia = 0; dia < DIAS_SEMANA; dia++) {
	            System.out.println("Día " + (dia + 1) + ": " + totalPorDia[dia] + " presentes");
	        }
	        System.out.println("\nEstudiantes que asistieron todos los días:");
	        for (int estudiante = 0; estudiante < NUM_ESTUDIANTES; estudiante++) {
	            if (ausenciasPorEstudiante[estudiante] == 0) {
	                System.out.println("Estudiante #" + (estudiante + 1));
	            }
	        }
	        System.out.println("\nDía con mayor número de ausencias: Día " + (diaMaxAusencias + 1) + " (" + maxAusencias + " ausencias)");
	    }
	}
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorRenta gestor = new GestorRenta();

    public static void main(String[] args) {
        cargarDatosIniciales();
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("Rentamovil");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Cotizar Alquiler");
            System.out.println("3. Confirmar Alquiler");
            System.out.println("4. Registrar Devolucion");
            System.out.println("5. Generar Reporte de Flota");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarNuevoVehiculo();
                    break;
                case 2:
                    cotizar();
                    break;
                case 3:
                    alquilar();
                    break;
                case 4:
                    devolver();
                    break;
                case 5:
                    gestor.generarReporte();
                    break;
                case 6:
                    System.out.println("Cerrando sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    private static void cargarDatosIniciales() {
        gestor.registrarVehiculo(new Automovil("P001", "Toyota", 2020, 150.0, 5, true));
        gestor.registrarVehiculo(new Automovil("P002", "Honda", 2018, 120.0, 5, false));
        gestor.registrarVehiculo(new Motocicleta("M001", "Suzuki", 2021, 50.0, 150));
        gestor.registrarVehiculo(new Motocicleta("M002", "Yamaha", 2022, 100.0, 300));
        gestor.registrarVehiculo(new CamionetaCarga("C001", "Isuzu", 2019, 200.0, 1.5));
        gestor.registrarVehiculo(new CamionetaCarga("C002", "Hino", 2023, 250.0, 3.0));
    }

    private static void registrarNuevoVehiculo() {
        System.out.println("Tipo de vehiculo");
        System.out.println("1. Automovil\n2. Motocicleta\n3. Camioneta de Carga");
        int tipo = leerEntero();
        
        System.out.print("Ingrese placa: ");
        String placa = scanner.nextLine().trim();
        if (placa.isEmpty()) {
            System.out.println("Error: La placa no puede estar vacia.");
            return;
        }

        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Ingrese modelo (Anio): ");
        int modelo = leerEnteroPositivo();
        if (modelo == -1) return;

        System.out.print("Ingrese tarifa diaria (Q): ");
        double tarifa = leerDoublePositivo();
        if (tarifa == -1) return;

        if (tipo == 1) {
            System.out.print("Cantidad de pasajeros: ");
            int pasajeros = leerEnteroPositivo();
            if (pasajeros == -1) return;
            System.out.print("Es automatico? (1: Si, 2: No): ");
            boolean automatico = leerEntero() == 1;
            gestor.registrarVehiculo(new Automovil(placa, marca, modelo, tarifa, pasajeros, automatico));
            
        } else if (tipo == 2) {
            System.out.print("Cilindraje (cc): ");
            int cc = leerEnteroPositivo();
            if (cc == -1) return;
            gestor.registrarVehiculo(new Motocicleta(placa, marca, modelo, tarifa, cc));
            
        } else if (tipo == 3) {
            System.out.print("Capacidad de carga (Toneladas): ");
            double toneladas = leerDoublePositivo();
            if (toneladas == -1) return;
            gestor.registrarVehiculo(new CamionetaCarga(placa, marca, modelo, tarifa, toneladas));
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void cotizar() {
        System.out.print("Ingrese placa: ");
        String placa = scanner.nextLine().trim();
        System.out.print("Dias a alquilar: ");
        int dias = leerEnteroPositivo();
        if (dias > 0) gestor.cotizar(placa, dias);
    }

    private static void alquilar() {
        System.out.print("Ingrese placa: ");
        String placa = scanner.nextLine().trim();
        System.out.print("Dias de alquiler: ");
        int dias = leerEnteroPositivo();
        if (dias > 0) {
            gestor.confirmarAlquiler(placa, dias);
        }
    }

    private static void devolver() {
        System.out.print("Ingrese placa a devolver: ");
        String placa = scanner.nextLine().trim();
        gestor.registrarDevolucion(placa);
    }

    private static int leerEntero() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine(); 
            return -1;
        }
    }

    private static int leerEnteroPositivo() {
        int valor = leerEntero();
        if (valor <= 0) {
            System.out.println("Error: El valor debe ser un numero entero mayor a cero.");
            return -1;
        }
        return valor;
    }

    private static double leerDoublePositivo() {
        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();
            if (valor <= 0) {
                System.out.println("Error: El valor debe ser mayor a cero.");
                return -1;
            }
            return valor;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Error: Formato numerico incorrecto.");
            return -1;
        }
    }
}
import java.util.ArrayList;

public class GestorRenta {
    private ArrayList<Vehiculo> flota;
    private double ingresosTotales;

    public GestorRenta() {
        this.flota = new ArrayList<>();
        this.ingresosTotales = 0.0;
    }

    public void registrarVehiculo(Vehiculo v) {
        if (buscarVehiculo(v.getPlaca()) != null) {
            System.out.println("Error: Ya existe un vehiculo con la placa " + v.getPlaca());
        } else {
            flota.add(v);
            System.out.println("Vehiculo registrado con exito.");
        }
    }

    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : flota) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public void cotizar(String placa, int dias) {
        Vehiculo v = buscarVehiculo(placa);
        if (v == null) {
            System.out.println("Error: Vehiculo no encontrado.");
            return;
        }
        double total = v.cotizarAlquiler(dias);
        System.out.println("Detalles de cotización");
        System.out.println("Placa: " + v.getPlaca());
        System.out.println("Caracteristicas: " + v.toString());
        System.out.println("Estado actual: " + (v.isDisponible() ? "Disponible" : "Ocupado"));
        System.out.printf("Costo Total por %d dias: Q%.2f%n", dias, total);
        System.out.println("------------------------------");
    }

    public boolean confirmarAlquiler(String placa, int dias) {
        Vehiculo v = buscarVehiculo(placa);
        if (v == null) {
            System.out.println("Error: Vehiculo no encontrado.");
            return false;
        }
        if (!v.isDisponible()) {
            System.out.println("Error: El vehiculo se encuentra actualmente ocupado.");
            return false;
        }
        
        double total = v.cotizarAlquiler(dias);
        v.setDisponible(false);
        ingresosTotales += total;
        System.out.printf("Alquiler confirmado. Total cobrado: Q%.2f%n", total);
        return true;
    }

    public boolean registrarDevolucion(String placa) {
        Vehiculo v = buscarVehiculo(placa);
        if (v == null) {
            System.out.println("Error: Vehiculo no encontrado.");
            return false;
        }
        if (v.isDisponible()) {
            System.out.println("Error: El vehiculo ya se encuentra disponible. No se puede devolver.");
            return false;
        }
        
        v.setDisponible(true);
        System.out.println("Devolucion registrada. El vehiculo esta nuevamente disponible.");
        return true;
    }

    public void generarReporte() {
        int autoDisp = 0, autoOcup = 0;
        int motoDisp = 0, motoOcup = 0;
        int camDisp = 0, camOcup = 0;

        for (Vehiculo v : flota) {
            boolean disp = v.isDisponible();
            if (v instanceof Automovil) {
                if (disp) autoDisp++; else autoOcup++;
            } else if (v instanceof Motocicleta) {
                if (disp) motoDisp++; else motoOcup++;
            } else if (v instanceof CamionetaCarga) {
                if (disp) camDisp++; else camOcup++;
            }
        }

        System.out.println("Reporte de Jornada");
        System.out.println("Automoviles    -> Disponibles: " + autoDisp + " | Ocupados: " + autoOcup);
        System.out.println("Motocicletas   -> Disponibles: " + motoDisp + " | Ocupados: " + motoOcup);
        System.out.println("Camionetas     -> Disponibles: " + camDisp + " | Ocupados: " + camOcup);
        System.out.printf("Ingresos Totales Acumulados: Q%.2f%n", ingresosTotales);
        System.out.println("--------------------------");
    }
}
public class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, int modelo, double tarifaDiaria, int cilindraje) {
        super(placa, marca, modelo, tarifaDiaria);
        this.cilindraje = cilindraje;
    }

    @Override
    public double cotizarAlquiler(int dias) {
        double costoBase = tarifaDiaria * dias;
        double recargo = cilindraje > 250 ? 75.0 : 0.0;
        return costoBase + recargo;
    }

    @Override
    public String toString() {
        return super.toString() + " | Cilindraje: " + cilindraje + "cc";
    }
}
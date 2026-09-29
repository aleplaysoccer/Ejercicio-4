public class CamionetaCarga extends Vehiculo {
    private double capacidadToneladas;

    public CamionetaCarga(String placa, String marca, int modelo, double tarifaDiaria, double capacidadToneladas) {
        super(placa, marca, modelo, tarifaDiaria);
        this.capacidadToneladas = capacidadToneladas;
    }

    @Override
    public double cotizarAlquiler(int dias) {
        double costoBase = tarifaDiaria * dias;
        double recargo = 100.0 * capacidadToneladas * dias;
        return costoBase + recargo;
    }

    @Override
    public String toString() {
        return super.toString() + " | Capacidad: " + capacidadToneladas + " Toneladas";
    }
}
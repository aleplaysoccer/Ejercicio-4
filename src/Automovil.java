public class Automovil extends Vehiculo {
    private int pasajeros;
    private boolean esAutomatico;

    public Automovil(String placa, String marca, int modelo, double tarifaDiaria, int pasajeros, boolean esAutomatico) {
        super(placa, marca, modelo, tarifaDiaria);
        this.pasajeros = pasajeros;
        this.esAutomatico = esAutomatico;
    }

    @Override
    public double cotizarAlquiler(int dias) {
        double costoBase = tarifaDiaria * dias;
        double recargo = esAutomatico ? (50.0 * dias) : 0.0;
        return costoBase + recargo;
    }

    @Override
    public String toString() {
        return super.toString() + " | Pasajeros: " + pasajeros + " | Transmision: " + (esAutomatico ? "Automatica" : "Manual");
    }
}
public abstract class Vehiculo {
    protected String placa;
    protected String marca;
    protected int modelo;
    protected double tarifaDiaria;
    protected boolean disponible;

    public Vehiculo(String placa, String marca, int modelo, double tarifaDiaria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public abstract double cotizarAlquiler(int dias);

    @Override
    public String toString() {
        return "Marca: " + marca + " | Modelo: " + modelo + " | Tarifa Base: Q" + String.format("%.2f", tarifaDiaria);
    }
}
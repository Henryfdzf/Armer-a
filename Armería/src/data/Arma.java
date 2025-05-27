package data;
import java.io.Serializable;

public abstract class Arma implements Serializable {
    private String nombre;
    private String duenio;
    private int danio;
    private String universo;

    public Arma(String nombre, String duenio, int danio, String universo) {
        this.nombre = nombre;
        this.duenio = duenio;
        this.danio = danio;
        this.universo = universo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public String getUniverso() {
        return universo;
    }

    public void setUniverso(String universo) {
        this.universo = universo;
    }

    public abstract TipoArma getTipo();

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Dueño: " + duenio +
               ", Daño: " + danio + ", Universo: " + universo +
               ", Tipo: " + getTipo();
    }
}

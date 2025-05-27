package data;

public class ArmaDistancia extends Arma {

    public ArmaDistancia(String nombre, String duenio, int danio, String universo) {
        super(nombre, duenio, danio, universo);
    }

    @Override
    public TipoArma getTipo() {
        return TipoArma.DISTANCIA;
    }
}

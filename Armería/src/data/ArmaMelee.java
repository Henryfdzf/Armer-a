package data;

public class ArmaMelee extends Arma {

    public ArmaMelee(String nombre, String duenio, int danio, String universo) {
        super(nombre, duenio, danio, universo);
    }

    @Override
    public TipoArma getTipo() {
        return TipoArma.MELEE;
    }
}

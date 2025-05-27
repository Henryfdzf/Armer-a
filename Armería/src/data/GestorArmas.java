package data;

import java.io.*;
import java.util.*;

public class GestorArmas {
    private final List<Arma> listaArmas;
    private static final String RUTA_FICHERO = "res/FicheroArmeria.txt";

    public GestorArmas() {
        this.listaArmas = cargarDesdeFichero();
    }

    public void agregarArma(Arma arma) {
        listaArmas.add(arma);
    }

    public boolean eliminarArmaPorNombre(String nombre) {
        Iterator<Arma> it = listaArmas.iterator();
        while (it.hasNext()) {
            Arma a = it.next();
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                it.remove();
                System.out.println("Arma '" + nombre + "' eliminada.");
                return true;
            }
        }
        System.out.println("No se encontró un arma con ese nombre.");
        return false;
    }

    public boolean modificarArma(String nombre, Scanner sc) {
        for (int i = 0; i < listaArmas.size(); i++) {
            Arma a = listaArmas.get(i);
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                System.out.print("Nuevo nombre (actual: " + a.getNombre() + "): ");
                String nuevoNombre = sc.nextLine();
                System.out.print("Nuevo dueño (actual: " + a.getDuenio() + "): ");
                String nuevoDuenio = sc.nextLine();

                System.out.print("Nuevo daño (actual: " + a.getDanio() + "): ");
                while (!sc.hasNextInt()) {
                    System.out.print("Introduce un número válido para el daño: ");
                    sc.next();
                }
                int nuevoDanio = sc.nextInt();
                sc.nextLine();

                System.out.print("Nuevo universo (actual: " + a.getUniverso() + "): ");
                String nuevoUniverso = sc.nextLine();

                System.out.print("Nuevo tipo (1: cuerpo a cuerpo, 2: distancia): ");
                int nuevoTipo = sc.nextInt();
                sc.nextLine();

                Arma nuevaArma = (nuevoTipo == 1)
                        ? new ArmaMelee(nuevoNombre, nuevoDuenio, nuevoDanio, nuevoUniverso)
                        : new ArmaDistancia(nuevoNombre, nuevoDuenio, nuevoDanio, nuevoUniverso);

                listaArmas.set(i, nuevaArma);
                System.out.println("Arma modificada correctamente.");
                return true;
            }
        }
        System.out.println("No se encontró un arma con ese nombre.");
        return false;
    }

    public void mostrarTodas() {
        if (listaArmas.isEmpty()) {
            System.out.println("No hay armas registradas.");
        } else {
            for (Arma a : listaArmas) {
                System.out.println(a);
            }
        }
    }

    public void mostrarPorTipo(TipoArma tipo) {
        boolean encontrado = false;
        for (Arma a : listaArmas) {
            if (a.getTipo() == tipo) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay armas del tipo: " + tipo);
        }
    }

    public void mostrarPorUniverso(String universo) {
        boolean encontrado = false;
        for (Arma a : listaArmas) {
            if (a.getUniverso().equalsIgnoreCase(universo)) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay armas del universo: " + universo);
        }
    }

    public void guardarEnFichero() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_FICHERO))) {
            for (Arma a : listaArmas) {
                pw.println(a.getClass().getSimpleName() + ";" +
                           a.getNombre() + ";" +
                           a.getDuenio() + ";" +
                           a.getDanio() + ";" +
                           a.getUniverso());
            }
            System.out.println("Datos guardados en texto plano.");
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }

    private List<Arma> cargarDesdeFichero() {
        List<Arma> armas = new ArrayList<>();
        File fichero = new File(RUTA_FICHERO);
        if (!fichero.exists()) return armas;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String tipo = partes[0];
                    String nombre = partes[1];
                    String duenio = partes[2];
                    int danio = Integer.parseInt(partes[3]);
                    String universo = partes[4];

                    Arma arma = tipo.equals("ArmaMelee")
                            ? new ArmaMelee(nombre, duenio, danio, universo)
                            : new ArmaDistancia(nombre, duenio, danio, universo);

                    armas.add(arma);
                }
            }
            System.out.println("Datos cargados desde texto plano.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        return armas;
    }
}

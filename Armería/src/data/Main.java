package data;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorArmas gestor = new GestorArmas();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Añadir arma");
            System.out.println("2. Mostrar todas las armas");
            System.out.println("3. Mostrar armas por tipo");
            System.out.println("4. Mostrar armas por universo");
            System.out.println("5. Salir");
            System.out.println("6. Eliminar arma");
            System.out.println("7. Modificar arma");
            System.out.print("Elige una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Por favor, introduce un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del arma: ");
                    String nombre = sc.nextLine();
                    System.out.print("Dueño del arma: ");
                    String duenio = sc.nextLine();
                    System.out.print("Daño: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Introduce un número válido para el daño: ");
                        sc.next();
                    }
                    int danio = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Universo: ");
                    String universo = sc.nextLine();
                    System.out.print("Tipo (1: cuerpo a cuerpo, 2: distancia): ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Introduce 1 o 2: ");
                        sc.next();
                    }
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    Arma arma = (tipo == 1)
                        ? new ArmaMelee(nombre, duenio, danio, universo)
                        : new ArmaDistancia(nombre, duenio, danio, universo);
                    gestor.agregarArma(arma);
                    System.out.println("Arma añadida.");
                    break;
                case 2:
                    gestor.mostrarTodas();
                    break;
                case 3:
                    System.out.print("Tipo (1: cuerpo a cuerpo, 2: distancia): ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Introduce 1 o 2: ");
                        sc.next();
                    }
                    int tipoMostrar = sc.nextInt();
                    sc.nextLine();
                    TipoArma tipoEnum = (tipoMostrar == 1) ? TipoArma.MELEE : TipoArma.DISTANCIA;
                    gestor.mostrarPorTipo(tipoEnum);
                    break;
                case 4:
                    System.out.print("Universo: ");
                    String uni = sc.nextLine();
                    gestor.mostrarPorUniverso(uni);
                    break;
                case 5:
                    gestor.guardarEnFichero();
                    System.out.println("Saliendo...");
                    break;
                case 6:
                    System.out.print("Nombre del arma a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    gestor.eliminarArmaPorNombre(nombreEliminar);
                    break;
                case 7:
                    System.out.print("Nombre del arma a modificar: ");
                    String nombreModificar = sc.nextLine();
                    gestor.modificarArma(nombreModificar, sc);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
        sc.close();
    }
}

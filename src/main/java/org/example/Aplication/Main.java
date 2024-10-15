package org.example.Aplication;

import org.example.Aplication.domain.Biblioteca;
import org.example.Infraestructure.BibliotecaRepositoryImpl;
import org.example.Interfaces.BibliotecaRepository;
import org.example.Service.BibliotecaService;
import org.example.Service.BibliotecaServiceImpl;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaService bibliotecaService;

    static {
        BibliotecaRepository bibliotecaRepository = new BibliotecaRepositoryImpl();
        bibliotecaService = new BibliotecaServiceImpl(bibliotecaRepository);
    }

    public static void main(String[] args) {
boolean salir = false;

while (!salir){
    System.out.println("1. Lista bibliotecas");
    System.out.println("2. Crear biblioteca");
    System.out.println("3. Actualizar biblioteca");
    System.out.println("4. Eliminar biblioteca");
    System.out.println("5. Salir");
    System.out.print("Seleccione una opción: ");
    int opcion = scanner.nextInt();

    scanner.nextLine();

    switch (opcion){
        case 1:
            ListadeBibliotecas();
            break;
        case 2:
            crearBiblioteca();
            break;
        case 3:
actualizarBiblioteca();
            break;
        case  4:
eliminarBiblioteca();
            break;
        case  5:

            salir= true;
            break;
        default:
            System.out.println("Opccion invalida");
    }
}



    }

private static void ListadeBibliotecas(){
    List<Biblioteca>bibliotecas = bibliotecaService.FindAll();

    if (bibliotecas.isEmpty()){
        System.out.println("no hay bibliotecas");
    }else {
        System.out.println("Lista de bibliotecas");
        for (Biblioteca biblioteca : bibliotecas){
            System.out.println(biblioteca);
        }
    }
}

private static void crearBiblioteca(){
    System.out.println("Ingrese el id de la biblioteca");
    int id = scanner.nextInt();
    scanner.nextLine();
    System.out.println("ingrese el nombre de la biblioteca");
    String nombreBilioteca = scanner.nextLine();
    System.out.println("Ingrese la direccion de la biblioteca");
    String direccion = scanner.nextLine();

    Biblioteca biblioteca = new Biblioteca();
    biblioteca.setId(id);
    biblioteca.setDireccion(direccion);
    biblioteca.setNombre(nombreBilioteca);
try {
    bibliotecaService.save(biblioteca);
    System.out.println("Biblioteca creada");
}catch (IllegalArgumentException e){
    System.out.println("error" + e.getMessage());
}


    }


private static void actualizarBiblioteca(){
    System.out.println("Ingrese el Id de la biblioteca");
    int id = scanner.nextInt();
    scanner.nextLine();

    Biblioteca biblioteca = bibliotecaService.findById(id);
    if (biblioteca == null){
        System.out.println("No se encontro ninguna biblioteca con Id" + id);

    }

    System.out.println("Ingrese el nuevo nombre de la biblioteca");
    String nombre = scanner.nextLine();
    if (!nombre.isEmpty()){
        biblioteca.setNombre(nombre);
    }

    System.out.println("Ingrese la nueva direccion de la biblioteca");
    String direccion = scanner.nextLine();
    if (!direccion.isEmpty()){
        biblioteca.setDireccion(direccion);
    }

    try {
        bibliotecaService.update(biblioteca);
        System.out.println("Biblioteca Actualizada");
    }catch (IllegalArgumentException e){
        System.out.println("Error: " + e.getMessage());
    }




    }
    private static void eliminarBiblioteca () {
        System.out.print("Ingrese el ID de la biblioteca a eliminar: ");
        int idd = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Biblioteca biblioteca1 = bibliotecaService.findById(idd);
        if (biblioteca1 == null) {
            System.out.println("No se encontró la biblioteca con ID " + idd);
            return;
        }

        bibliotecaService.delete(idd);
        System.out.println("biblioteca  eliminada exitosamente.");
    }
}
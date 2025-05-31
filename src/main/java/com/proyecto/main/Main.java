package com.proyecto.main;

import com.proyecto.entidades.Contacto;
import com.proyecto.dao.ContactoDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static final ContactoDAO dao = new ContactoDAO ();
	private static final Scanner scanner = new Scanner (System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion;
        do {
            System.out.println("1. Agregar contacto");
            System.out.println("2. Ver todos");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Editar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
	                case 1:
	                    agregar();
	                    break;
	                case 2:
	                    listar();
	                    break;
	                case 3:
	                    buscar();
	                    break;
	                case 4:
	                    editar();
	                    break;
	                case 5:
	                    eliminar();
	                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } while (opcion != 0);
    }

	//Agregar contacto
    static void agregar() throws Exception {
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa tu numero de teléfono: ");
        int telefono = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingresa tu Email: ");
        String email = scanner.nextLine();
        System.out.print("Ingresa tu fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        Contacto c = new Contacto();
        c.setNombre(nombre);
        c.setTelefono(telefono);
        c.setEmail(email);
        c.setFecha_nacimiento(fecha);
        dao.agregarContacto(c);
    }

    //Listar contactos
    static void listar() throws Exception {
        List<Contacto> lista = dao.obtenerTodos();
        lista.forEach(System.out::println);
    }

    //Buscar contactos
    static void buscar() throws Exception {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        Contacto c = dao.buscarPorNombre(nombre);
        if (c != null) System.out.println(c);
        else System.out.println("No encontrado.");
    }

    //Editar contacto
    static void editar() throws Exception {
        System.out.print("ID del contacto a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Contacto c = new Contacto();
        c.setId(id);
        System.out.print("Nuevo nombre: ");
        c.setNombre(scanner.nextLine());
        System.out.print("Nuevo teléfono: ");
        c.setTelefono(Integer.parseInt(scanner.nextLine()));
        System.out.print("Nuevo email: ");
        c.setEmail(scanner.nextLine());
        System.out.print("Nueva fecha nacimiento (YYYY-MM-DD): ");
        c.setFecha_nacimiento(LocalDate.parse(scanner.nextLine()));
        dao.actualizarContacto(c);
    }
    
    //Eliminar contacto
    static void eliminar() throws Exception {
        System.out.print("ID del contacto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        dao.eliminarContacto(id);
		
	}

}

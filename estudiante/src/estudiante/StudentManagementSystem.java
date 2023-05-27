/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiante;

/**
 *
 * @author Usuario
 */
import java.io.*;

public class StudentManagementSystem {
    private Estudiante[] estudiantes;

    public StudentManagementSystem() {
        estudiantes = new Estudiante[50]; // Capacidad máxima de 50 estudiantes
    }

    public void addStudent(Estudiante estudiante) {
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i] == null) {
                estudiantes[i] = estudiante;
                break;
            }
        }
    }

    public Estudiante getStudent(int rollNumber) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante != null && estudiante.getNumeroLista() == rollNumber) {
                return estudiante;
            }
        }
        return null;
    }

    public void storeData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(estudiantes);
            System.out.println("Datos de estudiantes almacenados en el archivo: " + filename);
        } catch (IOException e) {
            System.out.println("Error al almacenar los datos de los estudiantes: " + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            estudiantes = (Estudiante[]) ois.readObject();
            System.out.println("Datos de estudiantes cargados desde el archivo: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos de los estudiantes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();

        // Crear objetos de estudiantes
        Estudiante estudiante1 = new Estudiante("Laura Balderrama", 4, "2do semestre");
        Estudiante estudiante2 = new Estudiante("Diego Rivera", 18, "2do semestre");
        Estudiante estudiante3 = new Estudiante("Victor Granados", 24, "2do semestre");

        // Agregar estudiantes al sistema
        system.addStudent(estudiante1);
        system.addStudent(estudiante2);
        system.addStudent(estudiante3);

        // Almacenar datos en un archivo
        system.storeData("datos_estudiantes.dat");

        // Borrar los datos de los estudiantes
        system.estudiantes = new Estudiante[100];

        // Cargar datos desde el archivo
        system.loadData("datos_estudiantes.dat");

        // Recuperar información de un estudiante y mostrarla
        Estudiante estudianteRecuperado = system.getStudent(1);
        if (estudianteRecuperado != null) {
            System.out.println("Nombre del estudiante: " + estudianteRecuperado.getNombre());
            System.out.println("Número de lista: " + estudianteRecuperado.getNumeroLista());
            System.out.println("Grado: " + estudianteRecuperado.getGrado());
        } else {
            System.out.println("Estudiante no encontrado");
        }
    }
}

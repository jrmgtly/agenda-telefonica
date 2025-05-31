package com.proyecto.entidades;

import java.time.LocalDate;

import lombok.Data;

//Getters & Setters, constructor y metodo ToString utilizando Lombok

@Data

public class Contacto {

	private int id;
	private int telefono;
	private String nombre;
	private String email;
	private LocalDate fecha_nacimiento;
	
	
}

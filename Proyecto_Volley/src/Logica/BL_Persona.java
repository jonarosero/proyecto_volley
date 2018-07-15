/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Clases.Persona;
import Data.Dat_Persona;

/**
 *
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class BL_Persona {

    Dat_Persona persona = new Dat_Persona();

    // Metodo para insertar persona
    public int insertarPersona(Persona objPersona) {
        String cedula = objPersona.getCedula();
        String nombre = objPersona.getNombres();
        String apelli = objPersona.getApellidos();
        int edad = objPersona.getEdad();
        String genero = objPersona.getGenero();
        return persona.InsertarPersona(nombre, apelli, cedula, edad, genero);
    }

}

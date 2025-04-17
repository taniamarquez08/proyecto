package Historial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// ---------------------------------------------------------------------------------------------------
// Clase para el modelo de datos de un paciente
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// ---------------------------------------------------------------------------------------------------
// Clase para el modelo de datos de un paciente
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// ---------------------------------------------------------------------------------------------------
// Clase para el modelo de datos de un paciente
class Paciente {
    private String nombre, edad, peso, tipoSangre, estatura;
    private boolean diabetes, hipertension, corazon;

    public Paciente(String nombre, String edad, String peso, String tipoSangre, String estatura,
                    boolean diabetes, boolean hipertension, boolean corazon) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.diabetes = diabetes;
        this.hipertension = hipertension;
        this.corazon = corazon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isHipertension() {
        return hipertension;
    }

    public void setHipertension(boolean hipertension) {
        this.hipertension = hipertension;
    }

    public boolean isCorazon() {
        return corazon;
    }

    public void setCorazon(boolean corazon) {
        this.corazon = corazon;
    }

    @Override
    public String toString() {
        return nombre + " - " + edad + " a�os - " + peso + " kg - Tipo de Sangre: " + tipoSangre
               + " - Estatura: " + estatura + " cm - Padecimientos: "
               + (diabetes ? "Diabetes " : "") + (hipertension ? "Hipertensi�n " : "") + (corazon ? "Coraz�n " : "");
    }
}
package RegistroCitas;
public class DateTime {
    
    private int año;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;

    public DateTime(int a, int m, int d, int h, int min){

        año = a;
        mes = m;
        dia = d;
        hora = h;
        minuto = min;

    }

    public int getAño() {
        return año;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setFechaHora(int a, int m, int d, int h, int min) {
        
        año = a;
        mes = m;
        dia = d;
        hora = h;
        minuto = min;

    }

    public String getString(){

        return "Hora: " + hora + ":" + minuto + " , el: " + dia + "/ " + mes + "/ " + año; 

    }

}

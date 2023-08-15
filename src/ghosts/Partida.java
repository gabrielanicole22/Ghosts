/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gabriela Mejía
 */
public class Partida {

    Usuario contrincante;
    boolean victoria;
    String bandoUsado;
    double puntosGanados;
    String fecha;
    String mensajeGane;
    public Partida(Usuario contrincante, boolean victoria, String mensajeGane, String bandoUsado, double puntosGanados, Date fechaObj) {
        this.contrincante = contrincante;
        this.victoria = victoria;
        this.bandoUsado = bandoUsado;
        this.puntosGanados = puntosGanados;
        this.mensajeGane=mensajeGane;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
        this.fecha = formato.format(fechaObj);
    }
}
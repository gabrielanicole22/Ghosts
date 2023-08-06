/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

/**
 *
 * @author Gabriela Mejía
 */
public class Stats {
    
    private static int partidasJugadas = 0;
    
    public static int getPartidasJugadas() {
        return partidasJugadas;
    }
    
    void addPartida(boolean gananHeroes) {
        partidasJugadas++;
    }
}
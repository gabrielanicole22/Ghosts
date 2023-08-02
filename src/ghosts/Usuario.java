/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

import java.util.ArrayList;

/**
 *
 * @author Gabriela Mejía
 */
public class Usuario {
        private String usuario;
    private String contrasena;
    private double puntos = 0.0;
    int partidasBuenos = 0;
    int partidasMalos = 0;
    boolean tutorialActivo = false; // REPRESENTA SI EL MODO TUTORIAL ESTA ACTIVADO O NO.
    ArrayList<Partida> partidasJugadas;
  
    public Usuario(String usuario, String contrasena) {
        this.partidasJugadas = new ArrayList<Partida>();
        this.usuario = usuario.trim();
        this.contrasena = contrasena.trim();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (!usuario.trim().isEmpty()) {
            this.usuario = usuario.trim();
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (!contrasena.trim().isEmpty()) {
            this.contrasena = contrasena.trim();        
        }
    }
    
    public void addPartida(Partida partida) {
        partidasJugadas.add(partida);
        puntos += partida.puntosGanados;
    }

    public double getPuntos() {
        return puntos;
    }
    
    public ArrayList<Partida> getPartidas() {
        return partidasJugadas;
    }
    /**
     * Verifica que el nombre de usuario y la contraseña especificada sea igual a a guardada por el usuario.
     * @return 
     */
    public boolean validarCredenciales(String usuario, String contrasena) {
        return (this.usuario.equals(usuario) && this.contrasena.equals(contrasena));
    }    
}

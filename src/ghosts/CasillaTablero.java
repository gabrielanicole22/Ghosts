/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Gabriela Mejia
 */
public class CasillaTablero {

    JLabel label;
    Personaje personajeActual;
    int row;
    int column;

    public CasillaTablero(int row, int column, Personaje personajeActual) {
        this.label = new JLabel();
        this.row = row;
        this.column = column;
        this.personajeActual = personajeActual;
        this.label = new JLabel("[" + row + ", " + column + "]"); // Default label

        label.setBorder(BorderFactory.createLineBorder(Color.pink));
    }

    public void highlightMove(boolean activar) {
        if (activar) {
            label.setBackground(Color.green);
            label.setOpaque(true);
        } else {
            label.setOpaque(false);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setPersonaje(Personaje personaje) {
        personajeActual = personaje;
        if (personaje == null) {
            label.setText("");
            label.setIcon(null);
        } else {
            if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            } else {
                label.setText(personajeActual.nombre);
            }
        }
    }

    public void esconderCasilla(boolean esconder) {
        if (esconder) {
            if (personajeActual.iconOculto != null) {
                label.setIcon(personajeActual.iconOculto);
                label.repaint();
            } else {
                label.setIcon(null);
                label.setText("???");
            }
        } else {
            if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            } else {
                label.setText(personajeActual.nombre);
            }
        }
    }

    public void setSelected(boolean selected) {
        if (selected) {
            label.setBackground(Color.pink);
            label.setOpaque(true);
            label.repaint();
        } else {
            label.setOpaque(false);
        }
    }
}
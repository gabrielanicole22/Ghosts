/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

import java.util.ArrayList;

/**
 *
 * @author Gabriela Mejia
 */
public abstract class PersonajeGenius extends PersonajeBase {

    public PersonajeGenius(String nombre, int rango, boolean Player1, String iconPath) {
        super(nombre, rango, Player1, iconPath);
    }

    public static ArrayList<Personaje> getGhostsPlayer1Genius() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        if (Usuario.ModoGenius) {
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
        }
        return personajes;
    }

    public static ArrayList<Personaje> getGhostsPlayer1GeniusFake() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        if (Usuario.ModoGenius) {
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
        }
        return personajes;
    }

    public static ArrayList<Personaje> getGhostsPlayer2Genius() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        if (Usuario.ModoGenius) {
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
        }
        return personajes;
    }

    public static ArrayList<Personaje> getGhostsPlayer2GeniusFake() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        if (Usuario.ModoGenius) {
            personajes.add(new Personaje("fake", 0, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
        }
        return personajes;
    }
}

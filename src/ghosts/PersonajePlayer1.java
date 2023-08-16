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
public abstract class PersonajePlayer1 extends PersonajeBase {

    public PersonajePlayer1(String nombre, int rango, String iconPath) {
        super(nombre, rango, true, iconPath);
    }

    public static ArrayList<Personaje> getGhostsPlayer1() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();

        if (Usuario.ModoNormal) {
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));

        } else if (Usuario.ModoExpert) {
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
        } else if (Usuario.ModoGenius) {
            personajes.add(new Personaje("malo", 1, true, null));
            personajes.add(new Personaje("bueno", 2, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
        }
        return personajes;
    }
}

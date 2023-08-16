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
public abstract class PersonajePlayer2 extends PersonajeBase {

    public PersonajePlayer2(String nombre, int rango, String iconPath) {
        super(nombre, rango, false, iconPath);
    }

    public static ArrayList<Personaje> getGhostsPlayer2() {
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();

        if (Usuario.ModoNormal) {
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
        } else if (Usuario.ModoExpert) {
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
        } else if (Usuario.ModoGenius) {
            personajes.add(new Personaje("malo", 3, false, null));
            personajes.add(new Personaje("bueno", 4, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
            personajes.add(new Personaje("fake", 0, false, null));
        }
        return personajes;
    }
}

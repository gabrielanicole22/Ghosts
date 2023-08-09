/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

/**
 *
 * @author Gabriela Mejía
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;

public class Personaje {

    ImageIcon icono;
    String nombre;
    int rango;
    boolean esPlayer1;
    boolean posicionado = false;
    ImageIcon iconOculto;
    Usuario usuario;

    public Personaje(String nombre, int rango, boolean Player1, String iconPath) {
        this.nombre = nombre;
        this.rango = rango;
        this.esPlayer1 = Player1;

        try {
            Image resizedImg = resizeImage(ImageIO.read(new File("src/Icons/ghost.png")), 52, 52);
            iconOculto = new ImageIcon(resizedImg);
        } catch (Exception e) {
            iconOculto = null;
        }
        try {
            Image newImg = resizeImage(ImageIO.read(new File(iconPath)), 52, 52);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }
        loadIcon();
    }

    private void loadIcon() {
        String filename;
        filename = "src/Icons/" + nombre.replace(" ", "") + ".png";
        try {
            Image newImg = resizeImage(ImageIO.read(new File(filename)), 52, 52);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }
    }

    private Image resizeImage(Image img, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
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
            personajes.add(new Personaje("fake", 0, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
            personajes.add(new Personaje("fake", 10, true, null));
        }
        return personajes;
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

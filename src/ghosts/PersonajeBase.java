/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Gabriela Mejia
 */
public abstract class PersonajeBase implements IconoCargable, Redimensionable {

    protected ImageIcon icono;
    protected String nombre;
    protected int rango;
    protected boolean esPlayer1;
    protected boolean posicionado = false;
    protected ImageIcon iconOculto;
    protected Usuario usuario;

    public PersonajeBase(String nombre, int rango, boolean Player1, String iconPath) {
        this.nombre = nombre;
        this.rango = rango;
        this.esPlayer1 = Player1;

        try {
            Image resizedImg = resizeImage(ImageIO.read(new File("src/Icons/ghost.png")), 100, 100);
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

    public String getNombre() {
        return nombre;
    }

    private Image resizeImage(Image img, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    @Override
    public void loadIcon() {
        String filename;
        if (Usuario.EsModoAleatorio) {
            filename = "src/img/" + nombre.replace(" ", "") + ".png";
        } else {
            filename = "src/Icons/" + nombre.replace(" ", "") + ".png";
        }
        try {
            Image newImg = resizeImage(ImageIO.read(new File(filename)), 100, 100);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }
    }
}

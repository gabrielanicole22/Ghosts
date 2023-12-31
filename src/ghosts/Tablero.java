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
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class Tablero extends JPanel {

    private boolean hayCasillaSeleccionada = false;
    private CasillaTablero casillaSeleccionada;
    private CasillaTablero[][] casillas;
    private boolean turnoPlayer1 = true;
    boolean esTutorial;
    boolean hayRetirado = false;
    boolean juegoTerminado = false;
    private int GhostsMalosPlayer1;
    private int GhostsBuenosPlayer1;
    private int GhostsMalosPlayer2;
    private int GhostsBuenosPlayer2;

    ArrayList<Personaje> FantasmasInicialesPlayer1 = PersonajePlayer1.getGhostsPlayer1();
    ArrayList<Personaje> FantasmasInicialesPlayer2 = PersonajePlayer2.getGhostsPlayer2();
    ArrayList<Personaje> FantasmasInicialesGeniusP1 = PersonajeGenius.getGhostsPlayer1Genius();
    ArrayList<Personaje> FantasmasInicialesGeniusP2 = PersonajeGenius.getGhostsPlayer2Genius();
    ArrayList<Personaje> FantasmasInicialesGeniusP1Fake = PersonajeGenius.getGhostsPlayer1GeniusFake();
    ArrayList<Personaje> FantasmasInicialesGeniusP2Fake = PersonajeGenius.getGhostsPlayer2GeniusFake();
    private ArrayList<Personaje> FantasmasEliminadosPlayer1 = new ArrayList<Personaje>();
    private ArrayList<Personaje> FantasmasEliminadosPlayer2 = new ArrayList<Personaje>();

    private JTextArea GhostsP1EliminatedArea;
    private JTextArea GhostsP2EliminatedArea;

    SistemaUsuarios sistemaUsuarios;
    Stats stats;
    Usuario Player1, Player2;

    Juego gameWindow;
    MenuInicio mainWindow;
    private Image imagenFondo;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Dibujar la imagen de fondo
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

    public Tablero(JTextArea FantasmasEliminadosPlayer1Area, JTextArea FantasmasEliminadosPlayer2Area, SistemaUsuarios sistemaUsuarios, Stats stats, Usuario Player1, Usuario Player2, boolean esTutorial, Juego gameWindow, MenuInicio mainWindow) {
        // Cargar la imagen de fondo
        ImageIcon imagenIcono = new ImageIcon("src/img/fondo.jpg");
        imagenFondo = imagenIcono.getImage();

        // Agregar registro de partidas a los usuarios
        Player1.partidasBuenos++;
        Player2.partidasMalos++;

        sistemaUsuarios.actualizarUsuario(Player1);
        sistemaUsuarios.actualizarUsuario(Player2);
        casillas = new CasillaTablero[6][6];

        this.stats = stats;
        this.mainWindow = mainWindow;
        this.sistemaUsuarios = sistemaUsuarios;
        this.Player1 = Player1;
        this.Player2 = Player2;
        this.GhostsP1EliminatedArea = FantasmasEliminadosPlayer1Area;
        this.GhostsP2EliminatedArea = FantasmasEliminadosPlayer2Area;
        this.esTutorial = esTutorial;
        this.gameWindow = gameWindow;
        setLayout(new GridLayout(6, 6));
        // Crear las etiquetas para representar la cuadrícula del tablero
        casillas = new CasillaTablero[6][6];

        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                CasillaTablero casilla = new CasillaTablero(row, column, null); // RELLENAR PERSONAJES DESPUES
                casillas[row][column] = casilla;
                add(casillas[row][column].label);
            }
        }

        // Manejar eventos de clic en las etiquetas
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                // Todavia no se ha seleccionado una casilla
                if (!hayCasillaSeleccionada) {
                    // Obtener la posición de la etiqueta en la cuadrícula
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (casillas[i][j].label == label) {
                                casillaSeleccionada = casillas[i][j];

                                // VERIFICAR QUE SEA UNA FICHA DEL JUGADOR DEL TURNO ACTUAL Y TENGA UN PERSONAJE
                                if (casillaSeleccionada.personajeActual != null
                                        && casillaSeleccionada.personajeActual.esPlayer1 == turnoPlayer1) {
                                    hayCasillaSeleccionada = true;
                                    //mostrarInformacionPersonaje();
                                    if (esTutorial) {
                                        resaltarSiEsMovimientoValido();
                                    }
                                    resaltarZonasProhibidas();
                                    casillaSeleccionada.setSelected(true);
                                    break;
                                } else {
                                    // Reiniciar el valor de la casilla seleccionada ya que no cumple
                                    casillaSeleccionada = null;
                                    hayCasillaSeleccionada = false;
                                    break;
                                }
                            }
                        }
                    }
                } else { // ya hay una casilla seleccionada y se intentara mover
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 6; j++) {

                            if (casillas[i][j].label == label) {

                                // SI LA CASILLA CLICKEADA ES UNA FICHA DEL MISMO BANDO, CAMBIAR A ESA FICHA 
                                if (casillas[i][j].personajeActual != null) {
                                    if (casillas[i][j].personajeActual.esPlayer1 == turnoPlayer1) {
                                        // Actualizar casillas  
                                        borrarResaltadoMovimientos();
                                        casillaSeleccionada.setSelected(false); // Deseleccionar la casilla anterior

                                        casillaSeleccionada = casillas[i][j];
                                        casillaSeleccionada.setSelected(true);
                                        if (esTutorial) {
                                            resaltarSiEsMovimientoValido();
                                        }
                                        resaltarZonasProhibidas();
                                        //mostrarInformacionPersonaje();
                                        casillaSeleccionada.setSelected(true);
                                        break;
                                    }
                                }
                                // Se dio click en una casilla con un rival o una casilla vacia
                                if (esMovimientoValido(i, j)) {
                                    moverPersonaje(i, j);
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR. MOVIMIENTO INVALIDO.");
                                }
                            }
                        }
                    }
                }
            }
        };
        // Agregar el manejador de eventos a las etiquetas
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                casillas[row][column].label.addMouseListener(mouseAdapter);
            }
        }
        esconderPersonajes();

        SwingUtilities.invokeLater(() -> {
        });
        /* posicionarFantasmas();
        resaltarZonasProhibidas();
        mostrarMensajeInicial(); // mostrar el turno inicial del jugador
        esconderPersonajes();
         */
        setVisible(true);
        repaint();
    }

    public void TerminardeCargar() {
        posicionarFantasmas();
        resaltarZonasProhibidas();
        mostrarMensajeInicial(); // mostrar el turno inicial del jugador
        esconderPersonajes();
    }

    private boolean esMovimientoValido(int row, int column) {
        int currentRow = casillaSeleccionada.row;
        int currentColumn = casillaSeleccionada.column;

        boolean esPlayer1 = casillaSeleccionada.personajeActual.esPlayer1;

        // Verificar si el movimiento es ortogonal
        boolean isOrthogonal = (row == currentRow && Math.abs(column - currentColumn) == 1)
                || (column == currentColumn && Math.abs(row - currentRow) == 1);

        // Verificar si la nueva posición está dentro de los espacios restringidos
        boolean isRestricted = (row == 0 && column == 0) || (row == 0 && column == 5)
                || (row == 5 && column == 0) || (row == 5 && column == 5);

        // verificar si hay un personaje en la casilla y que sea del mismo bando
        boolean hasCharacter = casillas[row][column].personajeActual != null
                && casillas[row][column].personajeActual.esPlayer1 == esPlayer1;

        // Si el personaje es de rango 2 y se mueve a las casillas mencionadas o si el personaje es de rango 4 y se mueve a las casillas mencionadas, se registra al jugador actual como ganador
        if ((casillaSeleccionada.personajeActual.rango == 2 && ((row == 0 && column == 0) || (row == 0 && column == 5)))
                || (casillaSeleccionada.personajeActual.rango == 4 && ((row == 5 && column == 0) || (row == 5 && column == 5)))) {
            juegoTerminado = true;
            Usuario ganador, perdedor;
            String bandoGanador, bandoPerdedor;
            if (turnoPlayer1) {
                ganador = Player1;
                perdedor = Player2;
                bandoGanador = "Player 1";
                bandoPerdedor = "Player 2";
            } else {
                ganador = Player2;
                perdedor = Player1;
                bandoGanador = "Player 2";
                bandoPerdedor = "Player 1";
            }

            String mensaje = " ";
            // Mostrar mensaje de victoria
            if (turnoPlayer1) {
                mensaje = "¡" + Player1.getUsuario() + " triunfó al sacar del castillo un fantasma bueno!";
            } else {
                mensaje = "¡" + Player2.getUsuario() + " triunfó al sacar del castillo un fantasma bueno!";
            }
            Partida partidaGanador = new Partida(perdedor, true, mensaje, bandoGanador, 3, new Date(), ganador, perdedor);
            Partida partidaPerdedor = new Partida(ganador, false, mensaje, bandoPerdedor, 0, new Date(), ganador, perdedor);

            // Agrega las partidas a los jugadores
            ganador.addPartida(partidaGanador);
            perdedor.addPartida(partidaPerdedor);

            // Agrega las partidas al sistema de usuarios (si es necesario)
            sistemaUsuarios.actualizarUsuario(ganador);
            sistemaUsuarios.actualizarUsuario(perdedor);

            JOptionPane.showMessageDialog(null, mensaje);

            gameWindow.dispose();
            return true;
        }
        // Si el personaje es de rango 1 o 3, mostrar mensaje sin ganar
        if (((casillaSeleccionada.personajeActual.rango == 1 || casillaSeleccionada.personajeActual.rango == 10) && ((row == 0 && column == 0) || (row == 0 && column == 5)))
                || ((casillaSeleccionada.personajeActual.rango == 3 || casillaSeleccionada.personajeActual.rango == 0) && ((row == 5 && column == 0) || (row == 5 && column == 5)))) {

            String mensaje;

            if (casillaSeleccionada.personajeActual.rango == 1 || casillaSeleccionada.personajeActual.rango == 3) {
                mensaje = "Has sacado a un fantasma malo del castillo.";
            } else {
                mensaje = "Has sacado a un fantasma falso del castillo.";
            }

            casillaSeleccionada.personajeActual = null;
            JOptionPane.showMessageDialog(null, mensaje);

            System.out.println(mensaje);

            return true;
        }

        // El movimiento es válido solo si es ortogonal, no está en un espacio restringido (zonas prohibidas) y no tiene otra ficha del mismo bando
        return isOrthogonal && !isRestricted && !hasCharacter;
    }

    private void resaltarSiEsMovimientoValido() {
        // Iterar por todas las casillas y verificar que sean un movimiento valido para resaltarlas
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                casillas[i][j].highlightMove(esMovimientoValido(i, j));
                casillas[i][j].label.repaint();
            }
        }
        resaltarZonasProhibidas();
    }

    private void esconderPersonajes() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (casillas[i][j].personajeActual != null) {
                    casillas[i][j].esconderCasilla(turnoPlayer1 != casillas[i][j].personajeActual.esPlayer1);
                }
            }
        }
    }

    public void resaltarZonasProhibidas() {
        // Resaltar las zonas restringidas en color negro
        int[][] restrictedZones = {{0, 0}, {0, 5}, {5, 0}, {5, 5}};
        for (int[] zone : restrictedZones) {
            int row = zone[0];
            int column = zone[1];
            colocarImagenEnCasilla(row, column, "puertaa", 100, 100);
        }
    }

    public void colocarImagenEnCasilla(int fila, int columna, String imageName, int width, int height) {
        ImageIcon icon = new ImageIcon("src/img/" + imageName + ".jpg");
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        casillas[fila][columna].label.setIcon(resizedIcon);
    }

    private void moverPersonaje(int newRow, int newColumn) {
        if (juegoTerminado) {
            return;
        }
        if (casillas[newRow][newColumn].personajeActual != null) {
            Personaje ganador = calcularCombate(casillaSeleccionada.personajeActual, casillas[newRow][newColumn].personajeActual);
            JPanel panel;
            String mensajeCombate = "";

            if (ganador == casillaSeleccionada.personajeActual) {
                if (casillas[newRow][newColumn].personajeActual.rango == 2) {
                    mensajeCombate = "¡Te has comido a un fantasma bueno de " + Player1.getUsuario() + "!";
                } else if (casillas[newRow][newColumn].personajeActual.rango == 4) {
                    mensajeCombate = "¡Te has comido a un fantasma bueno de " + Player2.getUsuario() + "!";
                } else if (casillas[newRow][newColumn].personajeActual.rango == 1) {
                    mensajeCombate = "¡Te has comido a un fantasma malo de " + Player1.getUsuario() + "!";
                } else if (casillas[newRow][newColumn].personajeActual.rango == 3) {
                    mensajeCombate = "¡Te has comido a un fantasma malo de " + Player2.getUsuario() + "!";
                } else if (casillas[newRow][newColumn].personajeActual.rango == 0) {
                    mensajeCombate = "¡ERA UN FANTASMA FALSO de " + Player2.getUsuario() + "!";
                } else if (casillas[newRow][newColumn].personajeActual.rango == 10) {
                    mensajeCombate = "¡ERA UN FANTASMA FALSO de " + Player1.getUsuario() + "!";
                }

            } else {
                mensajeCombate = "¡Tu fantasma falso no puede comer a fantasmas malos ni buenos, queda eliminado!";
            }

            JOptionPane.showMessageDialog(null, mensajeCombate);
            // Eliminar pieza derrotada.
            if (ganador == null) { // Ambas piezas fueron eliminadas porque eran del mismo rango
                casillaSeleccionada.setPersonaje(null);
                casillas[newRow][newColumn].setPersonaje(null);
            } else if (casillaSeleccionada.personajeActual == ganador) {
                // Mover el ganador a la casilla de la pieza derrotada
                casillaSeleccionada.setPersonaje(null);
                casillas[newRow][newColumn].setPersonaje(ganador);
            } else {
                // La pieza atacante perdio
                casillaSeleccionada.setPersonaje(null);
            }
            mostrarPersonajesEliminados();
            actualizarTurno();
            return;
        }
        // No habia un personaje en esa casilla asi que solo se actualiza la posicion
        Personaje personaje = casillaSeleccionada.personajeActual;
        casillaSeleccionada.setPersonaje(null);
        borrarResaltadoMovimientos();
        // Mover la imagen a la nueva posición
        casillas[newRow][newColumn].setPersonaje(personaje);
        actualizarTurno();
    }

    public Personaje calcularCombate(Personaje atacante, Personaje defensor) {
        Personaje ganador = atacante;

        if ((atacante.rango == 0 || atacante.rango == 10) && (defensor.rango != 0 && defensor.rango != 10)) {
            // Si el atacante es de rango 0 o 10, y el defensor no es de rango 0 ni 10
            if (atacante.rango == 0) {
                FantasmasEliminadosPlayer1.add(atacante);
            } else {
                FantasmasEliminadosPlayer2.add(atacante);
            }
            ganador = defensor; // El defensor gana en este caso
        } else {
            if (defensor.esPlayer1) {
                FantasmasEliminadosPlayer1.add(defensor);
            } else {
                FantasmasEliminadosPlayer2.add(defensor);
            }
        }

        return ganador;
    }

    public void actualizarTurno() {
        if (juegoTerminado) {
            return;
        }

        casillaSeleccionada = null;
        hayCasillaSeleccionada = false;

        turnoPlayer1 = !turnoPlayer1;
        setVisible(false);
        if (!esTutorial) {
            esconderPersonajes();
        }
        GhostsP1EliminatedArea.setText("");
        GhostsP2EliminatedArea.setText("");

        String mensaje;
        if (!turnoPlayer1) {
            mensaje = "Fin del turno de " + Player1.getUsuario() + ", deja que  " + Player2.getUsuario() + " juegue su turno.";
        } else {
            mensaje = "Fin del turno de " + Player2.getUsuario() + " deja que  " + Player1.getUsuario() + " juegue su turno.";
        }
        JOptionPane.showMessageDialog(null, mensaje);

        String mensajeTurno = MensajeTurnosJugador();
        String player1Nombre = NombreUser1();
        String player2Nombre = NombreUser2();
        borrarResaltadoMovimientos();
        resaltarZonasProhibidas();

        gameWindow.setTurnoLabel(mensajeTurno);
        gameWindow.setPlayer1(player1Nombre);
        gameWindow.setPlayer2(player2Nombre);
        mostrarPersonajesEliminados();

        if (!esTutorial) {
            esconderPersonajes();
        }
        setVisible(true);
    }

    public void mostrarMensajeInicial() {
        String mensaje2 = MensajeTurnosJugador();
        String mensaje1 = NombreUser1();
        String mensaje3 = NombreUser2();
        gameWindow.setTurnoLabel(mensaje2);
        gameWindow.setPlayer1(mensaje1);
        gameWindow.setPlayer2(mensaje3);
        mostrarPersonajesEliminados();
    }

    private String MensajeTurnosJugador() {
        if (!turnoPlayer1) {
            return Player2.getUsuario() + " jugando.";
        } else {
            return Player1.getUsuario() + " jugando.";
        }
    }

    private String NombreUser1() {
        return "Fantasmas de: " + Player1.getUsuario();
    }

    private String NombreUser2() {
        return "Fantasmas de: " + Player2.getUsuario();
    }

    public void surrender() {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de rendirte?", "Confirmar rendición", JOptionPane.YES_NO_OPTION);

        Date fecha = new Date();

        if (confirm == JOptionPane.YES_OPTION) {
            Usuario ganador, perdedor;
            String bandoGanador, bandoPerdedor;
            if (turnoPlayer1) {
                ganador = Player2;
                perdedor = Player1;
                bandoGanador = "Player 2";
                bandoPerdedor = "Player 1";
            } else {
                ganador = Player1;
                perdedor = Player2;
                bandoGanador = "Player 1";
                bandoPerdedor = "Player 2";
            }
            String mensaje = ganador.getUsuario() + " ha ganado ya que "
                    + perdedor.getUsuario() + " se ha retirado del juego. - " + fecha;
            Partida partidaGanador = new Partida(perdedor, true, mensaje, bandoGanador, 3, fecha, ganador, perdedor);
            Partida partidaPerdedor = new Partida(ganador, false, mensaje, bandoPerdedor, 0, fecha, ganador, perdedor);

            // El bando opuesto gana la partida.
            stats.addPartida(!turnoPlayer1);
            ganador.addPartida(partidaGanador);
            perdedor.addPartida(partidaPerdedor);

            sistemaUsuarios.actualizarUsuario(ganador);
            sistemaUsuarios.actualizarUsuario(perdedor);
            juegoTerminado = true;

            JOptionPane.showMessageDialog(null, mensaje);
            gameWindow.dispose();
        }
    }

    public void borrarResaltadoMovimientos() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                casillas[i][j].label.setOpaque(false);
                casillas[i][j].label.repaint();
            }
        }
        resaltarZonasProhibidas();
    }

    public void mostrarPersonajesEliminados() {
        if (Usuario.ModoNormal) {
            GhostsMalosPlayer1 = 4;
            GhostsMalosPlayer2 = 4;
            GhostsBuenosPlayer1 = 4;
            GhostsBuenosPlayer2 = 4;
        } else if (Usuario.ModoExpert) {
            GhostsMalosPlayer1 = 2;
            GhostsMalosPlayer2 = 2;
            GhostsBuenosPlayer1 = 2;
            GhostsBuenosPlayer2 = 2;
        } else if (Usuario.ModoGenius) {
            GhostsMalosPlayer1 = 1;
            GhostsMalosPlayer2 = 1;
            GhostsBuenosPlayer1 = 1;
            GhostsBuenosPlayer2 = 1;
        }
        String mensajeVictoria = " ";

        for (Personaje personaje : FantasmasEliminadosPlayer1) {
            if (personaje.rango == 1) {
                GhostsMalosPlayer1--;
            } else if (personaje.rango == 2) {
                GhostsBuenosPlayer1--;
            }
        }
        for (Personaje personaje : FantasmasEliminadosPlayer2) {
            if (personaje.rango == 3) {
                GhostsMalosPlayer2--;
            } else if (personaje.rango == 4) {
                GhostsBuenosPlayer2--;
            }
        }

        String mensajePlayer1 = "Fantasmas malos: " + GhostsMalosPlayer1 + "\n"
                + "Fantasmas buenos: " + GhostsBuenosPlayer1 + "\n";
        String mensajePlayer2 = "Fantasmas malos: " + GhostsMalosPlayer2 + "\n"
                + "Fantasmas buenos: " + GhostsBuenosPlayer2 + "\n";

        GhostsP1EliminatedArea.setText(mensajePlayer1);
        GhostsP2EliminatedArea.setText(mensajePlayer2);

        // Verificar si los fantasmas buenos o malos llegaron a 0
        if (GhostsBuenosPlayer1 == 0 || GhostsBuenosPlayer2 == 0 || GhostsMalosPlayer1 == 0 || GhostsMalosPlayer2 == 0) {
            if (GhostsMalosPlayer1 == 0) {
                mensajeVictoria = "¡" + Player1.getUsuario() + " triunfó porque " + Player2.getUsuario() + " le capturó todos sus fantasmas malos!";
                Partida partidaGanador = new Partida(Player2, true, mensajeVictoria, "1", 3, new Date(), Player1, Player2);
                Partida partidaPerdedor = new Partida(Player1, false, mensajeVictoria, "2", 0, new Date(), Player1, Player2);
                Player1.addPartida(partidaGanador);
                Player2.addPartida(partidaPerdedor);
                stats.addPartida(true);
            } else if (GhostsMalosPlayer2 == 0) {
                mensajeVictoria = "¡" + Player2.getUsuario() + " triunfó porque " + Player1.getUsuario() + " le capturó todos sus fantasmas malos!";
                Partida partidaGanador = new Partida(Player1, true, mensajeVictoria, "2", 3, new Date(), Player2, Player1);
                Partida partidaPerdedor = new Partida(Player2, false, mensajeVictoria, "1", 0, new Date(), Player2, Player1);
                Player2.addPartida(partidaGanador);
                Player1.addPartida(partidaPerdedor);
                stats.addPartida(false);
            } else if (GhostsBuenosPlayer1 == 0) {
                mensajeVictoria = "¡" + Player2.getUsuario() + " triunfó sobre " + Player1.getUsuario() + " porque capturó todos sus fantasmas buenos!";
                Partida partidaGanador = new Partida(Player2, true, mensajeVictoria, "2", 3, new Date(), Player2, Player1);
                Partida partidaPerdedor = new Partida(Player1, false, mensajeVictoria, "1", 0, new Date(), Player2, Player1);
                Player2.addPartida(partidaGanador);
                Player1.addPartida(partidaPerdedor);
                stats.addPartida(false);
            } else if (GhostsBuenosPlayer2 == 0) {
                mensajeVictoria = "¡" + Player1.getUsuario() + " triunfó sobre " + Player2.getUsuario() + " porque capturó todos sus fantasmas buenos!";
                Partida partidaGanador = new Partida(Player1, true, mensajeVictoria, "1", 3, new Date(), Player1, Player2);
                Partida partidaPerdedor = new Partida(Player2, false, mensajeVictoria, "2", 0, new Date(), Player1, Player2);
                Player1.addPartida(partidaGanador);
                Player2.addPartida(partidaPerdedor);
                stats.addPartida(true);
            }

            JOptionPane.showMessageDialog(null, mensajeVictoria);
            juegoTerminado = true;
            gameWindow.dispose();
            mainWindow.setStats(stats);
        }
    }
    /*
     private int obtenerCoordenada(String mensaje) {
        int coordenada = -1;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensaje);
                coordenada = Integer.parseInt(input);
                if (coordenada >= 0 && coordenada <= 5) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Coordenada fuera de rango. Ingresa un valor entre 0 y 5.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor numérico válido.");
            }
        }
        return coordenada;
    }*/
    private boolean player1FinishedPositioning = false;
    private boolean player2FinishedPositioning = false;
    private boolean juegoComenzado = false;

    //Recursiva
    private int obtenerCoordenada(String mensaje) {
        try {
            String input = JOptionPane.showInputDialog(mensaje);
            int coordenada = Integer.parseInt(input);
            if (coordenada >= 0 && coordenada <= 5) {
                return coordenada;
            } else {
                JOptionPane.showMessageDialog(null, "Coordenada fuera de rango. Ingresa un valor entre 0 y 5.");
                return obtenerCoordenada(mensaje);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa un valor numérico válido.");
            return obtenerCoordenada(mensaje);
        }
    }

    public void posicionarAleatoriamente() {
        Random random = new Random();
        int charactersPlaced = 0;
        for (int i = 0; i < FantasmasInicialesPlayer1.size(); i++) {
            Personaje personajeActual = FantasmasInicialesPlayer1.get(i);
            int columnaAleatoria;
            int filaAleatoria;
            if (personajeActual.rango == 2 || personajeActual.rango == 1 || personajeActual.rango == 10) {
                int[] filas = {4, 5};
                filaAleatoria = filas[random.nextInt(filas.length)]; // Elegir entre filas 4 y 5
                // Elegir columna aleatoria hasta que esté libre ese espacio y no sea una esquina
                do {
                    filaAleatoria = filas[random.nextInt(filas.length)];
                    columnaAleatoria = random.nextInt(6);
                } while (casillas[filaAleatoria][columnaAleatoria].personajeActual != null
                        || (filaAleatoria == 0 && (columnaAleatoria == 0 || columnaAleatoria == 5))
                        || (filaAleatoria == 5 && (columnaAleatoria == 0 || columnaAleatoria == 5)));

                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
                charactersPlaced++;
            }
        }
        for (int i = 0; i < FantasmasInicialesPlayer2.size(); i++) {
            Personaje personajeActual = FantasmasInicialesPlayer2.get(i);
            int columnaAleatoria;
            int filaAleatoria;

            if (personajeActual.rango == 3 || personajeActual.rango == 4 || personajeActual.rango == 0) {
                int[] filas = {0, 1};
                filaAleatoria = filas[random.nextInt(filas.length)]; // Elegir entre filas 0 y 1

                // Elegir columna aleatoria hasta que esté libre ese espacio y no sea una esquina
                do {
                    filaAleatoria = filas[random.nextInt(filas.length)];
                    columnaAleatoria = random.nextInt(6);
                } while (casillas[filaAleatoria][columnaAleatoria].personajeActual != null
                        || (filaAleatoria == 0 && (columnaAleatoria == 0 || columnaAleatoria == 5))
                        || (filaAleatoria == 5 && (columnaAleatoria == 0 || columnaAleatoria == 5)));

                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
                charactersPlaced++;
            }

        }
    }

    public void posicionarManualmente() {
        if (!Usuario.EsModoAleatorio && ((Usuario.ModoExpert) || (Usuario.ModoNormal))) {
            if (!juegoComenzado) {
                if (!player1FinishedPositioning) {
                    // Posicionamiento manual para el jugador 1
                    while (!FantasmasInicialesPlayer1.isEmpty()) {
                        int fila = obtenerCoordenada(Player1.getUsuario() + ", posiciona un fantasma "+FantasmasInicialesPlayer1.get(0).getNombre()+". Ingresa la fila (4-5):");
                        int columna = obtenerCoordenada("Ingresa la columna (0-5):");
                        if (casillas[fila][columna].personajeActual != null) {
                            JOptionPane.showMessageDialog(null, "La casilla seleccionada ya está ocupada por un personaje. Elige otra casilla.");
                            continue;  // Vuelve a pedir las coordenadas
                        }
                        if ((fila == 4 && columna >= 0 && columna <= 5) || (fila == 5 && columna >= 1 && columna <= 4)) {
                            Personaje personaje = FantasmasInicialesPlayer1.remove(0);
                            personaje.posicionado = true;
                            casillas[fila][columna].setPersonaje(personaje);

                            if (FantasmasInicialesPlayer1.isEmpty()) {
                                player1FinishedPositioning = true;
                                turnoPlayer1 = !turnoPlayer1;
                                esconderPersonajes(); // Esconder personajes del jugador 1
                                JOptionPane.showMessageDialog(null, Player1.getUsuario() + " ha colocado todos sus fantasmas. Es el turno del jugador 2 para posicionar sus fantasmas.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Coordenadas inválidas. " + Player1.getUsuario() + " solo puede posicionar fantasmas en las filas 4-5, columnas 0-5, excepto las esquinas.");
                        }
                    }
                } else if (!player2FinishedPositioning) {
                    // Posicionamiento manual para el jugador 2
                    while (!FantasmasInicialesPlayer2.isEmpty()) {
                        int fila = obtenerCoordenada(Player2.getUsuario() + ", posiciona un fantasma "+FantasmasInicialesPlayer2.get(0).getNombre()+". Ingresa la fila (0-1):");
                        int columna = obtenerCoordenada("Ingresa la columna (0-5):");
                        if (casillas[fila][columna].personajeActual != null) {
                            JOptionPane.showMessageDialog(null, "La casilla seleccionada ya está ocupada por un personaje. Elige otra casilla.");
                            continue;  // Vuelve a pedir las coordenadas
                        }
                        if ((fila == 1 && columna >= 0 && columna <= 5) || (fila == 0 && columna >= 1 && columna <= 4)) {
                            Personaje personaje = FantasmasInicialesPlayer2.remove(0);
                            personaje.posicionado = true;
                            casillas[fila][columna].setPersonaje(personaje);

                            if (FantasmasInicialesPlayer2.isEmpty()) {
                                if (player1FinishedPositioning && player2FinishedPositioning) {
                                    turnoPlayer1 = !turnoPlayer1;
                                    player2FinishedPositioning = true;
                                    esconderPersonajes(); // Esconder personajes del jugador 2
                                    JOptionPane.showMessageDialog(null, "Ambos jugadores han colocado sus fantasmas. La partida puede comenzar.");
                                    juegoComenzado = true; // Marcar el juego como comenzado
                                }
                                player2FinishedPositioning = true;
                                turnoPlayer1 = !turnoPlayer1;

                                esconderPersonajes(); // Esconder personajes del jugador 2
                                JOptionPane.showMessageDialog(null, "Ambos jugadores han colocado sus fantasmas. La partida puede comenzar.");
                                juegoComenzado = true; // Marcar el juego como comenzado
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Coordenadas inválidas. " + Player2.getUsuario() + " solo puede posicionar fantasmas en las filas 0-1, columnas 0-5, excepto las esquinas.");
                        }
                    }
                }
            }
        }
    }

    private boolean esCoordenadaValida(Personaje personaje, int fila, int columna) {
        if ((personaje.rango == 1 || personaje.rango == 2) && (fila == 4 && columna >= 0 && columna <= 5) || (fila == 5 && columna >= 1 && columna <= 4)) {
            return casillas[fila][columna].personajeActual == null;
        }
        if ((personaje.rango == 3 || personaje.rango == 4) && (fila == 1 && columna >= 0 && columna <= 5) || (fila == 0 && columna >= 1 && columna <= 4)) {
            return casillas[fila][columna].personajeActual == null;
        }
        return false;
    }

    public void posicionarManualYAleatorio() {
        Random random = new Random();

        if (!Usuario.EsModoAleatorio && Usuario.ModoGenius) {
            if (!juegoComenzado) {
                if (!player1FinishedPositioning) {
                    while (!FantasmasInicialesGeniusP1.isEmpty()) {
                        int fila = obtenerCoordenada(Player1.getUsuario() + ", posiciona un fantasma " + FantasmasInicialesGeniusP1.get(0).getNombre() + ". Ingresa la fila (4-5):");
                        int columna = obtenerCoordenada("Ingresa la columna (0-5):");
                        Personaje personaje = FantasmasInicialesGeniusP1.get(0);
                        if (!esCoordenadaValida(personaje, fila, columna)) {
                            JOptionPane.showMessageDialog(null, "Coordenadas inválidas o rango incorrecto. " + Player1.getUsuario() + " solo puede posicionar fantasmas en las filas 4-5, columnas 0-5, excepto las esquinas.");
                            continue; // Reiniciar el ciclo para pedir nuevas coordenadas
                        }
                        if ((personaje.rango == 1 || personaje.rango == 2)
                                && ((fila == 4 && columna >= 0 && columna <= 5) || (fila == 5 && columna >= 1 && columna <= 4))) {
                            if (casillas[fila][columna].personajeActual != null) {
                                JOptionPane.showMessageDialog(null, "La casilla seleccionada ya está ocupada por un personaje. Elige otra casilla.");
                                continue;
                            }
                            personaje.posicionado = true;
                            casillas[fila][columna].setPersonaje(personaje);

                            FantasmasInicialesGeniusP1.remove(0);

                            if (FantasmasInicialesGeniusP1.isEmpty()) {
                                player1FinishedPositioning = true;
                                turnoPlayer1 = !turnoPlayer1;
                                esconderPersonajes(); // Esconder personajes del jugador 1
                                if (player2FinishedPositioning) {
                                    turnoPlayer1 = !turnoPlayer1;
                                    JOptionPane.showMessageDialog(null, "Ambos jugadores han colocado sus fantasmas. La partida puede comenzar.");
                                    juegoComenzado = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, Player1.getUsuario() + " ha colocado todos sus fantasmas. Es el turno del jugador 2 para posicionar sus fantasmas.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Coordenadas inválidas o rango incorrecto. " + Player1.getUsuario() + " solo puede posicionar fantasmas en las filas 4-5, columnas 0-5 excepto las esquinas.");
                            break;
                        }
                    }
                }
                for (Personaje personajeActual : FantasmasInicialesGeniusP1Fake) {
                    if (personajeActual.rango == 10 && !personajeActual.posicionado) {
                        int filaAleatoria;
                        int columnaAleatoria;
                        do {
                            filaAleatoria = 4 + random.nextInt(2);
                            columnaAleatoria = 1 + random.nextInt(4);
                        } while (casillas[filaAleatoria][columnaAleatoria].personajeActual != null);

                        personajeActual.posicionado = true;
                        casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                    }
                    esconderPersonajes();
                }

                if (!player2FinishedPositioning) {
                    while (!FantasmasInicialesGeniusP2.isEmpty()) {
                        int fila = obtenerCoordenada(Player2.getUsuario() + ", posiciona un fantasma " + FantasmasInicialesGeniusP2.get(0).getNombre() + ". Ingresa la fila (0-1):");
                        int columna = obtenerCoordenada("Ingresa la columna (0-5):");
                        Personaje personaje = FantasmasInicialesGeniusP2.get(0);
                        if (!esCoordenadaValida(personaje, fila, columna)) {
                            JOptionPane.showMessageDialog(null, "Coordenadas inválidas o rango incorrecto. " + Player2.getUsuario() + " solo puede posicionar fantasmas en las filas 0-1, columnas 0-5, excepto las esquinas.");
                            continue; // Reiniciar el ciclo para pedir nuevas coordenadas
                        }
                        if ((personaje.rango == 3 || personaje.rango == 4)
                                && ((fila == 1 && columna >= 0 && columna <= 5) || (fila == 0 && columna >= 1 && columna <= 4))) {
                            if (casillas[fila][columna].personajeActual != null) {
                                JOptionPane.showMessageDialog(null, "La casilla seleccionada ya está ocupada por un personaje. Elige otra casilla.");
                                continue;
                            }
                            personaje.posicionado = true;
                            casillas[fila][columna].setPersonaje(personaje);

                            FantasmasInicialesGeniusP2.remove(0);

                            if (FantasmasInicialesGeniusP2.isEmpty()) {
                                player2FinishedPositioning = true;
                                turnoPlayer1 = !turnoPlayer1;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Coordenadas inválidas o rango incorrecto. " + Player2.getUsuario() + " solo puede posicionar fantasmas en las filas 0 y 1, excepto las esquinas.");
                            break;
                        }
                    }
                }
                for (Personaje personajeActual : FantasmasInicialesGeniusP2Fake) {
                    if (personajeActual.rango == 0 && !personajeActual.posicionado) {
                        int filaAleatoria;
                        int columnaAleatoria;
                        do {
                            filaAleatoria = random.nextInt(2);
                            columnaAleatoria = 1 + random.nextInt(4);
                        } while (casillas[filaAleatoria][columnaAleatoria].personajeActual != null);

                        personajeActual.posicionado = true;
                        casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                    }
                    esconderPersonajes();

                }

                if (player1FinishedPositioning && player2FinishedPositioning) {
                    turnoPlayer1 = !turnoPlayer1;
                    player2FinishedPositioning = true;
                    esconderPersonajes(); // Esconder personajes del jugador 2
                    JOptionPane.showMessageDialog(null, "Ambos jugadores han colocado sus fantasmas. La partida puede comenzar.");
                    juegoComenzado = true; // Marcar el juego como comenzado
                }
                player2FinishedPositioning = true;
                turnoPlayer1 = !turnoPlayer1;

                esconderPersonajes(); // Esconder personajes del jugador 2
                JOptionPane.showMessageDialog(null, "Ambos jugadores han colocado sus fantasmas. La partida puede comenzar.");
                juegoComenzado = true; // Marcar el juego como comenzado
            }
        }
    }

    public void posicionarFantasmas() {
        if (Usuario.EsModoAleatorio) {
            posicionarAleatoriamente();
        } else {
            posicionarManualmente();
            posicionarManualYAleatorio();
        }
    }
}
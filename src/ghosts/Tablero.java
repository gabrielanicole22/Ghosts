/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ghosts;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class Tablero extends JPanel{
    private boolean hayCasillaSeleccionada = false;
    private CasillaTablero casillaSeleccionada;
    private CasillaTablero[][] casillas;
    private boolean turnoPlayer1 = true;
    boolean esTutorial;
    boolean hayRetirado = false;
    boolean juegoTerminado = false;
    private int GhostsMalosPlayer1 = 4;
    private int GhostsBuenosPlayer1 = 4;
    private int GhostsMalosPlayer2 = 4;
    private int GhostsBuenosPlayer2 = 4;
    
    ArrayList<Personaje> FantasmasInicialesPlayer1 = Personaje.getGhostsPlayer1();
    ArrayList<Personaje> FantasmasInicialesPlayer2 = Personaje.getGhostsPlayer2();
    
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
        // Dibujar la imagen de fondo
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }

    public Tablero(JTextArea FantasmasEliminadosPlayer1Area, JTextArea FantasmasEliminadosPlayer2Area, SistemaUsuarios sistemaUsuarios, Stats stats, Usuario Player1, Usuario Player2, boolean esTutorial, Juego gameWindow, MenuInicio mainWindow) {
        // Cargar la imagen de fondo
        //ImageIcon imagenIcono = new ImageIcon("src/img/tablero.png");
        //imagenFondo = imagenIcono.getImage();
        
        // Agregar registro de partidas a los usuarios
        Player1.partidasBuenos++;
        Player2.partidasMalos++;
        
        sistemaUsuarios.actualizarUsuario(Player1);
        sistemaUsuarios.actualizarUsuario(Player2);
        
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
                                if (casillaSeleccionada.personajeActual != null && 
                                    casillaSeleccionada.personajeActual.esPlayer1 == turnoPlayer1) {
                                    hayCasillaSeleccionada = true;
                                    //mostrarInformacionPersonaje();
                                    if (esTutorial) resaltarSiEsMovimientoValido();
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
                    for (int i=0;i < 6; i++)  {
                        for (int j = 0;j<6;j++) {
                            
                            if (casillas[i][j].label == label) {
                                
                                // SI LA CASILLA CLICKEADA ES UNA FICHA DEL MISMO BANDO, CAMBIAR A ESA FICHA 
                                if (casillas[i][j].personajeActual != null) {
                                    if (casillas[i][j].personajeActual.esPlayer1 == turnoPlayer1) {
                                        // Actualizar casillas  
                                        borrarResaltadoMovimientos();
                                        casillaSeleccionada.setSelected(false); // Deseleccionar la casilla anterior
                                        
                                        casillaSeleccionada = casillas[i][j];
                                        casillaSeleccionada.setSelected(true);
                                        if (esTutorial) resaltarSiEsMovimientoValido();
                                        resaltarZonasProhibidas();
                                        //mostrarInformacionPersonaje();
                                        casillaSeleccionada.setSelected(true);
                                        break;
                                    }
                                }
                                // Se dio click en una casilla con un rival o una casilla vacia
                                if (esMovimientoValido(i, j)) {
                                    moverPersonaje(i, j);
                                } else{
                                    if (esTutorial)
                                            JOptionPane.showMessageDialog(null, "MOVIMIENTO INVALIDO.\nTus movimientos validos estan coloreados de verde.");
                                    else
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
        // Establecer posiciones iniciales
        posicionarTodo();
        resaltarZonasProhibidas();
        mostrarMensajeInicial(); // mostrar el turno inicial del jugador
        
        if (!esTutorial) esconderPersonajes(); // Esconder personajes en caso de que no se este jugando en modo tutorial
        setVisible(true);
        repaint();
    }

private boolean esMovimientoValido(int row, int column) {
    int currentRow = casillaSeleccionada.row;
    int currentColumn = casillaSeleccionada.column;

    boolean esPlayer1 = casillaSeleccionada.personajeActual.esPlayer1;

    if (casillaSeleccionada.personajeActual.rango == 1 || casillaSeleccionada.personajeActual.rango == 2 || casillaSeleccionada.personajeActual.rango == 3 || casillaSeleccionada.personajeActual.rango == 4) {
        // Verificar si el movimiento es ortogonal
        boolean isOrthogonal = (row == currentRow && Math.abs(column - currentColumn) == 1) ||
                (column == currentColumn && Math.abs(row - currentRow) == 1);

        // Verificar si la nueva posición está dentro de los espacios restringidos
        boolean isRestricted;

        if (esPlayer1) {
            isRestricted = (row == 5 && column == 0) || (row == 5 && column == 5);
        } else {
            isRestricted = (row == 0 && column == 0) || (row == 0 && column == 5);
        }

        // verificar si hay un personaje en la casilla
        boolean hasCharacter = (casillas[row][column].personajeActual != null);

        // VERIFICAR QUE SI HAY UN PERSONAJE, QUE ESTE SEA DEL MISMO BANDO
        if (hasCharacter) {
            hasCharacter = (casillas[row][column].personajeActual.esPlayer1 == esPlayer1);
        }

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

        Partida partidaGanador = new Partida(perdedor, true, bandoGanador, 3, new Date());
        Partida partidaPerdedor = new Partida(ganador, false, bandoPerdedor, 0, new Date());

        // Agrega las partidas a los jugadores
        ganador.addPartida(partidaGanador);
        perdedor.addPartida(partidaPerdedor);

        // Agrega las partidas al sistema de usuarios (si es necesario)
        sistemaUsuarios.actualizarUsuario(ganador);
        sistemaUsuarios.actualizarUsuario(perdedor);

        // Mostrar mensaje de victoria
        if (turnoPlayer1) {
            JOptionPane.showMessageDialog(null, Player1.getUsuario() + " ha ganado. Logró sacar un fantasma bueno.");
        } else {
            JOptionPane.showMessageDialog(null, Player2.getUsuario() + " ha ganado. Logró sacar un fantasma bueno.");
        }
}
        // El movimiento es válido solo si es ortogonal, no está en un espacio restringido (zonas prohibidas) y no tiene otra ficha del mismo bando
        return isOrthogonal && !isRestricted && !hasCharacter;
}    return false;
}


    private boolean tieneMovimientosValidos(boolean bandoPlayer1) {
        // Recorrer el array en busca de fichas que se pueden mover
        for (int r = 0;r<6;r++){
            for (int c = 0;c<6;c++) {
                CasillaTablero casillaActual = casillas[r][c];
                if (casillaActual.personajeActual == null) continue;
                if (casillaActual.personajeActual.esPlayer1 == bandoPlayer1 && casillaActual.personajeActual.rango > 0) return true;
            }
        }
        return false;
    }
    
    private void resaltarSiEsMovimientoValido() {
        // Iterar por todas las casillas y verificar que sean un movimiento valido para resaltarlas
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j<6;j++) {
                casillas[i][j].highlightMove(esMovimientoValido(i, j));
                casillas[i][j].label.repaint();
            }
        }
        resaltarZonasProhibidas();
     }
    
    private void esconderPersonajes() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j<6;j++) {
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
            casillas[row][column].label.setOpaque(true);
            casillas[row][column].label.setBackground(Color.BLACK);
            casillas[row][column].label.repaint();
        }
    }
    
    private void moverPersonaje(int newRow, int newColumn) {
        if (juegoTerminado) {
        return;
    }
        // VERIFICAR QUE HAYA UN PERSONAJE EN LA NUEVA CASILLA PARA TOMAR EL COMBATE
        if (casillas[newRow][newColumn].personajeActual != null) {
            // Entrar en combate ya que esta restringido que piezas del mismo rango entren en combate.
            Personaje ganador = calcularCombate(casillaSeleccionada.personajeActual, casillas[newRow][newColumn].personajeActual);
            
            JPanel panel;
            
            // Mostrar mensaje del ganador/empate en pantalla
            if (ganador == casillaSeleccionada.personajeActual) {
                //System.out.println("GANO LA FICHA DEL TURNO ACTUAL");
                panel = new VentanaCombate(ganador, casillas[newRow][newColumn].personajeActual, 1);
            } else if (ganador == null) {
                //System.out.println("FUE UN EMPATE");
                panel = new VentanaCombate(casillaSeleccionada.personajeActual, casillas[newRow][newColumn].personajeActual, -1);
            } else {
                //System.out.println("GANO LA FICHA DEL TURNO OPUESTO");
                panel = new VentanaCombate(casillaSeleccionada.personajeActual, ganador, 0);
            }
            JOptionPane.showMessageDialog(null, panel);
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
        /*if (atacante.esHeroe) {
            FantasmasEliminadosPlayer2.add(defensor);
        } else {
            FantasmasEliminadosPlayer1.add(defensor);
        }
        return defensor;*/
        Personaje ganador = atacante;
        // aqui agrego el personaje eliminado a la lista correspondiente
        if (defensor.esPlayer1) {
            FantasmasEliminadosPlayer1.add(defensor);
        } else {
            FantasmasEliminadosPlayer2.add(defensor);
        }
        // se regresaa el ganador del combate
        return ganador;// devuelve el personaje eliminado (defensor) para fines de referencia o procesamiento adicional.
    }
    
    public void actualizarTurno() {
        if (juegoTerminado) return;
        
        casillaSeleccionada = null;
        hayCasillaSeleccionada = false;
        
        turnoPlayer1 = !turnoPlayer1;
        setVisible(false);
        if (!esTutorial) esconderPersonajes();
        GhostsP1EliminatedArea.setText("");
        GhostsP2EliminatedArea.setText("");
        
        String mensaje;
        if (!turnoPlayer1) mensaje = "fin del turno de " + Player1.getUsuario() + ", deja que  " + Player2.getUsuario() + " juegue su turno.";
        else mensaje = "fin del turno de " + Player2.getUsuario() + " deja que  " + Player1.getUsuario() + " juegue su turno.";
        JOptionPane.showMessageDialog(null, mensaje);
        
        String mensajeTurno = MensajeTurnosJugador();
        String player1Nombre = NombreUser1();
        String player2Nombre = NombreUser2();
        borrarResaltadoMovimientos();
        resaltarZonasProhibidas();
        mostrarPersonajesEliminados();

        gameWindow.setTurnoLabel(mensajeTurno);
        gameWindow.setPlayer1(player1Nombre);
        gameWindow.setPlayer2(player2Nombre);

        if (!esTutorial) esconderPersonajes();
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
        return "Fantasmas de: "+Player1.getUsuario();
    }

    private String NombreUser2() {
        return "Fantasmas de: "+Player2.getUsuario();
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
            String mensaje = ganador.getUsuario() +" ha ganado ya que " + 
            perdedor.getUsuario() + " se ha retirado del juego. - " + fecha;
            Partida partidaGanador = new Partida(perdedor, true, bandoGanador, 3, fecha);
            Partida partidaPerdedor = new Partida(ganador, false, bandoPerdedor, 0, fecha);

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
        for (int i = 0; i<6;i++) {
            for (int j = 0; j<6;j++){
                casillas[i][j].label.setOpaque(false);
                casillas[i][j].label.repaint();
            }
        }
        resaltarZonasProhibidas();
    }

    public void mostrarPersonajesEliminados() {
    GhostsMalosPlayer1=4;
    GhostsMalosPlayer2=4;
    GhostsBuenosPlayer1=4;
    GhostsBuenosPlayer2=4;
    String mensajeVictoria=" ";
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

    String mensajePlayer1 = "Fantasmas malos: " + GhostsMalosPlayer1 + "\n" +
                           "Fantasmas buenos: " + GhostsBuenosPlayer1 + "\n";
    String mensajePlayer2 = "Fantasmas malos: " + GhostsMalosPlayer2 + "\n" +
                            "Fantasmas buenos: " + GhostsBuenosPlayer2 + "\n";

    GhostsP1EliminatedArea.setText(mensajePlayer1);
    GhostsP2EliminatedArea.setText(mensajePlayer2);

    // Verificar si los fantasmas buenos o malos llegaron a 0
    if (GhostsBuenosPlayer1 == 0 || GhostsBuenosPlayer2 == 0) {
        mensajeVictoria = (GhostsBuenosPlayer1 == 0) ? Player2.getUsuario() + " ha ganado. Fantasmas buenos de " + Player1.getUsuario() + " llegaron a 0." 
                                                         : Player1.getUsuario() + " ha ganado. Fantasmas buenos de " + Player2.getUsuario() + " llegaron a 0.";
        JOptionPane.showMessageDialog(null, mensajeVictoria);
            gameWindow.dispose();

        // Registrar partida para los jugadores y asignar puntos
        if (GhostsBuenosPlayer1 == 0) {
            Partida partidaGanador = new Partida(Player2, false, "2", 3, new Date());
            Partida partidaPerdedor = new Partida(Player1, false, "1", 0, new Date());
            Player2.addPartida(partidaGanador);
            Player1.addPartida(partidaPerdedor);
            stats.addPartida(false);
        } else {
            Partida partidaGanador = new Partida(Player1, false, "1", 3, new Date());
            Partida partidaPerdedor = new Partida(Player2, false, "2", 0, new Date());
            Player1.addPartida(partidaGanador);
            Player2.addPartida(partidaPerdedor);
            stats.addPartida(true);
        }

    } else if (GhostsMalosPlayer1 == 0 || GhostsMalosPlayer2 == 0) {
        juegoTerminado = true;
        mensajeVictoria = (GhostsMalosPlayer1 == 0) ? Player2.getUsuario() + " ha ganado. Fantasmas malos de " + Player1.getUsuario() + " llegaron a 0."
                                                            : Player1.getUsuario() + " ha ganado. Fantasmas malos de " + Player2.getUsuario() + " llegaron a 0.";
        JOptionPane.showMessageDialog(null, mensajeVictoria);
            gameWindow.dispose();

        // Registrar partida para los jugadores y asignar puntos
        if (GhostsMalosPlayer1 == 0) {
            juegoTerminado = true;
            Partida partidaGanador = new Partida(Player2, false, "2", 3, new Date());
            Partida partidaPerdedor = new Partida(Player1, false, "1", 0, new Date());
            Player2.addPartida(partidaGanador);
            Player1.addPartida(partidaPerdedor);
            stats.addPartida(false);
        } else {
                                
            juegoTerminado = true;

            Partida partidaGanador = new Partida(Player1, false, "1", 3, new Date());
            Partida partidaPerdedor = new Partida(Player2, false, "2", 0, new Date());
            Player1.addPartida(partidaGanador);
            Player2.addPartida(partidaPerdedor);
            stats.addPartida(true);
        }
    }
            if (juegoTerminado) {
            mainWindow.setStats(stats);
            JOptionPane.showMessageDialog(null, mensajeVictoria);
            gameWindow.dispose();
        }
}

    public void posicionarFantasmas() {
        Random random = new Random();
        int charactersPlaced = 0;
        for (int i = 0; i < FantasmasInicialesPlayer1.size(); i++) {
            Personaje personajeActual = FantasmasInicialesPlayer1.get(i);
            int columnaAleatoria;
            int filaAleatoria;
            if (personajeActual.rango == 2 || personajeActual.rango==1) {
                int[] filas = {4, 5};
                filaAleatoria = filas[random.nextInt(filas.length)]; // Elegir entre filas 4 y 5
                // Elegir columna aleatoria hasta que esté libre ese espacio y no sea una esquina
                do {
                    columnaAleatoria = random.nextInt(6);
                } while (casillas[filaAleatoria][columnaAleatoria].personajeActual != null ||
                        (filaAleatoria == 0 && (columnaAleatoria == 0 || columnaAleatoria == 5)) ||
                        (filaAleatoria == 5 && (columnaAleatoria == 0 || columnaAleatoria == 5)));

                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
                charactersPlaced++;
            }
        }
        for (int i = 0; i < FantasmasInicialesPlayer2.size(); i++) {
            Personaje personajeActual = FantasmasInicialesPlayer2.get(i);
            int columnaAleatoria;
            int filaAleatoria;

            if (personajeActual.rango==3|| personajeActual.rango==4) {
                int[] filas = {0, 1};
                filaAleatoria = filas[random.nextInt(filas.length)]; // Elegir entre filas 0 y 1

                // Elegir columna aleatoria hasta que esté libre ese espacio y no sea una esquina
                do {
                    columnaAleatoria = random.nextInt(6);
                } while (casillas[filaAleatoria][columnaAleatoria].personajeActual != null ||
                        (filaAleatoria == 0 && (columnaAleatoria == 0 || columnaAleatoria == 5)) ||
                        (filaAleatoria == 5 && (columnaAleatoria == 0 || columnaAleatoria == 5)));

                casillas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
                personajeActual.posicionado = true;
                charactersPlaced++;
            }
        }
    }
    
    public void posicionarTodo() {
        posicionarFantasmas();
    }
}
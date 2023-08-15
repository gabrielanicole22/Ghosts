/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghosts;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Gabriela Mejia
 */
public class Juego extends javax.swing.JFrame {

    /**
     * Creates new form Juego
     */
 Tablero tablero;

    public Juego(SistemaUsuarios sistemaUsuarios, Stats stats, Usuario player1, Usuario player2, MenuInicio mainWindow) {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gamePanel.setLayout(new GridLayout(1, 1));
        gamePanel.setPreferredSize(new Dimension(800, 800));        
        tablero = new Tablero(
                fantasmasEliminadosPlayer1,
                fantasmasEliminadosPlayer2,
                sistemaUsuarios,
                stats,
                player1,
                player2,
                sistemaUsuarios.usuarioIniciado.tutorialActivo,
                this, mainWindow);
        gamePanel.add(tablero);
        gamePanel.repaint();
        setVisible(true);
        tablero.TerminardeCargar();
         SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(100); // Dar tiempo al formulario para mostrarse por completo
                tablero.posicionarManualmente(); // Realizar el posicionamiento manual
                return null;
            }
        };
        
        worker.execute(); // Ejecutar el SwingWorker
    }
    
                  Color SELECT_COLOR = new Color(37, 203, 232);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        turnoLabel = new javax.swing.JLabel();
        player1 = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fantasmasEliminadosPlayer1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        fantasmasEliminadosPlayer2 = new javax.swing.JTextArea();
        player2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        turnoLabel.setForeground(new java.awt.Color(255, 255, 255));
        turnoLabel.setText("turno");
        jPanel2.add(turnoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, -1, -1));

        player1.setForeground(new java.awt.Color(255, 255, 255));
        player1.setText("jugador 1");
        jPanel2.add(player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, -1, -1));

        gamePanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        jPanel2.add(gamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 570));

        fantasmasEliminadosPlayer1.setEditable(false);
        fantasmasEliminadosPlayer1.setBackground(new java.awt.Color(0, 51, 102));
        fantasmasEliminadosPlayer1.setColumns(20);
        fantasmasEliminadosPlayer1.setForeground(new java.awt.Color(204, 204, 255));
        fantasmasEliminadosPlayer1.setRows(5);
        jScrollPane1.setViewportView(fantasmasEliminadosPlayer1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(0, 51, 102));

        fantasmasEliminadosPlayer2.setEditable(false);
        fantasmasEliminadosPlayer2.setBackground(new java.awt.Color(0, 51, 102));
        fantasmasEliminadosPlayer2.setColumns(20);
        fantasmasEliminadosPlayer2.setForeground(new java.awt.Color(204, 204, 255));
        fantasmasEliminadosPlayer2.setRows(5);
        jScrollPane2.setViewportView(fantasmasEliminadosPlayer2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 310, -1, -1));

        player2.setForeground(new java.awt.Color(255, 255, 255));
        player2.setText("jugador 2");
        jPanel2.add(player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, -1, -1));

        jButton1.setText("Rendirse");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 470, 90, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fondos/fondoJuego.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 480, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tablero.surrender();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        // TODO add your handling code here:
        jButton1.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        // TODO add your handling code here:
        jButton1.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jButton1MouseExited

    public void setTurnoLabel(String text) {
        turnoLabel.setText(text);
    }

    public void setPlayer1(String text) {
        player1.setText(text);
    }

    public void setPlayer2(String text) {
        player2.setText(text);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SistemaUsuarios sys = new SistemaUsuarios();
                sys.registrarUsuario("Gab", "mua");
                sys.registrarUsuario("Tay", "mua");
                sys.iniciarSesion("Gab", "mua");

                Usuario user1 = sys.getUsuario("Gab");
                Usuario user2 = sys.getUsuario("Tay");

                user1.tutorialActivo = false;
                new Juego(sys, new Stats(), user1, user2, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea fantasmasEliminadosPlayer1;
    private javax.swing.JTextArea fantasmasEliminadosPlayer2;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel turnoLabel;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghosts;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriela Mejía
 */
public class MenuInicio extends javax.swing.JFrame {

    /**
     * Creates new form MenuInicio
     */
        Color SELECT_COLOR = new Color(37, 203, 232);

    private Usuario usuario;
    SistemaUsuarios sistemaUsuarios;
    Stats stats = new Stats();

    public MenuInicio(SistemaUsuarios sistemaUsuarios) {
        initComponents();
        this.sistemaUsuarios = sistemaUsuarios;
        usuario = sistemaUsuarios.getUsuarioActual();
        setResizable(false);
        habilitarBotonPartidaNueva();
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    private void habilitarBotonPartidaNueva() {
        Usuario[] usuarios = sistemaUsuarios.getUsuariosActivos();
        boolean habilitar = usuarios.length > 1;;
        btnJugar.setEnabled(habilitar);
    }

    public void setSistemaUsuarios(SistemaUsuarios sistemaUsuarios) {
        this.sistemaUsuarios = sistemaUsuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        //mostrarInformacionUsuario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnJugar = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JLabel();
        btnReports = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JLabel();
        btnProfile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnJugar.setForeground(new java.awt.Color(255, 255, 255));
        btnJugar.setText("Jugar");
        btnJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJugarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnJugarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnJugarMouseExited(evt);
            }
        });
        jPanel1.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, -1, -1));

        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("Configuración");
        btnConfiguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseExited(evt);
            }
        });
        jPanel1.add(btnConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        btnReports.setForeground(new java.awt.Color(255, 255, 255));
        btnReports.setText("Reportes");
        btnReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportsMouseExited(evt);
            }
        });
        jPanel1.add(btnReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, -1, -1));

        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        jPanel1.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        btnProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnProfile.setText("Mi Perfil");
        btnProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProfileMouseExited(evt);
            }
        });
        jPanel1.add(btnProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnJugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJugarMouseClicked
        // TODO add your handling code here:
        PartidaNueva partida = new PartidaNueva(sistemaUsuarios, stats, this);
        partida.cargarUsuarios();
        partida.setVisible(true);
    }//GEN-LAST:event_btnJugarMouseClicked

    private void btnConfiguracionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseClicked
        // TODO add your handling code here:
                Configuracion config = new Configuracion(sistemaUsuarios, this);
        config.setUsuario(usuario);
        config.setVisible(true);
    }//GEN-LAST:event_btnConfiguracionMouseClicked

    private void btnReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportsMouseClicked
        // TODO add your handling code here:
                RankingReportes ranks = new RankingReportes(sistemaUsuarios, stats);
        ranks.setVisible(true);
    }//GEN-LAST:event_btnReportsMouseClicked

    private void btnProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileMouseClicked
        // TODO add your handling code here:
                MiPerfil perfil = new MiPerfil(sistemaUsuarios, this);
        perfil.setUsuario(usuario);
        perfil.setVisible(true);
    }//GEN-LAST:event_btnProfileMouseClicked

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        // TODO add your handling code here:
                int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            sistemaUsuarios.usuarioIniciado = null;
            menuPrincipal.setSistemaUsuarios(sistemaUsuarios);
            menuPrincipal.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnJugarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJugarMouseEntered
        // TODO add your handling code here:
        btnJugar.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnJugarMouseEntered

    private void btnJugarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJugarMouseExited
        // TODO add your handling code here:
        btnJugar.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnJugarMouseExited

    private void btnConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseEntered
        // TODO add your handling code here:
                btnConfiguracion.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnConfiguracionMouseEntered

    private void btnConfiguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseExited
        // TODO add your handling code here:
                btnConfiguracion.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnConfiguracionMouseExited

    private void btnReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportsMouseEntered
        // TODO add your handling code here:
                btnReports.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnReportsMouseEntered

    private void btnReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportsMouseExited
        // TODO add your handling code here:
                btnReports.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnReportsMouseExited

    private void btnProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileMouseEntered
        // TODO add your handling code here:
                btnProfile.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnProfileMouseEntered

    private void btnProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileMouseExited
        // TODO add your handling code here:
                btnProfile.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnProfileMouseExited

    private void btnCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseEntered
        // TODO add your handling code here:
                btnCerrarSesion.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnCerrarSesionMouseEntered

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited
        // TODO add your handling code here:
                btnCerrarSesion.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnCerrarSesionMouseExited


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
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuInicio(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCerrarSesion;
    private javax.swing.JLabel btnConfiguracion;
    private javax.swing.JLabel btnJugar;
    private javax.swing.JLabel btnProfile;
    private javax.swing.JLabel btnReports;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

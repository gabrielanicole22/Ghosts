/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghosts;

import java.awt.Color;
import java.awt.Cursor;

/**
 *
 * @author Gabriela Mejía
 */
public class Dificultad extends javax.swing.JFrame {

    /**
     * Creates new form Dificultad
     */
    private SistemaUsuarios sistemaUsuarios;
    private Usuario usuario;
    MenuInicio ventanaPrincipal;
    Configuracion config;

    public Dificultad(SistemaUsuarios sistemaUsuarios, Configuracion config, Usuario usuario) {
        initComponents();
        this.sistemaUsuarios = sistemaUsuarios;
        this.config = config;
        this.usuario = usuario;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void actualizarUsuario() {
        sistemaUsuarios.actualizarUsuario(usuario);
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
        btnDNormal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDExpert = new javax.swing.JLabel();
        btnDGenius = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDNormal.setFont(new java.awt.Font("Ravie", 0, 30)); // NOI18N
        btnDNormal.setForeground(new java.awt.Color(255, 255, 255));
        btnDNormal.setText("NORMAL");
        btnDNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDNormalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDNormalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDNormalMouseExited(evt);
            }
        });
        jPanel1.add(btnDNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe Marker", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Escoge nivel de dificultad:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        btnDExpert.setFont(new java.awt.Font("Ravie", 0, 30)); // NOI18N
        btnDExpert.setForeground(new java.awt.Color(255, 255, 255));
        btnDExpert.setText("EXPERT");
        btnDExpert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDExpertMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDExpertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDExpertMouseExited(evt);
            }
        });
        jPanel1.add(btnDExpert, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        btnDGenius.setFont(new java.awt.Font("Ravie", 0, 30)); // NOI18N
        btnDGenius.setForeground(new java.awt.Color(255, 255, 255));
        btnDGenius.setText("GENIUS");
        btnDGenius.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDGeniusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDGeniusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDGeniusMouseExited(evt);
            }
        });
        jPanel1.add(btnDGenius, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jLabel1.setFont(new java.awt.Font("Ravie", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fondos/confi2.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDNormalMouseClicked
        // TODO add your handling code here:
        Usuario.ModoNormal = true;
        Usuario.ModoExpert = false;
        Usuario.ModoGenius = false;
        actualizarUsuario();
        config.setSistemaUsuarios(sistemaUsuarios);
        this.dispose();
    }//GEN-LAST:event_btnDNormalMouseClicked

    private void btnDExpertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDExpertMouseClicked
        // TODO add your handling code here:
        Usuario.ModoNormal = false;
        Usuario.ModoExpert = true;
        Usuario.ModoGenius = false;
        actualizarUsuario();
        config.setSistemaUsuarios(sistemaUsuarios);
        this.dispose();
    }//GEN-LAST:event_btnDExpertMouseClicked

    private void btnDGeniusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDGeniusMouseClicked
        // TODO add your handling code here:
        Usuario.ModoNormal = false;
        Usuario.ModoExpert = false;
        Usuario.ModoGenius = true;
        actualizarUsuario();
        config.setSistemaUsuarios(sistemaUsuarios);
        this.dispose();
    }//GEN-LAST:event_btnDGeniusMouseClicked
    Color SELECT_COLOR = new Color(235, 209, 151);

    private void btnDNormalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDNormalMouseEntered
        // TODO add your handling code here:
        btnDNormal.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnDNormalMouseEntered

    private void btnDNormalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDNormalMouseExited
        // TODO add your handling code here:
        btnDNormal.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnDNormalMouseExited

    private void btnDExpertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDExpertMouseEntered
        // TODO add your handling code here:
        btnDExpert.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnDExpertMouseEntered

    private void btnDExpertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDExpertMouseExited
        // TODO add your handling code here:
        btnDExpert.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnDExpertMouseExited

    private void btnDGeniusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDGeniusMouseEntered
        // TODO add your handling code here:
        btnDGenius.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnDGeniusMouseEntered

    private void btnDGeniusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDGeniusMouseExited
        // TODO add your handling code here:
        btnDGenius.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnDGeniusMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDExpert;
    private javax.swing.JLabel btnDGenius;
    private javax.swing.JLabel btnDNormal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

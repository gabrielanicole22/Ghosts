/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghosts;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Gabriela Mejía
 */
public class MenuRegistrarse extends javax.swing.JFrame {

    /**
     * Creates new form MenuRegistrarse
     */
    SistemaUsuarios sistemaUsuarios;
    // Guardar la ventana del MenuPrincipal para poder cerrarla en caso de que se inicie sesion poder cerrarla.
    MenuPrincipal menuPrincipal = null;

    public MenuRegistrarse(SistemaUsuarios sistemaUsuarios, MenuPrincipal menuPrincipal) {
        initComponents();

        this.menuPrincipal = menuPrincipal;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setMinimumSize(new Dimension(800, 500));
        this.sistemaUsuarios = sistemaUsuarios;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        btnRegistro = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("usuario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));
        jPanel1.add(userInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 144, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));
        jPanel1.add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 144, -1));

        btnRegistro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistro.setText("Registrarse");
        btnRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistroMouseExited(evt);
            }
        });
        jPanel1.add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fondos/Registro.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroMouseClicked
        // TODO add your handling code here:
               String username = userInput.getText().trim();
        String password = new String(passwordInput.getPassword());

        // Recorrer la lista buscando espacios
        boolean validUsername = sistemaUsuarios.esUsuarioUnico(username);

        if (!validUsername) {
            JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya está en uso. Intente con otro nombre de usuario.");
            return;
        }

        // VERIFICAR QUE NO SE PUEDAN PONER ESPACIOS ENTRE MEDIO DEL USUARIO.
        validUsername = sistemaUsuarios.verificarEspaciosUsuario(username);

        if (!validUsername) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede llevar espacios");
            return;
        }

        // VERIFICAR QUE UN CAMPO NO ESTE VACIO
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
        }

        sistemaUsuarios.registrarUsuario(username, password);
        JOptionPane.showMessageDialog(null, "Se ha registrado correctamente.");

        // Devolver el usuario al menu principa;
        menuPrincipal.setSistemaUsuarios(sistemaUsuarios);
        this.dispose();
    }//GEN-LAST:event_btnRegistroMouseClicked

    private void btnRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroMouseEntered
        // TODO add your handling code here:
                btnRegistro.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnRegistroMouseEntered

    private void btnRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistroMouseExited
        // TODO add your handling code here:
                btnRegistro.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnRegistroMouseExited

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
            java.util.logging.Logger.getLogger(MenuRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuRegistrarse(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables
}

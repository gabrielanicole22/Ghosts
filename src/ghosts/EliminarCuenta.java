/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghosts;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabriela Mejía
 */
public class EliminarCuenta extends javax.swing.JFrame {

    /**
     * Creates new form EliminarCuenta
     */
    private Usuario usuario;
    SistemaUsuarios sistemaUsuarios;
    MenuInicio ventanaPrincipal;
    
    public EliminarCuenta(SistemaUsuarios sistemaUsuarios, MenuInicio ventanaPrincipal) {
        initComponents();
        this.sistemaUsuarios = sistemaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Esconder label de prueba
        jLabel1.setVisible(false);
        
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        btnEliminar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel2.setText("Ingrese su contraseña para confirmar:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 198, -1));

        btnEliminar.setText("Borrar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
                    // Obtener la contraseña ingresada para luego verificar si es la misma y proceder a eliminar cuenta
    String contrasenaIngresada = txtContraseña.getText();

    if (usuario != null) {
        // Verifica si la contraseña ingresada coincide con la contraseña actual del usuario (actual porque antes pudo haberse cambiado)
        if (contrasenaIngresada.equals(usuario.getContrasena())) {
            // Eliminar la cuenta del usuario
            sistemaUsuarios.eliminarUsuario(usuario);

            JOptionPane.showMessageDialog(this, "La cuenta ha sido eliminada exitosamente.");
            
            // Abrir el menu prinicpal
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.setSistemaUsuarios(sistemaUsuarios); 
            menuPrincipal.setVisible(true); 
            
            // Cerrar la ventana del menu de inicio
            ventanaPrincipal.dispose();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "La contraseña ingresada es incorrecta.");
        }
    }
    }//GEN-LAST:event_btnEliminarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtContraseña;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghosts;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriela Mejía
 */
public class MiPerfil extends javax.swing.JFrame {

    /**
     * Creates new form MiPerfil
     */
    private SistemaUsuarios sistemaUsuarios;
    private Usuario usuario;
    MenuInicio ventanaPrincipal;

    public MiPerfil(SistemaUsuarios sistemaUsuarios, MenuInicio ventanaPrincipal) {
        initComponents();
        this.sistemaUsuarios = sistemaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;
        cargarPartidas();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setSistemaUsuarios(SistemaUsuarios sistemaUsuarios) {
        this.sistemaUsuarios = sistemaUsuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        mostrarInformacionUsuario();
        mostrarInformacionUsuarioo();
    }

    public void cargarPartidas() {
        ArrayList<Partida> partidas = sistemaUsuarios.usuarioIniciado.getPartidas();
        DefaultTableModel model = (DefaultTableModel) matchTable.getModel();

        for (int i = (partidas.toArray().length - 1); i >= 0; i--) {
            Partida partida = partidas.get(i);
            String resultado;

            if (partida.puntosGanados == 1.5) {
                resultado = "EMPATE";
            } else {
                resultado = (partida.victoria) ? "VICTORIA" : "DERROTA";
            }

            model.addRow(new Object[]{partida.contrincante.getUsuario(), resultado, partida.fecha, partida.puntosGanados});
        }
    }

    public void mostrarInformacionUsuario() {
        if (usuario != null) {
            String nombreUsuario = usuario.getUsuario();
            labelUsuario.setText(nombreUsuario);
        }
    }
    public void mostrarInformacionUsuarioo() {
        if (usuario != null) {
            double puntosTotales = usuario.getPuntos();
            Labelpuntos.setText(String.valueOf(puntosTotales));
        }
    }
    

    public void mostrarMiPerfil(String nombre) {
        labelUsuario.setText(nombre);
    }
    public void mostrarMiPutno(double puntos) {
            Labelpuntos.setText(String.valueOf(puntos));
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
        labelUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        matchTable = new javax.swing.JTable();
        btnCambiar = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Labelpuntos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelUsuario.setText("usuario");
        jPanel1.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        matchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contrincante", "Resultado", "Fecha", "Puntos Ganados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(matchTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 586, 300));

        btnCambiar.setText("CAMBIAR PASSWORD");
        btnCambiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCambiarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        btnBack.setText("REGRESAR");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        jLabel2.setText("Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel3.setText("Puntos Totales:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        Labelpuntos.setText("puntos");
        jPanel1.add(Labelpuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 84, 16));

        jLabel1.setText("Mi Perfil");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        btnEliminar.setText("ELIMINAR CUENTA");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, -1, -1));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiarMouseClicked
        // TODO add your handling code here:
                CambiarPassword changePassword = new CambiarPassword(sistemaUsuarios, this);
        changePassword.setUsuario(usuario);
        changePassword.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
                this.dispose();

    }//GEN-LAST:event_btnBackMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
                EliminarCuenta delete = new EliminarCuenta(sistemaUsuarios, ventanaPrincipal);
        delete.setUsuario(usuario);
        delete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEliminarMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Labelpuntos;
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnCambiar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JTable matchTable;
    // End of variables declaration//GEN-END:variables
}

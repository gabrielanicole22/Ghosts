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
public class RankingReportes extends javax.swing.JFrame {

    /**
     * Creates new form UniversoMarvel
     */
    Color SELECT_COLOR = new Color(177, 177, 177);

    private Usuario usuario;
    SistemaUsuarios sistemaUsuarios;
    Stats stats = new Stats();

    public RankingReportes(SistemaUsuarios sistemaUsuarios, Stats stats) {
        this.sistemaUsuarios = sistemaUsuarios;
        usuario = sistemaUsuarios.getUsuarioActual();
        setResizable(false);
        this.sistemaUsuarios = sistemaUsuarios;
        this.stats = stats;
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void setStats(Stats stats) {
        this.stats = stats;
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

        jScrollPane2 = new javax.swing.JScrollPane();
        matchTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        matchTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JLabel();
        btnJuegos = new javax.swing.JLabel();
        btnRanking = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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
        jScrollPane2.setViewportView(matchTable);

        matchTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(matchTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Parchment", 0, 130)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Reportes");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        btnRegresar.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("REGRESAR");
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 500, -1, -1));

        btnJuegos.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        btnJuegos.setForeground(new java.awt.Color(255, 255, 255));
        btnJuegos.setText("DESCRIPCIÓN DE MIS JUEGOS");
        btnJuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJuegosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnJuegosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnJuegosMouseExited(evt);
            }
        });
        jPanel1.add(btnJuegos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        btnRanking.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        btnRanking.setForeground(new java.awt.Color(255, 255, 255));
        btnRanking.setText("RANKING");
        btnRanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRankingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRankingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRankingMouseExited(evt);
            }
        });
        jPanel1.add(btnRanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fondos/fondoRaRep.png"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(1140, 540));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRankingMouseClicked
        // TODO add your handling code here:
        Reportes ranks = new Reportes(sistemaUsuarios, stats);
        ranks.setVisible(true);
    }//GEN-LAST:event_btnRankingMouseClicked

    private void btnRankingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRankingMouseEntered
        // TODO add your handling code here:
        btnRanking.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnRankingMouseEntered

    private void btnRankingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRankingMouseExited
        // TODO add your handling code here:
        btnRanking.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnRankingMouseExited

    private void btnJuegosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJuegosMouseClicked
        // TODO add your handling code here:
        Juegos juegos = new Juegos(sistemaUsuarios, stats);
        juegos.setVisible(true);
    }//GEN-LAST:event_btnJuegosMouseClicked

    private void btnJuegosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJuegosMouseEntered
        // TODO add your handling code here:
        btnJuegos.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnJuegosMouseEntered

    private void btnJuegosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJuegosMouseExited
        // TODO add your handling code here:
        btnJuegos.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);

    }//GEN-LAST:event_btnJuegosMouseExited

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        btnRegresar.setForeground(SELECT_COLOR);
        setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        btnRegresar.setForeground(Color.white);
        setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btnRegresarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnJuegos;
    private javax.swing.JLabel btnRanking;
    private javax.swing.JLabel btnRegresar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable matchTable;
    private javax.swing.JTable matchTable1;
    // End of variables declaration//GEN-END:variables
}

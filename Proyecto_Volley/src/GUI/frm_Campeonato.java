package GUI;

import Clases.Campeonato;
import Data.Dat_ConexionBD;
import Logica.BL_Campeonato;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class frm_Campeonato extends javax.swing.JFrame {

    int insertar = 0;
    int actualizar = 0;
    ArrayList<Campeonato> ArrayCampeonatos = new ArrayList<>();
    BL_Campeonato objBLCampeonato = new BL_Campeonato();
    Campeonato objCam = new Campeonato();
    Dat_ConexionBD objCon = new Dat_ConexionBD();
    public int idCam;

    public frm_Campeonato() throws ClassNotFoundException, SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        iniciar();
        listarTabla();
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos"> 
    private void iniciar() {
        txtNombre.setText("");
        txtNombre.setEnabled(false);
        txtRepresentante.setEnabled(false);
        txtRepresentante.setText("");
        spnAño.setValue(2018);
        spnAño.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnAgregarCamp.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnSeleccionar.setEnabled(true);
    }

    private void cargardatos() {
        int fila = tblCampeonatos.getSelectedRow();
        txtNombre.setText(tblCampeonatos.getValueAt(fila, 0).toString());
        txtRepresentante.setText(tblCampeonatos.getValueAt(fila, 1).toString());
        spnAño.setValue(Integer.parseInt(tblCampeonatos.getValueAt(fila, 2).toString()));
    }

    public void listarTabla() throws ClassNotFoundException, SQLException {
        tblCampeonatos.removeAll();
        Object columnas[] = {"Campeonato", "Representante", "Año"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tblCampeonatos.setModel(modelo);
        try {
            objCon.AbrirConexion();
            ArrayCampeonatos = objBLCampeonato.ConsultarCamp(ArrayCampeonatos);
            for (int i = 0; i < ArrayCampeonatos.size(); i++) {
                modelo.addRow(new Object[]{ArrayCampeonatos.get(i).getNombre(), ArrayCampeonatos.get(i).getRepresentante(),
                    ArrayCampeonatos.get(i).getAnio()});
            }
            objCon.CerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    // </editor-fold> 
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRepresentante = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        spnAño = new com.toedter.calendar.JYearChooser();
        btnAgregarCamp = new javax.swing.JToggleButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCampeonatos = new javax.swing.JTable();
        btnModificar = new javax.swing.JToggleButton();
        btnSeleccionar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Campeonatos");

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("CAMPEONATOS");

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setText("Representante");

        txtRepresentante.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel5.setText("Año");

        btnAgregarCamp.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAgregarCamp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarCamp.setText("Agregar");
        btnAgregarCamp.setBorder(null);
        btnAgregarCamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCampActionPerformed(evt);
            }
        });

        tblCampeonatos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblCampeonatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Campeonato", "Representante", "Año"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCampeonatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCampeonatosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblCampeonatos);

        btnModificar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSeleccionar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel6.setText("Nombre");

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(txtRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregarCamp, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSeleccionar)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spnAño, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnAgregarCamp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCampActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCampActionPerformed
        insertar = 1;
        txtNombre.setText("");
        txtRepresentante.setText("");
        txtNombre.setEnabled(true);
        txtRepresentante.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSeleccionar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnAgregarCamp.setEnabled(false);
        spnAño.setEnabled(true);
    }//GEN-LAST:event_btnAgregarCampActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        actualizar = 1;
        txtNombre.setEnabled(true);
        txtNombre.setEditable(true);
        txtRepresentante.setEditable(true);
        txtRepresentante.setEnabled(true);
        spnAño.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnSeleccionar.setEnabled(false);
        btnModificar.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        //Permite llamar a ventana principal
        frmPrincipal escritorio;
        try {
            ArrayList<Campeonato> ArrayCam = new ArrayList<>();

            int fila = tblCampeonatos.getSelectedRow();
            ArrayCam = objBLCampeonato.ConsultarIdCamp(ArrayCam, tblCampeonatos.getValueAt(fila, 0).toString(), tblCampeonatos.getValueAt(fila, 1).toString(), Integer.parseInt(tblCampeonatos.getValueAt(fila, 2).toString()));
            for (Campeonato ArrayCampeonato : ArrayCam) {
                this.idCam = ArrayCampeonato.getIdCampeonato();
            }
            escritorio = new frmPrincipal(idCam);
            escritorio.setVisible(true);
            this.setVisible(false);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frm_Campeonato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed
    private void tblCampeonatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCampeonatosMouseClicked
        cargardatos();
        btnModificar.setEnabled(true);
        btnSeleccionar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAgregarCamp.setEnabled(false);
        btnGuardar.setEnabled(false);
        txtNombre.setEnabled(false);
        txtRepresentante.setEnabled(false);
        txtNombre.setEditable(false);
        txtRepresentante.setEditable(false);
        spnAño.setEnabled(false);
    }//GEN-LAST:event_tblCampeonatosMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        insertar = 0;
        actualizar = 0;
        iniciar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (insertar == 1) {
            try {
                objCon.AbrirConexion();
                int bandera = objBLCampeonato.InsertarCampeonato(txtNombre.getText(), txtRepresentante.getText(), spnAño.getValue());
                if (bandera > 0) {
                    JOptionPane.showMessageDialog(null, "Campeonato registrado");
                    iniciar();
                    listarTabla();
                    tblCampeonatos.removeAll();
                    insertar = 0;
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registar campeonato");
                }
                objCon.CerrarConexion();

            } catch (Exception e) {
                System.err.println(e);
            }
        } else if (actualizar == 1) {
            try {
                if (actualizar == 1) {
                    int RowSel = this.tblCampeonatos.getSelectedRow();
                    objCam = new Campeonato(this.txtNombre.getText(), this.txtRepresentante.getText(), String.valueOf(this.spnAño.getValue()));
                    int bandera = objBLCampeonato.ActualizaCampeonato(objCam, tblCampeonatos.getValueAt((RowSel), 0).toString());
                    if (bandera > 0) {
                        JOptionPane.showMessageDialog(null, "Campeonato actualizado");
                        iniciar();
                        listarTabla();
                        actualizar = 0;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar campeonato");
                    }
                    objCon.CerrarConexion();
                }

            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Campeonato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frm_Campeonato().setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(frm_Campeonato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAgregarCamp;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JToggleButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JYearChooser spnAño;
    private javax.swing.JTable tblCampeonatos;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRepresentante;
    // End of variables declaration//GEN-END:variables
}

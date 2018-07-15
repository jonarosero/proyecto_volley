package GUI;

import Clases.Campeonato;
import Clases.Colegio;
import Data.Dat_ConexionBD;
import Logica.BL_Colegio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class frm_Reg_Colegios extends javax.swing.JInternalFrame {

    int insertar = 0;
    int actualizar = 0;
    ArrayList<Colegio> ArrayColegios = new ArrayList<>();
    BL_Colegio objBLColegio = new BL_Colegio();
    DefaultTableModel modelo = new DefaultTableModel();
    Dat_ConexionBD objCon = new Dat_ConexionBD();
    Colegio objCol;

    /**
     * Creates new form frm_Reg_Colegios
     */
    public frm_Reg_Colegios() throws ClassNotFoundException, SQLException {
        initComponents();
        txtColegios.setEditable(false);
        txtColegios.setEnabled(false);
        txtRepresentante.setEditable(false);
        txtRepresentante.setEnabled(false);
        btnAgreColegio.setEnabled(false);
        btnCancelar.setEnabled(false);
        listarTabla();
    }

    private void cargardatos() {
        int fila = tblColegios.getSelectedRow();
        txtColegios.setText(tblColegios.getValueAt(fila, 0).toString());
        txtRepresentante.setText(tblColegios.getValueAt(fila, 1).toString());
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos">
    public void cargarCombo() throws ClassNotFoundException, SQLException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
    }

    public void listarTabla() throws ClassNotFoundException, SQLException {
        tblColegios.removeAll();
        Object columnas[] = {"Colegio", "Representante"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tblColegios.setModel(modelo);
        try {
            objCon.AbrirConexion();
            ArrayColegios = objBLColegio.ListarColegios(ArrayColegios);
            for (int i = 0; i < ArrayColegios.size(); i++) {
                modelo.addRow(new Object[]{ArrayColegios.get(i).getNombreCole(), ArrayColegios.get(i).getNombreRep()});
            }
            objCon.CerrarConexion();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblColegios = new javax.swing.JTable();
        btnAgreColegio = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        txtRepresentante = new javax.swing.JTextField();
        txtColegios = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setTitle("REGISTRO COLEGIOS");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("REGISTRO COLEGIOS");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setText("Nombre Colegio");

        jScrollPane2.setAutoscrolls(true);

        tblColegios.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblColegios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Colegio", "Representante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblColegios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblColegiosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblColegios);

        jScrollPane2.setViewportView(jScrollPane1);

        btnAgreColegio.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAgreColegio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgreColegio.setText("Agregar");
        btnAgreColegio.setBorder(null);
        btnAgreColegio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgreColegioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setText("Representante");

        btnCancelar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 65, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(254, 254, 254))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRepresentante, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel2))
                            .addComponent(txtColegios)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(btnAgreColegio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btnCancelar)))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(txtColegios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgreColegio)
                    .addComponent(btnCancelar))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgreColegioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgreColegioActionPerformed
        try {
            int RowSel = this.tblColegios.getSelectedRow();
            objCol = new Colegio(txtColegios.getText(), txtRepresentante.getText());
            int bandera = objBLColegio.ActualizaColegio(objCol);
            if (bandera > 0) {
                JOptionPane.showMessageDialog(null, "Representante Registrado");
                listarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar representante");
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }//GEN-LAST:event_btnAgreColegioActionPerformed

    private void tblColegiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblColegiosMouseClicked
        // TODO add your handling code here:
        cargardatos();
        btnAgreColegio.setEnabled(true);
        btnCancelar.setEnabled(true);
        txtRepresentante.setEditable(true);
        txtRepresentante.setEnabled(true);
        btnAgreColegio.setEnabled(true);
        btnCancelar.setEnabled(true);
        if (txtRepresentante.getText().isEmpty()) {
            btnAgreColegio.setText("Agregar");
        } else {
            btnAgreColegio.setText("Actualizar");
        }
    }//GEN-LAST:event_tblColegiosMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        txtColegios.setEditable(false);
        txtColegios.setEnabled(false);
        txtRepresentante.setEditable(false);
        txtRepresentante.setEnabled(false);
        btnAgreColegio.setEnabled(false);
        btnCancelar.setEnabled(false);
        txtColegios.setText("");
        txtRepresentante.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAgreColegio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblColegios;
    private javax.swing.JTextField txtColegios;
    private javax.swing.JTextField txtRepresentante;
    // End of variables declaration//GEN-END:variables
}

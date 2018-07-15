package GUI;

import Clases.Campeonato;
import Clases.Equipo;
import Clases.Partido;
import Clases.Resultado;
import Logica.BL_Campeonato;
import Logica.BL_Partido;
import Logica.BL_Resultados;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class frm_Reg_ResultadosPar extends javax.swing.JInternalFrame {

    BL_Campeonato objManCampeonato = new BL_Campeonato();
    BL_Partido objManPartido = new BL_Partido();
    ArrayList<Equipo> lstEquipo = new ArrayList();
    ArrayList<Resultado> lstResultado = new ArrayList();
    BL_Resultados objManResultado = new BL_Resultados();
    BL_Resultados objManResultado1 = new BL_Resultados();

    public frm_Reg_ResultadosPar() throws ClassNotFoundException, SQLException {
        initComponents();
        comboCamp();
        comboFecha();
    }

    public void comboCamp() throws ClassNotFoundException, SQLException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ArrayList<Campeonato> lstCamp = new ArrayList<>();
        lstCamp = objManCampeonato.ConsultarCamp(lstCamp);
        for (Campeonato actual : lstCamp) {
            Campeonato obtAdmin = new Campeonato(actual.getNombre());
            cmbCampeonato.addItem(actual.getNombre());
        }
    }

    public void comboFecha() throws ClassNotFoundException, SQLException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ArrayList<Partido> lstPartido = new ArrayList<>();
        lstPartido = objManPartido.consultarFechaPartido();
        for (Partido actual : lstPartido) {
            Partido obtpar = new Partido(actual.getFecha());
            cmbFecha.addItem(actual.getFecha());
        }
    }

    public void SpinerTotal() {

        int equipo1 = (Integer) spnEq1.getValue();
        int equipo2 = (Integer) spnEq2.getValue();

        int set2equipo1 = (Integer) spnSet2Eq1.getValue();
        int set2equipo2 = (Integer) spnSet2Eq2.getValue();

        int set3equipo1 = (Integer) spnSet3Eq1.getValue();
        int set3equipo2 = (Integer) spnSet3Eq2.getValue();

        int Total = equipo1 - equipo2;

        if (equipo1 < 25 || equipo2 < 25 && (Total == 2)) {
            Guardar();
        } else if (equipo1 > 25 || equipo2 > 25 && (Total == 2)) {
            Guardar();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar...");
        }
        if (set2equipo1 < 25 || set2equipo2 < 25 && (Total == 2)) {
            Guardar();
        } else if (set2equipo1 > 25 || set2equipo2 > 25 && (Total == 2)) {
            Guardar();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar...");
        }
        if (set3equipo1 < 25 || set3equipo2 < 25 && (Total == 2)) {
            Guardar();
        } else if (set3equipo1 > 25 || set3equipo2 > 25 && (Total == 2)) {
            Guardar();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar...");
        }
    }

    public void TablaEquipos() {
        String fecha = cmbFecha.getSelectedItem().toString();
        try {
            lstEquipo = objManResultado.consultarPartido(fecha);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblEquipos.removeAll();
        Object columnas[] = {"EQUIPO 1", "EQUIPO 2"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tblEquipos.setModel(modelo);
        for (Equipo objEqui : lstEquipo) {
            String NewValor[] = {
                objEqui.getNombreEq1(),
                objEqui.getNombreEq2(),};
            modelo.addRow(NewValor);
        }
    }

    public void Guardar() {
        DateFormat df = new SimpleDateFormat("yyy-MM-dd");
        Date fechaHoy = Calendar.getInstance().getTime();
        String fechSistema = df.format(fechaHoy);
        txtLabe.setText(fechSistema);

        int equipo1 = Integer.parseInt(spnEq1.getValue().toString());// + (Integer) spnSet2Eq1.getValue() + (Integer) spnSet3Eq1.getValue();
        int equipo2 = Integer.parseInt(spnEq2.getValue().toString());// + (Integer) spnSet2Eq2.getValue() + (Integer) spnSet3Eq2.getValue();
        int RowSel = this.tblEquipos.getSelectedRow();
        Resultado objResulta = new Resultado(fechSistema, equipo1, tblEquipos.getValueAt(RowSel, 0).toString());
        Resultado objResulta1 = new Resultado(fechSistema, equipo2, tblEquipos.getValueAt(RowSel, 1).toString());
        int bandera = objManResultado.insertarResultado(objResulta, fechSistema, tblEquipos.getValueAt(RowSel, 0).toString(), tblEquipos.getValueAt(RowSel, 1).toString(), 1);
        int bandera1 = objManResultado1.insertarResultado(objResulta1, fechSistema, tblEquipos.getValueAt(RowSel, 0).toString(), tblEquipos.getValueAt(RowSel, 1).toString(), 2);
        if (bandera > 0) {
            JOptionPane.showMessageDialog(null, "RESULTADO REGISTRADO");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR RESULTADO");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbCampeonato = new javax.swing.JComboBox<>();
        cmbFecha = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGuardarResultado = new javax.swing.JButton();
        spnEq1 = new javax.swing.JSpinner();
        spnEq2 = new javax.swing.JSpinner();
        spnSet2Eq1 = new javax.swing.JSpinner();
        spnSet2Eq2 = new javax.swing.JSpinner();
        spnSet3Eq1 = new javax.swing.JSpinner();
        spnSet3Eq2 = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEquipos = new javax.swing.JTable();
        txtLabe = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO RESULTADOS DEL PARTIDO");
        setAutoscrolls(true);

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Registrar Resultados");

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setText("Fecha:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setText("Campeonato");

        cmbCampeonato.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        cmbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCampeonato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(cmbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 134, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCampeonato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(243, 243, 243));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(120, 120, 120));
        jLabel3.setText("Equipo1");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(120, 120, 120));
        jLabel4.setText("Equipo 2");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(120, 120, 120));
        jLabel5.setText("Set 1");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(120, 120, 120));
        jLabel6.setText("Set 2");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(120, 120, 120));
        jLabel7.setText("Set 3");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(120, 120, 120));
        jLabel8.setText("Set 1");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(120, 120, 120));
        jLabel9.setText("Set 2");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(120, 120, 120));
        jLabel10.setText("Set 3");

        btnGuardarResultado.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarResultado.setText("Guardar");
        btnGuardarResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarResultadoActionPerformed(evt);
            }
        });

        spnEq1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        spnEq1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        spnEq2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        spnEq2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spnEq2.setToolTipText("");

        spnSet2Eq1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        spnSet2Eq2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        spnSet3Eq1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        spnSet3Eq2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spnEq1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(spnSet3Eq1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnSet2Eq1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnSet3Eq2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnEq2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnSet2Eq2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardarResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)))
                .addGap(118, 118, 118))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(spnEq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnSet2Eq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnSet3Eq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spnEq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(spnSet2Eq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(spnSet3Eq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnGuardarResultado)
                .addContainerGap())
        );

        tblEquipos.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipo 1", "Equipo 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEquipos);

        txtLabe.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLabe, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 33, Short.MAX_VALUE)
                .addComponent(txtLabe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cmbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFechaActionPerformed
        TablaEquipos();
    }//GEN-LAST:event_cmbFechaActionPerformed

    private void btnGuardarResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarResultadoActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarResultadoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarResultado;
    private javax.swing.JComboBox<String> cmbCampeonato;
    private javax.swing.JComboBox<String> cmbFecha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spnEq1;
    private javax.swing.JSpinner spnEq2;
    private javax.swing.JSpinner spnSet2Eq1;
    private javax.swing.JSpinner spnSet2Eq2;
    private javax.swing.JSpinner spnSet3Eq1;
    private javax.swing.JSpinner spnSet3Eq2;
    private javax.swing.JTable tblEquipos;
    private javax.swing.JTextField txtLabe;
    // End of variables declaration//GEN-END:variables
}

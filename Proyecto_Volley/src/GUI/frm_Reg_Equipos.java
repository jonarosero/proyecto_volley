package GUI;

import Clases.Colegio;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Clases.*;
import Logica.*;
import Data.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class frm_Reg_Equipos extends javax.swing.JInternalFrame {

    //VALIDAR
    Validaciones objValidar = new Validaciones();
    //CONEXION
    Dat_ConexionBD conect = new Dat_ConexionBD();
    //COLEGIO
    ArrayList<Colegio> ArrayColegio = new ArrayList<>();
    BL_Colegio colegioBL = new BL_Colegio();
    //JUGADORES
    ArrayList<Jugador> ArrayJugadores = new ArrayList<>();
    BL_Jugadores jugadoresBL = new BL_Jugadores();
    //EQUIPOS
    BL_Equipos equiposBL = new BL_Equipos();
    //INSCRIPCION
    BL_Inscripcion inscripcionBL = new BL_Inscripcion();
    //CAMPEONATO_EQUIPO
    BL_Campeonato_Equipo camEquiBL = new BL_Campeonato_Equipo();

    //MENSAJES
    boolean paso = true;
    boolean paso1 = true;
    boolean paso2 = true;

    //FECHA DEL SISTEMA
    DateFormat df = new SimpleDateFormat("yyy-MM-dd");
    Date fechaHoy = Calendar.getInstance().getTime();
    String fecha = df.format(fechaHoy);

    //TABLA CATEGORIA INFERIOR
    Object columnas[] = {"CÉDULA", "NOMBRE", "APELLIDO", "EDAD", "ESTADO"};
    DefaultTableModel modelo = new DefaultTableModel(null, columnas);

    //TABLA CATEGORIA MEDIA
    DefaultTableModel modelo2 = new DefaultTableModel(null, columnas);

    //TABLA CATEGORIA SUPERIOR
    DefaultTableModel modelo3 = new DefaultTableModel(null, columnas);
    frmPrincipal frm;

    //INICIAR EL FRAME
    public frm_Reg_Equipos() throws ClassNotFoundException, SQLException, IOException {
        initComponents();
        lblFecha.setText(fecha);
        comboColegio();

        //CONTROL DE ACTIVACION Y EDICION DE JTEXTFIELD Y BOTONES
        botones();

        //TABLA CATEGORIA INFERIOR
        tblCategoriaInferior.setModel(modelo);

        //TABLA CATEGORIA MEDIA
        tblCategoriaMedia.setModel(modelo2);

        //TABLA CATEGORIA SUPERIOR
        tblCategoriaSuperior.setModel(modelo3);
    }

    // Listar JUGADORES por CATEGORIA Dependiendo su edad 
    public void listarJugadores() throws ClassNotFoundException, SQLException {
        int edad = 0;
        String colegio = cmbColegios.getSelectedItem().toString();
        String genero = cmbGenero.getSelectedItem().toString();
        ArrayJugadores = equiposBL.ConsultarJugadores(genero, colegio);
        for (Jugador ListJuga : ArrayJugadores) {
            edad = ListJuga.getEdad();
            if (edad <= 13 && edad >= 11) {
                if (ListJuga.getEstado() == 0) {
                    modelo.addRow(new Object[]{ListJuga.getCedula(), ListJuga.getNombres(),
                        ListJuga.getApellidos(), ListJuga.getEdad(), ListJuga.getEstado()});
                    paso = true;
                } else if (ListJuga.getEstado() == 1) {
                    paso = false;
                }
            } else if (edad <= 16 && edad >= 14) {
                if (ListJuga.getEstado() == 0) {
                    modelo2.addRow(new Object[]{ListJuga.getCedula(), ListJuga.getNombres(),
                        ListJuga.getApellidos(), ListJuga.getEdad(), ListJuga.getEstado()});
                    paso1 = true;
                } else if (ListJuga.getEstado() == 1) {
                    paso1 = false;
                }
            } else if (edad <= 19 && edad >= 17) {
                if (ListJuga.getEstado() == 0) {
                    modelo3.addRow(new Object[]{ListJuga.getCedula(), ListJuga.getNombres(),
                        ListJuga.getApellidos(), ListJuga.getEdad(), ListJuga.getEstado()});
                    paso2 = true;
                } else if (ListJuga.getEstado() == 1) {
                    paso2 = false;
                }
            }
        }
        try {
            conect.CerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModificarEstado(int i) throws ClassNotFoundException, SQLException {
        jugadoresBL.ActualizarEstado(tblCategoriaInferior.getValueAt(i, 0).toString());
        jugadoresBL.ActualizarEstado(tblCategoriaMedia.getValueAt(i, 0).toString());
        jugadoresBL.ActualizarEstado(tblCategoriaSuperior.getValueAt(i, 0).toString());
    }

    public void Inscribirjugadores() throws ClassNotFoundException, SQLException {
        //CANTIDAD DE FILAS DE LAS TABLAS
        int tamanioInf = this.tblCategoriaInferior.getRowCount();
        int tamanioMed = this.tblCategoriaMedia.getRowCount();
        int tamanioSup = this.tblCategoriaSuperior.getRowCount();
        //Se valida si la tabla de ctaegoria inferior está llena para poder realizar los inserts
        if (tamanioInf > 0) {
            //Se inscriben los jugadores de la categoria inferior
            for (int i = 0; i < tamanioInf; i++) {
                try {
                    conect.AbrirConexion();
                    int bandera = inscripcionBL.InscribirJugadores(fecha,
                            tblCategoriaInferior.getValueAt(i, 0).toString(),
                            txtNombreE_CatInfe.getText().toString(), frm.idCamp);
                    if (bandera > 0) {
                        //Método para modificar el estado de los jugadores de la categoria inferior
                        ModificarEstado(i);
                    } else {
                        JOptionPane.showMessageDialog(null, "Detalle no cargado");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Método para asignar los equipos a un campeonato
            camEquiBL.InsertarEquipoCamp(txtNombreE_CatInfe.getText(), frm.idCamp);
            JOptionPane.showMessageDialog(null, "Categoria inferior ingresada correctamente", "Inscripcion", JOptionPane.INFORMATION_MESSAGE);
        }
        //Se valida si la tabla de ctaegoria media está llena para poder realizar los inserts
        if (tamanioMed > 0) {
            //Se inscriben los jugadores de la categoria media
            for (int i = 0; i < tamanioMed; i++) {
                try {
                    conect.AbrirConexion();
                    int bandera = inscripcionBL.InscribirJugadores(fecha,
                            tblCategoriaMedia.getValueAt(i, 0).toString(),
                            txtNombreE_CatMed.getText().toString(), frm.idCamp);
                    if (bandera > 0) {
                        //Método para modificar el estado de los jugadores de la categoria media
                        ModificarEstado(i);
                    } else {
                        JOptionPane.showMessageDialog(null, "Detalle no cargado");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Método para asignar los equipos a un campeonato
            camEquiBL.InsertarEquipoCamp(txtNombreE_CatMed.getText(), frm.idCamp);

            JOptionPane.showMessageDialog(null, "Categoria media ingresada correctamente", "Inscripcion", JOptionPane.INFORMATION_MESSAGE);
        }
        //Se valida si la tabla de ctaegoria superior está llena para poder realizar los inserts
        if (tamanioSup > 0) {
            //Se inscriben los jugadores de la categoria superior
            for (int i = 0; i < tamanioSup; i++) {
                try {
                    conect.AbrirConexion();
                    int bandera = inscripcionBL.InscribirJugadores(fecha,
                            tblCategoriaSuperior.getValueAt(i, 0).toString(),
                            txtNombreE_CatSup.getText().toString(), frm.idCamp);
                    if (bandera > 0) {
                        //Método para modificar el estado de los jugadores de la categoria superior
                        ModificarEstado(i);
                    } else {
                        JOptionPane.showMessageDialog(null, "Detalle no cargado");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Método para asignar los equipos a un campeonato
            camEquiBL.InsertarEquipoCamp(txtNombreE_CatSup.getText(), frm.idCamp);
            JOptionPane.showMessageDialog(null, "Categoria superior ingresada correctamente", "Inscripcion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void comboColegio() throws ClassNotFoundException, SQLException, IOException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ArrayList<Colegio> lstCole = new ArrayList<>();
        lstCole = colegioBL.ListarColegios(ArrayColegio);
        for (Colegio actual : lstCole) {
            Colegio obtAdmin = new Colegio(actual.getNombreCole());
            cmbColegios.addItem(actual.getNombreCole());
        }
    }

    public boolean camposCompletos() {
        boolean blnCamposCompletos = false;
        if (!txtNombreE_CatInfe.getText().isEmpty() && !txtNombreE_CatMed.getText().isEmpty() && !txtNombreE_CatSup.getText().isEmpty()) {
            blnCamposCompletos = true;
        }
        return blnCamposCompletos;
    }

    //SE ACTIVA BOTON ASIGNAR CATEGORIA
    public void botones() {
        btnAsignar_Categoria.setEnabled(true);
        btnGuardarCategoria.setEnabled(false);
        btnCancelar.setEnabled(false);
        txtNombreE_CatInfe.setEditable(false);
        txtNombreE_CatMed.setEditable(false);
        txtNombreE_CatSup.setEditable(false);
        txtNombreE_CatInfe.setEnabled(false);
        txtNombreE_CatMed.setEnabled(false);
        txtNombreE_CatSup.setEnabled(false);
    }

    //LIMPIA TODA LA INTERFAZ
    public void BotonesTotal() {
        botones();
        txtNombreE_CatInfe.setText("");
        txtNombreE_CatMed.setText("");
        txtNombreE_CatSup.setText("");
        modelo.setRowCount(0);
        modelo2.setRowCount(0);
        modelo3.setRowCount(0);
    }

    // </editor-fold> 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btnAsignar_Categoria = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCategoriaSuperior = new javax.swing.JTable();
        btnGuardarCategoria = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombreE_CatInfe = new javax.swing.JTextField();
        txtNombreE_CatSup = new javax.swing.JTextField();
        txtNombreE_CatMed = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cmbColegios = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCategoriaInferior = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCategoriaMedia = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setTitle("REGISTRO EQUIPOS POR CATEGORÍA");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("JUGADORES POR COLEGIO");

        btnAsignar_Categoria.setBackground(new java.awt.Color(153, 255, 153));
        btnAsignar_Categoria.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAsignar_Categoria.setText("Asignar Categoria");
        btnAsignar_Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignar_CategoriaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel4.setText("CATEGORIA INFERIOR");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel5.setText("CATEGORIA MEDIA");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel6.setText("CATEGORIA SUPERIOR");

        tblCategoriaSuperior.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblCategoriaSuperior.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tblCategoriaSuperior);

        btnGuardarCategoria.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardarCategoria.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnGuardarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCategoria.setText("Guardar");
        btnGuardarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCategoriaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("       REGISTRO EQUIPOS POR CATEGORÍAS");

        jLabel8.setText("Nombre Equipo:");

        jLabel9.setText("Nombre Equipo:");

        jLabel10.setText("Nombre Equipo:");

        txtNombreE_CatInfe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreE_CatInfeKeyTyped(evt);
            }
        });

        txtNombreE_CatSup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreE_CatSupKeyTyped(evt);
            }
        });

        txtNombreE_CatMed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreE_CatMedKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setText("Género");

        cmbGenero.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setText("Fecha:");

        cmbColegios.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbColegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbColegiosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Colegio");

        lblFecha.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(cmbColegios, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel11)
                .addGap(14, 14, 14)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(cmbColegios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11))
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblCategoriaInferior.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblCategoriaInferior.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblCategoriaInferior);

        tblCategoriaMedia.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblCategoriaMedia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tblCategoriaMedia);

        btnCancelar.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
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
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNombreE_CatInfe, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNombreE_CatMed, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(97, 97, 97))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(btnGuardarCategoria)
                        .addGap(56, 56, 56)
                        .addComponent(btnCancelar)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNombreE_CatSup, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnAsignar_Categoria)
                                .addGap(315, 315, 315))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAsignar_Categoria)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtNombreE_CatInfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreE_CatMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreE_CatSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCategoria)
                    .addComponent(btnCancelar))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsignar_CategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignar_CategoriaActionPerformed
        //VACIA TABLAS PARA VOLVER A LLENAR
        modelo.setRowCount(0);
        modelo2.setRowCount(0);
        modelo3.setRowCount(0);

        //ACTIVACION DE BOTON GUARDAR
        btnGuardarCategoria.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAsignar_Categoria.setEnabled(false);

        try {
            listarJugadores();
            if (paso == false) {
                JOptionPane.showMessageDialog(null, "Jugadores de categoria inferior ya ingresados", "Jugadores ingresados", JOptionPane.ERROR_MESSAGE);
                paso = true;
                botones();
            }
            if (paso1 == false) {
                JOptionPane.showMessageDialog(null, "Jugadores de categoria media ya ingresados", "Jugadores ingresados", JOptionPane.ERROR_MESSAGE);
                paso1 = true;
                botones();
            }
            if (paso2 == false) {
                JOptionPane.showMessageDialog(null, "Jugadores de categoria superior ya ingresados", "Jugadores ingresados", JOptionPane.ERROR_MESSAGE);
                paso2 = true;
                botones();
            } else if (paso == true || paso1 == true || paso2 == true) {
                JOptionPane.showMessageDialog(null, "Se han clasificado por Categoria Correctamente", "Clasificacion", JOptionPane.INFORMATION_MESSAGE);
                paso = true;
                paso1 = true;
                paso2 = true;
                btnAsignar_Categoria.setEnabled(false);
            }
            //CANTIDAD DE FILAS DE LAS TABLAS
            int tamanioInf = this.tblCategoriaInferior.getRowCount();
            int tamanioMed = this.tblCategoriaMedia.getRowCount();
            int tamanioSup = this.tblCategoriaSuperior.getRowCount();

            //ACTIVACION DE JTEXTFIELD PARA INGRESAR NOMBRE DEL EQUIPO
            if (tamanioInf > 0) {
                txtNombreE_CatInfe.setEditable(true);
                txtNombreE_CatInfe.setEnabled(true);
            }
            if (tamanioMed > 0) {
                txtNombreE_CatMed.setEditable(true);
                txtNombreE_CatMed.setEnabled(true);
            }
            if (tamanioSup > 0) {
                txtNombreE_CatSup.setEditable(true);
                txtNombreE_CatSup.setEnabled(true);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAsignar_CategoriaActionPerformed

    private void btnGuardarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCategoriaActionPerformed
        // TODO add your handling code here:
        camposCompletos();
        try {
            if (cmbGenero.getSelectedItem().toString().equals("MASCULINO")) {
                equiposBL.InsertarEquipo(txtNombreE_CatInfe.getText(), 3, cmbColegios.getSelectedItem().toString());
                equiposBL.InsertarEquipo(txtNombreE_CatMed.getText(), 4, cmbColegios.getSelectedItem().toString());
                equiposBL.InsertarEquipo(txtNombreE_CatSup.getText(), 5, cmbColegios.getSelectedItem().toString());
                Inscribirjugadores();
            } else if (cmbGenero.getSelectedItem().toString().equals("FEMENINO")) {
                equiposBL.InsertarEquipo(txtNombreE_CatInfe.getText(), 0, cmbColegios.getSelectedItem().toString());
                equiposBL.InsertarEquipo(txtNombreE_CatMed.getText(), 1, cmbColegios.getSelectedItem().toString());
                equiposBL.InsertarEquipo(txtNombreE_CatSup.getText(), 2, cmbColegios.getSelectedItem().toString());
                Inscribirjugadores();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Equipos.class.getName()).log(Level.SEVERE, null, ex);
        }
        BotonesTotal();
    }//GEN-LAST:event_btnGuardarCategoriaActionPerformed

    private void cmbColegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbColegiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbColegiosActionPerformed

    private void txtNombreE_CatInfeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreE_CatInfeKeyTyped
        // TODO add your handling code here:
        objValidar.ValidarCaracteres(evt);
    }//GEN-LAST:event_txtNombreE_CatInfeKeyTyped

    private void txtNombreE_CatMedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreE_CatMedKeyTyped
        // TODO add your handling code here:
        objValidar.ValidarCaracteres(evt);
    }//GEN-LAST:event_txtNombreE_CatMedKeyTyped

    private void txtNombreE_CatSupKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreE_CatSupKeyTyped
        // TODO add your handling code here:
        objValidar.ValidarCaracteres(evt);

    }//GEN-LAST:event_txtNombreE_CatSupKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        BotonesTotal();

    }//GEN-LAST:event_btnCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignar_Categoria;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarCategoria;
    private javax.swing.JComboBox<String> cmbColegios;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JTable tblCategoriaInferior;
    private javax.swing.JTable tblCategoriaMedia;
    private javax.swing.JTable tblCategoriaSuperior;
    private javax.swing.JTextField txtNombreE_CatInfe;
    private javax.swing.JTextField txtNombreE_CatMed;
    private javax.swing.JTextField txtNombreE_CatSup;
    // End of variables declaration//GEN-END:variables
}

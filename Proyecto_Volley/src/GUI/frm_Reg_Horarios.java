package GUI;

import Clases.Arbitro;
import Clases.Equipo;
import Clases.Partido;
import Logica.BL_Arbitro;
import Logica.BL_HorarioPart;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class frm_Reg_Horarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form hora
     */
    //ARRAY EQUIPO PARA LSITAR
    ArrayList<Equipo> ArrayLisEq = new ArrayList<>();
    //ARRAY ARBITRO PARA LISTAR
    ArrayList<Arbitro> lstArbit = new ArrayList<>();
    //Objeto BL_HORARIOS
    BL_HorarioPart objBlHorarioP = new BL_HorarioPart();
    //Objeto BL_ARBITRO
    BL_Arbitro manArbir = new BL_Arbitro();
    //OBJETOS DE TIPO EQUIPO
    Equipo objEqui1;
    Equipo objEqui2;
    //Objeto Partido
    Partido objPartido;
    // Variables globales de la Fecha y Hora
    String fecha;
    String hora;
    // Creamos los objetos Columnas para cada modelo de tabla
    Object columnas[] = {"Nombre Equipo"};
    Object columnas2[] = {"Equipo 1", "Equipo 2", "Fecha", "Hora", "Cancha", "Arbitro Lateral", "Arbitro Central", "Categoria"};
    //Creamos los Modelos de Tabla con sus Columnas
    DefaultTableModel modeloEqui1 = new DefaultTableModel(null, columnas);
    DefaultTableModel modeloParti = new DefaultTableModel(null, columnas2);
    DefaultTableModel modeloEqui2 = new DefaultTableModel(null, columnas);
    //Objetos del FramePrincipal
    frmPrincipal escritorio;

    public frm_Reg_Horarios() throws ClassNotFoundException, SQLException {
        initComponents();
        //Cargamos desde el inicio los Combobox de Los Arbitros Central y Lateral
        comboArbitro();
        comboArbitro2();
        //Asignamos los modelos a la tablas
        tbPartidos.setModel(modeloParti);
        tblEquipo1.setModel(modeloEqui1);
        tblEquipos2.setModel(modeloEqui2);

    }

    // <editor-fold defaultstate="collapsed" desc="Metodos"> 
    public void Insertar_Partido() throws ClassNotFoundException, SQLException {
        //Variable para obtener el numero de filas
        int fila = this.tbPartidos.getRowCount();
        //obtengo el ID del Campeonato del objeto Frame PRincipal
        int id = escritorio.idCamp;
        for (int i = 0; i < fila; i++) {
            //Insertamos desde las Tablas 
            int ms = objBlHorarioP.InsertarEquipo(tbPartidos.getValueAt(i, 7).toString(),
                    tbPartidos.getValueAt(i, 2).toString(),
                    tbPartidos.getValueAt(i, 3).toString(),
                    Integer.parseInt(tbPartidos.getValueAt(i, 4).toString()),
                    id);
            //MENSAJES
            if (ms > 0) {
                JOptionPane.showMessageDialog(null, "Partido Ingresado Correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se a ingresado Partido Correctamente");
            }
        }
        ArrayList<Partido> ArrayLis = new ArrayList<>();
    }

    //LISTAR EQUIPO 1
    public void listarEqu1() throws ClassNotFoundException, SQLException {
        modeloEqui1.setRowCount(0);
        tblEquipo1.setModel(modeloEqui1);
        ArrayLisEq = objBlHorarioP.ListarEquipoCat(cmbCategoria.getSelectedIndex());
        for (Equipo Arrequipo : ArrayLisEq) {
            String NewValor[] = {Arrequipo.getNombreEq()};
            modeloEqui1.addRow(NewValor);
        }
    }

    public void listarEqu2() throws ClassNotFoundException, SQLException {
        modeloEqui2.setRowCount(0);
        tblEquipos2.setModel(modeloEqui2);
        ArrayLisEq = objBlHorarioP.ListarEquipoCat(cmbCategoria.getSelectedIndex());
        for (Equipo Arrequipo : ArrayLisEq) {
            String NewValor[] = {Arrequipo.getNombreEq()};
            modeloEqui2.addRow(NewValor);
        }
    }

    // Metodo para cargar los colegios en el ComboBox colegios
    public void comboArbitro() throws ClassNotFoundException, SQLException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ArrayList<Arbitro> lstArbitro = new ArrayList<>();
        lstArbitro = manArbir.consultarNomArbitroLateral();
        for (Arbitro actual : lstArbitro) {
            Arbitro objArbi = new Arbitro(actual.getNombres(), actual.getApellidos());
            cmbArbitroLat.addItem(actual.getNombres() + " " + actual.getApellidos());
        }
    }

    public void comboArbitro2() throws ClassNotFoundException, SQLException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ArrayList<Arbitro> lstArbitro = new ArrayList<>();
        lstArbitro = manArbir.consultarNomArbitroCentral();
        for (Arbitro actual : lstArbitro) {
            Arbitro objArbi = new Arbitro(actual.getNombres(), actual.getApellidos());
            cmbArbitroCen.addItem(actual.getNombres() + " " + actual.getApellidos());
        }
    }

    public void agregarPartido() {
        String categoria = "";
        String genero = "";
        if (cmbCategoria.getSelectedIndex() == 0 || cmbCategoria.getSelectedIndex() == 3) {
            categoria = "INFERIOR";
        } else if (cmbCategoria.getSelectedIndex() == 1 || cmbCategoria.getSelectedIndex() == 4) {
            categoria = "MEDIA";
        } else if (cmbCategoria.getSelectedIndex() == 2 || cmbCategoria.getSelectedIndex() == 5) {
            categoria = "SUPERIOR";
        }
        if (cmbCategoria.getSelectedIndex() == 0 || cmbCategoria.getSelectedIndex() == 1 || cmbCategoria.getSelectedIndex() == 2) {
            genero = "FEMENINO";
        } else if (cmbCategoria.getSelectedIndex() == 3 || cmbCategoria.getSelectedIndex() == 4 || cmbCategoria.getSelectedIndex() == 5) {
            genero = "MASCULINO";
        }
        //FECHA Y HORA
        fecha = new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate());
        hora = txtHora.getText();
        //FILA SELECIONADA EN LA TABLA EQUIPOS
        int fila = tblEquipo1.getSelectedRow();
        int fila2 = tblEquipos2.getSelectedRow();
        //Obtiene los valores de la fila seleccionada de la tabla y crea un objeto Equipo con ellos
        objEqui1 = new Equipo(tblEquipo1.getValueAt((fila), 0).toString());
        objEqui2 = new Equipo(tblEquipos2.getValueAt((fila2), 0).toString());
        if (fila != -1) {
            modeloParti.addRow(new Object[]{objEqui1.getNombreEq(),
                objEqui2.getNombreEq(),
                fecha, hora, cmbCancha.getSelectedIndex() + 1,
                cmbArbitroLat.getSelectedItem().toString(),
                cmbArbitroCen.getSelectedItem().toString(),
                categoria});
            modeloEqui1.removeRow(fila);
            modeloEqui2.removeRow(fila2);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese Todos los Campos", "Asignar Campos", JOptionPane.ERROR_MESSAGE);
        }
    }

    // </editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngroup = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPartidos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEquipo1 = new javax.swing.JTable();
        btnPasar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEquipos2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbArbitro = new javax.swing.JLabel();
        cmbCancha = new javax.swing.JComboBox<>();
        cmbArbitroLat = new javax.swing.JComboBox<>();
        lbCancha = new javax.swing.JLabel();
        cmbArbitroCen = new javax.swing.JComboBox<>();
        lbArbitro1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbCategoria = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        lbFecha = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        lbHora = new javax.swing.JLabel();
        txtHora = new javax.swing.JFormattedTextField();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO FECHAS DE PARTIDOS");
        setAutoscrolls(true);
        setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("REGISTRO DE FECHAS DE PARTIDOS");

        tbPartidos.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tbPartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbPartidos);

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel1.setText("VS");

        tblEquipo1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Equipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEquipo1);

        btnPasar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPasar.setText(">");
        btnPasar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarActionPerformed(evt);
            }
        });

        tblEquipos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Equipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblEquipos2);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setText("EQUIPOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(btnPasar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPasar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        jPanel3.setBackground(new java.awt.Color(243, 243, 243));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setForeground(new java.awt.Color(204, 204, 204));

        lbArbitro.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbArbitro.setForeground(new java.awt.Color(102, 102, 102));
        lbArbitro.setText("Arbitro Central:");

        cmbCancha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCancha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cancha 1", "Cancha 2", "Cancha 3", "Cancha 4", "Cancha 5", "Cancha 6", "Cancha 7" }));

        cmbArbitroLat.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        lbCancha.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbCancha.setForeground(new java.awt.Color(102, 102, 102));
        lbCancha.setText("Cancha:");

        cmbArbitroCen.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        lbArbitro1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbArbitro1.setForeground(new java.awt.Color(102, 102, 102));
        lbArbitro1.setText("Arbitro Lateral:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lbCancha)
                .addGap(18, 18, 18)
                .addComponent(cmbCancha, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lbArbitro1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbArbitro)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbArbitroCen, 0, 132, Short.MAX_VALUE)
                    .addComponent(cmbArbitroLat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbArbitroLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbArbitroCen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCancha)
                            .addComponent(lbArbitro1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)))
                .addGap(69, 69, 69))
        );

        jPanel2.setBackground(new java.awt.Color(243, 243, 243));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        lbCategoria.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbCategoria.setForeground(new java.awt.Color(102, 102, 102));
        lbCategoria.setText("Categoria:");

        cmbCategoria.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inferior femenino", "Media femenino", "Superior femenino", "Inferior masculina", "Media masculina", "Superior masculina" }));
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });

        lbFecha.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbFecha.setForeground(new java.awt.Color(102, 102, 102));
        lbFecha.setText("Fecha:");

        txtFecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtFecha.setPreferredSize(new java.awt.Dimension(65, 20));

        lbHora.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        lbHora.setForeground(new java.awt.Color(102, 102, 102));
        lbHora.setText("Hora:");

        try {
            txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(lbCategoria)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbFecha)
                        .addGap(30, 30, 30)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(lbHora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCategoria))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        btnModificar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addGap(40, 40, 40)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnEliminar)
                        .addGap(227, 227, 227))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            //INSERTA LOS PARTIDOS
            Insertar_Partido();
            //HACE QUE LAS FILAS FILAS SE PONGAN EN 0
            modeloParti.setRowCount(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Reg_Horarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Horarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        try {
            //METODO PARA LISTAR LOS EQUIPOS
            listarEqu1();
            listarEqu2();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frm_Reg_Horarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void btnPasarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarActionPerformed
        //AL DAR CLICK SE VA AGREGAR EL PARTIDO A LA TABLA
        agregarPartido();
    }//GEN-LAST:event_btnPasarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPasar;
    private javax.swing.ButtonGroup btngroup;
    private javax.swing.JComboBox<String> cmbArbitroCen;
    private javax.swing.JComboBox<String> cmbArbitroLat;
    private javax.swing.JComboBox<String> cmbCancha;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbArbitro;
    private javax.swing.JLabel lbArbitro1;
    private javax.swing.JLabel lbCancha;
    private javax.swing.JLabel lbCategoria;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbHora;
    private javax.swing.JTable tbPartidos;
    private javax.swing.JTable tblEquipo1;
    private javax.swing.JTable tblEquipos2;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JFormattedTextField txtHora;
    // End of variables declaration//GEN-END:variables
}

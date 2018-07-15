package GUI;

import Clases.Arbitro;
import Clases.Colegio;
import Clases.Encriptacion_Desencriptacion;
import Clases.Jugador;
import Clases.Persona;
import Clases.Usuario;
import Clases.Validaciones;
import Logica.BL_Arbitro;
import Logica.BL_Colegio;
import Logica.BL_Jugadores;
import Logica.BL_Loguin;
import Logica.BL_Persona;
import java.io.IOException;
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
public class frm_Reg_Jugadores extends javax.swing.JInternalFrame {

    Validaciones objValidar = new Validaciones();
    ArrayList<Jugador> ArrayJugador = new ArrayList<>();
    ArrayList<Colegio> ArrayColegio = new ArrayList<>();
    ArrayList<Arbitro> ArrayArbitro = new ArrayList<>();
    BL_Arbitro objManArbitro = new BL_Arbitro();
    BL_Colegio colegioBL = new BL_Colegio();
    BL_Jugadores objManJuga = new BL_Jugadores();
    BL_Loguin objManLog = new BL_Loguin();
    BL_Persona personaBL = new BL_Persona();
    Persona objPersona = new Persona();
    Jugador objJugador = new Jugador();
    Encriptacion_Desencriptacion objEncrip = new Encriptacion_Desencriptacion();
    int tipoRegistro;
    int tipoTabla;

    public frm_Reg_Jugadores() throws ClassNotFoundException, SQLException, IOException {
        initComponents();
        comboColegio();
        deshabilitar();
        buttonGroup1.add(central);
        buttonGroup1.add(lateral);
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos"> 
    // Metodo para cargar los colegios en el ComboBox colegios
    public void comboColegio() throws ClassNotFoundException, SQLException, IOException {
        DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
        ArrayList<Colegio> lstCole = new ArrayList<>();
        lstCole = colegioBL.ComboColegio();
        for (Colegio actual : lstCole) {
            Colegio obtAdmin = new Colegio(actual.getNombreCole());
            cmbcolegios.addItem(actual.getNombreCole());
        }
    }

    // Metodo para deshabilitar los botones
    public void deshabilitar() {
        cmbGenero.setEnabled(false);
        cmbcolegios.setEnabled(false);
        cmbSangre.setEnabled(false);
        spnEdad.setEnabled(false);
        SpnEstatura.setEnabled(false);
        SpnPeso.setEnabled(false);
        cmbPosicion.setEnabled(false);
        cmbConfede.setEnabled(false);
        SpnCamiseta.setEnabled(false);
        txtApellido.setEnabled(false);
        txtCedula.setEnabled(false);
        txtNombre.setEnabled(false);
        lateral.setEnabled(false);
        central.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnJugador.setEnabled(true);
        btnAlbitro.setEnabled(true);
        btnConsultar.setEnabled(true);

    }

    // Metodo para habilitar campos al hacer click en boton Registrar Jugador
    public void habilitarJugador() {
        tipoRegistro = 1;
        cmbGenero.setEnabled(true);
        cmbcolegios.setEnabled(true);
        cmbSangre.setEnabled(true);
        spnEdad.setEnabled(true);
        cmbConfede.setEnabled(false);
        SpnEstatura.setEnabled(true);
        SpnPeso.setEnabled(true);
        cmbPosicion.setEnabled(true);
        SpnCamiseta.setEnabled(true);
        txtApellido.setEnabled(true);
        txtCedula.setEnabled(true);
        txtNombre.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnAlbitro.setEnabled(true);
        btnConsultar.setEnabled(true);
    }

    // Metodo para habilitar campos al hacer click en boton Registrar Arbitro
    public void HabilitarAlbitro() {
        tipoRegistro = 0;
        txtApellido.setEnabled(true);
        txtCedula.setEnabled(true);
        txtNombre.setEnabled(true);
        cmbConfede.setEnabled(true);
        SpnEstatura.setEnabled(false);
        cmbGenero.setEnabled(true);
        cmbcolegios.setEnabled(false);
        cmbSangre.setEnabled(false);
        spnEdad.setEnabled(true);
        SpnPeso.setEnabled(false);
        cmbPosicion.setEnabled(false);
        SpnCamiseta.setEnabled(false);
        lateral.setEnabled(true);
        central.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);

    }

    public void ModificarArbitroBotones() {
        cmbGenero.setEnabled(true);
        txtCedula.setEnabled(true);
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        cmbConfede.setEnabled(true);
        central.setEnabled(true);
        lateral.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        spnEdad.setEnabled(true);
        cmbcolegios.setEnabled(false);
        cmbSangre.setEnabled(false);
        SpnEstatura.setEnabled(false);
        SpnPeso.setEnabled(false);
        cmbPosicion.setEnabled(false);
        SpnCamiseta.setEnabled(false);
        btnAlbitro.setEnabled(false);
        btnJugador.setEnabled(false);
    }

    public void ModificarJugadorBotonoes() {
        cmbGenero.setEnabled(true);
        txtCedula.setEnabled(true);
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        cmbConfede.setEnabled(false);
        central.setEnabled(false);
        lateral.setEnabled(false);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        spnEdad.setEnabled(true);
        cmbcolegios.setEnabled(true);
        cmbSangre.setEnabled(true);
        SpnEstatura.setEnabled(true);
        SpnPeso.setEnabled(true);
        cmbPosicion.setEnabled(true);
        SpnCamiseta.setEnabled(true);
        btnJugador.setEnabled(false);
        btnAlbitro.setEnabled(false);

    }

    //Metodo para cargar en la tabla los jugadores de genero masculino 
    public void TablaM() {

        try {
            ArrayJugador = objManJuga.consultarMasculino();
            ArrayColegio = colegioBL.ListarColegios(ArrayColegio);

        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbMasculino.removeAll();
        Object columnas[] = {"CEDULA", "NOMBRE", "APELLIDOS", "EDAD", "GENERO", "COLEGIO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tbMasculino.setModel(modelo);
        Colegio col = new Colegio();
        for (Jugador objJugador : ArrayJugador) {
            String NewValor[] = {
                objJugador.getCedula(),
                objJugador.getNombres(),
                objJugador.getApellidos(),
                String.valueOf(objJugador.getEdad()),
                objJugador.getGenero(),
                objJugador.objColegio.getNombreCole().toString(),};
            modelo.addRow(NewValor);
        }
    }

    //Metodo para cargar en la tabla los jugadores de genero Femenino 
    public void TablaF() {
        try {
            ArrayJugador = objManJuga.consultarFmenino();

        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbFemenino.removeAll();
        Object columnas[] = {"CEDULA", "NOMBRE", "APELLIDOS", "EDAD", "GENERO", "COLEGIO"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tbFemenino.setModel(modelo);

        for (Jugador objJugador : ArrayJugador) {
            String NewValor[] = {
                objJugador.getCedula(),
                objJugador.getNombres(),
                objJugador.getApellidos(),
                String.valueOf(objJugador.getEdad()),
                objJugador.getGenero(),
                objJugador.objColegio.getNombreCole().toString(),};
            modelo.addRow(NewValor);
        }
    }

    //Metodo para cargar en la tabla los Arbitros
    public void TablaArbitro() {
        try {
            ArrayArbitro = objManArbitro.consultarArbitro();
        } catch (SQLException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbAlbitro.removeAll();
        Object columnas[] = {"Cedula", "Nombre", "Apellidos", "Edad", "Genero", "TipoArbitro", "Confederacion"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        tbAlbitro.setModel(modelo);

        for (Arbitro actual : ArrayArbitro) {
            String NewValor[] = {
                actual.getCedula(),
                actual.getNombres(),
                actual.getApellidos(),
                String.valueOf(actual.getEdad()),
                actual.getGenero(),
                actual.getTipoArb(),
                actual.getConfederacion()
            };
            modelo.addRow(NewValor);
        }
    }

    public boolean camposCompletos() {
        boolean blnCamposCompletos = false;
        if (!txtCedula.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty()) {
            blnCamposCompletos = true;
        }
        return blnCamposCompletos;
    }

    public void ModificarJugadorMasculino() throws ClassNotFoundException, SQLException {
        String nombre = txtNombre.getText().toUpperCase();
        String apellido = txtApellido.getText().toUpperCase();
        String cedula = txtCedula.getText().toUpperCase();
        int edad = (Integer) spnEdad.getValue();
        String genero = cmbGenero.getSelectedItem().toString();
        double estatura = (Double) SpnEstatura.getValue();
        double peso = (Double) SpnPeso.getValue();
        String sangre = cmbSangre.getSelectedItem().toString();
        String posicion = cmbPosicion.getSelectedItem().toString();
        int camiseta = (Integer) SpnCamiseta.getValue();
        String colegio = cmbcolegios.getSelectedItem().toString();
        String confederacion = cmbConfede.getSelectedItem().toString();
        int estado = 0;

        String tipoArbitro = null;
        if (central.isSelected()) {
            tipoArbitro = central.getActionCommand();
        } else if (lateral.isSelected()) {
            tipoArbitro = lateral.getActionCommand();
        }
        int bandera;
        if (camposCompletos()) {
            int RowSel = this.tbMasculino.getSelectedRow();
            Persona objPersona = new Persona(nombre, apellido, cedula, edad, genero);
            Jugador objJugador = new Jugador(estatura, peso, sangre, posicion, camiseta, estado);
            Arbitro objArbitro = new Arbitro(nombre, apellido, cedula, edad, genero, tipoArbitro, confederacion);

            bandera = objManJuga.ActualizaJugador(objPersona, objJugador, tbMasculino.getValueAt(RowSel, 0).toString(), tbMasculino.getValueAt(RowSel, 5).toString(), colegio);
            if (bandera > 0) {
                JOptionPane.showMessageDialog(null, "JUGADOR ACTUALIZADO");
                limpiar();
                deshabilitar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR JUGADOR");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los campos.");
        }

    }

    public void ModificarJugadorFemenino() throws ClassNotFoundException, SQLException {
        String nombre = txtNombre.getText().toUpperCase();
        String apellido = txtApellido.getText().toUpperCase();
        String cedula = txtCedula.getText();
        int edad = (Integer) spnEdad.getValue();
        String genero = cmbGenero.getSelectedItem().toString();
        double estatura = (Double) SpnEstatura.getValue();
        double peso = (Double) SpnPeso.getValue();
        String sangre = cmbSangre.getSelectedItem().toString();
        String posicion = cmbPosicion.getSelectedItem().toString();
        int camiseta = (Integer) SpnCamiseta.getValue();
        String colegio = cmbcolegios.getSelectedItem().toString();
        String confederacion = cmbConfede.getSelectedItem().toString();
        int estado = 0;

        String tipoArbitro = null;
        if (central.isSelected()) {
            tipoArbitro = central.getActionCommand();
        } else if (lateral.isSelected()) {
            tipoArbitro = lateral.getActionCommand();
        }
        int bandera;
        if (camposCompletos()) {
            int RowSel = this.tbFemenino.getSelectedRow();
            Persona objPersona = new Persona(nombre, apellido, cedula, edad, genero);
            Jugador objJugador = new Jugador(estatura, peso, sangre, posicion, camiseta, estado);
            Arbitro objArbitro = new Arbitro(nombre, apellido, cedula, edad, genero, tipoArbitro, confederacion);

            bandera = objManJuga.ActualizaJugador(objPersona, objJugador, tbFemenino.getValueAt(RowSel, 0).toString(), tbFemenino.getValueAt(RowSel, 5).toString(), colegio);
            if (bandera > 0) {
                JOptionPane.showMessageDialog(null, "JUGADOR ACTUALIZADO");
                limpiar();
                deshabilitar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR JUGADOR");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los campos.");
        }

    }

    public void ModificarArbitro() throws ClassNotFoundException, SQLException {
        String nombre = txtNombre.getText().toUpperCase();
        String apellido = txtApellido.getText().toUpperCase();
        String cedula = txtCedula.getText();
        int edad = (Integer) spnEdad.getValue();
        String genero = cmbGenero.getSelectedItem().toString();
        double estatura = (Double) SpnEstatura.getValue();
        double peso = (Double) SpnPeso.getValue();
        String sangre = cmbSangre.getSelectedItem().toString();
        String posicion = cmbPosicion.getSelectedItem().toString();
        int camiseta = (Integer) SpnCamiseta.getValue();
        String colegio = cmbcolegios.getSelectedItem().toString();
        String confederacion = cmbConfede.getSelectedItem().toString();
        int estado = 0;

        String tipoArbitro = null;
        if (central.isSelected()) {
            tipoArbitro = central.getActionCommand();
        } else if (lateral.isSelected()) {
            tipoArbitro = lateral.getActionCommand();
        }
        if (camposCompletos()) {
            int bandera;
            int RowSel = this.tbAlbitro.getSelectedRow();
            Persona objPersona = new Persona(nombre, apellido, cedula, edad, genero);
            Jugador objJugador = new Jugador(estatura, peso, sangre, posicion, camiseta, estado);
            Arbitro objArbitro = new Arbitro(nombre, apellido, cedula, edad, genero, tipoArbitro, confederacion);

            bandera = objManArbitro.ActualizaArbitro(objPersona, objArbitro, tbAlbitro.getValueAt(RowSel, 0).toString());

            if (bandera > 0) {
                JOptionPane.showMessageDialog(null, "ARBITRO ACTUALIZADO...");
                limpiar();
                deshabilitar();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR ARBITRO....");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los campos.");
        }

    }

    private void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
    }
// </editor-fold> 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JPTABLAS = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbMasculino = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        ALBITRO = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbFemenino = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbAlbitro = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Registro = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        SpnCamiseta = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        cmbConfede = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        SpnPeso = new javax.swing.JSpinner();
        cmbPosicion = new javax.swing.JComboBox<>();
        cmbSangre = new javax.swing.JComboBox<>();
        SpnEstatura = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        cmbcolegios = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        spnEdad = new javax.swing.JSpinner();
        central = new javax.swing.JRadioButton();
        lateral = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnJugador = new javax.swing.JToggleButton();
        btnAlbitro = new javax.swing.JToggleButton();

        setClosable(true);
        setTitle("REGISTRO JUGADORES Y ÁRBITROS");
        setToolTipText("");
        setAutoscrolls(true);
        setVisible(true);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("REGISTRO JUGADORES Y ÁRBITROS");

        JPTABLAS.setBackground(new java.awt.Color(243, 243, 243));
        JPTABLAS.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        JPTABLAS.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("JUGADORES MASCULINO");

        tbMasculino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C.I.", "NOMBRE", "APELLIDOS", "EDAD", "GÉNERO"
            }
        ));
        tbMasculino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasculinoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbMasculino);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("JUGADORES FEMENINO");

        ALBITRO.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        ALBITRO.setForeground(new java.awt.Color(102, 102, 102));
        ALBITRO.setText("ARBITRO");

        tbFemenino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C.I.", "NOMBRE", "APELLIDOS", "EDAD", "GÉNERO"
            }
        ));
        tbFemenino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFemeninoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbFemenino);

        tbAlbitro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C.I.", "NOMBRE", "APELLIDOS", "TIPO DE ARBITRO"
            }
        ));
        tbAlbitro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAlbitroMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbAlbitro);

        javax.swing.GroupLayout JPTABLASLayout = new javax.swing.GroupLayout(JPTABLAS);
        JPTABLAS.setLayout(JPTABLASLayout);
        JPTABLASLayout.setHorizontalGroup(
            JPTABLASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPTABLASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPTABLASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ALBITRO, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPTABLASLayout.setVerticalGroup(
            JPTABLASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPTABLASLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ALBITRO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(JPTABLAS);

        Registro.setBackground(new java.awt.Color(243, 243, 243));
        Registro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Registro.setForeground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nombres");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Apellidos");

        txtCedula.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Cedula");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Género");

        cmbGenero.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Edad");

        jLabel27.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Estatura(m)");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Peso(Kg)");

        jLabel29.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Posicion");

        jLabel30.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Tipo Sangre");

        SpnCamiseta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        SpnCamiseta.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Confederacion");

        cmbConfede.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbConfede.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AFRICANA ", "ASIA", "EUROPEA", "SUDAMERICANA", "AMERICA DEL NORTE" }));

        jLabel32.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("N° Camiseta");

        SpnPeso.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        SpnPeso.setModel(new javax.swing.SpinnerNumberModel(100.0d, null, 2000.0d, 1.0d));

        cmbPosicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DELANTERO IZQUIERDO", "DELANTERO CENTRO", "DELANTERO DERECHO", "ZAQUERO CENTRO", "ZAQUERO IZQUIERDA", "LIBRERO", " " }));

        cmbSangre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+" }));

        SpnEstatura.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        SpnEstatura.setModel(new javax.swing.SpinnerNumberModel(1000.0d, null, 2099.0d, 1.0d));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Colegios");

        cmbcolegios.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("Tipo de Arbitro");

        spnEdad.setModel(new javax.swing.SpinnerNumberModel(1, null, 100, 1));

        central.setText("CENTRAL");

        lateral.setText("LATERAL");

        javax.swing.GroupLayout RegistroLayout = new javax.swing.GroupLayout(Registro);
        Registro.setLayout(RegistroLayout);
        RegistroLayout.setHorizontalGroup(
            RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroLayout.createSequentialGroup()
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RegistroLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RegistroLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(RegistroLayout.createSequentialGroup()
                        .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SpnPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SpnEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(RegistroLayout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbSangre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SpnCamiseta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(central)
                                .addGap(39, 39, 39)
                                .addComponent(lateral)
                                .addGap(19, 19, 19))
                            .addGroup(RegistroLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbcolegios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbConfede, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        RegistroLayout.setVerticalGroup(
            RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbcolegios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel23)
                .addGap(7, 7, 7)
                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addGap(7, 7, 7)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addGap(12, 12, 12)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addGap(11, 11, 11)
                .addComponent(cmbConfede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(central)
                    .addComponent(lateral))
                .addGap(9, 9, 9)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cmbSangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpnCamiseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpnPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(SpnEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(Registro);

        btnConsultar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jButton1.setText("CANCELAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnGuardar)
                .addGap(79, 79, 79)
                .addComponent(btnConsultar)
                .addGap(79, 79, 79)
                .addComponent(btnModificar)
                .addGap(72, 72, 72)
                .addComponent(btnEliminar)
                .addGap(65, 65, 65)
                .addComponent(jButton1)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultar)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnJugador.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnJugador.setText("REGISTRAR JUGADOR");
        btnJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugadorActionPerformed(evt);
            }
        });

        btnAlbitro.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAlbitro.setText("REGISTRAR ALBITRO");
        btnAlbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlbitroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlbitro)
                        .addGap(865, 865, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        objValidar.ValidarCaracteres(evt);;
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        objValidar.ValidarCaracteres(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        objValidar.ValidarNumeros(evt);
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        TablaF();//Cargar Tabla de Jugadores Femeninos
        TablaM();//Cargar Tabla de Jugadores Masculinos
        TablaArbitro();//Cargar Tabla de Arbitros
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugadorActionPerformed
        habilitarJugador();
    }//GEN-LAST:event_btnJugadorActionPerformed

    private void btnAlbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlbitroActionPerformed
        HabilitarAlbitro();
    }//GEN-LAST:event_btnAlbitroActionPerformed

    // Se inserta los jugadores y Arbitros 
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombre = txtNombre.getText().toUpperCase();
        String apellido = txtApellido.getText().toUpperCase();
        String cedula = txtCedula.getText();
        int edad = (Integer) spnEdad.getValue();
        String genero = cmbGenero.getSelectedItem().toString();
        double estatura = SpnEstatura.getComponentCount();
        double peso = SpnPeso.getComponentCount();
        String sangre = cmbSangre.getSelectedItem().toString();
        String posicion = cmbPosicion.getSelectedItem().toString();
        int camiseta = (Integer) SpnCamiseta.getValue();
        String colegio = cmbcolegios.getSelectedItem().toString();
        int estado = 0;
        String confederacion = cmbConfede.getSelectedItem().toString();
        System.out.println("Vista " + confederacion);
        String tipoArbitro = null;
        if (central.isSelected()) {
            tipoArbitro = central.getActionCommand();
        } else if (lateral.isSelected()) {
            tipoArbitro = lateral.getActionCommand();
        }

        // VALIDA CAMPOS COMPLETOS PARA REALIZAR EL INSERT
        if (camposCompletos()) {
            Persona objPersona = new Persona(nombre, apellido, cedula, edad, genero);
            Jugador objJugador = new Jugador(estatura, peso, sangre, posicion, camiseta, estado);
            Arbitro objArbitro = new Arbitro(nombre, apellido, cedula, edad, genero, tipoArbitro, confederacion);
            //Arbitro objArbitro = new Arbitro(tipoArbitro, confederacion);
            Usuario objUsuario = new Usuario(cedula, cedula);
            //INSERTA JUGADOR 
            if (tipoRegistro == 1) {
                personaBL.insertarPersona(objPersona);
                int bandera = objManJuga.insertarJugador(objJugador, cedula, colegio);
                // VALIDA SI SE REGISTRA EL JUGADOR 
                if (bandera > 0) {
                    JOptionPane.showMessageDialog(null, "JUGADOR INSERTADO");
                    limpiar();
                    deshabilitar();

                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR JUGADOR");
                    limpiar();
                }
                //INSERTA ARBITRO
            } else if (tipoRegistro == 0) {

                personaBL.insertarPersona(objPersona);
                int bandera = objManArbitro.InsertarArbitro(objArbitro, cedula);
                String pass = objEncrip.encriptar(cedula);
                objManLog.InsertarCredenciales(cedula, pass);
                System.out.println(confederacion);
                System.out.println(tipoArbitro);

                //VALIDA SI SE REGISTRA EL JUGADOR 
                if (bandera > 0) {
                    JOptionPane.showMessageDialog(null, "ARBITRO INSERTADO. PUEDE INGRESAR AL SISTEMA CON SU NÚMERO DE CEDULA");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR ARBITRO");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los campos.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Se modifica los Jugadores por genero y Arbitros
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        if (tipoTabla == 1) {
            try {
                ModificarJugadorMasculino();
                limpiar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tipoTabla == 0) {
            try {
                ModificarArbitro();
                limpiar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (tipoTabla == 2) {
            try {
                ModificarJugadorFemenino();
                limpiar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frm_Reg_Jugadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void tbMasculinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasculinoMouseClicked
        txtCedula.setText(tbMasculino.getModel().getValueAt(tbMasculino.getSelectedRow(), 0).toString());
        txtNombre.setText(tbMasculino.getModel().getValueAt(tbMasculino.getSelectedRow(), 1).toString());
        txtApellido.setText(tbMasculino.getModel().getValueAt(tbMasculino.getSelectedRow(), 2).toString());
        ModificarJugadorBotonoes();
        tipoTabla = 1;
    }//GEN-LAST:event_tbMasculinoMouseClicked

    private void tbAlbitroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlbitroMouseClicked
        txtCedula.setText(tbAlbitro.getModel().getValueAt(tbAlbitro.getSelectedRow(), 0).toString());
        txtNombre.setText(tbAlbitro.getModel().getValueAt(tbAlbitro.getSelectedRow(), 1).toString());
        txtApellido.setText(tbAlbitro.getModel().getValueAt(tbAlbitro.getSelectedRow(), 2).toString());
        ModificarArbitroBotones();
        btnEliminar.setEnabled(false);
        tipoTabla = 0;

    }//GEN-LAST:event_tbAlbitroMouseClicked

    private void tbFemeninoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFemeninoMouseClicked
        txtCedula.setText(tbFemenino.getModel().getValueAt(tbFemenino.getSelectedRow(), 0).toString());
        txtNombre.setText(tbFemenino.getModel().getValueAt(tbFemenino.getSelectedRow(), 1).toString());
        txtApellido.setText(tbFemenino.getModel().getValueAt(tbFemenino.getSelectedRow(), 2).toString());
        ModificarJugadorBotonoes();
        tipoTabla = 2;

    }//GEN-LAST:event_tbFemeninoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        deshabilitar();
        limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Se elimina la Persona por medio del parametro cedula
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText().toUpperCase();
        String apellido = txtApellido.getText().toUpperCase();
        String cedula = txtCedula.getText().toUpperCase();
        int edad = (Integer) spnEdad.getValue();
        String genero = cmbGenero.getSelectedItem().toString();
        Persona objPersona = new Persona(nombre, apellido, cedula, edad, genero);
        int bandera = objManJuga.EliminarJugador(cedula);
        if (bandera > 0) {
            JOptionPane.showMessageDialog(null, "JUGADOR ELIMINADO");
            limpiar();

        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR ARBITRO, SELECCIONE UN JUGADOR");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ALBITRO;
    private javax.swing.JPanel JPTABLAS;
    private javax.swing.JPanel Registro;
    private javax.swing.JSpinner SpnCamiseta;
    private javax.swing.JSpinner SpnEstatura;
    private javax.swing.JSpinner SpnPeso;
    private javax.swing.JToggleButton btnAlbitro;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JToggleButton btnJugador;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton central;
    private javax.swing.JComboBox<String> cmbConfede;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbPosicion;
    private javax.swing.JComboBox<String> cmbSangre;
    private javax.swing.JComboBox<String> cmbcolegios;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JRadioButton lateral;
    private javax.swing.JSpinner spnEdad;
    private javax.swing.JTable tbAlbitro;
    private javax.swing.JTable tbFemenino;
    private javax.swing.JTable tbMasculino;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

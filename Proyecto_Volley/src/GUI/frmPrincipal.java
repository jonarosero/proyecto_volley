package GUI;

import Logica.*;
import Data.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @authors Cristhian Apolo, Marco Caicedo, Accel Loarte, Juan Ramón y Fernando
 * León
 */
public class frmPrincipal extends javax.swing.JFrame {

    BL_Loguin manLoguin = new BL_Loguin();
    frmLoguin loguin;

    //Variable retornada de frmLoguin para validar tipo usuario
    private static boolean validarUser;
    //Id campeonato de frmCampeonato
    public static int idCamp;

    public frmPrincipal(boolean valUser) throws ClassNotFoundException, SQLException {
        initComponents();
        this.validarUser = valUser;
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        comprobarUser();
        cerrar();
    }

    public frmPrincipal(int id) throws ClassNotFoundException, SQLException {
        initComponents();
        this.idCamp = id;
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        comprobarUser();
        cerrar();
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos"> 
    public void ControlaInstancia(JInternalFrame frame) {
        boolean mostrar = true;
        String Nombre = frame.getTitle();
        for (int a = 0; a < escritorio.getComponentCount(); a++) {     // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (frame.getClass().isInstance(escritorio.getComponent(a))) {
                JOptionPane.showMessageDialog(rootPane, "La ventana " + Nombre + " que interta abrir ya está abierta, cierre la ventana actual e intente nuevamente");
                //System.out.println("esta instancia, no se debe mostrar");
                frame.toFront();
                escritorio.moveToFront(frame);
                mostrar = false;

            } else {
                //System.out.println("no lo es, puede mostrarse");
            }
        }
        if (mostrar) {
            escritorio.add(frame);
        }
        frame.show();
    }

    public void cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    confirmarSalida();
                }
            });
        } catch (Exception e) {
        }
    }

    public void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Está seguro de cerrar la aplicación?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "¡Hasta pronto (:!");
            System.exit(0);
        }
    }

    public void comprobarUser() throws ClassNotFoundException, SQLException {
        /*
        validarUser = true -> Administrador
        validarUser = false -> Arbitro
         */
        System.out.println("Fram" + validarUser);
        if (validarUser == true) {
            System.out.println("Administrador");
            menuItem_Reg_ResultadoPartido.setVisible(false);
        }
        if (validarUser == true) {
            System.out.println("Arbitro");
            menuItem_Reg_Campeonato.setVisible(false);
            menuItem_Reg_Colegio.setVisible(false);
            menuItem_Reg_Equipos.setVisible(false);
            menuItem_Reg_HorariosPartidos.setVisible(false);
            menuItem_Reg_Jugadores.setVisible(false);
        }
    }

    // </editor-fold> 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        menu = new javax.swing.JMenuBar();
        menu_Archivo = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItem_Salir = new javax.swing.JMenuItem();
        menu_Registros = new javax.swing.JMenu();
        menuItem_Reg_Campeonato = new javax.swing.JMenuItem();
        menuItem_Reg_Colegio = new javax.swing.JMenuItem();
        menuItem_Reg_Jugadores = new javax.swing.JMenuItem();
        menuItem_Reg_Equipos = new javax.swing.JMenuItem();
        menuItem_Reg_HorariosPartidos = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuItem_Reg_ResultadoPartido = new javax.swing.JMenuItem();
        menu_Resultados = new javax.swing.JMenu();
        menuItem_Resultado_Campeonato = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        escritorio.setBackground(new java.awt.Color(204, 255, 255));
        escritorio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1184, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        menu.setBackground(new java.awt.Color(204, 204, 204));
        menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menu.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N

        menu_Archivo.setText("Sistema");
        menu_Archivo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        menu_Archivo.add(jSeparator1);

        menuItem_Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Salir.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        menuItem_Salir.setText("Salir");
        menuItem_Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuItem_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_SalirActionPerformed(evt);
            }
        });
        menu_Archivo.add(menuItem_Salir);

        menu.add(menu_Archivo);

        menu_Registros.setText("Registros");
        menu_Registros.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N

        menuItem_Reg_Campeonato.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Reg_Campeonato.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Reg_Campeonato.setText("Registrar Campeonato");
        menuItem_Reg_Campeonato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Reg_CampeonatoActionPerformed(evt);
            }
        });
        menu_Registros.add(menuItem_Reg_Campeonato);

        menuItem_Reg_Colegio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Reg_Colegio.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Reg_Colegio.setText("Registrar Colegio");
        menuItem_Reg_Colegio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Reg_ColegioActionPerformed(evt);
            }
        });
        menu_Registros.add(menuItem_Reg_Colegio);

        menuItem_Reg_Jugadores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Reg_Jugadores.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Reg_Jugadores.setText("Registrar Jugadores");
        menuItem_Reg_Jugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Reg_JugadoresActionPerformed(evt);
            }
        });
        menu_Registros.add(menuItem_Reg_Jugadores);

        menuItem_Reg_Equipos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Reg_Equipos.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Reg_Equipos.setText("Registrar Equipos");
        menuItem_Reg_Equipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Reg_EquiposActionPerformed(evt);
            }
        });
        menu_Registros.add(menuItem_Reg_Equipos);

        menuItem_Reg_HorariosPartidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Reg_HorariosPartidos.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Reg_HorariosPartidos.setText("Asignar Horarios Partidos");
        menuItem_Reg_HorariosPartidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Reg_HorariosPartidosActionPerformed(evt);
            }
        });
        menu_Registros.add(menuItem_Reg_HorariosPartidos);
        menu_Registros.add(jSeparator2);

        menuItem_Reg_ResultadoPartido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Reg_ResultadoPartido.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Reg_ResultadoPartido.setText("Registrar Resultado Partido");
        menuItem_Reg_ResultadoPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Reg_ResultadoPartidoActionPerformed(evt);
            }
        });
        menu_Registros.add(menuItem_Reg_ResultadoPartido);

        menu.add(menu_Registros);

        menu_Resultados.setText("Resultados");
        menu_Resultados.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N

        menuItem_Resultado_Campeonato.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Resultado_Campeonato.setFont(new java.awt.Font("Century Gothic", 2, 14)); // NOI18N
        menuItem_Resultado_Campeonato.setText("Visualizar Resultado Campeonato");
        menuItem_Resultado_Campeonato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_Resultado_CampeonatoActionPerformed(evt);
            }
        });
        menu_Resultados.add(menuItem_Resultado_Campeonato);

        menu.add(menu_Resultados);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItem_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItem_SalirActionPerformed

    private void menuItem_Reg_JugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Reg_JugadoresActionPerformed
        try {
            frm_Reg_Jugadores jugadores = new frm_Reg_Jugadores();
            ControlaInstancia(jugadores);

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(frmPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_Reg_JugadoresActionPerformed

    private void menuItem_Reg_HorariosPartidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Reg_HorariosPartidosActionPerformed
        try {
            frm_Reg_Horarios horarios = new frm_Reg_Horarios();
            ControlaInstancia(horarios);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frmPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_Reg_HorariosPartidosActionPerformed

    private void menuItem_Reg_ColegioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Reg_ColegioActionPerformed
        try {
            frm_Reg_Colegios colegios = new frm_Reg_Colegios();
            ControlaInstancia(colegios);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frmPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_Reg_ColegioActionPerformed

    private void menuItem_Reg_ResultadoPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Reg_ResultadoPartidoActionPerformed
        frm_Reg_ResultadosPar registroPartidos = null;
        try {
            registroPartidos = new frm_Reg_ResultadosPar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        ControlaInstancia(registroPartidos);

    }//GEN-LAST:event_menuItem_Reg_ResultadoPartidoActionPerformed

    private void menuItem_Reg_EquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Reg_EquiposActionPerformed
        frm_Reg_Equipos equipo;
        try {
            equipo = new frm_Reg_Equipos();
            ControlaInstancia(equipo);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_Reg_EquiposActionPerformed

    private void menuItem_Resultado_CampeonatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Resultado_CampeonatoActionPerformed
        try {
            frm_Visu_Partidos visualizarRes = new frm_Visu_Partidos();
            ControlaInstancia(visualizarRes);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_menuItem_Resultado_CampeonatoActionPerformed

    private void menuItem_Reg_CampeonatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_Reg_CampeonatoActionPerformed
        try {
            frm_Reg_Campeonato campeonatoInternal = new frm_Reg_Campeonato();
            ControlaInstancia(campeonatoInternal);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem_Reg_CampeonatoActionPerformed

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
            java.util.logging.Logger.getLogger(frmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmPrincipal(validarUser).setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem menuItem_Reg_Campeonato;
    private javax.swing.JMenuItem menuItem_Reg_Colegio;
    private javax.swing.JMenuItem menuItem_Reg_Equipos;
    private javax.swing.JMenuItem menuItem_Reg_HorariosPartidos;
    private javax.swing.JMenuItem menuItem_Reg_Jugadores;
    private javax.swing.JMenuItem menuItem_Reg_ResultadoPartido;
    private javax.swing.JMenuItem menuItem_Resultado_Campeonato;
    private javax.swing.JMenuItem menuItem_Salir;
    private javax.swing.JMenu menu_Archivo;
    private javax.swing.JMenu menu_Registros;
    private javax.swing.JMenu menu_Resultados;
    // End of variables declaration//GEN-END:variables
}

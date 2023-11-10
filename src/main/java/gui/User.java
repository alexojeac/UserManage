package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.App;
import model.ZIP;

public class User extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaMenuUsuario;
    private JButton btnVerDatos;
    private JButton btnCambiarContraseña;
    private JButton btnBorrarUsuario;
    private JButton btnExportarUsuarios;
    private JButton btnCrearNuevoUsuario;
    private JButton btnCerrarSesion;
    private App app;
    private String nombreUsuario;

    public User(App app, String nombreUsuario) {
        this.app = app;
        this.nombreUsuario = nombreUsuario;
        int paddingLeft = 75;

        setTitle("Aplicación usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        etiquetaMenuUsuario = new JLabel(nombreUsuario, SwingConstants.CENTER);
        etiquetaMenuUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
        etiquetaMenuUsuario.setBounds(0, 20, 300, 30);
        contentPane.add(etiquetaMenuUsuario);

        btnVerDatos = new JButton("Ver datos");
        btnVerDatos.setBounds(paddingLeft, 64, 150, 23);
        btnVerDatos.addActionListener(this);
        contentPane.add(btnVerDatos);

        btnCambiarContraseña = new JButton("Cambiar contraseña");
        btnCambiarContraseña.setBounds(paddingLeft, 98, 150, 23);
        btnCambiarContraseña.addActionListener(this);
        contentPane.add(btnCambiarContraseña);

        if (!nombreUsuario.equals("admin")) {
            btnBorrarUsuario = new JButton("Borrar usuario");
            btnBorrarUsuario.setBounds(paddingLeft, 132, 150, 23);
            btnBorrarUsuario.addActionListener(this);
            btnBorrarUsuario.setBackground(Color.decode("#e74c3c"));
            btnBorrarUsuario.setForeground(Color.decode("#ecf0f1"));
            contentPane.add(btnBorrarUsuario);
        }

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBounds(paddingLeft, 200, 150, 23);
        btnCerrarSesion.addActionListener(this);
        contentPane.add(btnCerrarSesion);

        System.out.println(nombreUsuario);
        if (nombreUsuario.equals("admin")) {
            setBounds(0, 0, 300, 360);

            btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
            btnCrearNuevoUsuario.setBounds(paddingLeft, 180, 150, 23);
            btnCrearNuevoUsuario.addActionListener(this);
            contentPane.add(btnCrearNuevoUsuario);

            btnExportarUsuarios = new JButton("Exportar usuarios");
            btnExportarUsuarios.setBounds(paddingLeft, 210, 150, 23);
            btnExportarUsuarios.addActionListener(this);
            contentPane.add(btnExportarUsuarios);

            btnCerrarSesion.setBounds(paddingLeft, 270, 150, 23);
        }

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExportarUsuarios) {
            System.out.println("Exportar usuario (ZIP)");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("usuarios.zip"));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                app.createZipFile(selectedFile);
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            } else {
                System.out.println("Selección de archivo cancelada.");
            }
        }

        if (e.getSource() == btnVerDatos) {
            UserDetails userDetailView = new UserDetails(app, nombreUsuario, app.getUserAge(), app.getUserMail());
            userDetailView.setVisible(true);
        }

        if (e.getSource() == btnCerrarSesion) {
            app.setSession(false);
            dispose();
        }

        if (e.getSource() == btnCambiarContraseña) {
            UserChangePassword changePassView = new UserChangePassword(app, nombreUsuario);
            changePassView.setVisible(true);
        }

        if (e.getSource() == btnCrearNuevoUsuario) {
            UserCreate userCreateView = new UserCreate(app);
            userCreateView.setVisible(true);
        }

        if (e.getSource() == btnBorrarUsuario) {
            UserDelete userDeleteView = new UserDelete(app, nombreUsuario);
            userDeleteView.setVisible(true);
        }
    }

}

package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.App;

public class UserCreate extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel etiquetaCrearUsuario;
    private JLabel etiquetaNombre;
    private JLabel etiquetaContraseña;
    private JLabel etiquetaEdad;
    private JLabel etiquetaCorreo;
    private JTextField textoNombre;
    private JPasswordField textoContraseña;
    private JTextField textoEdad;
    private JTextField textoCorreo;
    private JButton btnCrear;
    private JButton btnCancelar;
    private App app;

    public UserCreate(App app) {
        this.app = app;
        setTitle("Aplicación usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 322, 385);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaNombre.setBounds(65, 56, 68, 14);
        contentPane.add(etiquetaNombre);

        etiquetaCrearUsuario = new JLabel("CREAR USUARIO");
        etiquetaCrearUsuario.setBounds(83, 11, 154, 20);
        etiquetaCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(etiquetaCrearUsuario);

        textoNombre = new JTextField();
        textoNombre.setBounds(65, 81, 214, 20);
        contentPane.add(textoNombre);
        textoNombre.setColumns(10);

        etiquetaContraseña = new JLabel("Contraseña:");
        etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaContraseña.setBounds(65, 112, 68, 14);
        contentPane.add(etiquetaContraseña);

        textoContraseña = new JPasswordField();
        textoContraseña.setColumns(10);
        textoContraseña.setBounds(65, 137, 214, 20);
        contentPane.add(textoContraseña);

        etiquetaEdad = new JLabel("Edad:");
        etiquetaEdad.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaEdad.setBounds(65, 168, 68, 14);
        contentPane.add(etiquetaEdad);

        textoEdad = new JTextField();
        textoEdad.setColumns(10);
        textoEdad.setBounds(65, 193, 214, 20);
        contentPane.add(textoEdad);

        etiquetaCorreo = new JLabel("Correo electrónico:");
        etiquetaCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        etiquetaCorreo.setBounds(65, 224, 214, 14);
        contentPane.add(etiquetaCorreo);

        textoCorreo = new JTextField();
        textoCorreo.setColumns(10);
        textoCorreo.setBounds(65, 249, 214, 20);
        contentPane.add(textoCorreo);

        btnCrear = new JButton("Crear");
        btnCrear.setBounds(172, 299, 89, 23);
        btnCrear.addActionListener(this);
        contentPane.add(btnCrear);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(39, 299, 89, 23);
        btnCancelar.addActionListener(this);
        contentPane.add(btnCancelar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancelar) {
            dispose();
        }

        if (e.getSource() == btnCrear) {
            if (textoNombre.getText().equals("") || textoContraseña.getText().equals("") || textoEdad.getText().equals("") || textoCorreo.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Para crear un usuario deben estar todos los campos completos", "Error", JOptionPane.WARNING_MESSAGE);
            } else  {
                app.createUser(textoNombre.getText(), textoContraseña.getText(), Integer.parseInt(textoEdad.getText()), textoCorreo.getText());
                dispose();
            }

        }
    }
}

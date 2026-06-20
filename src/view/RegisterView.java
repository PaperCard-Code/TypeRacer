package view;

import controller.PlayerController;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {

    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    private JButton registerButton;

    private PlayerController controller;

    public RegisterView() {

        controller =
                new PlayerController();

        setTitle("Player Registration");

        setSize(400,300);

        setLocationRelativeTo(null);

        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                10));

        txtName = new JTextField();

        txtEmail = new JTextField();

        txtPassword =
                new JPasswordField();

        registerButton =
                new JButton(
                        "Register");

        panel.add(new JLabel("Name"));
        panel.add(txtName);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("Password"));
        panel.add(txtPassword);

        panel.add(new JLabel());
        panel.add(registerButton);

        add(panel);

        registerButton.addActionListener(e -> {

            boolean success =
                    controller.registerPlayer(

                            txtName.getText(),

                            txtEmail.getText(),

                            String.valueOf(
                                    txtPassword.getPassword()
                            )
                    );

            if(success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful");

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Failed");
            }
        });
    }
}
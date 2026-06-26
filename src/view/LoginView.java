package view;

import controller.AdminController;
import controller.PlayerController;

import model.Admin;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

	private JTextField txtEmail;

	private JPasswordField txtPassword;

	private JComboBox<String> roleBox;

	private JButton loginButton;

	public LoginView() {

		setTitle("Login");

		setSize(400, 250);

		setLocationRelativeTo(null);

		initializeComponents();

		setVisible(true);
	}

	private void initializeComponents() {

		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

		txtEmail = new JTextField();

		txtPassword = new JPasswordField();

		roleBox = new JComboBox<>(new String[] { "Player", "Admin" });

		loginButton = new JButton("Login");

		panel.add(new JLabel("Email"));
		panel.add(txtEmail);

		panel.add(new JLabel("Password"));
		panel.add(txtPassword);

		panel.add(new JLabel("Role"));
		panel.add(roleBox);

		panel.add(new JLabel());
		panel.add(loginButton);

		add(panel);

		loginButton.addActionListener(e -> login());
	}

	private void login() {

		String email = txtEmail.getText();

		String password = String.valueOf(txtPassword.getPassword());

		String role = roleBox.getSelectedItem().toString();

		if (role.equals("Player")) {

			PlayerController controller = new PlayerController();

			Player player = controller.login(email, password);

			if (player != null) {

				new PlayerDashboardView(player);

				dispose();

			} else {

				JOptionPane.showMessageDialog(this, "Invalid Login");
			}
		} else {

			AdminController controller = new AdminController();

			Admin admin = controller.login(email, password);

			if (admin != null) {

				new AdminDashboardView(admin);

				dispose();

			} else {

				JOptionPane.showMessageDialog(this, "Invalid Login");
			}
		}
	}
}
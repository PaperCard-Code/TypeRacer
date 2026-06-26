package view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {

	private JButton loginButton;
	private JButton registerButton;
	private JButton exitButton;

	public HomeView() {

		setTitle("Type Racer");

		setSize(500, 300);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeComponents();

		addActions();

		setVisible(true);

	}

	private void initializeComponents() {

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 1, 10, 10));

		JLabel title = new JLabel("Type Racer!", SwingConstants.CENTER);

		title.setFont(new Font("Arial", Font.BOLD, 22));

		loginButton = new JButton("Login");

		registerButton = new JButton("Register");

		exitButton = new JButton("Exit");

		panel.add(title);

		panel.add(loginButton);

		panel.add(registerButton);

		panel.add(exitButton);

		add(panel);

	}

	private void addActions() {

		// Login Button

		loginButton.addActionListener(e -> {

			new LoginView();

			dispose();

		});

		// Register Button

		registerButton.addActionListener(e -> {

			new RegisterView();

		});

		// Exit Button

		exitButton.addActionListener(e -> {

			System.exit(0);

		});

	}

}
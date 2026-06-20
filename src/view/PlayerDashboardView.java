package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerDashboardView extends JFrame {

	private Player player;

	public PlayerDashboardView(Player player) {

		this.player = player;

		setTitle("Player Dashboard");

		setSize(500, 300);

		setLocationRelativeTo(null);

		initializeComponents();

		setVisible(true);
	}

	private void initializeComponents() {

		JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

		JLabel welcome = new JLabel("Welcome " + player.getName(), SwingConstants.CENTER);

		JButton startTest = new JButton("Start Typing Test");

		JButton rankings = new JButton("View Rankings");

		JButton logout = new JButton("Logout");

		panel.add(welcome);
		panel.add(startTest);
		panel.add(rankings);
		panel.add(logout);

		add(panel);

		startTest.addActionListener(e -> new TypingTestView(player));

		rankings.addActionListener(e -> new RankingView());

		logout.addActionListener(e -> {

			dispose();

			new HomeView();
		});
	}
}
package view;

import controller.TypingTestController;

import model.Player;
import model.Ranking;
import model.TypingTestResult;

import javax.swing.*;
import java.awt.*;

public class TypingTestView extends JFrame {

	private Player player;

	private TypingTestController controller;

	private JTextArea targetArea;

	private JTextArea inputArea;

	private JLabel timerLabel;

	private JButton submitButton;

	private String targetText;

	private int remainingSeconds = 30;

	private Timer timer;

	public TypingTestView(Player player) {

		this.player = player;

		controller = new TypingTestController();

		setTitle("Type Racer");

		setSize(800, 600);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		initializeComponents();

		startTest();

		setVisible(true);
	}

	private void initializeComponents() {

		targetArea = new JTextArea();

		targetArea.setEditable(false);

		targetArea.setLineWrap(true);

		targetArea.setWrapStyleWord(true);

		targetArea.setFont(new Font("Monospaced", Font.PLAIN, 18));

		inputArea = new JTextArea();

		inputArea.setLineWrap(true);

		inputArea.setWrapStyleWord(true);

		inputArea.setFont(new Font("Monospaced", Font.PLAIN, 18));

		timerLabel = new JLabel("Time: 30", SwingConstants.CENTER);

		timerLabel.setFont(new Font("Arial", Font.BOLD, 24));

		submitButton = new JButton("Submit");

		// Prompt panel

		JPanel promptPanel = new JPanel(new BorderLayout());

		promptPanel.setBorder(BorderFactory.createTitledBorder("Text To Type"));

		JScrollPane promptScroll = new JScrollPane(targetArea);

		promptScroll.setPreferredSize(new Dimension(700, 120));

		promptPanel.add(promptScroll, BorderLayout.CENTER);

		// Input panel

		JPanel inputPanel = new JPanel(new BorderLayout());

		inputPanel.setBorder(BorderFactory.createTitledBorder("Your Input"));

		JScrollPane inputScroll = new JScrollPane(inputArea);

		inputScroll.setPreferredSize(new Dimension(700, 200));

		inputPanel.add(inputScroll, BorderLayout.CENTER);

		// Button panel

		JPanel buttonPanel = new JPanel();

		buttonPanel.add(submitButton);

		// Main content

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		centerPanel.add(promptPanel);

		centerPanel.add(inputPanel);

		setLayout(new BorderLayout());

		add(timerLabel, BorderLayout.NORTH);

		add(centerPanel, BorderLayout.CENTER);

		add(buttonPanel, BorderLayout.SOUTH);

		submitButton.addActionListener(e -> finishTest());
	}

	private void startTest() {

		targetText = controller.generateTestText();

		targetArea.setText(targetText);

		timer = new Timer(1000, e -> {

			remainingSeconds--;

			timerLabel.setText(String.valueOf(remainingSeconds));

			if (remainingSeconds <= 0) {

				timer.stop();

				finishTest();
			}
		});

		timer.start();
	}

	private void finishTest() {

		if (timer != null && timer.isRunning()) {

			timer.stop();
		}

		String typedText = inputArea.getText();

		int elapsedTime = 30 - remainingSeconds;

		// Prevent division by zero
		if (elapsedTime <= 0) {

			elapsedTime = 1;
		}

		TypingTestResult result = controller.calculateResults(targetText, typedText, elapsedTime);

		Ranking ranking = controller.finishTest(player, targetText, typedText, elapsedTime);

		JOptionPane.showMessageDialog(

				this,

				"Test Complete\n\n"

						+ "Correct Characters: " + result.getCorrectChars()

						+ "\nIncorrect Characters: " + result.getIncorrectChars()

						+ "\nAccuracy: " + String.format("%.2f", result.getAccuracy()) + "%"

						+ "\nWPM: " + String.format("%.2f", result.getWpm())

		);

		dispose();
	}
}
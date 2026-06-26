package view;

import controller.RankingController;

import model.Ranking;

import util.PDFGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class RankingView extends JFrame {

	private JTable rankingTable;

	private DefaultTableModel model;

	private JButton refreshButton;

	private JButton exportButton;

	private RankingController controller;

	public RankingView() {

		controller = new RankingController();

		setTitle("Ranking Board");

		setSize(900, 500);

		setLocationRelativeTo(null);

		initializeComponents();

		loadTable();

		setVisible(true);
	}

	private void initializeComponents() {

		model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Player");
		model.addColumn("WPM");
		model.addColumn("Accuracy");
		model.addColumn("Date");

		rankingTable = new JTable(model);

		refreshButton = new JButton("Refresh");

		exportButton = new JButton("Export PDF");

		JPanel buttonPanel = new JPanel();

		buttonPanel.add(refreshButton);
		buttonPanel.add(exportButton);

		add(new JScrollPane(rankingTable), BorderLayout.CENTER);

		add(buttonPanel, BorderLayout.SOUTH);

		refreshButton.addActionListener(e -> loadTable());

		exportButton.addActionListener(e -> exportPDF());
	}

	private void loadTable() {

		model.setRowCount(0);

		ArrayList<Ranking> rankings = controller.getRankings();

		for (Ranking ranking : rankings) {

			model.addRow(new Object[] {

					ranking.getId(),

					ranking.getPlayerName(),

					String.format("%.2f", ranking.getWpm()),

					String.format("%.2f", ranking.getAccuracy()),

					ranking.getTestDate() });
		}
	}

	private void exportPDF() {

		JFileChooser chooser = new JFileChooser();

		int result = chooser.showSaveDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {

			String path = chooser.getSelectedFile().getAbsolutePath();

			if (!path.endsWith(".pdf")) {

				path += ".pdf";
			}

			PDFGenerator.exportTable(rankingTable, path);

			JOptionPane.showMessageDialog(this, "PDF Exported Successfully");
		}
	}
}
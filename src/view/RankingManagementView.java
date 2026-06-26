package view;

import controller.RankingController;

import model.Ranking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

import util.PDFGenerator;

public class RankingManagementView extends JFrame {

	private JTable table;
	private DefaultTableModel model;
	private JButton deleteButton;
	private JButton refreshButton;
	private RankingController controller;
	private JButton exportButton;

	public RankingManagementView() {

		controller = new RankingController();

		setTitle("Ranking Management");

		setSize(900, 500);

		setLocationRelativeTo(null);

		initializeComponents();

		loadData();

		setVisible(true);
	}

	private void initializeComponents() {

		model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Player");
		model.addColumn("WPM");
		model.addColumn("Accuracy");
		model.addColumn("Date");

		table = new JTable(model);

		deleteButton = new JButton("Delete");
		refreshButton = new JButton("Refresh");
		exportButton = new JButton("Export PDF");

		JPanel panel = new JPanel();

		panel.add(deleteButton);
		panel.add(refreshButton);
		panel.add(exportButton);

		add(new JScrollPane(table), BorderLayout.CENTER);

		add(panel, BorderLayout.SOUTH);

		deleteButton.addActionListener(e -> deleteSelected());
		refreshButton.addActionListener(e -> loadData());
		exportButton.addActionListener(e -> exportPDF());
	}

	private void loadData() {

		model.setRowCount(0);

		ArrayList<Ranking> rankings = controller.getRankings();

		for (Ranking ranking : rankings) {

			model.addRow(new Object[] {

					ranking.getId(),

					ranking.getPlayerName(),

					ranking.getWpm(),

					ranking.getAccuracy(),

					ranking.getTestDate() });
		}
	}

	private void deleteSelected() {

		int row = table.getSelectedRow();

		if (row == -1) {

			JOptionPane.showMessageDialog(this, "Select a row");

			return;
		}

		int id = Integer.parseInt(model.getValueAt(row, 0).toString());

		controller.deleteRanking(id);
		loadData();
	}

	private void exportPDF() {

		JFileChooser chooser = new JFileChooser();

		int result = chooser.showSaveDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {

			String path = chooser.getSelectedFile().getAbsolutePath();

			if (!path.endsWith(".pdf")) {

				path += ".pdf";
			}

			PDFGenerator.exportTable(table, path);

			JOptionPane.showMessageDialog(this, "PDF Exported Successfully");
		}
	}

}
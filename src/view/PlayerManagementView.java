package view;

import controller.PlayerController;
import model.Player;
import util.PDFGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PlayerManagementView extends JFrame {

	private JTable table;

	private DefaultTableModel model;

	private PlayerController controller;

	public PlayerManagementView() {

		controller = new PlayerController();

		setTitle("Player Management");

		setSize(700, 400);

		setLocationRelativeTo(null);

		initializeComponents();

		loadPlayers();

		setVisible(true);
	}

	private void initializeComponents() {

		model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Email");

		table = new JTable(model);

		JButton delete = new JButton("Delete");

		JButton refresh = new JButton("Refresh");

		JButton export = new JButton("Export PDF");

		JPanel buttons = new JPanel();

		buttons.add(delete);

		buttons.add(refresh);

		buttons.add(export);

		add(new JScrollPane(table), BorderLayout.CENTER);

		add(buttons, BorderLayout.SOUTH);

		delete.addActionListener(e -> deletePlayer());

		refresh.addActionListener(e -> loadPlayers());

		export.addActionListener(e -> exportPDF());

	}

	private void loadPlayers() {

		model.setRowCount(0);

		ArrayList<Player> players = controller.getAllPlayers();

		for (Player p : players) {

			model.addRow(new Object[] {

					p.getId(),

					p.getName(),

					p.getEmail()

			});
		}

	}

	private void deletePlayer() {

		int row = table.getSelectedRow();

		if (row == -1) {

			JOptionPane.showMessageDialog(this, "Select player first");

			return;
		}

		int id = Integer.parseInt(model.getValueAt(row, 0).toString());

		controller.deletePlayer(id);

		loadPlayers();

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
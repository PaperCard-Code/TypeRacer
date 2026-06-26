package view;

import controller.AdminController;

import model.Admin;

import util.PDFGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class AdminManagementView extends JFrame {

	private JTable table;

	private DefaultTableModel model;

	private AdminController controller;

	public AdminManagementView() {

		controller = new AdminController();

		setTitle("Admin Management");

		setSize(700, 400);

		setLocationRelativeTo(null);

		createGUI();

		loadAdmins();

		setVisible(true);

	}

	private void createGUI() {

		model = new DefaultTableModel();

		model.addColumn("ID");

		model.addColumn("Name");

		model.addColumn("Email");

		table = new JTable(model);

		JButton add = new JButton("Add");

		JButton delete = new JButton("Delete");

		JButton refresh = new JButton("Refresh");

		JButton export = new JButton("Export PDF");

		JPanel panel = new JPanel();

		panel.add(add);

		panel.add(delete);

		panel.add(refresh);

		panel.add(export);

		add(new JScrollPane(table), BorderLayout.CENTER);

		add(panel, BorderLayout.SOUTH);

		add.addActionListener(e -> addAdmin());

		delete.addActionListener(e -> deleteAdmin());

		refresh.addActionListener(e -> loadAdmins());

		export.addActionListener(e -> exportPDF());

	}

	private void loadAdmins() {

		model.setRowCount(0);

		ArrayList<Admin> admins = controller.getAllAdmins();

		for (Admin a : admins) {

			model.addRow(new Object[] {

					a.getId(),

					a.getName(),

					a.getEmail()

			});
		}

	}

	private void addAdmin() {

		String name = JOptionPane.showInputDialog("Name");

		String email = JOptionPane.showInputDialog("Email");

		String password = JOptionPane.showInputDialog("Password");

		controller.addAdmin(name, email, password);

		loadAdmins();

	}

	private void deleteAdmin() {

		int row = table.getSelectedRow();

		if (row == -1)
			return;

		int id = Integer.parseInt(model.getValueAt(row, 0).toString());

		controller.deleteAdmin(id);

		loadAdmins();

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
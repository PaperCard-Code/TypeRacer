package view;

import controller.WordCategoryController;

import model.WordCategory;

import util.PDFGenerator;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.util.ArrayList;

public class WordCategoryManagementView extends JFrame {

	private JTable table;

	private DefaultTableModel model;

	private WordCategoryController controller;

	public WordCategoryManagementView() {

		controller = new WordCategoryController();

		setTitle("Word Category Management");

		setSize(700, 400);

		setLocationRelativeTo(null);

		setup();

		loadCategories();

		setVisible(true);

	}

	private void setup() {

		model = new DefaultTableModel();

		model.addColumn("Category");

		model.addColumn("Rarity");

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

		add.addActionListener(e -> addCategory());

		delete.addActionListener(e -> deleteCategory());

		refresh.addActionListener(e -> loadCategories());

		export.addActionListener(e -> exportPDF());

	}

	private void loadCategories() {

		model.setRowCount(0);

		ArrayList<WordCategory> list = controller.getCategories();

		for (WordCategory wc : list) {

			model.addRow(new Object[] {

					wc.getWordCategory(),

					wc.getRarity()

			});
		}

	}

	private void addCategory() {

		String category = JOptionPane.showInputDialog("Category");

		double rarity = Double.parseDouble(JOptionPane.showInputDialog("Rarity"));

		controller.addCategory(category, rarity);

		loadCategories();

	}

	private void deleteCategory() {

		int row = table.getSelectedRow();

		if (row == -1)
			return;

		String category = model.getValueAt(row, 0).toString();

		controller.deleteCategory(category);

		loadCategories();

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
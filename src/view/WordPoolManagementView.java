package view;

import controller.WordPoolController;

import model.WordPool;

import util.PDFGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.util.ArrayList;

public class WordPoolManagementView extends JFrame {

	private JTable table;

	private DefaultTableModel model;

	private WordPoolController controller;

	public WordPoolManagementView() {

		controller = new WordPoolController();

		setTitle("Word Pool Management");

		setSize(700, 400);

		setLocationRelativeTo(null);

		setup();

		loadWords();

		setVisible(true);

	}

	private void setup() {

		model = new DefaultTableModel();

		model.addColumn("Word");

		model.addColumn("Category");

		table = new JTable(model);

		JButton add = new JButton("Add Word");

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

		add.addActionListener(e -> addWord());

		delete.addActionListener(e -> deleteWord());

		refresh.addActionListener(e -> loadWords());

		export.addActionListener(e -> exportPDF());

	}

	private void loadWords() {

		model.setRowCount(0);

		ArrayList<WordPool> words = controller.getWords();

		for (WordPool w : words) {

			model.addRow(new Object[] {

					w.getWord(),

					w.getCategory()

			});
		}

	}

	private void addWord() {

		String word = JOptionPane.showInputDialog("Word");

		String category = JOptionPane.showInputDialog("Category");

		controller.addWord(word, category);

		loadWords();

	}

	private void deleteWord() {

		int row = table.getSelectedRow();

		if (row == -1)
			return;

		String word = model.getValueAt(row, 0).toString();

		controller.deleteWord(word);

		loadWords();

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
package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.JTable;

import java.io.FileOutputStream;

public class PDFGenerator {

	public static void exportTable(JTable table, String fileName) {

		try {

			Document document = new Document();

			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();

			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

			for (int i = 0; i < table.getColumnCount(); i++) {

				pdfTable.addCell(table.getColumnName(i));
			}

			for (int row = 0; row < table.getRowCount(); row++) {

				for (int col = 0; col < table.getColumnCount(); col++) {

					Object value = table.getValueAt(row, col);

					pdfTable.addCell(String.valueOf(value));
				}
			}

			document.add(pdfTable);

			document.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
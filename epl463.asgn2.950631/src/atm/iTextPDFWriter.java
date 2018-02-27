/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Class to create the PDF file for statement
 * 
 * @author EPL463
 */
public class iTextPDFWriter {

	/**
	 * Constructor Gets the string and splits it to statement information. Then it
	 * adds the different parts of statement to the PDF file.
	 * 
	 * @param input
	 */
	@SuppressWarnings("unused")
	public iTextPDFWriter(String inputs) {
		String input[]=inputs.split("&&");
		String titleStatement=input[0];
		String tokens[] = input[1].split("\n");
		int length = tokens.length;
		String AccountNumber = null, Name = null, Surname = null, AccountType = null, Balance = null;
		ArrayList<String> TransactionHistory = null;

		if (length == 1)
			Balance = tokens[0];
		else if (length >= 5) {
			AccountNumber = tokens[0];
			Name = tokens[1];
			Surname = tokens[2];
			AccountType = tokens[3];
			Balance = tokens[4];
		}
		if (length > 5) {
			TransactionHistory = new ArrayList<String>();
			for (int i = 0; i < length - 6; i++) {
				TransactionHistory.add(tokens[6 + i]);
			}
		}

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {

			
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(titleStatement+".pdf"));
			document.open();
			Paragraph title = new Paragraph(titleStatement,
					FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD, new BaseColor(0, 153, 51)));
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(40);
			document.add(title);
			Paragraph Balancetext = new Paragraph(Balance,
					FontFactory.getFont(FontFactory.COURIER_BOLD, 14, Font.BOLDITALIC + Font.UNDERLINE));
			if (length == 1) {
				document.add(Balancetext);
				document.close();
				return;
			}

			document.add(new Paragraph(AccountNumber, FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Font.BOLD)));

			document.add(new Paragraph(Name + "         " + Surname, FontFactory.getFont(FontFactory.COURIER, 12)));
			document.add(new Paragraph(AccountType,
					FontFactory.getFont(FontFactory.COURIER, 12, new BaseColor(255, 153, 0))));
			document.add(Balancetext);

			if (length > 5) {
				PdfPTable t = new PdfPTable(4);

				t.setSpacingBefore(10);
				t.setSpacingAfter(25);
				PdfPCell c1 = new PdfPCell(
						new Phrase("Time", FontFactory.getFont(FontFactory.COURIER_BOLD, 13, Font.BOLD)));
				t.addCell(c1);
				PdfPCell c2 = new PdfPCell(
						new Phrase("Date", FontFactory.getFont(FontFactory.COURIER_BOLD, 13, Font.BOLD)));
				t.addCell(c2);
				PdfPCell c3 = new PdfPCell(
						new Phrase("Amount", FontFactory.getFont(FontFactory.COURIER_BOLD, 13, Font.BOLD)));
				t.addCell(c3);
				PdfPCell c4 = new PdfPCell(
						new Phrase("Used from", FontFactory.getFont(FontFactory.COURIER_BOLD, 13, Font.BOLD)));
				t.addCell(c4);

				for (String s : TransactionHistory) {
					String tk[] = s.split(" - ");

					t.addCell(tk[0]);
					t.addCell(tk[1]);
					t.addCell(tk[2]);
					t.addCell(tk[3]);
				}
				Paragraph hist = new Paragraph("Transaction History");
				hist.setAlignment(Element.ALIGN_CENTER);
				document.add(hist);
				document.add(t);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("File Statement.pdf already in use");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Problem with document");
		} finally {
			document.close();
		}

	}
}

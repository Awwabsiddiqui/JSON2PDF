package com.example.springrest.method;

import java.io.FileNotFoundException;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

// Adding table in a pdf using java
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class json2pdfTable {

	public static String json2pdf(String json , String fileName) {
		String file = fileName+".pdf";
		try {
		JSONObject job = new JSONObject(json);
		PdfDocument pdfDoc;

			pdfDoc = new PdfDocument(new PdfWriter(file));

			Document doc = new Document(pdfDoc);

			Table table = new Table(2);

			String sellerData = "\n Seller : \n" + job.get("seller").toString() + "\n" + job.get("sellerAddress") + "\n"
					+ job.get("sellerGstin") + "\n\n";
			Cell cellseller = new Cell();
			Paragraph pseller = new Paragraph(sellerData);
			pseller.setTextAlignment(TextAlignment.CENTER);
			cellseller.add(pseller);
			table.addCell(cellseller);

			String buyerData = "\n Buyer : \n" + job.get("buyer").toString() + "\n" + job.get("buyerAddress") + "\n"
					+ job.get("buyerGstin") + "\n\n";
			Cell cellbuyer = new Cell();
			Paragraph pbuyer = new Paragraph(buyerData);
			pbuyer.setTextAlignment(TextAlignment.CENTER);
			cellbuyer.add(pbuyer);
			table.addCell(cellbuyer);

			doc.add(table);

			// ITEMS :::
			JSONArray jar = new JSONArray(job.get("items").toString());

			Table tableItem = new Table(4);

			Cell cellitem = new Cell();
			Paragraph pitem = new Paragraph("Item");
			pitem.setTextAlignment(TextAlignment.CENTER);
			cellitem.add(pitem);
			tableItem.addCell(cellitem);
			cellitem = new Cell();
			pitem = new Paragraph("Quantity");
			pitem.setTextAlignment(TextAlignment.CENTER);
			cellitem.add(pitem);
			tableItem.addCell(cellitem);
			cellitem = new Cell();
			pitem = new Paragraph("Rate");
			pitem.setTextAlignment(TextAlignment.CENTER);
			cellitem.add(pitem);
			tableItem.addCell(cellitem);
			cellitem = new Cell();
			pitem = new Paragraph("Amount");
			pitem.setTextAlignment(TextAlignment.CENTER);
			cellitem.add(pitem);
			tableItem.addCell(cellitem);
			
			String[] arr = { "name", "quantity", "rate", "amount" };
			for (int i = 0; i < jar.length(); i++) {
				JSONObject jobInner = new JSONObject(jar.get(i).toString());

				for (String str : arr) {
					cellitem = new Cell();
					pitem = new Paragraph(jobInner.get(str).toString());
					pitem.setTextAlignment(TextAlignment.CENTER);
					cellitem.add(pitem);
					tableItem.addCell(cellitem);
				}

			}

			doc.add(tableItem);

			doc.close();
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
		return file;
	}

//	public static void main(String args[]) throws Exception {
//		String json = "{\r\n" + "  \"seller\": \"XYZ Pvt. Ltd.\",\r\n" + "  \"sellerGstin\": \"29AABBCCDD121ZD\",\r\n"
//				+ "  \"sellerAddress\": \"New Delhi, India\",\r\n" + "  \"buyer\": \"Vedant Computers\",\r\n"
//				+ "  \"buyerGstin\": \"29AABBCCDD131ZD\",\r\n" + "  \"buyerAddress\": \"New Delhi, India\",\r\n"
//				+ "  \"items\": [\r\n" + "    {\r\n" + "      \"name\": \"Product 1\",\r\n"
//				+ "      \"quantity\": \"12 Nos\",\r\n" + "      \"rate\": 123,\r\n" + "      \"amount\": 1476\r\n"
//				+ "    }\r\n" + "  ]\r\n" + "}";
//
//		{
//			  "seller": "XYZ Pvt. Ltd.",
//			  "sellerGstin": "29AABBCCDD121ZD",
//			  "sellerAddress": "New Delhi, India",
//			  "buyer": "Vedant Computers",
//			  "buyerGstin": "29AABBCCDD131ZD",
//			  "buyerAddress": "New Delhi, India",
//			  "items": [
//			    {
//			      "name": "Product 1",
//			      "quantity": "12 Nos",
//			      "rate": 123,
//			      "amount": 1476
//			    }
//			  ]
//			}
//
//		json2pdf(json);
//	}
}

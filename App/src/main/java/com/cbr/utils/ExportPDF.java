package com.cbr.utils;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import com.cbr.App;
import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;
import com.cbr.models.InventoryProduct;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.StyleConstants;

public class ExportPDF
{
//    public static void main(String[] args) {
//        try {
//            List<FixedInvoice> invoices = App.getDataStore().getInvoices().getDataList();
//            ExportPDF("C:\\Users\\livia\\example.pdf", invoices.get(0));
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
    public ExportPDF(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            System.out.println(filePath);
        }
    }

    public static void exportPDF(String dest, FixedInvoice invoice) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.LETTER);
        doc.setMargins(24, 24, 24, 24);

        Table table = new Table(4).useAllAvailableWidth().setBorder(Border.NO_BORDER);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        // Title
        Paragraph title = new Paragraph("Invoice " + invoice.getId()).setFont(bold);
        doc.add(title);

        Paragraph customer = new Paragraph("Customer: " + invoice.getCustomerId());
        doc.add(customer);

        // List of product
        List<BoughtProduct> productList = invoice.getBoughtProducts();
        boolean isEvenRow = false;
        for (BoughtProduct product : productList) {
            Cell cell1 = new Cell().add(new Paragraph(product.getCount().toString())).setBorder(Border.NO_BORDER);
            Cell cell2 = new Cell().add(new Paragraph(product.getProductName())).setBorder(Border.NO_BORDER);
            Cell cell3 = new Cell().add(new Paragraph("@" +product.getSellPrice().getValue().toString())).setBorder(Border.NO_BORDER);
            Double totalPriceProduct = product.getSellPrice().getValue() * product.getCount();
            Cell cell4 = new Cell().add(new Paragraph(totalPriceProduct.toString())).setBorder(Border.NO_BORDER);
            if (isEvenRow) {
                cell1.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                cell2.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                cell3.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                cell4.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            }
            else  {
                cell1.setBackgroundColor(ColorConstants.WHITE);
                cell2.setBackgroundColor(ColorConstants.WHITE);
                cell3.setBackgroundColor(ColorConstants.WHITE);
                cell4.setBackgroundColor(ColorConstants.WHITE);
            }
            isEvenRow = !isEvenRow;
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
        }

        // Add discount and points used
        Cell discountLabel = new Cell(1, 3).add(new Paragraph("Discount")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(discountLabel);
        Cell discount = new Cell().add(new Paragraph(invoice.getDiscount().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setHorizontalAlignment(HorizontalAlignment.RIGHT);
        table.addCell(discount);
        Cell pointLabel = new Cell(1, 3).add(new Paragraph("Points Used")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(pointLabel);
        Cell points = new Cell().add(new Paragraph(invoice.getUsedPoint().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setHorizontalAlignment(HorizontalAlignment.RIGHT);
        table.addCell(points);

        // Add aditional costs
        Map<String, String> additionalCosts = invoice.getAdditionalCosts();
        for (Map.Entry<String, String> entry : additionalCosts.entrySet()) {;
            Cell key = new Cell(1, 3).add(new Paragraph(entry.getKey())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(key);
            Cell value = new Cell().add(new Paragraph(entry.getValue())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT);
            table.addCell(value);
        }

        // Grand total and get points
        Cell totalLabel = new Cell(1, 3).add(new Paragraph("Grand Total")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(bold);
        table.addCell(totalLabel);
        Cell total = new Cell().add(new Paragraph(invoice.getGrandTotal().getValue().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT).setFont(bold);
        table.addCell(total);
        Cell getPointLabel = new Cell(1, 3).add(new Paragraph("Grand Total")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(bold);
        table.addCell(getPointLabel);
        Cell getPoint = new Cell().add(new Paragraph(invoice.getGetPoint().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT).setFont(bold);
        table.addCell(getPoint);

        doc.add(table);
        doc.close();
    }

}

package com.cbr.utils;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import com.cbr.App;
import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class ExportPDF
{
    private String dest;
    public void init(Button buttonExport, FixedInvoice invoice) {
        // create a new FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Invoice");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // display the dialog and wait for user input
        File selectedFile = fileChooser.showSaveDialog(buttonExport.getScene().getWindow());

        // if user has chosen a file, get the file path
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            dest = filePath;
            try {
                ExportPDF.exportPDF(dest, invoice);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.setOnCloseRequest(alertEvent -> {
                    alert.close();
                });
                alert.show();
            }
        }
    }

    public void init(MenuItem menuExport) {
        // create a new FileChooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Statement");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // display the dialog and wait for user input
        File selectedFile = fileChooser.showSaveDialog(menuExport.getParentPopup().getScene().getWindow());

        // if user has chosen a file, get the file path
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            this.dest = filePath;
            try {
                List<FixedInvoice> invoices = App.getDataStore().getInvoices().getDataList();
                ExportPDF.exportPDF(dest, invoices);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.setOnCloseRequest(alertEvent -> {
                    alert.close();
                });
                alert.show();
            }
        }
    }

    public static void exportInvoice(FixedInvoice invoice, Table table) throws Exception {
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        // List of product
        List<BoughtProduct> productList = invoice.getBoughtProducts();
        boolean isEvenRow = false;
        for (BoughtProduct product : productList) {
            Cell cell1 = new Cell().add(new Paragraph(product.getCount().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
            Cell cell2 = new Cell().add(new Paragraph(product.getProductName())).setBorder(Border.NO_BORDER);
            Cell cell3 = new Cell().add(new Paragraph("@" +product.getSellPrice().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
            Cell cell4 = new Cell().add(new Paragraph(product.total().toString())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
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

        // Add aditional costs
        Map<String, String> additionalCosts = invoice.getAdditionalCosts();
        for (Map.Entry<String, String> entry : additionalCosts.entrySet()) {;
            Cell key = new Cell(1, 3).add(new Paragraph(entry.getKey())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(key);
            Cell value = new Cell().add(new Paragraph(entry.getValue())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT);
            table.addCell(value);
        }

        // Add discount and points used
        Cell discountLabel = new Cell(1, 3).add(new Paragraph("Discount")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(discountLabel);
        Cell discount = new Cell().add(new Paragraph(invoice.getDiscount().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT);
        table.addCell(discount);
        Cell pointLabel = new Cell(1, 3).add(new Paragraph("Points Used")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(pointLabel);
        Cell points = new Cell().add(new Paragraph(invoice.getUsedPoint().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT);
        table.addCell(points);


        // Grand total and get points
        Cell totalLabel = new Cell(1, 3).add(new Paragraph("Grand Total")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(bold);
        table.addCell(totalLabel);
        Cell total = new Cell().add(new Paragraph(invoice.getGrandTotal().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT).setFont(bold);
        table.addCell(total);
        Cell getPointLabel = new Cell(1, 3).add(new Paragraph("Points")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(bold);
        table.addCell(getPointLabel);
        Cell getPoint = new Cell().add(new Paragraph(invoice.getGetPoint().toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.LIGHT_GRAY).setTextAlignment(TextAlignment.RIGHT).setFont(bold);
        table.addCell(getPoint);
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

        ExportPDF.exportInvoice(invoice, table);

        doc.add(table);
        doc.close();
    }


    public static void exportPDF(String dest, List<FixedInvoice> invoices) throws Exception{
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.LETTER);
        doc.setMargins(24, 24, 24, 24);

        Table table = new Table(5).useAllAvailableWidth().setBorder(Border.NO_BORDER);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        // Title
        Paragraph title = new Paragraph("Financial Statement").setFont(bold).setTextAlignment(TextAlignment.CENTER);
        doc.add(title);

        if (invoices.size() > 0) {
            Map<LocalDate, List<FixedInvoice>> invoiceMap = new HashMap<>();
            for (FixedInvoice invoice : invoices) {
                LocalDate date = invoice.getCreatedAt().toLocalDate();
                if (invoiceMap.containsKey(date)) {
                    invoiceMap.get(date).add(invoice);
                } else {
                    List<FixedInvoice> invoiceList = new ArrayList<>();
                    invoiceList.add(invoice);
                    invoiceMap.put(date, invoiceList);
                }
            }

            Map<LocalDate, List<FixedInvoice>> sortedInvoiceMap = new TreeMap<>(new Comparator<LocalDate>() {
                @Override
                public int compare(LocalDate d1, LocalDate d2) {
                    return d1.compareTo(d2);
                }
            });
            sortedInvoiceMap.putAll(invoiceMap);

            Map<LocalDate, Integer> rowSpan = new HashMap<>();
            for (List<FixedInvoice> invoiceList : sortedInvoiceMap.values()) {
                LocalDate date = invoiceList.get(0).getCreatedAt().toLocalDate();
                int row = 0;
                for (FixedInvoice invoice : invoiceList) {
                    row += 5;
                    row += invoice.getAdditionalCosts().size();
                    row += invoice.getBoughtProducts().size();
                }
                rowSpan.put(date, row + 2);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            // Iterate date
            for (Map.Entry<LocalDate, List<FixedInvoice>> entry : sortedInvoiceMap.entrySet()) {
                LocalDate date = entry.getKey();
                List<FixedInvoice> invoicesPerDate = entry.getValue();

                Cell dateLabel = new Cell(rowSpan.get(date), 1).add(new Paragraph(date.format(formatter))).setFont(bold).setBorder(Border.NO_BORDER);
                table.addCell(dateLabel);
                Double income = 0.0;
                Double revenue = 0.0;

                for (FixedInvoice invoice : invoicesPerDate) {
                    Cell totalLabel = new Cell(1, 4).add(new Paragraph("Transaction " + invoice.getId())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE).setFont(bold).setFontColor(ColorConstants.WHITE);
                    table.addCell(totalLabel);
                    income += invoice.getGrandTotal().getValue();
                    revenue += invoice.revenue();
                    ExportPDF.exportInvoice(invoice, table);
                }

                // Total income and revenue
                Cell totalLabel = new Cell(1, 3).add(new Paragraph("Total Income")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE).setFont(bold).setFontColor(ColorConstants.WHITE);
                table.addCell(totalLabel);
                Cell total = new Cell().add(new Paragraph(income.toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE).setFont(bold).setFontColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.RIGHT);
                table.addCell(total);
                Cell getPointLabel = new Cell(1, 3).add(new Paragraph("Total Revenue")).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE).setFont(bold).setFontColor(ColorConstants.WHITE);
                table.addCell(getPointLabel);
                Cell getPoint = new Cell().add(new Paragraph(revenue.toString())).setBorder(Border.NO_BORDER).setBackgroundColor(ColorConstants.BLUE).setFont(bold).setFontColor(ColorConstants.WHITE).setTextAlignment(TextAlignment.RIGHT);
                table.addCell(getPoint);
            }
            doc.add(table);
        }
        else {
            Paragraph noTransaction = new Paragraph("You don't have any transaction yet.").setTextAlignment(TextAlignment.CENTER);
            doc.add(noTransaction);
        }

        doc.close();
    }
}

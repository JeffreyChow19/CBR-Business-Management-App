package com.cbr.utils;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.cbr.App;
import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;
import com.cbr.models.InventoryProduct;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.properties.HorizontalAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
        doc.setMargins(10, 10, 10, 10);

        Table table = new Table(4);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);

        List<BoughtProduct> productList = invoice.getBoughtProducts();
        for (BoughtProduct product : productList) {
            Cell cell1 = new Cell().add(new Paragraph(product.getCount().toString()).setFontColor(new DeviceRgb(0, 0, 0)));
            cell1.setBorder(new SolidBorder(new DeviceRgb(0, 0, 0), 1));
            table.addCell(cell1);
            Cell cell2 = new Cell().add(new Paragraph(product.getProductName()).setFontColor(new DeviceRgb(0, 0, 0)));
            cell2.setBorder(new SolidBorder(new DeviceRgb(0, 0, 0), 1));
            table.addCell(cell2);
            Cell cell3 = new Cell().add(new Paragraph("@" +product.getSellPrice().getValue().toString()).setFontColor(new DeviceRgb(0, 0, 0)));
            cell2.setBorder(new SolidBorder(new DeviceRgb(0, 0, 0), 1));
            table.addCell(cell3);
            Double totalPriceProduct = product.getSellPrice().getValue() * product.getCount();
            Cell cell4 = new Cell().add(new Paragraph(totalPriceProduct.toString()).setFontColor(new DeviceRgb(0, 0, 0)));
            cell2.setBorder(new SolidBorder(new DeviceRgb(0, 0, 0), 1));
            table.addCell(cell4);
        }

        doc.add(table);
        doc.add(new AreaBreak());

        doc.close();

    }
}

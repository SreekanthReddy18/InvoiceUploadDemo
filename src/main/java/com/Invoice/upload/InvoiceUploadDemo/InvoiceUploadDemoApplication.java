package com.Invoice.upload.InvoiceUploadDemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.xml.ws.RespectBinding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InvoiceUploadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceUploadDemoApplication.class, args);
	}

	// @RequestMapping(value = "/create/invoice", method = RequestMethod.POST)
	public static ResponseEntity<String> createInvoice(Invoice invoice) throws IOException {
		System.out.println("Entered: " + invoice.toString());
		BufferedWriter out = null;
		String fileSeparator = System.getProperty("file.separator");

		// absolute file name with path
		String absoluteFilePath = "F:" + fileSeparator + "MyInvoiceFile" + System.currentTimeMillis() + ".xml";
		File file = null;

		try {
			file = new File(absoluteFilePath);
			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println(absoluteFilePath + " File Created");
				}
			}
			FileWriter fstream = new FileWriter(file, true);
			out = new BufferedWriter(fstream);
			out.write(invoice.toString());
			out.newLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception ex) {

			}
		}

		// return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(value = "/get/invoice/{invoicenumber}/{agent}", method = RequestMethod.GET)
	public ResponseEntity<String> getInvoice(@PathVariable String invoicenumber, @PathVariable String agent)
			throws IOException {
		return createInvoice(new Invoice(invoicenumber, agent));
	}

}

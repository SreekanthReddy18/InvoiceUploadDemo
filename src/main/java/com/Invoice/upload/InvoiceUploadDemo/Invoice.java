package com.Invoice.upload.InvoiceUploadDemo;

import java.io.Serializable;

public class Invoice implements Serializable {
	private String invoiceNumber;
	  private String agent;
	  
  public Invoice(String invoiceNumber, String agent) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.agent = agent;
	}
public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	@Override
	public String toString() {
		return invoiceNumber +","+ agent;
	}

  
}

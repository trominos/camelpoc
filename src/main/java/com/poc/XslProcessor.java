package com.poc;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class XslProcessor implements Processor {
	
	String xslFile;
	
	public XslProcessor(String xsl) {
	    this.xslFile = xsl;	
	}

    public void process(Exchange exchange) throws Exception {

      System.out.println("DO XSL transformation here with >>>>>" + this.xslFile);	
 
          
    }
    
    
	
}

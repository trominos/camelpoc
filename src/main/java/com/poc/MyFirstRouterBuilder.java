package com.poc;

import org.apache.camel.builder.RouteBuilder;

public class MyFirstRouterBuilder extends RouteBuilder {
	
   private String input;
   private String output;
   private String xsl;
	
    @Override
   public void configure() throws Exception {
       try{
           from( this.input).process(new XslProcessor(this.xsl)).to(this.output);
       }catch(Exception e){

       }
    }
    
    public MyFirstRouterBuilder(String input,String output,String xsl) {
    		this.input =   input;
    		this.output = output;
    		this.xsl = xsl;
    }

}
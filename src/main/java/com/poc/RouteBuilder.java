package com.poc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;

public class RouteBuilder  implements Processor {
	

    public void process(Exchange exchange) throws Exception {

    	    System.out.println("----------------");
    	    System.out.println("-------Route builder is called---------");    	    
        String routeString = exchange.getIn().getBody(String.class);
        System.out.println(routeString);
        String from = "";
        String to = "";
        String xsl =  "";
        
        if(routeString.length() < 10) {
        	   return;
        }else {
        	   StringTokenizer st = new StringTokenizer(routeString,"=");
        	   String base = st.nextToken();
           from = base + "input";
           xsl = st.nextToken();
           to = base + "output";
           System.out.println("-------------------------------------");
           System.out.println(from.trim());
           System.out.println(xsl.trim());
           System.out.println(to.trim());
           System.out.println("-------------------------------------");
        	
        }
  /*      
        String from = "file:/Users/tojojose/projects/camlpoc-data/route-one/input";
        String to = "file://Users/tojojose/projects/camlpoc-data/route-one/output";
        String xsl = "test.xsl";
  */

        boolean duplicateRoute = false;      
        
   
        Iterator<Endpoint> iterator = exchange.getContext().getEndpoints().iterator();
        while(iterator.hasNext()) {
        	  Endpoint endPoint = iterator.next(); 
          String er[] = endPoint.getEndpointBaseUri().split(":///");
          if(er.length == 2) {
        	    String allreadyExistingEndPoint = endPoint.getEndpointBaseUri().split(":///")[1];
        	    String newEndPoint = from.split(":/")[1];
        	    if(allreadyExistingEndPoint.equalsIgnoreCase(newEndPoint)) {
        	      	duplicateRoute = true ;
        	      	System.out.println("Duplicagte route -" + from);
        	    }
          }
        }

        if(!duplicateRoute) {
            exchange.getContext().addRoutes(new MyFirstRouterBuilder(from,to,xsl));
        }
        
              
    }

    public void configure() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public RouteBuilder() {
    }

}

package com.mycompany.app;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.*;

import java.util.List;

public class SignGuestbookServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

  String entry = req.getParameter("entry");   
  
      try {
             MemcacheService _service = MemcacheServiceFactory.getMemcacheService();
             List entries = (List)_service.get("entries");
             if (entries == null) {
	             entries = new java.util.ArrayList<String>();
			 }
	         entries.add(entry);
             _service.put("entries",entries);
         }
         catch (Exception e) { 
            System.out.println(e.getMessage());
         }
      resp.sendRedirect("index.jsp");
}
}

package by.epam.lobanok.controller.jsptag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTag extends SimpleTagSupport{
	
	public void doStartTag() throws JspException, IOException {
       JspWriter writer = getJspContext().getOut();
       writer.println("Республика Беларусь, г.Минск");
       writer.println("Октябрьская 10");
    }
} 
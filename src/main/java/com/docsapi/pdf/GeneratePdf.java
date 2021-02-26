package com.docsapi.pdf;
import com.docsapi.dao.DataAccessObjectDAO;
import com.docsapi.entities.ColumnDetails;
import com.docsapi.entities.SavedData;
import com.docsapi.repository.ColumnDetailsRepository;
import com.docsapi.repository.SavedDataRepository;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
public class GeneratePdf {
	
	@Autowired
	SavedDataRepository dataRepository;
	@Autowired
	ColumnDetailsRepository columnDetailsRepository;
	
	public static final String FONT = "static/FreeSans.ttf";
	public void creation(String id,String doc_name,List<ColumnDetails> clist,List<SavedData> slist) throws IOException
	{
		final String DEST = "./src/main/resources/static/"+doc_name+id+".pdf";
		
		DataAccessObjectDAO dao=new DataAccessObjectDAO();
		long doc_id=Long.parseLong(id);
		
		 	File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        
	        new GeneratePdf().pdfCreateion(DEST,clist,slist,doc_name);
		
	}
	
	public void pdfCreateion(String dest,List<ColumnDetails> clist,List<SavedData> slist,String doc_name) throws IOException
	{
		        //Initialize PDF writer
		        PdfWriter writer = new PdfWriter(dest);
		        PdfFont f = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
		        //Initialize PDF document
		        PdfDocument pdf = new PdfDocument(writer);
		        
		        // Initialize document
		        Document document = new Document(pdf);
		        Rectangle rect = new Rectangle(0, 0);       
		        PdfLinkAnnotation annotation = new PdfLinkAnnotation(rect);        

		        ImageData imageData = ImageDataFactory.create("src/main/resources/static/images/capture.png");
		     // Create layout image object and provide parameters. Page number = 1
		     Image image = new Image(imageData).scaleAbsolute(280,60).setFixedPosition(170,755);
		     // This adds the image to the page
		     document.add(image);
		     
		      PdfAction action = PdfAction.createURI("https://play.google.com/store/apps/details?id=ds.docusheet.table");       
		      annotation.setAction(action); 
		      Link link = new Link("Download App", annotation);        
		      Paragraph paragraph = new Paragraph();              
		      paragraph.add((link)).setFixedPosition(20, 730, 100);                    
		      document.add(paragraph);   
		      
		     imageData = ImageDataFactory.create("src/main/resources/static/images/googleplay.png");
		     image = new Image(imageData).scaleAbsolute(60,20).setFixedPosition(100,730);
		     document.add(image);
		     document.add(new Paragraph("All registers in one app").setFixedPosition(450, 730, 150));
		     
		     document.add(new Paragraph(doc_name).setFixedPosition(250, 675, 300).setFontSize(20).setFont(f));
		     
		     Table table2=new Table(1);
		     table2.addCell("").setBackgroundColor(ColorConstants.BLUE).setWidth(800).setHeight(1).setFixedPosition(0, 710, 800);
		     document.add(table2);
		     
		        Table table=new Table(clist.size());
		        table.setRelativePosition(0,150,0,0);
		        table.setFont(f);
		        table.setWidth(520);
		        table.setFontSize(11);
		        DeviceRgb lineColor = new DeviceRgb(204,240,229);
		        DeviceRgb border = new DeviceRgb(203,159,159);
		       
		        //creating only column name
		        for (int i = 0; i < clist.size(); i++) {
		        	Cell cell=new Cell();
		        	cell.add(new Paragraph(clist.get(i).getColumn_names()));
		     cell.setBorder(new SolidBorder(border,0.8f)).setBackgroundColor(lineColor).setTextAlignment(TextAlignment.CENTER);
		     table.addCell(cell);   
		        }
		        
		        //for creating every rows with all column data
		        for(int i=0;i<slist.size();i++)
	            {
	                if(!(slist.get(i).getColumn1()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn1()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);   
	                }
	                if(!(slist.get(i).getColumn2()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn2()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn3()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn3()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn4()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn4()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn5()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn5()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn6()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn6()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn7()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn7()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn8()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn8()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn9()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn9()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn10()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn10()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn11()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn11()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn12()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn12()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn13()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn13()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn14()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn14()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn15()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn15()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn16()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn16()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }if(!(slist.get(i).getColumn17()==null))
	            {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn17()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	            }
	                if(!(slist.get(i).getColumn18()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn18()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn19()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn19()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	                if(!(slist.get(i).getColumn20()==null))
	                {
	                	Cell cell=new Cell();
			        	cell.add(new Paragraph(slist.get(i).getColumn20()));
			     cell.setBorder(new SolidBorder(border,0.8f)).setTextAlignment(TextAlignment.CENTER);
			     table.addCell(cell);
	                }
	            }
		        
		        //to print totals
		        for (int i = 0; i < clist.size(); i++) {
		        	Cell cell=new Cell();
		        	if(clist.get(i).getTotal().equals("0")) {
		        	cell.add(new Paragraph(""));
		        	}
		        	else
		        	cell.add(new Paragraph(clist.get(i).getTotal()));
		        		
		     cell.setBorder(new SolidBorder(border,0.8f)).setBackgroundColor(lineColor).setTextAlignment(TextAlignment.CENTER);
		     table.addCell(cell);   
		        } 

		        document.add(table.setFont(f));
		        
		        //Close document
		        document.close();
		    }
	}


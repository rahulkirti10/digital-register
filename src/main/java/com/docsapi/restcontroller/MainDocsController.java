package com.docsapi.restcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docsapi.dao.DataAccessObjectDAO;
import com.docsapi.entities.AddMoreThanOneColumn;
import com.docsapi.entities.AllDataList;
import com.docsapi.entities.CalculationDetails;
import com.docsapi.entities.ChangeColumnData;
import com.docsapi.entities.ColumnDetails;
import com.docsapi.entities.ColumnFormula;
import com.docsapi.entities.Column_Id;
import com.docsapi.entities.DeleteColumn;
import com.docsapi.entities.Doc_Id;
import com.docsapi.entities.Document;
import com.docsapi.entities.GetAllDocument;
import com.docsapi.entities.GetDataByColumn;
import com.docsapi.entities.GetDocsData;
import com.docsapi.entities.Referrals;
import com.docsapi.entities.RowData;
import com.docsapi.entities.Row_Id;
import com.docsapi.entities.SavedData;
import com.docsapi.entities.SingleDataUpdate;
import com.docsapi.entities.UserAddressDetails;
import com.docsapi.entities.UserDetails;
import com.docsapi.pdf.GeneratePdf;

@RestController
public class MainDocsController {

	@Autowired
	private DataAccessObjectDAO dao;
	
	//testing url
	@GetMapping("/")
	public String home()
	{
		return "working";
	}

	
	
	//to download the pdf file
	@GetMapping("/create-pdf/{doc_id}")
	public ResponseEntity<Object>  createpdf(@PathVariable("doc_id") String doc_id) throws IOException
	{
		
		String doc_name=dao.getDocName(Long.parseLong(doc_id));
		
		dao.GeneratePdf(doc_id,doc_name);
		final String path = "src/main/resources/static/"+doc_name+doc_id+".pdf";
		final String DEST = "./src/main/resources/static/"+doc_name+doc_id+".pdf";
		try {
			File file=new File(path);
			InputStreamResource resource=new InputStreamResource(new FileInputStream(file)); 
			HttpHeaders headers=new HttpHeaders();
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
			File del=new File(DEST);
			Boolean b=file.delete();
			System.out.println(b);
			return responseEntity;
			} catch (Exception e ) {
				return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
			} 
		
	}
	
	
	//for get all Documents of user
	@GetMapping("/get-all-document/{user_id}")
	public ResponseEntity<GetAllDocument> getAllDocumet(@PathVariable String user_id)
	{
		List<Document> list=dao.getAllDocument(user_id);
		GetAllDocument allDocument=new GetAllDocument();
		allDocument.setDocument(list);
		if(list.size()<=0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
		return ResponseEntity.of(Optional.of(allDocument));
		
	}
	@GetMapping("/get-user/{user_id}")
	public ResponseEntity<UserDetails> getUser(@PathVariable String user_id)
	{
		try {
			UserDetails userDetails=dao.getUser(user_id);
			return ResponseEntity.of(Optional.of(userDetails));
			
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//for save a new Document
	@PostMapping("/save-document-create")
	public ResponseEntity<Doc_Id> SavedDocs(@RequestBody AllDataList data)
	{
		try {
		int doc_id=(int)dao.SaveData(data);
		Doc_Id id=new Doc_Id();
		id.setDoc_id(String.valueOf(doc_id));
		return ResponseEntity.of(Optional.of(id));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/save-firebase-users/{user_id}")
	public ResponseEntity<String> SavedUser(@PathVariable("user_id") String user_id, @RequestBody String phone_no)
	{
		try {
		dao.saveUserId(user_id,phone_no);
		return ResponseEntity.of(Optional.of("Document Save Successfully"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/get-user-details/{user_id}")
	public String getUserDetails(@PathVariable("user_id") String user_id)
	{
		String result="true";
		try {
		UserDetails details=dao.getUserDetails(user_id);
		return result;
		}
		catch(Exception e)
		{ result="false";
		return result;
		}
	}
	
	//get a particular document
	@GetMapping("/get-document-data/{doc_id}")
	public ResponseEntity<GetDocsData> getDocumentById(@PathVariable long doc_id)
	{
		List<SavedData> list=dao.getDocument(doc_id);
		GetDocsData data=new GetDocsData();
		data.setData(list);
		if(list.size()<=0)
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
				return ResponseEntity.of(Optional.of(data));
		
	}
	@GetMapping("/get-document-serial/{doc_id}")
	public ResponseEntity<GetDocsData> getDocumentBySerial(@PathVariable long doc_id)
	{
		List<SavedData> list=dao.getDocumentSerial(doc_id);
		GetDocsData data=new GetDocsData();
		data.setData(list);
		if(list.size()<=0)
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
				return ResponseEntity.of(Optional.of(data));
		
	}
	@GetMapping("/referrals-count/{referFrom}")
	public String getReferralsCount(@PathVariable String referFrom)
	{
		String list=String.valueOf(dao.getReferralsCount(referFrom));
		return list;
		
	}
	@GetMapping("/get-column-data/{doc_id}")
	public ResponseEntity<GetDataByColumn> getcolumnById(@PathVariable long doc_id)
	{
		List<ColumnDetails> list=dao.getcolumn(doc_id);
		GetDataByColumn data=new GetDataByColumn();
		data.setData(list);
		if(list.size()<=0)
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
				return ResponseEntity.of(Optional.of(data));
		
	}
	
	//delete any document
	@DeleteMapping("/delete-document/{doc_id}")
	public ResponseEntity<String> deleteDcomunet(@PathVariable long doc_id)
	{
		try {
		dao.deleteDocument(doc_id);
		return ResponseEntity.of(Optional.of("delete successfully"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PostMapping("/delete-column/{doc_id}")
	public ResponseEntity<String> deleteColumn(@RequestBody DeleteColumn deleteColumn,@PathVariable long doc_id)
	{
		try {
		dao.deleteColumn(deleteColumn,doc_id);
		return ResponseEntity.of(Optional.of("delete successfully"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}
	//delete user
	@DeleteMapping("/delete-user-self/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable String user_id)
	{
		try {
		dao.deleteUser(user_id);
		return ResponseEntity.of(Optional.of("delete successfully"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}
	//update any document data
	@PutMapping("/update-single-data/{doc_id}")
	public ResponseEntity<Boolean> updateSingleData(@RequestBody SingleDataUpdate data,@PathVariable long doc_id)
	{
		System.out.println(data.getData());
		dao.updateSingleData(data,doc_id);
		return ResponseEntity.of(Optional.of(true));	
	}

	@PutMapping("/change-document-name/{doc_id}")
	public ResponseEntity<Boolean> changeDocumentName(@RequestBody String doc_name,@PathVariable long doc_id)
	{
		try {

		if(dao.changeDocumentName(doc_name,doc_id)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/change-column-name/{id}")
	public ResponseEntity<Boolean> changeColumnName(@RequestBody String columnNames,@PathVariable long id)
	{
		try {
		if(dao.changeColumnName(columnNames,id)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/change-column-type/{id}")
	public ResponseEntity<Boolean> changeColumnType(@RequestBody String columnType,@PathVariable long id)
	{
		try {
		if(dao.changeColumnType(columnType,id)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/change-column-data")
	public ResponseEntity<Boolean> changeColumnData(@RequestBody ChangeColumnData data)
	{
		try {
		if(dao.changeColumnData(data)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
@PutMapping("/update-total/{id}")
public ResponseEntity<Boolean> updateTotal(@PathVariable String id,@RequestBody String value)
{
	try {
	if(dao.updateTotal(id,value)>0)
	return ResponseEntity.of(Optional.of(true));
	else
		return ResponseEntity.of(Optional.of(false));	
	}
	catch(Exception e)
	{
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}	

@PutMapping("/add-formula")
public ResponseEntity<Boolean> AddFormula(@RequestBody ColumnFormula columnFormula)
{
	try {
		if(dao.AddFormula(columnFormula)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
}
	@PutMapping("/update-document-serial/{doc_id}")
	public ResponseEntity<Boolean> UpdateDocument(@PathVariable long doc_id)
	{
		dao.UpdateDocument(doc_id);
		return ResponseEntity.of(Optional.of(true));	
	}
	
	@PutMapping("/update-user-phone/{user_id}")
	public ResponseEntity<Boolean> UpdateUserPhone(@PathVariable String user_id,@RequestBody String phone_no)
	{
		try {
			if(dao.UpdateUserPhone(user_id, phone_no)>0)
			return ResponseEntity.of(Optional.of(true));
			else
				return ResponseEntity.of(Optional.of(false));	
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	
	@PutMapping("/update-user-bname/{user_id}")
	public ResponseEntity<Boolean> UpdateUserBname(@PathVariable String user_id,@RequestBody String bname)
	{
		try {
			if(dao.UpdateUserBname(user_id, bname)>0)
			return ResponseEntity.of(Optional.of(true));
			else
				return ResponseEntity.of(Optional.of(false));	
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	@PutMapping("/update-user-address/{user_id}")
	public ResponseEntity<Boolean> UpdateUserAddress(@PathVariable String user_id,@RequestBody UserAddressDetails userAddressDetails)
	{
		try {
			if(dao.UpdateUseraddress(user_id, userAddressDetails)>0)
			return ResponseEntity.of(Optional.of(true));
			else
				return ResponseEntity.of(Optional.of(false));	
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	@PutMapping("/update-pdf-branding/{user_id}")
	public ResponseEntity<Boolean> UpdateBrandng(@PathVariable String user_id,@RequestBody String branding)
	{
		try {
			if(dao.UpdateBranding(user_id,branding)>0)
			return ResponseEntity.of(Optional.of(true));
			else
				return ResponseEntity.of(Optional.of(false));	
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	@PutMapping("/update-business-details/{user_id}")
	public ResponseEntity<Boolean> UpdateBDetails(@PathVariable String user_id,@RequestBody String branding)
	{
		try {
			if(dao.UpdateBDetails(user_id,branding)>0)
			return ResponseEntity.of(Optional.of(true));
			else
				return ResponseEntity.of(Optional.of(false));	
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	
	
	@PostMapping("/add-row/{doc_id}")
	public ResponseEntity<Row_Id> AddRow(@RequestBody AllDataList data,@PathVariable long doc_id)
	{
		try {
		int id=dao.AddRow(data,doc_id);
		Row_Id list=new Row_Id();
		list.setRow_id(String.valueOf(id));
		if(id>0)
		return ResponseEntity.of(Optional.of(list));
		else
			return ResponseEntity.of(Optional.of(list));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/add-column/{doc_id}")
	public ResponseEntity<Column_Id> AddColumn(@RequestBody ColumnDetails columnDetails,@PathVariable long doc_id)
	{
		try {
		int c_id=dao.AddColumn(columnDetails,doc_id);
		Column_Id id=new Column_Id();
		id.setColumn_id(String.valueOf(c_id));
		if(c_id>0)
		return ResponseEntity.of(Optional.of(id));
		else
			return ResponseEntity.of(Optional.of(id));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PostMapping("/add-referrals")
	public ResponseEntity<Boolean> AddReferrals(@RequestBody Referrals refer)
	{
		try {
		if(dao.AddReferrals(refer)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/add-column-morethanone/{doc_id}")
	public ResponseEntity<Boolean> AddColumnMoreThanOne(@RequestBody AddMoreThanOneColumn columnDetails,@PathVariable long doc_id)
	{
		try {
		if(dao.AddMoreThanOneColumn(columnDetails,doc_id)>0)
		return ResponseEntity.of(Optional.of(true));
		else
			return ResponseEntity.of(Optional.of(false));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PostMapping("/add-row-below")
	public ResponseEntity<Row_Id> AddRowBelow(@RequestBody RowData rowData)
	{
		try {
		int id=dao.RowDataBelow(rowData);
		Row_Id list=new Row_Id();
		list.setRow_id(String.valueOf(id));
		if(id>0)
		return ResponseEntity.of(Optional.of(list));
		else
			return ResponseEntity.of(Optional.of(list));	
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PostMapping("/add-row-above")
	public ResponseEntity<Row_Id> AddRowAbove(@RequestBody RowData rowData)
	{
		try {
			int id=dao.RowDataAbove(rowData);
			Row_Id list=new Row_Id();
			list.setRow_id(String.valueOf(id));
			if(id>0)
			return ResponseEntity.of(Optional.of(list));
			else
				return ResponseEntity.of(Optional.of(list));	
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}	
	@PutMapping("/update-document-count")
	public String documentCount()
	{
		dao.updateDocumentCount();
		return "true";
	}
	@GetMapping("/get-refer-count/{userid}")
	public String refercount(@PathVariable String userid)
	{
		int count=dao.getReferCount(userid);
		return String.valueOf(count);
	}
	
}

package com.docsapi.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docsapi.entities.AddMoreThanOneColumn;
import com.docsapi.entities.AllDataList;
import com.docsapi.entities.ChangeColumnData;
import com.docsapi.entities.ColumnDetails;
import com.docsapi.entities.ColumnFormula;
import com.docsapi.entities.DeleteColumn;
import com.docsapi.entities.Document;
import com.docsapi.entities.Referrals;
import com.docsapi.entities.RowData;
import com.docsapi.entities.SavedData;
import com.docsapi.entities.SingleDataUpdate;
import com.docsapi.entities.UserAddressDetails;
import com.docsapi.entities.UserDetails;
import com.docsapi.pdf.GeneratePdf;
import com.docsapi.pdf.GeneratePdfVIP;
import com.docsapi.repository.ColumnDetailsRepository;
import com.docsapi.repository.DocumentRepository;
import com.docsapi.repository.ReferralsRepository;
import com.docsapi.repository.SavedDataRepository;
import com.docsapi.repository.UserDetailsRepository;
@Component
public class DataAccessObjectDAO {

	@Autowired
	SavedDataRepository dataRepository;
	@Autowired
	Document document;
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	UserDetailsRepository detailsRepository;
	@Autowired
	ColumnDetailsRepository columnDetailsRepository;
	@Autowired
	ReferralsRepository referralsRepository;
	
	
	//for save a new Document
	public long SaveData(AllDataList Alldata){
		
		String rcdata[][] =new String[1000][20];
		int col=Alldata.getCols_num();
		int rows=Alldata.getRows_num();
		
		Document document=new Document();
		
		UserDetails details=new UserDetails();
		details.setUser_id(Alldata.getUser_id());
		int count=Integer.parseInt(detailsRepository.findCount(Alldata.getUser_id()))+1;
		detailsRepository.updateCount(String.valueOf(count), Alldata.getUser_id());
		document.setDetails(details);
		document.setDocument_name(Alldata.getDocument_name());
		document.setUpdate_document("yes");
		List<SavedData> sd=new ArrayList<SavedData>();
		
		List<ColumnDetails> set=new ArrayList<>();
		ColumnDetails columnDetails=new ColumnDetails();
		for(int i=0;i<Alldata.getColumn_name().size();i++)
		{
			
			columnDetails.setColumn_names(Alldata.getColumn_name().get(i));
			columnDetails.setColumn_type(Alldata.getColumn_type().get(i));
			columnDetails.setColumn_nums(String.valueOf(Alldata.getCols_num()));
			columnDetails.setRow_nums(String.valueOf(Alldata.getRows_num()));
			columnDetails.setFormula("0");
			columnDetails.setDocument(document);
			set.add(columnDetails);
			columnDetails.setTotal("0");
			columnDetails=new ColumnDetails();
		}
		System.out.println(set.size());
		int i=0;
			int j=0,k=0;
			for(j=0;j<rows;j++)
			{
				for(k=0;k<col;k++)
				{
					
						rcdata[j][k]=Alldata.getData().get(i);
						i++;
				}
			}
			i=0;
			
			SavedData data=new SavedData();
			for (j=0;j<rows;j++)
			{
					data.setColumn1(rcdata[j][0]);
					data.setColumn2(rcdata[j][1]);
					data.setColumn3(rcdata[j][2]);
					data.setColumn4(rcdata[j][3]);
					data.setColumn5(rcdata[j][4]);
					data.setColumn6(rcdata[j][5]);
					data.setColumn7(rcdata[j][6]);
					data.setColumn8(rcdata[j][7]);
					data.setColumn9(rcdata[j][8]);
					data.setColumn10(rcdata[j][9]);
					data.setColumn11(rcdata[j][10]);
					data.setColumn12(rcdata[j][11]);
					data.setColumn13(rcdata[j][12]);
					data.setColumn14(rcdata[j][13]);
					data.setColumn15(rcdata[j][14]);
					data.setColumn16(rcdata[j][15]);
					data.setColumn17(rcdata[j][16]);
					data.setColumn18(rcdata[j][17]);
					data.setColumn19(rcdata[j][18]);
					data.setColumn20(rcdata[j][19]);
					data.setDocs(document);
					data.setSerialno(i+1);
					data.setHeight(Alldata.getHeight().get(i));
					data.setWidth(Alldata.getWidth().get(i));
					sd.add(data);
					data=new SavedData();
					i++;
				}
		document.setData(sd);
		document.setColumnDetails(set);
		documentRepository.save(document);
		List<Document> document_id=getAllDocument(Alldata.getUser_id());
		return document_id.get(document_id.size()-1).getDoc_id();
	}
	
	//for get all Documents of user
	public List<Document> getAllDocument(String user_id){
		List<Document> list=documentRepository.findAll(user_id);
		return list;
	}
	public UserDetails getUserDetails(String user_id){
		Optional<UserDetails> o=detailsRepository.findById(user_id);
		UserDetails details=o.get();
		return details;
	}
	
	//get a particular document
	public List<SavedData> getDocument(long doc_id) {	
		List<SavedData> list=null;
		try {
		list= dataRepository.findById(doc_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public List<SavedData> getDocumentSerial(long doc_id) {	
		List<SavedData> list=null;
		try {
		list= dataRepository.findBySerial(doc_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	//delete any document
	public void deleteDocument( long doc_id){
		dataRepository.deletedataByDocId(doc_id);
		columnDetailsRepository.deleteBydocId(doc_id);
		documentRepository.deleteById(doc_id);
	}
//delete column
	
	
	public void deleteColumn(DeleteColumn delete, long doc_id){
	
		List<SavedData> list=getDocument(doc_id);
		String rcdata[][]= new String[40][20];
		
		List<String> li=new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			li.add(list.get(i).getColumn1());
			li.add(list.get(i).getColumn2());
			li.add(list.get(i).getColumn3());
			li.add(list.get(i).getColumn4());
			li.add(list.get(i).getColumn5());
			li.add(list.get(i).getColumn6());
			li.add(list.get(i).getColumn7());
			li.add(list.get(i).getColumn8());
			li.add(list.get(i).getColumn9());
			li.add(list.get(i).getColumn10());
			li.add(list.get(i).getColumn11());
			li.add(list.get(i).getColumn12());
			li.add(list.get(i).getColumn13());
			li.add(list.get(i).getColumn14());
			li.add(list.get(i).getColumn15());
			li.add(list.get(i).getColumn16());
			li.add(list.get(i).getColumn17());
			li.add(list.get(i).getColumn18());
			li.add(list.get(i).getColumn19());
			li.add(list.get(i).getColumn20());		
		}
		System.out.println(li);
		int col=Integer.parseInt(delete.getDeleted_column_num());
		int start=0,end=20;
		List<String> finalList=new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			for(int j=start;j<end;j++)
			{
				if(j==col-1)
				{}
				else finalList.add(li.get(j));
			}
			finalList.add(null);
			start=end;
			end+=20;
			col+=20;
		}
		System.out.println(finalList);
		
		start=0;
		for(int i=0;i<list.size();i++)
		{
			for(int j=0;j<20;j++)
			{
				rcdata[i][j]=finalList.get(start);
				start++;
			}
		}
		Document document=new Document();
		document.setDoc_id(doc_id);
		SavedData data=new SavedData();
		for (int j=0;j<list.size();j++)
		{
				data.setColumn1(rcdata[j][0]);
				data.setColumn2(rcdata[j][1]);
				data.setColumn3(rcdata[j][2]);
				data.setColumn4(rcdata[j][3]);
				data.setColumn5(rcdata[j][4]);
				data.setColumn6(rcdata[j][5]);
				data.setColumn7(rcdata[j][6]);
				data.setColumn8(rcdata[j][7]);
				data.setColumn9(rcdata[j][8]);
				data.setColumn10(rcdata[j][9]);
				data.setColumn11(rcdata[j][10]);
				data.setColumn12(rcdata[j][11]);
				data.setColumn13(rcdata[j][12]);
				data.setColumn14(rcdata[j][13]);
				data.setColumn15(rcdata[j][14]);
				data.setColumn16(rcdata[j][15]);
				data.setColumn17(rcdata[j][16]);
				data.setColumn18(rcdata[j][17]);
				data.setColumn19(rcdata[j][18]);
				data.setColumn20(rcdata[j][19]);
				data.setDocs(document);
				data.setHeight(list.get(j).getHeight());
				data.setWidth(list.get(j).getWidth());
				data.setId(list.get(j).getId());
				dataRepository.save(data);
				data=new SavedData();
			}
	int column_nums=Integer.parseInt(delete.getColumn_nums())-1;
	long id=Long.parseLong(delete.getId());
	columnDetailsRepository.deleteById(id);
	columnDetailsRepository.addColumn(String.valueOf(column_nums),doc_id);
	}

//update any document data
	public int updateSingleData(SingleDataUpdate Alldata,long doc_id) {
		String rcdata[][] =new String[40][20];
		System.out.println(Alldata.getCols_nums());
		int col=Integer.parseInt(Alldata.getCols_nums());
		long id=Long.parseLong(Alldata.getId());
		Document document=new Document();
		document.setDoc_id(doc_id);
		int i=0;
			int j=0,k=0;
			for(j=0;j<1;j++)
			{
				for(k=0;k<col;k++)
				{
						rcdata[j][k]=Alldata.getData().get(i);
						i++;
				}
			}
			i=0;
			SavedData data=new SavedData();

					data.setColumn1(rcdata[0][0]);
					data.setColumn2(rcdata[0][1]);
					data.setColumn3(rcdata[0][2]);
					data.setColumn4(rcdata[0][3]);
					data.setColumn5(rcdata[0][4]);
					data.setColumn6(rcdata[0][5]);
					data.setColumn7(rcdata[0][6]);
					data.setColumn8(rcdata[0][7]);
					data.setColumn9(rcdata[0][8]);
					data.setColumn10(rcdata[0][9]);
					data.setColumn11(rcdata[0][10]);
					data.setColumn12(rcdata[0][11]);
					data.setColumn13(rcdata[0][12]);
					data.setColumn14(rcdata[0][13]);
					data.setColumn15(rcdata[0][14]);
					data.setColumn16(rcdata[0][15]);
					data.setColumn17(rcdata[0][16]);
					data.setColumn18(rcdata[0][17]);
					data.setColumn19(rcdata[0][18]);
					data.setColumn20(rcdata[0][19]);
					data.setId(id);
					data.setDocs(document);
					data.setHeight(Alldata.getHeight().get(i));
					data.setWidth(Alldata.getWidth().get(i));
					data.setSerialno(Integer.parseInt(Alldata.getSerialno()));
					dataRepository.save(data);		
				
		return 1;	
		
		}

	public void saveUserId(String user_id,String phone_no) {
		
		UserDetails details=new UserDetails();
		details.setUser_id(user_id);
		details.setPhone_no(phone_no);
		details.setDocument_count("0");
		detailsRepository.save(details);	
	}
	public void deleteUser(String user_id) {
		detailsRepository.deleteById(user_id);	
	}

	public int AddRow(AllDataList data, long doc_id) {
		Document document=new Document();
		document.setDoc_id(doc_id);
		SavedData savedData=new SavedData();
		savedData.setHeight(data.getHeight().get(0));
		savedData.setWidth(data.getWidth().get(0));
		
		String tempdata[][]=new String[40][20];
		
		for(int i=0;i<data.getData().size();i++)
		{
			tempdata[0][i]=data.getData().get(i);
		}
		savedData.setColumn1(tempdata[0][0]);
		savedData.setColumn2(tempdata[0][1]);
		savedData.setColumn3(tempdata[0][2]);
		savedData.setColumn4(tempdata[0][3]);
		savedData.setColumn5(tempdata[0][4]);
		savedData.setColumn6(tempdata[0][5]);
		savedData.setColumn7(tempdata[0][6]);
		savedData.setColumn8(tempdata[0][7]);
		savedData.setColumn9(tempdata[0][8]);
		savedData.setColumn10(tempdata[0][9]);
		savedData.setColumn11(tempdata[0][10]);
		savedData.setColumn12(tempdata[0][11]);
		savedData.setColumn13(tempdata[0][12]);
		savedData.setColumn14(tempdata[0][13]);
		savedData.setColumn15(tempdata[0][14]);
		savedData.setColumn16(tempdata[0][15]);
		savedData.setColumn17(tempdata[0][16]);
		savedData.setColumn18(tempdata[0][17]);
		savedData.setColumn19(tempdata[0][18]); 
		savedData.setColumn20(tempdata[0][19]);
		savedData.setSerialno(data.getRows_num()+1);
		savedData.setDocs(document);
		dataRepository.save(savedData);	
	
		int row=data.getRows_num()+1;
		String row_nums=String.valueOf(row);
		columnDetailsRepository.addRow(row_nums,doc_id);
		List<SavedData> list=getDocument(doc_id);
		return (int)list.get(list.size()-1).getId();	
	}

	public int changeDocumentName(String doc_name, long doc_id) {
		int status = documentRepository.changeName(doc_name,doc_id);
		return status;
	}

	public int changeColumnName(String column_names, long id) {
		
		int status=0;
			status=columnDetailsRepository.changeColumn(column_names,id);
		return status;
	}
	public int AddColumn(ColumnDetails columnDetails, long doc_id) {
		
		List<SavedData> list=getDocument(doc_id);
		System.out.println(list.size());
		Document document=new Document();
		SavedData savedData=new SavedData();
		document.setDoc_id(doc_id);
		columnDetails.setDocument(document);
		columnDetails.setTotal("0");
		columnDetails.setFormula("0");
		columnDetailsRepository.save(columnDetails);
		int temp=Integer.parseInt(columnDetails.getColumn_nums())+1;
		String tempdata[][]=new String[1000][20];
		for(int i=0;i<Integer.parseInt(columnDetails.getRow_nums());i++)
		{
			tempdata[i][0]=list.get(i).getColumn1();
			tempdata[i][1]=list.get(i).getColumn2();
			tempdata[i][2]=list.get(i).getColumn3();
			tempdata[i][3]=list.get(i).getColumn4();
			tempdata[i][4]=list.get(i).getColumn5();
			tempdata[i][5]=list.get(i).getColumn6();
			tempdata[i][6]=list.get(i).getColumn7();
			tempdata[i][7]=list.get(i).getColumn8();
			tempdata[i][8]=list.get(i).getColumn9();
			tempdata[i][9]=list.get(i).getColumn10();
			tempdata[i][10]=list.get(i).getColumn11();
			tempdata[i][11]=list.get(i).getColumn12();
			tempdata[i][12]=list.get(i).getColumn13();
			tempdata[i][13]=list.get(i).getColumn14();
			tempdata[i][14]=list.get(i).getColumn15();
			tempdata[i][15]=list.get(i).getColumn16();
			tempdata[i][16]=list.get(i).getColumn17();
			tempdata[i][17]=list.get(i).getColumn18();
			tempdata[i][18]=list.get(i).getColumn19();
			tempdata[i][19]=list.get(i).getColumn20();
		}
		for(int i=0;i<Integer.parseInt(columnDetails.getRow_nums());i++)
		{
			tempdata[i][temp-1]=" ";
		}
		for(int i=0;i<Integer.parseInt(columnDetails.getRow_nums());i++)
		{
			savedData.setId(list.get(i).getId());;
			savedData.setColumn1(tempdata[i][0]);
			savedData.setColumn2(tempdata[i][1]);
			savedData.setColumn3(tempdata[i][2]);		
			savedData.setColumn4(tempdata[i][3]);
			savedData.setColumn5(tempdata[i][4]);
			savedData.setColumn6(tempdata[i][5]);
			savedData.setColumn7(tempdata[i][6]);
			savedData.setColumn8(tempdata[i][7]);
			savedData.setColumn9(tempdata[i][8]);
			savedData.setColumn10(tempdata[i][9]);
			savedData.setColumn11(tempdata[i][10]);
			savedData.setColumn12(tempdata[i][11]);
			savedData.setColumn13(tempdata[i][12]);
			savedData.setColumn14(tempdata[i][13]);
			savedData.setColumn15(tempdata[i][14]);
			savedData.setColumn16(tempdata[i][15]);
			savedData.setColumn17(tempdata[i][16]);
			savedData.setColumn18(tempdata[i][17]);
			savedData.setColumn19(tempdata[i][18]);
			savedData.setColumn20(tempdata[i][19]);
			savedData.setHeight(list.get(i).getHeight());
			savedData.setWidth(list.get(i).getWidth());
			savedData.setSerialno(list.get(i).getSerialno());
			savedData.setDocs(document);
			dataRepository.save(savedData);
		}
		String column_nums=String.valueOf(temp);
		int status=columnDetailsRepository.addColumn(column_nums,doc_id);
		List<ColumnDetails> cd=getcolumn(doc_id);
		
		return (int)cd.get(cd.size()-1).getId();
	}
	public List<ColumnDetails> getcolumn(long doc_id) {
		List<ColumnDetails> list=null;
		try {
		list= columnDetailsRepository.findById(doc_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public int changeColumnType(String columnType, long id) {
	
		int status=columnDetailsRepository.changeColumnType(columnType,id);
		return status;
	}

	public int AddMoreThanOneColumn(AddMoreThanOneColumn columnDetails, long doc_id) {

		List<SavedData> list=getDocument(doc_id);
		System.out.println(list.size());
		Document document=new Document();
		SavedData savedData=new SavedData();
		document.setDoc_id(doc_id);
		ColumnDetails columnDetails2=new ColumnDetails();
		for(int i=0;i<3;i++)
		{
			columnDetails2.setDocument(document);
			columnDetails2.setColumn_names(columnDetails.getColumn_names().get(i));
			columnDetails2.setColumn_type(columnDetails.getColumn_type().get(i));
			columnDetails2.setColumn_nums(columnDetails.getColumn_nums());
			columnDetails2.setRow_nums(columnDetails.getRow_nums());
			columnDetailsRepository.save(columnDetails2);
			columnDetails2=new ColumnDetails();
		}
		int temp=Integer.parseInt(columnDetails.getColumn_nums())+3;
		String tempdata[][]=new String[1000][20];
		for(int i=0;i<Integer.parseInt(columnDetails.getRow_nums());i++)
		{
			tempdata[i][0]=list.get(i).getColumn1();
			tempdata[i][1]=list.get(i).getColumn2();
			tempdata[i][2]=list.get(i).getColumn3();
			tempdata[i][3]=list.get(i).getColumn4();
			tempdata[i][4]=list.get(i).getColumn5();
			tempdata[i][5]=list.get(i).getColumn6();
			tempdata[i][6]=list.get(i).getColumn7();
			tempdata[i][7]=list.get(i).getColumn8();
			tempdata[i][8]=list.get(i).getColumn9();
			tempdata[i][9]=list.get(i).getColumn10();
			tempdata[i][10]=list.get(i).getColumn11();
			tempdata[i][11]=list.get(i).getColumn12();
			tempdata[i][12]=list.get(i).getColumn13();
			tempdata[i][13]=list.get(i).getColumn14();
			tempdata[i][14]=list.get(i).getColumn15();
			tempdata[i][15]=list.get(i).getColumn16();
			tempdata[i][16]=list.get(i).getColumn17();
			tempdata[i][17]=list.get(i).getColumn18();
			tempdata[i][18]=list.get(i).getColumn19();
			tempdata[i][19]=list.get(i).getColumn20();
		}
		for(int i=0;i<Integer.parseInt(columnDetails.getRow_nums());i++)
			for(int j=0;j<3;j++)
		{
			tempdata[i][temp-j-1]=" ";
		}
		for(int i=0;i<Integer.parseInt(columnDetails.getRow_nums());i++)
		{
			savedData.setId(list.get(i).getId());;
			savedData.setColumn1(tempdata[i][0]);
			savedData.setColumn2(tempdata[i][1]);
			savedData.setColumn3(tempdata[i][2]);		
			savedData.setColumn4(tempdata[i][3]);
			savedData.setColumn5(tempdata[i][4]);
			savedData.setColumn6(tempdata[i][5]);
			savedData.setColumn7(tempdata[i][6]);
			savedData.setColumn8(tempdata[i][7]);
			savedData.setColumn9(tempdata[i][8]);
			savedData.setColumn10(tempdata[i][9]);
			savedData.setColumn11(tempdata[i][10]);
			savedData.setColumn12(tempdata[i][11]);
			savedData.setColumn13(tempdata[i][12]);
			savedData.setColumn14(tempdata[i][13]);
			savedData.setColumn15(tempdata[i][14]);
			savedData.setColumn16(tempdata[i][15]);
			savedData.setColumn17(tempdata[i][16]);
			savedData.setColumn18(tempdata[i][17]);
			savedData.setColumn19(tempdata[i][18]);
			savedData.setColumn20(tempdata[i][19]);
			savedData.setHeight(list.get(i).getHeight());
			savedData.setWidth(list.get(i).getWidth());
			savedData.setDocs(document);
			dataRepository.save(savedData);
		}
		String column_nums=String.valueOf(temp);
		int status=columnDetailsRepository.addColumn(column_nums,doc_id);
		return status;
	
	}

	public int RowDataBelow(RowData rowData) {
		int status=1;
		SavedData savedData=new SavedData();
		Document document=new Document();
		document.setDoc_id(Integer.parseInt(rowData.getDoc_id()));
		savedData.setDocs(document);
		savedData.setSerialno(Integer.parseInt(rowData.getCurrent_row_number())+1);
		savedData.setHeight("10");
		savedData.setWidth("10");
		
		String tempdata[][]=new String[40][20];
		for(int i=0;i<rowData.getData().size();i++)
		{
			tempdata[0][i]=rowData.getData().get(i);
		}
		savedData.setColumn1(tempdata[0][0]);
		savedData.setColumn2(tempdata[0][1]);
		savedData.setColumn3(tempdata[0][2]);
		savedData.setColumn4(tempdata[0][3]);
		savedData.setColumn5(tempdata[0][4]);
		savedData.setColumn6(tempdata[0][5]);
		savedData.setColumn7(tempdata[0][6]);
		savedData.setColumn8(tempdata[0][7]);
		savedData.setColumn9(tempdata[0][8]);
		savedData.setColumn10(tempdata[0][9]);
		savedData.setColumn11(tempdata[0][10]);
		savedData.setColumn12(tempdata[0][11]);
		savedData.setColumn13(tempdata[0][12]);
		savedData.setColumn14(tempdata[0][13]);
		savedData.setColumn15(tempdata[0][14]);
		savedData.setColumn16(tempdata[0][15]);
		savedData.setColumn17(tempdata[0][16]);
		savedData.setColumn18(tempdata[0][17]);
		savedData.setColumn19(tempdata[0][18]); 
		savedData.setColumn20(tempdata[0][19]);
		dataRepository.save(savedData);
		
		List<SavedData> list=getDocument(Long.parseLong(rowData.getDoc_id()));	
		int i=0;
		if(Integer.parseInt(rowData.getCurrent_row_number())<Integer.parseInt(rowData.getRows_num()))
		{
			
			i=0;
		for(i=0;i<list.size();i++)
		{
			Document doc=new Document();
			int serialno=Integer.parseInt(rowData.getCurrent_row_number())+1;
			int match=list.get(i).getSerialno();
			doc.setDoc_id(Long.parseLong(rowData.getDoc_id()));
			if(serialno==match)
			{	
				int serial=serialno+1;
				Long id=list.get(i).getId();
				status=dataRepository.UpdateRowBelow(serial, id);
				break;
			}
		}
		for(int j=0;j<list.size();j++)
		{
			Document doc=new Document();
			doc.setDoc_id(Long.parseLong(rowData.getDoc_id()));
			int serialno=Integer.parseInt(rowData.getCurrent_row_number())+1;
			int match=list.get(j).getSerialno();
			if(match>serialno&&i!=j)
			{
				int serial=match+1;
				Long id=list.get(j).getId();
				status=dataRepository.UpdateRowBelow(serial, id);
			}
		}
		}
		
		String rows_num=String.valueOf(Integer.parseInt(rowData.getRows_num())+1);
		long doc_id=Long.parseLong(rowData.getDoc_id());
		columnDetailsRepository.addRow(rows_num,doc_id);
		return (int)list.get(list.size()-1).getId();
	}

	public int RowDataAbove(RowData rowData) {
		int status=0;
		SavedData savedData=new SavedData();
		Document document=new Document();
		document.setDoc_id(Integer.parseInt(rowData.getDoc_id()));
		savedData.setDocs(document);
		savedData.setSerialno(Integer.parseInt(rowData.getCurrent_row_number()));
		savedData.setHeight("10");
		savedData.setWidth("10");
		
		String tempdata[][]=new String[40][20];
		for(int i=0;i<rowData.getData().size();i++)
		{
			tempdata[0][i]=rowData.getData().get(i);
		}
		savedData.setColumn1(tempdata[0][0]);
		savedData.setColumn2(tempdata[0][1]);
		savedData.setColumn3(tempdata[0][2]);
		savedData.setColumn4(tempdata[0][3]);
		savedData.setColumn5(tempdata[0][4]);
		savedData.setColumn6(tempdata[0][5]);
		savedData.setColumn7(tempdata[0][6]);
		savedData.setColumn8(tempdata[0][7]);
		savedData.setColumn9(tempdata[0][8]);
		savedData.setColumn10(tempdata[0][9]);
		savedData.setColumn11(tempdata[0][10]);
		savedData.setColumn12(tempdata[0][11]);
		savedData.setColumn13(tempdata[0][12]);
		savedData.setColumn14(tempdata[0][13]);
		savedData.setColumn15(tempdata[0][14]);
		savedData.setColumn16(tempdata[0][15]);
		savedData.setColumn17(tempdata[0][16]);
		savedData.setColumn18(tempdata[0][17]);
		savedData.setColumn19(tempdata[0][18]); 
		savedData.setColumn20(tempdata[0][19]);
		dataRepository.save(savedData);
		
		List<SavedData> list=getDocument(Long.parseLong(rowData.getDoc_id()));
		int i=0;
		if(Integer.parseInt(rowData.getCurrent_row_number())<=Integer.parseInt(rowData.getRows_num()))
		{
			
			i=0;
		for(i=0;i<list.size();i++)
		{
			Document doc=new Document();
			int serialno=Integer.parseInt(rowData.getCurrent_row_number());
			int match=list.get(i).getSerialno();
			doc.setDoc_id(Long.parseLong(rowData.getDoc_id()));
			if(serialno==match)
			{	
				int serial=serialno+1;
				Long id=list.get(i).getId();
				status=dataRepository.UpdateRowBelow(serial, id);
				break;
			}
		}
		for(int j=0;j<list.size();j++)
		{
			Document doc=new Document();
			doc.setDoc_id(Long.parseLong(rowData.getDoc_id()));
			int serialno=Integer.parseInt(rowData.getCurrent_row_number());
			int match=list.get(j).getSerialno();
			if(match>serialno&&i!=j)
			{
				int serial=match+1;
				Long id=list.get(j).getId();
				status=dataRepository.UpdateRowBelow(serial, id);
			}
		}
		}
		String rows_num=String.valueOf(Integer.parseInt(rowData.getRows_num())+1);
		long doc_id=Long.parseLong(rowData.getDoc_id());
		columnDetailsRepository.addRow(rows_num,doc_id);
		return (int)list.get(list.size()-1).getId();
	}
	
	public int UpdateDocument(long doc_id)
	{
		int status =0;
		List<SavedData> list=getDocument(doc_id);
		for(int i=0;i<list.size();i++)
		{
			long id=list.get(i).getId();
			int serial=i+1;
			status=dataRepository.UpdateRowBelow(serial,id);
		}
		String update="yes";
		status=documentRepository.updateDocs(update,doc_id);
		
		int rows=list.size();
		String rows_num=String.valueOf(rows);
		columnDetailsRepository.addRow(rows_num, doc_id);
		
		return status;
	}
	
	public int changeColumnData(ChangeColumnData data) {
		String [][] tempdata=new String[1000][20];
		List<SavedData> list=getDocument(data.getDoc_id());
		for(int i=0;i<list.size();i++)
		{
			tempdata[i][0]=list.get(i).getColumn1();
			tempdata[i][1]=list.get(i).getColumn2();
			tempdata[i][2]=list.get(i).getColumn3();
			tempdata[i][3]=list.get(i).getColumn4();
			tempdata[i][4]=list.get(i).getColumn5();
			tempdata[i][5]=list.get(i).getColumn6();
			tempdata[i][6]=list.get(i).getColumn7();
			tempdata[i][7]=list.get(i).getColumn8();
			tempdata[i][8]=list.get(i).getColumn9();
			tempdata[i][9]=list.get(i).getColumn10();
			tempdata[i][10]=list.get(i).getColumn11();
			tempdata[i][11]=list.get(i).getColumn12();
			tempdata[i][12]=list.get(i).getColumn13();
			tempdata[i][13]=list.get(i).getColumn14();
			tempdata[i][14]=list.get(i).getColumn15();
			tempdata[i][15]=list.get(i).getColumn16();
			tempdata[i][16]=list.get(i).getColumn17();
			tempdata[i][17]=list.get(i).getColumn18();
			tempdata[i][18]=list.get(i).getColumn19();
			tempdata[i][19]=list.get(i).getColumn20();
		}
		for(int i=0;i<list.size();i++)
		{
			tempdata[i][data.getCurrent_col_number()-1]=data.getData().get(i);
		}
		SavedData sd=new SavedData();

		for(int i=0;i<list.size();i++)
		{
			sd.setColumn1(tempdata[i][0]);
			sd.setColumn2(tempdata[i][1]);
			sd.setColumn3(tempdata[i][2]);
			sd.setColumn4(tempdata[i][3]);
			sd.setColumn5(tempdata[i][4]);
			sd.setColumn6(tempdata[i][5]);
			sd.setColumn7(tempdata[i][6]);
			sd.setColumn8(tempdata[i][7]);
			sd.setColumn9(tempdata[i][8]);
			sd.setColumn10(tempdata[i][9]);
			sd.setColumn11(tempdata[i][10]);
			sd.setColumn12(tempdata[i][11]);
			sd.setColumn13(tempdata[i][12]);
			sd.setColumn14(tempdata[i][13]);
			sd.setColumn15(tempdata[i][14]);
			sd.setColumn16(tempdata[i][15]);
			sd.setColumn17(tempdata[i][16]);
			sd.setColumn18(tempdata[i][17]);
			sd.setColumn19(tempdata[i][18]);
			sd.setColumn20(tempdata[i][19]);
			sd.setId(list.get(i).getId());
			sd.setHeight(list.get(i).getHeight());
			sd.setWidth(list.get(i).getWidth());
			sd.setDocs(list.get(i).getDocs());
			sd.setSerialno(list.get(i).getSerialno());
			dataRepository.save(sd);
			
		}
		return 1;
		
	}

	public int updateTotal(String c_id,String value) {
		int status=0;
		long id;
		id=Long.parseLong(c_id);
		status=columnDetailsRepository.updateTotal(value,id);
		return status;
	}

	public int AddFormula(ColumnFormula columnFormula) {
		int status=0;
		Long id=Long.parseLong(columnFormula.getCol_id());
		String formula=columnFormula.getFormula();
		
		status =columnDetailsRepository.saveFormula(id,formula);
		
		
		return status;
	}

	public int UpdateUserPhone(String user_id, String phone_no) {
		
		int status=detailsRepository.updatePhone(user_id,phone_no);
		
		return status;
	}

	public int UpdateUserBname(String user_id, String bname) {

		int status=detailsRepository.updateBname(user_id,bname);
		
		return status;
	}

	public int UpdateUseraddress(String user_id,UserAddressDetails userAddressDetails) {
		String b_add1=userAddressDetails.getB_add1();
		String b_add2=userAddressDetails.getB_add2();
		String pin=userAddressDetails.getPin();
		String city_state_country=userAddressDetails.getCity_state_country();
			int status=detailsRepository.updateAddress(user_id,b_add1,b_add2,pin,city_state_country);
		
		return status;
	}
	
	//this function give us the pdf
	public void GeneratePdf(String doc_id,String doc_name) throws IOException {
		
		
		List<SavedData> slist=getDocumentSerial(Long.parseLong(doc_id));
		List<ColumnDetails> clist=getcolumn(Long.parseLong(doc_id));
		Document document=getUser_id(Long.parseLong(doc_id));
		String user_id=document.getDetails().getUser_id();
		UserDetails userDetails=getUser(user_id);
		int count=getReferCount(user_id);
		if(count>=3) {
			GeneratePdfVIP pdf=new GeneratePdfVIP();	
		pdf.creation(doc_id,doc_name,clist,slist,userDetails);
		}
		else {
			GeneratePdf pdf=new GeneratePdf();	
			pdf.creation(doc_id,doc_name,clist,slist);
		}
	}
	
	public Document getUser_id(long doc_id)
	{
	Optional<Document> d=documentRepository.findById(doc_id);
	Document document=d.get();
	return document;
		
	}
	
	public String getDocName(long doc_id)
	{
		Optional<Document> d=documentRepository.findById(doc_id);
		Document doc=d.get();
		String name=doc.getDocument_name();
		return name;
		
	}

	public int AddReferrals(Referrals refer) {
		referralsRepository.save(refer);
		return 1;
	}

	public int getReferralsCount(String referFrom)
	{
		
		 List<Referrals> list=referralsRepository.findCount(referFrom);
		 return list.size();	
	}

	public void updateDocumentCount() {
	
		List<UserDetails> list=detailsRepository.docuemntCount();
		
		for(int i=0;i<list.size();i++)
		{
			int count=documentRepository.count(list.get(i).getUser_id());
			detailsRepository.updateCount(String.valueOf(count),list.get(i).getUser_id());
		}
		
	}

	public int getReferCount(String userid) {
		
	List<Referrals> list=referralsRepository.findCount(userid);
	int count=0;
	for(int i=0;i<list.size();i++)
	{
		if(count==3)return count;
		Optional<UserDetails> o=detailsRepository.findById(list.get(i).getReferTo());
		UserDetails u=o.get();
		if(Integer.parseInt(u.getDocument_count())>=3)
		{
			count++;
		}
	}
	return count;
	}

	public UserDetails getUser(String user_id) {
		
		Optional<UserDetails> o=detailsRepository.findById(user_id);
		UserDetails userDetails=o.get();
		
		return userDetails;
	}

	public int UpdateBranding(String user_id,String branding) {

int status=detailsRepository.UpdateBranding(user_id,branding);
		return status;
	}

	public int UpdateBDetails(String user_id, String branding) {
		int status=detailsRepository.UpdateBdetails(user_id,branding);
		return status;
	}
	
	
}





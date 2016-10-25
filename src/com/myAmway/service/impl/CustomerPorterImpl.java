package com.myAmway.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.myAmway.dao.IDao;
import com.myAmway.dao.exception.DataOperationException;
import com.myAmway.model.Customer;
import com.myAmway.service.CustomerPorter;
import com.myAmway.service.ProcessStatus;

@Service(value="customerPorter")
public class CustomerPorterImpl implements CustomerPorter {
	
	private transient Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="customerDao")
	private IDao<Customer> customerDao;
	
	@Override
	public ProcessStatus importer(String fileName) {
		InputStream inp = null;
		Workbook wb = null;
		
		List<Customer> customerList = new ArrayList<Customer>();
		
		try {
			inp = new FileInputStream(fileName);
			wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			
			for (int i = 1; i < 141; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(9);
				String name = this.getCellValue(cell);
				cell = row.getCell(10);
				String gender = this.getCellValue(cell);
				cell = row.getCell(11);
				String marriage = this.getCellValue(cell);
				cell = row.getCell(12);
				String age = this.getCellValue(cell);
				cell = row.getCell(13);
				String profession = this.getCellValue(cell);
				cell = row.getCell(14);
				String mobile = this.getCellValue(cell);
				cell = row.getCell(15);
				String birth = this.getCellValue(cell);
				cell = row.getCell(16);
				String address = this.getCellValue(cell);
				
				Customer customer = new Customer();
				customer.setName(name);
				customer.setGender(gender);
				customer.setAge(age);
				customer.setBirth(birth);
				String[] mobiles = mobile.split(" ");
				String mobile1 = mobiles[0];
				String mobile2 = "";
				if(mobiles.length > 1)
					mobile2 = mobiles[1];
				customer.setMobile(mobile1);
				System.out.println(marriage);
				boolean isM = (marriage.equals("Y")) ? true : false;
				customer.setMarriage(isM);
				
				customer.setMobile2(mobile2);
				customer.setAddress(address);
				customer.setProfession(profession);
				System.out.println(customer);
				customerList.add(customer);
				
			}
			
			customerDao.saveAll(customerList);
			
		} catch (FileNotFoundException e) {
			log.error(fileName + " file not found", e);
		} catch (InvalidFormatException e) {
			log.error(fileName + " 非法格式", e);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataOperationException e) {
			e.printStackTrace();
		} finally {
			try {
				inp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ProcessStatus.END;
	}
	
	private String getCellValue(Cell cell) {
		int cellType = cell.getCellType();
		if(cellType == Cell.CELL_TYPE_NUMERIC) {
			return cell.getNumericCellValue() + "";
		} else if(cellType == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if(cellType == Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue() + "";
		} 
		return "";
	}

}

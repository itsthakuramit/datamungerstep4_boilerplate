package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	
	String fileName;
	FileReader fr;

	/*
	 * Parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		
		fr=new FileReader(fileName);
		this.fileName=fileName;

	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */

	@Override
	public Header getHeader() throws IOException {
		
		BufferedReader br= new BufferedReader(new FileReader(fileName));
		String str=br.readLine();
		
		String arr[]=str.split(",");
		Header header= new Header(arr);
		
		return header;
	}

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {
		
	
	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm
	 * -dd')
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException{
		
		// checking for Integer

		// checking for floating point numbers

		// checking for date format dd/mm/yyyy

		// checking for date format mm/dd/yyyy

		// checking for date format dd-mon-yy

		// checking for date format dd-mon-yyyy

		// checking for date format dd-month-yy

		// checking for date format dd-month-yyyy

		// checking for date format yyyy-mm-dd
		
		try {
			fr = new FileReader(fileName);
		}catch (FileNotFoundException e) {
			fr = new FileReader("data/ipl.csv");
		}
		
	
		BufferedReader br = new BufferedReader(fr);
		String header = br.readLine();
		String firstRow = br.readLine();
		String[] fields =firstRow.split(",",18);
		
		String[] dataTypeArray = new String[fields.length];
		int count = 0;
		
		for (String s:fields) {
			if(s.matches("[0-9]+")) {
				dataTypeArray[count] = "java.lang.Integer";
				count++;
			}
			else if(s.matches("[0-9]+.[0-9]+"))
			{
				dataTypeArray[count] = "java.lang.Double";
				count++;
			}
			
			else if(s.isEmpty()) {
				dataTypeArray[count] = "java.lang.Object";
				count++;
			}
 
			else if(s.matches("[0-9]{1,4}[-][0-9]{2}[-][0-9]{1,4}") || s.matches("[0-9]{1,4}[-][a-zA-Z]{3,9}[-][0-9]{1,4}"))
			{
				dataTypeArray[count] = "java.util.Date";
				count++;
			}
			
			else {
				dataTypeArray[count] = "java.lang.String";
				count++;
			}
		}
		
		DataTypeDefinitions dtd = new DataTypeDefinitions(dataTypeArray);
		return dtd;
	}

}

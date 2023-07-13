package bankaccount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVRead{


	static ArrayList<String[]> read() {

		String path = "C:\\Users\\aarju\\eclipse-workspace\\BankAccountApplication\\src\\bankaccount\\NewBankAccounts.csv";

		ArrayList<String[]> newBankAccounts = new ArrayList<String[]>();
		
		
		String dataRow;

		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			while((dataRow = br.readLine()) != null) {
				String dataRecords[] = dataRow.split(",");
				newBankAccounts.add(dataRecords);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newBankAccounts;
	}
		

}

package DataProvider;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.automation.framework.utils.ExcelUtil;

public class TestDataProvider {
	
	//Creating data provider method to fetch data from excel and storing in variables

		@DataProvider(name="loginData" , parallel=true)
		public Object[][] getLoginData(){
			
			List<List<String>> sheet=ExcelUtil.readSheet("src/test/resources/testdata/login/loginData.xlsx","LoginData");

			Object[][] data= new Object[sheet.size()-1][3];
			
			//now getting each cells data:
			for (int i = 1; i < sheet.size(); i++) {

	            data[i - 1][0] = sheet.get(i).get(0); // username
	            data[i - 1][1] = sheet.get(i).get(1); // password
	            data[i - 1][2] = sheet.get(i).get(2); // expected
	        }
			return data;
		}

}

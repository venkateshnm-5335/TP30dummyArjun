package lead;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners
public class CreateLeads1 extends configuration.BaseClass{
	@Test(groups = "smoke")
	public void userShouldAbleToCreateLeadsByEnteringMandatoryFieldsTest() throws Exception {
	System.out.println("New file");
	}
}
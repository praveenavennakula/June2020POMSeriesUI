package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistartionPageTest extends BaseTest {
    Random rd = new Random();
    @BeforeClass
    public void RegPageSetup() {
        regPage = lp.navigateToRegisterPage();
       // rd = new Random();
    }

    @DataProvider
    public Object[][] getRegTestData() {
        Object[][] regData = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
        return regData;

    }

    public String generateRandomEmail() {
       // Random rd = new Random();
        String email = generateRandomString() + rd.nextInt() + "@gamil.com";

        return email;

    }

    public String generateRandomString() {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(10);//StringBuilder is mutable,faster
        // Random rd=new Random();
        for (int y = 0; y < sb.capacity(); y++) {
            int index = (int) (s.length()
                    * Math.random());//to generate random numbers of type doubles between the range of 0.0 and 1.0. This method returns a double value which is either greater than or equal to 0.0 and less than or equal to 1.0 along with a positive sign
            // System.out.println(index);
            sb.append(s.charAt(index));

        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    @Test(dataProvider = "getRegTestData")
    public void userRegisterTest(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
        //  String actSuccessMsg=regPage.userRegister("sarayu123","sarayu","sarayu1232346767swe@gmail.com","1234567890","sarayu","yes");
        String actSuccessMsg = regPage.userRegister(firstName, lastName, generateRandomEmail(), telephone, password, subscribe);
        Assert.assertEquals(actSuccessMsg, AppConstants.ACC_CREATE_SUCC_MSG);
    }

}

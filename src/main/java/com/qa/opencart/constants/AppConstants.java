package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    public static final String LOGIN_PAGE_TITLE = "login page title is:";
    public static final String ACC_PAGE_TITLE = "Account Page title is: ";

    public static final String LOGIN_PAGE_URL_PARAM = "route=account/login";
    public static final String ACC_PAGE_URL_PARAM = "route=account/account";


    public static final List<String> ACC_PAGE_SECTIONS_HEADERS = Arrays.asList("My Account", "My Orders",
            "My Affiliate Account", "Newsletter");
    public static final String REGISTER_SHEET_NAME = "register";

    public static int DEFAULT_LARGE_TIME_OUT = 10;
    public static int DEFAULT_TIME_OUT = 5;

    public static final int MACBOOK_PRO_IMAGES_COUNT=4;
    public static final int MACBOOK_AIR_IMAGES_COUNT=4;
    public static final int IMAC_IMAGES_COUNT=3;

    public static final String ACC_CREATE_SUCC_MSG="Your Account Has Been Created!";
}

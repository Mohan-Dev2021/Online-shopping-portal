package com.online.shop.utility;

import java.time.LocalDate;

public class EShopConstants {

	public static final String FILE_CONTENT_TYPE = "application/octet-stream";
	public static final String HEADER_VALUE = "attachment; filename=Products"+ LocalDate.now()+".xlsx";
	public static final String FILE_NAME_TYPE = "attachment; filename=\"";
	public static final String ATTACHEMENT_TYPE = "filename=";
	public static final String FORMAT =  ".xlsx";
}

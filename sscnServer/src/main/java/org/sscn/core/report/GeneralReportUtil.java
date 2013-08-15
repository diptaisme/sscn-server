package org.sscn.core.report;

public class GeneralReportUtil {
	/** The default directory. */
	private static String defaultDirectory = "/reports/";
	/** The rpt Test. */
	private static String rptTest = defaultDirectory
			+ "rptTest.jasper";
	private static String rptRegistrasi = defaultDirectory
			+ "rptPengumumanInstansi.jasper";
	
	public static String getRptTest() {
		return rptTest;
	}
	public static String getRptRegistrasi() {
		return rptRegistrasi;
	}
}	

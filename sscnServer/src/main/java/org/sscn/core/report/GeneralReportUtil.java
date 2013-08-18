package org.sscn.core.report;

public class GeneralReportUtil {
	/** The default directory. */
	private static String defaultDirectory = "/reports/";
	/** The rpt Test. */
	private static String rptTest = defaultDirectory + "rptTest.jasper";
	/** The rpt Registrasi. */
	private static String rptRegistrasi = defaultDirectory
			+ "rptRegistrasi.jasper";

	public static String getRptTest() {
		return rptTest;
	}

	public static String getRptRegistrasi() {
		return rptRegistrasi;
	}
}

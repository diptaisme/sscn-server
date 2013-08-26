package org.sscn.core.report;

public class GeneralReportUtil {
	/** The default directory. */
	private static String defaultDirectory = "/reports/";
	/** The logo garuda. */
	public static String PATH_TO_GARUDA_LOGO = "/reports/garuda.png";
	/** The rpt Test. */
	private static String rptTest = defaultDirectory + "rptTest.jasper";
	/** The rpt Registrasi. */
	private static String rptRegistrasi = defaultDirectory
			+ "rptRegistrasi.jasper";
	/** The rpt Peserta Ujian. */
	private static String rptPesertaUjian = defaultDirectory
			+ "rptPesertaUjian.jasper";

	public static String getRptTest() {
		return rptTest;
	}

	public static String getRptRegistrasi() {
		return rptRegistrasi;
	}
	
	public static String getRptPesertaUjian() {
		return rptPesertaUjian;
	}
}

package org.sscn.core.report;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.sscn.core.report.command.ReportCommand;
import org.sscn.core.report.command.ReportTestCommand;
import org.sscn.core.report.command.RptTestXlsCommand;

public class GeneralReportFactory {
	private static final org.apache.commons.logging.Log LOG = LogFactory
			.getLog(GeneralReportFactory.class);
	
	
	/** The Constant RPT_TEST_CETAK. */
	public static final String RPT_TEST_CETAK = "rptTestCetak";
	/** The Constant RPT_TEST_CETAK_XLS. */
	public static final String RPT_TEST_CETAK_XLS = "rptTestCetakXls";
	/** The Constant RPT_TEST_CETAK_XLS. */
	public static final String RPT_DUK_XLS = "rptDukXls";
	
	private static final Map<String, Class<? extends ReportCommand>> REPORT_COMMANDS = new HashMap<String, Class<? extends ReportCommand>>(
			54, 1);
	
	static {
		REPORT_COMMANDS.put(RPT_TEST_CETAK, ReportTestCommand.class);
		REPORT_COMMANDS.put(RPT_TEST_CETAK_XLS, RptTestXlsCommand.class);
	}

	/**
	 * This private constructor is created to prevent instantiation of this
	 * class.
	 * 
	 */
	private GeneralReportFactory() {
		// to prevent instantiation
	}

	/**
	 * This method is a factory method to get the <b> ReportCommand </b> object
	 * according to the <code> reportType </code> parameter given.
	 * 
	 * @param reportType
	 *            String
	 * @return ReportCommand
	 */
	public static ReportCommand getReportCommand(String reportType) {
		ReportCommand reportCommand = null;
		Class<? extends ReportCommand> reportCommandClass = REPORT_COMMANDS
				.get(reportType);
		if (reportCommandClass != null) {
			try {
				reportCommand = reportCommandClass.newInstance();
			} catch (Exception e) {
				LOG.debug("Can't create a ReportCommand object.", e);
			}
		}
		return reportCommand;
	}
}

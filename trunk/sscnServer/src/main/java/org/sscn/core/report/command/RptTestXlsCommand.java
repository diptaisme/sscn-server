package org.sscn.core.report.command;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.sscn.core.report.command.workbook.TestWorkbook;

@Component("RptTestXlsCommand")
public class RptTestXlsCommand extends ReportCommand {

	/** The Constant OUTPUT_STREAM_INITIAL_SIZE. */
	private static final int OUTPUT_STREAM_INITIAL_SIZE = 2048;

	private static final String TEMPLATE = "TEMPLATE";

	public static final String REPORT_TYPE = "XLS";

	private static final int INT_START_INDEX = 4;

	public static final String FORMAT = "FORMAT";

	private static final String START_INDEX = "START_INDEX";

	public static final String PARAM_1 = "PARAM_1";
	public static final String PARAM_2 = "PARAM_2";
	

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
			throws IOException {
		ByteArrayOutputStream bt = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(bt,
				OUTPUT_STREAM_INITIAL_SIZE);
		InputStream inp = new FileInputStream(myMap.get(TEMPLATE).toString());
		TestWorkbook wb = new TestWorkbook(myMap, inp);
		wb.createStyles();
		wb.setHeader();
		if (pMyData.length > 0) {
			// fill excel data
			int startIndex = Integer
					.parseInt(myMap.get(START_INDEX).toString());
			int lastRow = wb.fillData(startIndex, pMyData);
			// wb.createSummary(lastRow, 1);
		}
		wb.write(bos);
		bos.flush();
		bos.close();
		return bt.toByteArray();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
	}

}

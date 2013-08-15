package org.sscn.core.report.command;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component("ReportTestCommand")
public class ReportTestCommand extends ReportCommand {

	/**
	 * serialVersionUID
	 */

	private static final long serialVersionUID = -599742719308669945L;

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		
	}

}

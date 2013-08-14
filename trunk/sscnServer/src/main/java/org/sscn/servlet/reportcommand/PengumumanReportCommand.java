package org.sscn.servlet.reportcommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.sscn.core.report.command.ReportCommand;
import org.sscn.dao.DtPengumumanDao;
import org.sscn.persistence.entities.DtPengumuman;

@Component(value = "pengumumanReportCommand")
public class PengumumanReportCommand extends ReportCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9108544678866277629L;

	@Inject
	private DtPengumumanDao dtPengumumanDao;

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
	        throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException {
		String instansi = request.getParameter("instansi");
		String typeReportString = request.getParameter("typeReport");

		List<DtPengumuman> pengumumans = dtPengumumanDao.findByProperty(
		        "refInstansi.kode", instansi, null);

		if (pengumumans.size() > 0) {
			DtPengumuman thePengumuman = pengumumans.get(0);
			response.setContentType("application/pdf");
			try {
				ServletOutputStream servletOutputStream = response.getOutputStream();
				servletOutputStream.write(thePengumuman.getBerita());
				servletOutputStream.flush();
				servletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			response.setContentType("text/html");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
				        + "</HEAD><BODY>Maaf, Laporan Pengumuman Belum Tersedia</BODY></HTML>");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package org.sscn.core.report.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.sscn.core.report.GeneralReportUtil;
import org.sscn.manager.Constanta;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.services.RegistrasiService;

@Component("ReportRegistrasiCommand")
public class ReportRegistrasiCommand extends ReportCommand {

	@Inject
	private RegistrasiService registrasiService;
	/**
	 * serialVersionUID
	 */

	private static final long serialVersionUID = -599742719308669945L;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		if (request.getParameter("formID") != null) {
			// check form id dari form Pendaftaran web SSCN
			if (request.getParameter("formID").equals("32063786011852")) {
				System.out.println("instansi = "
						+ request.getParameter("instansiValue"));
				System.out.println("jabatan = "
						+ request.getParameter("jabatan"));
				System.out.println("lokasi_kerja = "
						+ request.getParameter("lokasi_kerja"));
				System.out.println("pendidikan = "
						+ request.getParameter("pendidikan"));
				try {
					DtPendaftaran pendafataran = registrasiService
							.insertPendaftaran(request);
					if (pendafataran == null) {
						cetakRegistrasiGagal(response);
					} else {
						// generate pdf :)
						byte[] byteStream = null;
						String instansi = "test saja kok";
						try {
							Map<String, Object> mapParamater = new HashMap<String, Object>();
							mapParamater.put("INSTANSI_KODE", instansi);
							String baseDir = getBaseDirectory(request);
							String fileName = baseDir
									+ GeneralReportUtil
											.getRptRegistrasi();

							List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
							listResult.add(mapParamater);
							this.generalPDFReports(listResult.toArray(),
									request, response, mapParamater, fileName);
						} catch (Exception ex) {
							ex.printStackTrace();
							cetakRegistrasiGagal(response);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					cetakRegistrasiGagal(response);
				}
			} else {
				cetakNotAksesRegistrasi(response);
			}
		} else {
			cetakNotAksesRegistrasi(response);
		}

	}

	private void cetakRegistrasiGagal(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf proses registrasi gagal. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void cetakNotAksesRegistrasi(HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
					+ "</HEAD><BODY>Maaf anda tidak dapat mengakses halaman ini. Klik <a href='"
					+ Constanta.URL_WEB_SSCN
					+ "'>link ini </a> untuk kembali</BODY></HTML>");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected byte[] generateXls(Object[] pMyData, Map<String, Object> myMap)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

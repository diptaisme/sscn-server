package org.sscn.core.report.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.sscn.core.report.GeneralReportUtil;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.manager.Constanta;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.services.PersyaratanService;
import org.sscn.services.RegistrasiService;

@Component("ReportRegistrasiCommand")
public class ReportRegistrasiCommand extends ReportCommand {

	@Inject
	private RefPendidikanDao refPendidikanDao;
	
	@Inject
	private RegistrasiService registrasiService;
	
	@Inject
	private PersyaratanService persyaratanService;
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
				try {
					DtPendaftaran pendaftaran = registrasiService
							.getPendaftaranByNoRegistrasi(request.getParameter("idRegistrasi"));
					
					if (pendaftaran == null) {
						cetakRegistrasiGagal(response);
					} else {
						// generate pdf :)
						try {
							Map<String, Object> mapParamater = generateParameterToReport(pendaftaran);

							String baseDir = getBaseDirectory(request);
							String fileName = baseDir
									+ GeneralReportUtil.getRptRegistrasi();

							List<DtPersyaratan> listPersyaratans = persyaratanService.findByProperty("refInstansi.kode", pendaftaran.getFormasi().getRefInstansi().getKode(), null, null);
							Object[] arrResult = listPersyaratans.toArray();							
							
							this.generalPDFReports(arrResult,
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
					+ "</HEAD><BODY>Maaf proses cetak registrasi gagal. Klik <a href='"
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

	private Map<String, Object> generateParameterToReport(
			DtPendaftaran pendaftaran) {
		Map<String, Object> mapParamater = new HashMap<String, Object>();
		mapParamater.put("NO_REGISTRASI", pendaftaran.getNoRegister());
		mapParamater.put("NAMA", pendaftaran.getNama());
		String tempatLahir = pendaftaran.getTmpLahir();
		SimpleDateFormat formatDateJava = new SimpleDateFormat("dd-MM-yyyy");
		String tglLahir = formatDateJava.format(pendaftaran.getTglLahir());
		mapParamater.put("TTL", tempatLahir + " / " + tglLahir);
		mapParamater.put("NIK", pendaftaran.getNoNik());
		mapParamater.put("UNIVERSITAS", pendaftaran.getLembaga());		
		String pendidikan = refPendidikanDao
					.findByProperty("kode", pendaftaran.getPendidikan(),
							null).get(0).getNama();
		mapParamater.put("PENDIDIKAN", pendidikan);
		mapParamater.put("NO_IJAZAH", pendaftaran.getNoIjazah());
		mapParamater.put("AKREDITAS", pendaftaran.getAkreditasi());
		mapParamater.put("JABATAN", pendaftaran.getFormasi().getRefJabatan()
				.getNama());
		mapParamater.put("INSTANSI", pendaftaran.getFormasi().getRefInstansi()
				.getNama());

		return mapParamater;
	}

}

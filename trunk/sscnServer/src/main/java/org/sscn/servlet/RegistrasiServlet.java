package org.sscn.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.sscn.manager.Constanta;
import org.sscn.manager.DBManager;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.services.RegistrasiService;

/**
 * Servlet implementation class RegistrasiServlet
 */
@Component("RegistrasiServlet")
public class RegistrasiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;

	/** The application context. */
	private ApplicationContext applicationContext;

	public void setApplicationContext(
			ApplicationContext currentApplicationContext) {
		applicationContext = currentApplicationContext;
	}

	/**
	 * Default constructor.
	 */
	public RegistrasiServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		setApplicationContext(WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletContext()));
		try {
			connection = DBManager.getConnection();
		} catch (Exception ex) {
			System.out.println("ex :" + ex.getMessage());
			connection = null;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
				+ "</HEAD><BODY>Maaf anda tidak dapat mengakses halaman ini. Klik <a href='"
				+ Constanta.URL_WEB_SSCN
				+ "'>link ini </a> untuk kembali</BODY></HTML>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("formID") != null) {
			// check form id dari form Pendaftaran web SSCN
			if (request.getParameter("formID").equals("32063786011852")) {
				System.out.println("instansi = "
						+ request.getParameter("instansi"));
				System.out.println("jabatan = "
						+ request.getParameter("jabatan"));
				System.out.println("lokasi_kerja = "
						+ request.getParameter("lokasi_kerja"));
				System.out.println("pendidikan = "
						+ request.getParameter("pendidikan"));
				try {
					RegistrasiService registrasiService = (RegistrasiService) applicationContext
							.getBean("RegistrasiService");
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
							byteStream = JasperRunManager
									.runReportToPdf(
											getServletContext()
													.getRealPath(
															"/WEB-INF/reports/rptPengumumanInstansi.jasper"),
											mapParamater, connection);

							OutputStream outStream = response.getOutputStream();
							response.setHeader("Content-Disposition",
									"attachment, filename=PengumumuanInstansi.pdf");
							response.setContentType("application/pdf");
							response.setContentLength(byteStream.length);
							outStream.write(byteStream, 0, byteStream.length);
							outStream.flush();
						} catch (JRException e) {
							e.printStackTrace();
						} catch (Exception ex) {
							ex.printStackTrace();
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

	private void cetakRegistrasiGagal(HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
				+ "</HEAD><BODY>Maaf proses registrasi gagal. Klik <a href='"
				+ Constanta.URL_WEB_SSCN
				+ "'>link ini </a> untuk kembali</BODY></HTML>");
		out.close();
	}

	private void cetakNotAksesRegistrasi(HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>SSCN</TITLE>"
				+ "</HEAD><BODY>Maaf anda tidak dapat mengakses halaman ini. Klik <a href='"
				+ Constanta.URL_WEB_SSCN
				+ "'>link ini </a> untuk kembali</BODY></HTML>");
		out.close();
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex :" + ex.getMessage());
		}
	}

}

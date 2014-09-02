package org.sscn.persistence.entities.view;

public class DataPendaftaran2014 extends DataPendaftaran {
	private String lokasiKode2;
	private String lokasiNama2;
	private String jabatanKode2;
	private String jabatanNama2;
	private String lokasiKode3;
	private String lokasiNama3;
	private String jabatanKode3;
	private String jabatanNama3;
	private String lokasiTest;
	private String lokasiTestNama;

	public DataPendaftaran2014(String noNik, String noRegister,
			String noPeserta, String nama, String tmpLahir, String tglLahir,
			String jnsKelamin, String alamat, String kodePos, String propinsi,
			String kota, String telpon, String email,
			String asalInstitusiPendidikan, String noIjazah, String akreditasi,
			String nilaiIpk, String instansiKode, String instansiNama,
			String lokasiKode, String lokasiNama, String jabatanKode,
			String jabatanNama, String pendidikanKode, String pendidikanNama,
			String tglCreated, String status, String lokasiKode2,
			String lokasiNama2, String jabatanKode2, String jabatanNama2,
			String lokasiKode3, String lokasiNama3, String jabatanKode3,
			String jabatanNama3, String lokasiTest, String lokasiTestNama) {
		super(noNik, noRegister, noPeserta, nama, tmpLahir, tglLahir,
				jnsKelamin, alamat, kodePos, propinsi, kota, telpon, email,
				asalInstitusiPendidikan, noIjazah, akreditasi, nilaiIpk,
				instansiKode, instansiNama, lokasiKode, lokasiNama,
				jabatanKode, jabatanNama, pendidikanKode, pendidikanNama,
				tglCreated, status);
		this.lokasiKode2 = lokasiKode2;
		this.lokasiNama2 = lokasiNama2;
		this.jabatanKode2 = jabatanKode2;
		this.jabatanNama2 = jabatanNama2;
		this.lokasiKode3 = lokasiKode3;
		this.lokasiNama3 = lokasiNama3;
		this.jabatanKode3 = jabatanKode3;
		this.jabatanNama3 = jabatanNama3;
		this.lokasiTest = lokasiTest;
		this.lokasiTestNama = lokasiTestNama;
	}

	public DataPendaftaran2014() {

	}

	public String getLokasiKode2() {
		return lokasiKode2;
	}

	public void setLokasiKode2(String lokasiKode2) {
		if (lokasiKode2 != null && lokasiKode2.equalsIgnoreCase("null")) {
			lokasiKode2 = "";
		}
		this.lokasiKode2 = lokasiKode2;
	}

	public String getLokasiNama2() {
		return lokasiNama2;
	}

	public void setLokasiNama2(String lokasiNama2) {
		if (lokasiNama2 != null && lokasiNama2.equalsIgnoreCase("null")) {
			lokasiNama2 = "";
		}
		this.lokasiNama2 = lokasiNama2;
	}

	public String getJabatanKode2() {
		return jabatanKode2;
	}

	public void setJabatanKode2(String jabatanKode2) {
		if (jabatanKode2 != null && jabatanKode2.equalsIgnoreCase("null")) {
			jabatanKode2 = "";
		}
		this.jabatanKode2 = jabatanKode2;
	}

	public String getJabatanNama2() {
		return jabatanNama2;
	}

	public void setJabatanNama2(String jabatanNama2) {
		if (jabatanNama2 != null && jabatanNama2.equalsIgnoreCase("null")) {
			jabatanNama2 = "";
		}
		this.jabatanNama2 = jabatanNama2;
	}

	public String getLokasiKode3() {
		return lokasiKode3;
	}

	public void setLokasiKode3(String lokasiKode3) {
		if (lokasiKode3 != null && lokasiKode3.equalsIgnoreCase("null")) {
			lokasiKode3 = "";
		}
		this.lokasiKode3 = lokasiKode3;
	}

	public String getLokasiNama3() {
		return lokasiNama3;
	}

	public void setLokasiNama3(String lokasiNama3) {
		if (lokasiNama3 != null && lokasiNama3.equalsIgnoreCase("null")) {
			lokasiNama3 = "";
		}
		this.lokasiNama3 = lokasiNama3;
	}

	public String getJabatanKode3() {
		return jabatanKode3;
	}

	public void setJabatanKode3(String jabatanKode3) {
		if (jabatanKode3 != null && jabatanKode3.equalsIgnoreCase("null")) {
			jabatanKode3 = "";
		}
		this.jabatanKode3 = jabatanKode3;
	}

	public String getJabatanNama3() {
		return jabatanNama3;
	}

	public void setJabatanNama3(String jabatanNama3) {
		if (jabatanNama3 != null && jabatanNama3.equalsIgnoreCase("null")) {
			jabatanNama3 = "";
		}
		this.jabatanNama3 = jabatanNama3;
	}

	public String getLokasiTest() {
		return lokasiTest;
	}

	public void setLokasiTest(String lokasiTest) {
		if (lokasiTest != null && lokasiTest.equalsIgnoreCase("null")) {
			lokasiTest = "";
		}
		this.lokasiTest = lokasiTest;
	}

	public String getLokasiTestNama() {
		return lokasiTestNama;
	}

	public void setLokasiTestNama(String lokasiTestNama) {
		if (lokasiTestNama != null && lokasiTestNama.equalsIgnoreCase("null")) {
			lokasiTestNama = "";
		}
		this.lokasiTestNama = lokasiTestNama;
	}

}

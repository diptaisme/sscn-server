package org.sscn.persistence.entities.view;

public class StatInstansiJabatan3Pilihan {
	private String kode;
	private String instansi;
	private String jabatan;
	private String pilihan1;
	private String pilihan2;
	private String pilihan3;	

	public StatInstansiJabatan3Pilihan() {
	}

	public StatInstansiJabatan3Pilihan(String kode, String instansi,
			String jabatan, String pilihan1, String pilihan2, String pilihan3) {
		super();
		this.kode = kode;
		this.instansi = instansi;
		this.jabatan = jabatan;
		this.pilihan1 = pilihan1;
		this.pilihan2 = pilihan2;
		this.pilihan3 = pilihan3;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getInstansi() {
		return instansi;
	}

	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getPilihan1() {
		return pilihan1;
	}

	public void setPilihan1(String pilihan1) {
		this.pilihan1 = pilihan1;
	}

	public String getPilihan2() {
		return pilihan2;
	}

	public void setPilihan2(String pilihan2) {
		this.pilihan2 = pilihan2;
	}

	public String getPilihan3() {
		return pilihan3;
	}

	public void setPilihan3(String pilihan3) {
		this.pilihan3 = pilihan3;
	}

	public String getTotal() {
		return ""+(Integer.parseInt(pilihan1) + Integer.parseInt(pilihan2) + Integer.parseInt(pilihan3));
	}	
}

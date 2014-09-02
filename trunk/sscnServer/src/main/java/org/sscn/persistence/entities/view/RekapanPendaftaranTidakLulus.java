package org.sscn.persistence.entities.view;

public class RekapanPendaftaranTidakLulus {
	private String nama;
	private String nik;
	private String syarat;
	public RekapanPendaftaranTidakLulus() {
		// NOOP
	}
	
	public RekapanPendaftaranTidakLulus(String nama, String nik, String syarat) {
		super();
		this.nama = nama;
		this.nik = nik;
		this.syarat = syarat;
	}
	/**
	 * @return the nama
	 */
	public String getNama() {
		return nama;
	}
	/**
	 * @param nama the nama to set
	 */
	public void setNama(String nama) {
		this.nama = nama;
	}
	/**
	 * @return the nik
	 */
	public String getNik() {
		return nik;
	}
	/**
	 * @param nik the nik to set
	 */
	public void setNik(String nik) {
		this.nik = nik;
	}
	/**
	 * @return the syarat
	 */
	public String getSyarat() {
		return syarat;
	}
	/**
	 * @param syarat the syarat to set
	 */
	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}
	
	
}

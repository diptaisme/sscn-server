package org.sscn.persistence.entities;

// Generated Jul 26, 2013 12:03:44 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtSyaratAdm generated by hbm2java
 */
@Entity
@Table(name = "dt_syarat_adm", catalog = "dbseleksicpns")
public class DtSyaratAdm implements java.io.Serializable {

	private String noRegister;
	private String instansi;
	private String kode;
	private String keterangan;

	public DtSyaratAdm() {
	}

	public DtSyaratAdm(String noRegister, String instansi, String kode,
			String keterangan) {
		this.noRegister = noRegister;
		this.instansi = instansi;
		this.kode = kode;
		this.keterangan = keterangan;
	}

	@Id
	@Column(name = "NO_REGISTER", unique = true, nullable = false, length = 10)
	public String getNoRegister() {
		return this.noRegister;
	}

	public void setNoRegister(String noRegister) {
		this.noRegister = noRegister;
	}

	@Column(name = "INSTANSI", nullable = false, length = 4)
	public String getInstansi() {
		return this.instansi;
	}

	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}

	@Column(name = "KODE", nullable = false, length = 2)
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	@Column(name = "KETERANGAN", nullable = false, length = 1)
	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}

package org.sscn.persistence.entities;

// Generated Aug 14, 2013 11:26:30 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * RefLokasi generated by hbm2java
 */
@Entity
@Table(name = "ref_lokasi")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RefLokasi implements java.io.Serializable {

	private String kode;
	private RefInstansi refInstansi;
	private String nama;
	private String status;

	public RefLokasi() {
	}

	public RefLokasi(String kode, RefInstansi refInstansi) {
		this.kode = kode;
		this.refInstansi = refInstansi;
	}

	public RefLokasi(String kode, RefInstansi refInstansi, String nama,
			String status) {
		this.kode = kode;
		this.refInstansi = refInstansi;
		this.nama = nama;
		this.status = status;
	}

	@Id
	@Column(name = "kode", unique = true, nullable = false, length = 8)
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "INSTANSI", nullable = false)	
	public RefInstansi getRefInstansi() {
		return this.refInstansi;
	}

	public void setRefInstansi(RefInstansi refInstansi) {
		this.refInstansi = refInstansi;
	}

	@Column(name = "nama", length = 100)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

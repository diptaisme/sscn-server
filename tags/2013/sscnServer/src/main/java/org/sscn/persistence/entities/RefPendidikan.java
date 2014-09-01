package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * RefPendidikan generated by hbm2java
 */
@Entity
@Table(name = "ref_pendidikan", catalog = "dbseleksicpns")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RefPendidikan implements java.io.Serializable {

	private String kode;
	private String nama;
	private String tingkat;

	public RefPendidikan() {
	}

	public RefPendidikan(String kode) {
		this.kode = kode;
	}

	public RefPendidikan(String kode, String nama, String tingkat) {
		this.kode = kode;
		this.nama = nama;
		this.tingkat = tingkat;
	}

	@Id
	@Column(name = "KODE", unique = true, nullable = false, length = 7)
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	@Column(name = "NAMA", length = 60)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "TINGKAT", length = 2)
	public String getTingkat() {
		return this.tingkat;
	}

	public void setTingkat(String tingkat) {
		this.tingkat = tingkat;
	}

}
package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DtUser generated by hbm2java
 */
@Entity
@Table(name = "dt_user", catalog = "dbseleksicpns")
public class DtUser implements java.io.Serializable {

	private String username;
	private RefInstansi refInstansi;
	private String nip;
	private String nama;
	private String password;
	private String kewenangan;
	private Date tglCreated;
	private Date tglUpdated;
	private String nipAdmin;

	public DtUser() {
	}

	public DtUser(String username, RefInstansi refInstansi, String nip,
			String nama, String password, String kewenangan, Date tglCreated,
			Date tglUpdated, String nipAdmin) {
		this.username = username;
		this.refInstansi = refInstansi;
		this.nip = nip;
		this.nama = nama;
		this.password = password;
		this.kewenangan = kewenangan;
		this.tglCreated = tglCreated;
		this.tglUpdated = tglUpdated;
		this.nipAdmin = nipAdmin;
	}

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTANSI", nullable = false)
	public RefInstansi getRefInstansi() {
		return this.refInstansi;
	}

	public void setRefInstansi(RefInstansi refInstansi) {
		this.refInstansi = refInstansi;
	}

	@Column(name = "NIP", nullable = false, length = 18)
	public String getNip() {
		return this.nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	@Column(name = "NAMA", nullable = false, length = 100)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "PASSWORD", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "KEWENANGAN", nullable = false, length = 1)
	public String getKewenangan() {
		return this.kewenangan;
	}

	public void setKewenangan(String kewenangan) {
		this.kewenangan = kewenangan;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TGL_CREATED", nullable = false, length = 10)
	public Date getTglCreated() {
		return this.tglCreated;
	}

	public void setTglCreated(Date tglCreated) {
		this.tglCreated = tglCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TGL_UPDATED", nullable = false, length = 10)
	public Date getTglUpdated() {
		return this.tglUpdated;
	}

	public void setTglUpdated(Date tglUpdated) {
		this.tglUpdated = tglUpdated;
	}

	@Column(name = "NIP_ADMIN", nullable = false, length = 18)
	public String getNipAdmin() {
		return this.nipAdmin;
	}

	public void setNipAdmin(String nipAdmin) {
		this.nipAdmin = nipAdmin;
	}

}
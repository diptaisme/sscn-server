package org.sscn.persistence.entities;

// Generated Aug 14, 2013 12:27:10 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * DtPendaftaran generated by hbm2java
 */
@Entity
@Table(name = "dt_peserta_cpns2013", catalog = "dbseleksicpns", uniqueConstraints = @UniqueConstraint(columnNames = "NO_PESERTA"))
public class DtPeserta implements java.io.Serializable {
	
	private String noPeserta;
	private String noRegister;
	private String nama;
	private String tglLahir;
	private DtHasil dtHasil;
	
	public DtPeserta(Long id, MFormasi formasi, String noPeserta, String noRegister, String nama,
			String tglLahir) {
		super();
		
	//	this.formasi = formasi;
		this.noPeserta = noPeserta;
		this.noRegister = noRegister;
		this.nama = nama;
		this.tglLahir = tglLahir;
	}


	public DtPeserta() {
		
	}
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMASI_ID", nullable = false)
	public MFormasi getFormasi() {
		return this.formasi;
	}

	public void setFormasi(MFormasi formasi) {
		this.formasi = formasi;
	} */

	@Column(name = "NO_REGISTER", unique = true, nullable = false, length = 10)
	public String getNoRegister() {
		return this.noRegister;
	}

	public void setNoRegister(String noRegister) {
		this.noRegister = noRegister;
	}

	@Column(name = "NAMA", nullable = false, length = 100)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "TGL_LHR_LJK", nullable = false, length = 8)
	public String getTglLahir() {
		return this.tglLahir;
	}

	public void setTglLahir(String tglLahir) {
		this.tglLahir = tglLahir;
	}
	
	@Id
	@Column(name = "NO_PESERTA", nullable = true, length = 11)
	public String getNoPeserta() {
		return this.noPeserta;
	}

	public void setNoPeserta(String noPeserta) {
		this.noPeserta = noPeserta;
	}


	/**
	 * @return the dtHasil
	 */
	public DtHasil getDtHasil() {
		return dtHasil;
	}


	/**
	 * @param dtHasil the dtHasil to set
	 */
	public void setDtHasil(DtHasil dtHasil) {
		this.dtHasil = dtHasil;
	}
}

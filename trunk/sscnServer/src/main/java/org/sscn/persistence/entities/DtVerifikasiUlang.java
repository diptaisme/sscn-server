package org.sscn.persistence.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "log_verifikasi_ulang")
public class DtVerifikasiUlang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2042267209404544143L;
	/**
	 * 
	 */
	
	private Integer id;
	private String pendaftar;
	private String persyaratan;
	private String verifikator;
	private String userReset;
	private String status;

	public DtVerifikasiUlang() {
		// NOP
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the pendaftar
	 */
	@Column(name = "no_pendaftar", nullable = false, length = 500)
	public String getPendaftar() {
		return pendaftar;
	}

	/**
	 * @param pendaftar the pendaftar to set
	 */
	public void setPendaftar(String pendaftar) {
		this.pendaftar = pendaftar;
	}

	/**
	 * @return the persyaratan
	 */
	public String getPersyaratan() {
		return persyaratan;
	}

	/**
	 * @param persyaratan the persyaratan to set
	 */
	@Column(name = "syarat", nullable = false, length = 100)
	public void setPersyaratan(String persyaratan) {
		this.persyaratan = persyaratan;
	}

	/**
	 * @return the verifikator
	 */
	public String getVerifikator() {
		return verifikator;
	}

	/**
	 * @param verifikator the verifikator to set
	 */
	public void setVerifikator(String verifikator) {
		this.verifikator = verifikator;
	}

	/**
	 * @return the userReset
	 */
	@Column(name = "user_reset", nullable = false, length = 100)
	public String getUserReset() {
		return userReset;
	}

	/**
	 * @param userReset the userReset to set
	 */
	public void setUserReset(String userReset) {
		this.userReset = userReset;
	}

	/**
	 * @return the status
	 */
	@Column(name = "status", nullable = false, length = 100)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

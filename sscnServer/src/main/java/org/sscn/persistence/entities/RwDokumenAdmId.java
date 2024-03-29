package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RwDokumenAdmId generated by hbm2java
 */
@Embeddable
public class RwDokumenAdmId implements java.io.Serializable {

	private String noReg;
	private String instansi;
	private String kode;
	private String status;
	private String keterangan;

	public RwDokumenAdmId() {
	}

	public RwDokumenAdmId(String noReg, String instansi, String kode,
			String status, String keterangan) {
		this.noReg = noReg;
		this.instansi = instansi;
		this.kode = kode;
		this.status = status;
		this.keterangan = keterangan;
	}

	@Column(name = "NO_REG", nullable = false, length = 10)
	public String getNoReg() {
		return this.noReg;
	}

	public void setNoReg(String noReg) {
		this.noReg = noReg;
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

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "KETERANGAN", nullable = false, length = 100)
	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RwDokumenAdmId))
			return false;
		RwDokumenAdmId castOther = (RwDokumenAdmId) other;

		return ((this.getNoReg() == castOther.getNoReg()) || (this.getNoReg() != null
				&& castOther.getNoReg() != null && this.getNoReg().equals(
				castOther.getNoReg())))
				&& ((this.getInstansi() == castOther.getInstansi()) || (this
						.getInstansi() != null
						&& castOther.getInstansi() != null && this
						.getInstansi().equals(castOther.getInstansi())))
				&& ((this.getKode() == castOther.getKode()) || (this.getKode() != null
						&& castOther.getKode() != null && this.getKode()
						.equals(castOther.getKode())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getKeterangan() == castOther.getKeterangan()) || (this
						.getKeterangan() != null
						&& castOther.getKeterangan() != null && this
						.getKeterangan().equals(castOther.getKeterangan())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getNoReg() == null ? 0 : this.getNoReg().hashCode());
		result = 37 * result
				+ (getInstansi() == null ? 0 : this.getInstansi().hashCode());
		result = 37 * result
				+ (getKode() == null ? 0 : this.getKode().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getKeterangan() == null ? 0 : this.getKeterangan()
						.hashCode());
		return result;
	}

}

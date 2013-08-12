package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtLaws generated by hbm2java
 */
@Entity
@Table(name = "dt_laws", catalog = "dbseleksicpns")
public class DtLaws implements java.io.Serializable {

	private String id;
	private String nomor;
	private String aturan;

	public DtLaws() {
	}

	public DtLaws(String id, String nomor, String aturan) {
		this.id = id;
		this.nomor = nomor;
		this.aturan = aturan;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 2)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NOMOR", nullable = false, length = 20)
	public String getNomor() {
		return this.nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	@Column(name = "ATURAN", nullable = false, length = 100)
	public String getAturan() {
		return this.aturan;
	}

	public void setAturan(String aturan) {
		this.aturan = aturan;
	}

}

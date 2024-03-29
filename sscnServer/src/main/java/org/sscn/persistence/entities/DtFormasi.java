package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DtFormasi generated by hbm2java
 */
@Entity
@Table(name = "dt_formasi")
public class DtFormasi implements java.io.Serializable {

	private Integer id;
	private MFormasi formasi;
	private RefPendidikan pendidikan;
	private Integer jumlah;

	public DtFormasi() {
	}

	public DtFormasi(MFormasi formasi) {
		this.formasi = formasi;
	}

	public DtFormasi(MFormasi MFormasi, RefPendidikan pendidikan, Integer jumlah) {
		this.formasi = MFormasi;
		this.pendidikan = pendidikan;
		this.jumlah = jumlah;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMASI", nullable = false)
	public MFormasi getFormasi() {
		return this.formasi;
	}

	public void setFormasi(MFormasi formasi) {
		this.formasi = formasi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PENDIDIKAN", nullable = false)
	public RefPendidikan getPendidikan() {
		return this.pendidikan;
	}

	public void setPendidikan(RefPendidikan pendidikan) {
		this.pendidikan = pendidikan;
	}

	@Column(name = "JUMLAH")
	public Integer getJumlah() {
		return this.jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

}

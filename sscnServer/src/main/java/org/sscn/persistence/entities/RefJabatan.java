package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * RefJabatan generated by hbm2java
 */
@Entity
@Table(name = "ref_jabatan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RefJabatan implements java.io.Serializable {

	private String kode;
	private String nama;
	private Set<MFormasi> MFormasis = new HashSet<MFormasi>(0);

	public RefJabatan() {
	}

	public RefJabatan(String kode) {
		this.kode = kode;
	}

	public RefJabatan(String kode, String nama, Set<MFormasi> MFormasis) {
		this.kode = kode;
		this.nama = nama;
		this.MFormasis = MFormasis;
	}

	@Id
	@Column(name = "KODE", unique = true, nullable = false, length = 7)
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	@Column(name = "NAMA", length = 100)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "refJabatan")
	public Set<MFormasi> getMFormasis() {
		return this.MFormasis;
	}

	public void setMFormasis(Set<MFormasi> MFormasis) {
		this.MFormasis = MFormasis;
	}

}

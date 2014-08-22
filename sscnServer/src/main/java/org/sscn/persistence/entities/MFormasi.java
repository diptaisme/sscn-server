package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MFormasi generated by hbm2java
 */
@Entity
@Table(name = "m_formasi")
public class MFormasi implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6070570916776524270L;
	private Integer id;
	private RefLokasi refLokasi;
	private RefInstansi refInstansi;
	private RefJabatan refJabatan;
	private Integer jumlah;
	private RefJenisFormasi refJenisFormasi;
	private Set<DtPendaftaran> dtPendaftarans = new HashSet<DtPendaftaran>(0);
	private Set<DtFormasi> dtFormasis = new HashSet<DtFormasi>(0);

	public MFormasi() {
		//NOOP
	}

	public MFormasi(RefLokasi refLokasi, RefInstansi refInstansi,
			RefJabatan refJabatan) {
		this.refLokasi = refLokasi;
		this.refInstansi = refInstansi;
		this.refJabatan = refJabatan;
	}

	public MFormasi(RefLokasi refLokasi, RefInstansi refInstansi,
			RefJabatan refJabatan, Integer jumlah,
			Set<DtPendaftaran> dtPendaftarans, Set<DtFormasi> dtFormasis) {
		this.refLokasi = refLokasi;
		this.refInstansi = refInstansi;
		this.refJabatan = refJabatan;
		this.jumlah = jumlah;
		this.dtPendaftarans = dtPendaftarans;
		this.dtFormasis = dtFormasis;
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
	@JoinColumn(name = "LOKASI", nullable = false)
	public RefLokasi getRefLokasi() {
		return this.refLokasi;
	}

	public void setRefLokasi(RefLokasi refLokasi) {
		this.refLokasi = refLokasi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTANSI", nullable = false)
	public RefInstansi getRefInstansi() {
		return this.refInstansi;
	}

	public void setRefInstansi(RefInstansi refInstansi) {
		this.refInstansi = refInstansi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JABATAN", nullable = false)
	public RefJabatan getRefJabatan() {
		return this.refJabatan;
	}

	public void setRefJabatan(RefJabatan refJabatan) {
		this.refJabatan = refJabatan;
	}

	@Column(name = "JUMLAH")
	public Integer getJumlah() {
		return this.jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formasi")
	public Set<DtPendaftaran> getDtPendaftarans() {
		return this.dtPendaftarans;
	}

	public void setDtPendaftarans(Set<DtPendaftaran> dtPendaftarans) {
		this.dtPendaftarans = dtPendaftarans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formasi")
	public Set<DtFormasi> getDtFormasis() {
		return this.dtFormasis;
	}

	public void setDtFormasis(Set<DtFormasi> dtFormasis) {
		this.dtFormasis = dtFormasis;
	}

	public void setRefJenisFormasi(RefJenisFormasi jenis) {
		this.refJenisFormasi = jenis;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jenis_formasi", nullable = false)
	public RefJenisFormasi getRefJenisFormasi() {
		return this.refJenisFormasi;
	}
}

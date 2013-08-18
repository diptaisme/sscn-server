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
@Table(name = "dt_pendaftaran", catalog = "dbseleksicpns", uniqueConstraints = @UniqueConstraint(columnNames = "NO_REGISTER"))
public class DtPendaftaran implements java.io.Serializable {

	private Long id;
	private MFormasi formasi;
	private String noNik;
	private String noRegister;
	private String nama;
	private String tmpLahir;
	private Date tglLahir;
	private String jnsKelamin;
	private String alamat;
	private String kodePos;
	private String propinsi;
	private String kota;
	private String telpon;
	private String email;
	private String pendidikan;
	private String lembaga;
	private String noIjazah;
	private String status;
	private String regStatus;
	private String noPeserta;
	private Date tglTest;
	private String lokasiTest;
	private Date tglCreated;
	private Date tglUpdated;
	private String userValidate;
	private Date tglValidate;
	private String keterangan;
	private String akreditasi;
	private String nilaiIpk;

	public DtPendaftaran() {
	}

	public DtPendaftaran(MFormasi mFormasi, String noNik, String noRegister,
			String nama, String tmpLahir, Date tglLahir, String jnsKelamin,
			String alamat, String kodePos, String propinsi, String kota,
			String telpon, String email, String pendidikan, String lembaga,
			String noIjazah, String status, String regStatus, String noPeserta,
			Date tglTest, String lokasiTest, Date tglCreated, Date tglUpdated,
			String userValidate, Date tglValidate, String keterangan,
			String akreditasi, String nilaiIpk) {
		this.formasi = mFormasi;
		this.noNik = noNik;
		this.noRegister = noRegister;
		this.nama = nama;
		this.tmpLahir = tmpLahir;
		this.tglLahir = tglLahir;
		this.jnsKelamin = jnsKelamin;
		this.alamat = alamat;
		this.kodePos = kodePos;
		this.propinsi = propinsi;
		this.kota = kota;
		this.telpon = telpon;
		this.email = email;
		this.pendidikan = pendidikan;
		this.lembaga = lembaga;
		this.noIjazah = noIjazah;
		this.status = status;
		this.regStatus = regStatus;
		this.noPeserta = noPeserta;
		this.tglTest = tglTest;
		this.lokasiTest = lokasiTest;
		this.tglCreated = tglCreated;
		this.tglUpdated = tglUpdated;
		this.userValidate = userValidate;
		this.tglValidate = tglValidate;
		this.keterangan = keterangan;
		this.akreditasi = akreditasi;
		this.nilaiIpk = nilaiIpk;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMASI_ID", nullable = false)
	public MFormasi getFormasi() {
		return this.formasi;
	}

	public void setFormasi(MFormasi formasi) {
		this.formasi = formasi;
	}

	@Column(name = "NO_NIK", nullable = false, length = 16)
	public String getNoNik() {
		return this.noNik;
	}

	public void setNoNik(String noNik) {
		this.noNik = noNik;
	}

	@Column(name = "NO_REGISTER", unique = true, nullable = false, length = 19)
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

	@Column(name = "TMP_LAHIR", nullable = false, length = 30)
	public String getTmpLahir() {
		return this.tmpLahir;
	}

	public void setTmpLahir(String tmpLahir) {
		this.tmpLahir = tmpLahir;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_LAHIR", nullable = false, length = 10)
	public Date getTglLahir() {
		return this.tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}

	@Column(name = "JNS_KELAMIN", nullable = false, length = 1)
	public String getJnsKelamin() {
		return this.jnsKelamin;
	}

	public void setJnsKelamin(String jnsKelamin) {
		this.jnsKelamin = jnsKelamin;
	}

	@Column(name = "ALAMAT", nullable = false, length = 200)
	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	@Column(name = "KODE_POS", nullable = false, length = 5)
	public String getKodePos() {
		return this.kodePos;
	}

	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}

	@Column(name = "PROPINSI", nullable = false, length = 150)
	public String getPropinsi() {
		return this.propinsi;
	}

	public void setPropinsi(String propinsi) {
		this.propinsi = propinsi;
	}

	@Column(name = "KOTA", nullable = false, length = 150)
	public String getKota() {
		return this.kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	@Column(name = "TELPON", nullable = false, length = 12)
	public String getTelpon() {
		return this.telpon;
	}

	public void setTelpon(String telpon) {
		this.telpon = telpon;
	}

	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PENDIDIKAN", nullable = false, length = 50)
	public String getPendidikan() {
		return this.pendidikan;
	}

	public void setPendidikan(String pendidikan) {
		this.pendidikan = pendidikan;
	}

	@Column(name = "LEMBAGA", nullable = false, length = 100)
	public String getLembaga() {
		return this.lembaga;
	}

	public void setLembaga(String lembaga) {
		this.lembaga = lembaga;
	}

	@Column(name = "NO_IJAZAH", nullable = false, length = 20)
	public String getNoIjazah() {
		return this.noIjazah;
	}

	public void setNoIjazah(String noIjazah) {
		this.noIjazah = noIjazah;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "REG_STATUS", nullable = false, length = 1)
	public String getRegStatus() {
		return this.regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	@Column(name = "NO_PESERTA", nullable = false, length = 10)
	public String getNoPeserta() {
		return this.noPeserta;
	}

	public void setNoPeserta(String noPeserta) {
		this.noPeserta = noPeserta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_TEST", nullable = false, length = 10)
	public Date getTglTest() {
		return this.tglTest;
	}

	public void setTglTest(Date tglTest) {
		this.tglTest = tglTest;
	}

	@Column(name = "LOKASI_TEST", nullable = false, length = 6)
	public String getLokasiTest() {
		return this.lokasiTest;
	}

	public void setLokasiTest(String lokasiTest) {
		this.lokasiTest = lokasiTest;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_CREATED", nullable = false, length = 10)
	public Date getTglCreated() {
		return this.tglCreated;
	}

	public void setTglCreated(Date tglCreated) {
		this.tglCreated = tglCreated;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_UPDATED", nullable = false, length = 10)
	public Date getTglUpdated() {
		return this.tglUpdated;
	}

	public void setTglUpdated(Date tglUpdated) {
		this.tglUpdated = tglUpdated;
	}

	@Column(name = "USER_VALIDATE", nullable = false, length = 18)
	public String getUserValidate() {
		return this.userValidate;
	}

	public void setUserValidate(String userValidate) {
		this.userValidate = userValidate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TGL_VALIDATE", nullable = false, length = 10)
	public Date getTglValidate() {
		return this.tglValidate;
	}

	public void setTglValidate(Date tglValidate) {
		this.tglValidate = tglValidate;
	}

	@Column(name = "KETERANGAN", nullable = false, length = 200)
	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	@Column(name = "AKREDITASI", nullable = false, length = 2)
	public String getAkreditasi() {
		return this.akreditasi;
	}

	public void setAkreditasi(String akreditasi) {
		this.akreditasi = akreditasi;
	}

	@Column(name = "NILAI_IPK", nullable = false, length = 5)
	public String getNilaiIpk() {
		return this.nilaiIpk;
	}

	public void setNilaiIpk(String nilaiIpk) {
		this.nilaiIpk = nilaiIpk;
	}

}

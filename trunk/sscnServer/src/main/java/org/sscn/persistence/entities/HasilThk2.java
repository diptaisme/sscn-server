package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DtHasil generated by hbm2java
 */
@Entity
@Table(name = "HASIL_THK2")
public class HasilThk2 implements java.io.Serializable {

	private String no;
	private InstansiThk2 instansiThk2;
	private String noTest;
	private String nama;
	private String flag;
	
	public HasilThk2() {
	}

	@Column(name = "NO", nullable = false, length = 5)
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "KD_INSKER", nullable = false)	
	public InstansiThk2 getInstansiThk2() {
		return this.instansiThk2;
	}

	public void setInstansiThk2(InstansiThk2 ins) {
		this.instansiThk2 = ins;
	}


	/**
	 * @return the noTest
	 */
	@Id
	@Column(name = "no_test", length = 12)
	public String getNoTest() {
		return noTest;
	}


	/**
	 * @param noTest the noTest to set
	 */
	public void setNoTest(String noTest) {
		this.noTest = noTest;
	}


	/**
	 * @return the nama
	 */
	@Column(name = "nama", length = 200)
	public String getNama() {
		return nama;
	}


	/**
	 * @param nama the nama to set
	 */
	public void setNama(String nama) {
		this.nama = nama;
	}


	/**
	 * @return the flag
	 */
	@Column(name = "flag", length = 1)
	public String getFlag() {
		return flag;
	}


	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

}

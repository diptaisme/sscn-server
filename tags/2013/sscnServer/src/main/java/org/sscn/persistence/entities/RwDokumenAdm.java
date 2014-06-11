package org.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RwDokumenAdm generated by hbm2java
 */
@Entity
@Table(name = "rw_dokumen_adm", catalog = "dbseleksicpns")
public class RwDokumenAdm implements java.io.Serializable {

	private RwDokumenAdmId id;

	public RwDokumenAdm() {
	}

	public RwDokumenAdm(RwDokumenAdmId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "noReg", column = @Column(name = "NO_REG", nullable = false, length = 10)),
			@AttributeOverride(name = "instansi", column = @Column(name = "INSTANSI", nullable = false, length = 4)),
			@AttributeOverride(name = "kode", column = @Column(name = "KODE", nullable = false, length = 2)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 1)),
			@AttributeOverride(name = "keterangan", column = @Column(name = "KETERANGAN", nullable = false, length = 100)) })
	public RwDokumenAdmId getId() {
		return this.id;
	}

	public void setId(RwDokumenAdmId id) {
		this.id = id;
	}

}

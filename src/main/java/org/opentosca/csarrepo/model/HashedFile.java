package org.opentosca.csarrepo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Thomas Kosch (mail@thomaskosch.com)
 */

@Entity
@Table(name = "hashed_file")
public class HashedFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hashed_file_id")
	private long id;

	@Column(name = "hash")
	private String hash;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "size")
	private String size;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "hashedFile")
	private List<CsarFile> csarFiles;

	public HashedFile() {
		this.csarFiles = new ArrayList<CsarFile>();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 *
	 * @param fileName
	 *            the filename to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 *
	 * @return the filename
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 *
	 * @param size
	 *            the size of the hashed file
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 *
	 * @return the size of the file
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Returns all CSAR files of the current CSAR
	 *
	 * @return the list of CSAR files
	 */
	public List<CsarFile> getCsarFiles() {
		return csarFiles;
	}
}
package org.jboss.orange.crud.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import org.jboss.orange.crud.model.History;
import javax.persistence.ManyToOne;
@Entity
public class Statistic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String buzzword;

	@Column
	private int instanceCount;

	@Column
	private int frequency;

	@Column
	private int density;

	@ManyToOne
	private History history;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Statistic)) {
			return false;
		}
		Statistic other = (Statistic) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getBuzzword() {
		return buzzword;
	}

	public void setBuzzword(String buzzword) {
		this.buzzword = buzzword;
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount) {
		this.instanceCount = instanceCount;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (buzzword != null && !buzzword.trim().isEmpty())
			result += "buzzword: " + buzzword;
		result += ", instanceCount: " + instanceCount;
		result += ", frequency: " + frequency;
		result += ", density: " + density;
		return result;
	}

	public History getHistory() {
		return this.history;
	}

	public void setHistory(final History history) {
		this.history = history;
	}
}
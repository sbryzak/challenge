package org.jboss.orange.crud.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import org.jboss.orange.crud.model.User;
import javax.persistence.ManyToOne;
@Entity
public class Buzzword implements Serializable {

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
	private boolean exclude;

	@ManyToOne
	private User user;

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
		if (!(obj instanceof Buzzword)) {
			return false;
		}
		Buzzword other = (Buzzword) obj;
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

	public boolean isExclude() {
		return exclude;
	}

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (buzzword != null && !buzzword.trim().isEmpty())
			result += "buzzword: " + buzzword;
		result += ", exclude: " + exclude;
		return result;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}
}
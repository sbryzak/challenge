package org.jboss.orange.crud.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.jboss.orange.crud.model.User;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
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

	@ManyToOne
	private User user;

	@Column
	private boolean exclude;

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

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public boolean isInclude() {
		return exclude;
	}

	public void setInclude(boolean include) {
		this.exclude = include;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (buzzword != null && !buzzword.trim().isEmpty())
			result += "buzzword: " + buzzword;
		result += ", include: " + exclude;
		return result;
	}
}
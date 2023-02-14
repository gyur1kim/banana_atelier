package com.ssafy.banana.db.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class CurationBookmarkId implements Serializable {
	private static final long serialVersionUID = 7238271834440935619L;
	@NotNull
	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@NotNull
	@Column(name = "curation_seq", nullable = false)
	private Long curationSeq;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		CurationBookmarkId entity = (CurationBookmarkId)o;
		return Objects.equals(this.curationSeq, entity.curationSeq) &&
			Objects.equals(this.userSeq, entity.userSeq);
	}

	@Override
	public int hashCode() {
		return Objects.hash(curationSeq, userSeq);
	}

}

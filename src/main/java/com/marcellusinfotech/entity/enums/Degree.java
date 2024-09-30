package com.marcellusinfotech.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Degree {
	BE_CSE("BE_CSE"), BE_NON_CSE("BE_NON_CSE"), PG("PG"), PHD("PHD");

	private final String degreeType;
}

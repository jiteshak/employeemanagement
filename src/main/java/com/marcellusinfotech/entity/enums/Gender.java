package com.marcellusinfotech.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

	MALE("Male"), FEMALE("Female"), OTHERS("Others");

	private final String displayName;

}

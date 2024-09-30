package com.marcellusinfotech.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AddressType {
	PERMANENT("PERMANENT"), TEMPORARY("TEMPORARY");

	private final String type;
}

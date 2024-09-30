package com.marcellusinfotech.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BloodGroup {
	O_NEGATIVE("O_NEGATIVE"), O_POSITIVE("O_POSITIVE"), A_NEGATIVE("A_NEGATIVE"), A_POSITIVE("A_POSITIVE"),
	B_NEGATIVE("B_NEGATIVE"), B_POSITIVE("B_POSITIVE"), AB_NEGATIVE("AB_NEGATIVE"), AB_POSITIVE("AB_POSITIVE");

	private final String bloodGroupType;
}

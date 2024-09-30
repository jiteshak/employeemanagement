package com.marcellusinfotech.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Nationality {

	Indian("India"), Foreigner("Other");

	private final String country;

}

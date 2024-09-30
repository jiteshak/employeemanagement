package com.marcellusinfotech.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmployeeStatus {
	ABSCONDED("ABSCONDED"), TERMINATED("TERMINATED"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String status;
}

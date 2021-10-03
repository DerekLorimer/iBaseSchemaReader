package com.dal;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public final class KeyAndVersion {

	private final String mKey;
	private final String mVersion;
	
	
	public KeyAndVersion(@NonNull String mKey, @NonNull String mVersion) {
		super();
		this.mKey = mKey;
		this.mVersion = mVersion;
	}
	
	

}

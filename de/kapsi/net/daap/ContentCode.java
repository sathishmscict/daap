
package de.kapsi.net.daap;

import java.io.OutputStream;
import java.io.IOException;

import de.kapsi.net.daap.chunks.ContentCodesNumber;
import de.kapsi.net.daap.chunks.ContentCodesName;
import de.kapsi.net.daap.chunks.ContentCodesType;
import de.kapsi.net.daap.chunks.Dictionary;

public final class ContentCode extends Dictionary {
	
	public ContentCode(String type, String name, int value) {
		super();
		
		add(new ContentCodesNumber(DaapUtil.toContentCodeNumber(type)));
		add(new ContentCodesName(name));
		add(new ContentCodesType(value));
	}
}

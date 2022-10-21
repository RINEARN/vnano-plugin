/*
 * Author:  RINEARN (Fumihiro Matsui), 2022
 * License: CC0
 * Interface Specification:
 *     https://www.vcssl.org/en-us/doc/connect/ExternalFunctionConnectorInterface1_SPEC_ENGLISH
 */

package org.vcssl.nano.plugin.system.xfci1;

import org.vcssl.nano.plugin.system.file.FileIOHub;
import org.vcssl.nano.plugin.system.file.FileIOMode;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ConnectorPermissionName;
import org.vcssl.connect.EngineConnectorInterface1;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

import java.io.File;
import java.io.IOException;
import java.rmi.ConnectIOException;
import java.util.Locale;


/**
 * A function plug-in providing "System.open(string fileName, string mode)" function.
 */
public class OpenStringStringXfci1Plugin extends OpenStringStringStringXfci1Plugin {

	/**
	 * Create a new instance of this plug-in.
	 * 
	 * @param fileIOHub The file I/O hub, through which the file I/O will be performed.
	 */
	public OpenStringStringXfci1Plugin(FileIOHub fileIOHub) {
		super(fileIOHub);
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[] { String.class, String.class };
	}

	@Override
	public String[] getParameterNames() {
		return new String[] { "fileName", "fileIOMode" };
	}

	@Override
	public boolean[] getParameterDataTypeArbitrarinesses() {
		return new boolean[]{ false, false };
	}

	@Override
	public boolean[] getParameterArrayRankArbitrarinesses() {
		return new boolean[]{ false, false };
	}

	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[]{ false, false };
	}

	@Override
	public boolean[] getParameterConstantnesses() {
		return new boolean[]{ true, true };
	}


	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {
		String fileName = String.class.cast(arguments[0]);
		String fileIOModeSpecifier = String.class.cast(arguments[1]);
		String encodingName = "UTF-8";

		// Open the file, and return the file ID assigned to it.
		int fileId = this.open(fileName, fileIOModeSpecifier, encodingName);
		return Long.valueOf(fileId);
	}
}

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

	/** Stores the name of the default encoding. */
	private String defaultEncodingName = null;

	/**
	 * Create a new instance of this plug-in.
	 * 
	 * @param fileIOHub The file I/O hub, through which the file I/O will be performed.
	 * @param defaultEncodingName The name of the default encoding.
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

		// Open the file, and return the file ID assigned to it.
		int fileId = this.open(fileName, fileIOModeSpecifier, this.defaultEncodingName);
		return Long.valueOf(fileId);
	}
	
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {
		super.initializeForExecution(engineConnector);
		
		// Get the character encoding from the option settings.
		EngineConnectorInterface1 eci1EngineConnector = EngineConnectorInterface1.class.cast(engineConnector);
		if (eci1EngineConnector.hasOptionValue("FILE_IO_ENCODING")) {
			this.defaultEncodingName = String.class.cast(eci1EngineConnector.getOptionValue("FILE_IO_ENCODING"));
		} else {
			this.defaultEncodingName = "UTF-8";
		}

		// Initialize resources for performing file I/O.
		this.fileIOHub.initializeResources();
	}
}

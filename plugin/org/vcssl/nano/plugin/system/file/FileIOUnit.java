/*
 * Author:  RINEARN (Fumihiro Matsui), 2022
 * License: CC0
 */

package org.vcssl.nano.plugin.system.file;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ConnectorFatalException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;


/**
 * A class performing file I/O from/to a file.
 */
public class FileIOUnit {

	/** The file I/O mode. */
	private FileIOMode mode = FileIOMode.UNOPEND_OR_CLOSED;

	/** The file from/to which this unit performs file I/O. */
	File file = null;

	/** The name of the character encoding of the file. */
	String encodingName = null;

	/** The line-feed code. */
	private String lineFeedCode = null;

	/** The input stream for inputting the content of the file. */
	private FileInputStream fileInputStream = null;

	/** Mediates between the fileInputStream and the bufferedReader. */
	private InputStreamReader inputStreamReader = null;

	/** The reader for reading contents from the file. */
	private BufferedReader bufferedReader = null;

	/** The input stream for outputting the content of the file. */
	private FileOutputStream fileOutputStream = null;

	/** Mediates between the fileOutputStream and the bufferedWriter. */
	private OutputStreamWriter outputStreamWriter = null;

	/** The writter for writing contents to the file. */
	private BufferedWriter bufferedWriter = null;

	/** The locale to determine the language of error messages. */
	private Locale locale = null;

	/** The flag for switching the language of error messages to Japanese. */
	private boolean isJapanese = false;


	/**
	 * Creates a new file I/O unit.
	 */
	public FileIOUnit() {
		this.setLocale(Locale.getDefault());
	}


	/**
	 * Sets the locale, for switching the language of error messages.
	 * 
	 * @param locale The locale for specifying the language of error messages.
	 */
	public synchronized void setLocale(Locale locale) {
		this.locale = locale;
		this.isJapanese =
			( locale.getLanguage()!=null && locale.getLanguage().equals("ja") )
			||
			( locale.getCountry()!=null && locale.getCountry().equals("JP") );
	}


	/**
	 * Gets the file I/O mode, specified when this instance opened the file.
	 * 
	 * @return The file I/O mode.
	 */
	public synchronized FileIOMode getFileIOMode() {
		return this.mode;
	}


	/**
	 * Gets the File instance, specified when this instance opened the file.
	 * 
	 * @return The File instance.
	 */
	public synchronized File getFile() {
		return this.file;
	}


	/**
	 * Opens the specified file.
	 * 
	 * @param filePath The file to be opened.
	 * @param modeSpecifier The string for specifying the file I/O mode (e.g.: "w" for WRITE, "r" for READ, and so on).
	 * @param encodingName The name of the character encoding of the file.
	 * @param lineFeedCode The line-feed code of the file.
	 * @throws ConnectorException Thrown when it has failed to open the file.
	 */
	public synchronized void open(String filePath, String modeSpecifier, String encodingName, String lineFeedCode)
			throws ConnectorException {

		if (!FileIOMode.SPECIFIER_ENUM_MAP.containsKey(modeSpecifier)) {
			if (this.isJapanese) {
				throw new ConnectorException("指定されたファイルI/Oモードはサポートされていません： " + modeSpecifier);
			} else {
				throw new ConnectorException("The specified file I/O mode is unsupported: " + modeSpecifier);
			}
		}
		this.mode = FileIOMode.SPECIFIER_ENUM_MAP.get(modeSpecifier);
		this.file = new File(filePath);
		this.encodingName = encodingName;
		this.lineFeedCode = lineFeedCode;

		// The file must already exists, excluding when writing modes are specified.
		// (Writing modes create a new file if it does not exist.)
		if (!file.exists() && !FileIOMode.WRITE_MODE_SET.contains(mode)) {
			if (this.isJapanese) {
				throw new ConnectorException("指定されたファイルが見つかりません： " + filePath);
			} else {
				throw new ConnectorException("The specified file is not found: " + filePath);
			}
		}

		// We can't perform file I/O from/to a directory.
		if (file.isDirectory()) {
			if (this.isJapanese) {
				throw new ConnectorException("ファイルを指定する必要がある引数に、フォルダが指定されました： " + filePath);
			} else {
				throw new ConnectorException("A file must be specified, but a directory (folder) is specified: " + filePath);
			}
		}

		// Open the specified file with the specified mode/encoding.
		if (FileIOMode.READ_MODE_SET.contains(mode)) {
			this.openForReading();

		} else if (FileIOMode.WRITE_MODE_SET.contains(mode)) {
			boolean appendsToCurrentContent = false;
			this.openForWriting(appendsToCurrentContent);
			
		} else if (FileIOMode.APPEND_MODE_SET.contains(mode)) {
			boolean appendsToCurrentContent = true;
			this.openForWriting(appendsToCurrentContent);
			
		} else {
			throw new ConnectorFatalException("Unexpected file I/O mode: " + mode);
		}
	}


	/**
	 * Opens the file for reading.
	 * 
	 * @throws ConnectorException Thrown when it has failed to open the file.
	 */
	private synchronized void openForReading() throws ConnectorException {
		try {
			this.fileInputStream = new FileInputStream(file);
		} catch (IOException ioe) {
			this.mode = FileIOMode.UNOPEND_OR_CLOSED;
			if (this.isJapanese) {
				throw new ConnectorException("指定されたファイルを開く際に、エラーが発生しました： " + this.file.getPath(), ioe);
			} else {
				throw new ConnectorException("An error has occurred when opening the specified file: " + this.file.getPath(), ioe);
			}
		}

		try {
			this.inputStreamReader = new InputStreamReader(this.fileInputStream, this.encodingName);
		} catch (UnsupportedEncodingException uee) {
			this.mode = FileIOMode.UNOPEND_OR_CLOSED;
			try {
				this.fileInputStream.close();
			} catch (IOException ioe2) {
				if (this.isJapanese) {
					throw new ConnectorException("指定されたファイルを閉じる際に、エラーが発生しました： " + this.file.getPath(), ioe2);
				} else {
					throw new ConnectorException("An error has occurred when closing the specified file: " + this.file.getPath(), ioe2);
				}
			}
			if (this.isJapanese) {
				throw new ConnectorException("指定された文字コード「 " + this.encodingName + " 」はサポートされていません。");
			} else {
				throw new ConnectorException("The specified caracter encoding \"" + this.encodingName + "\" is unsupported.");
			}
		}

		this.bufferedReader = new BufferedReader(this.inputStreamReader);
	}

	
	/**
	 * Opens the file for writing.
	 * 
	 * @param appendsToCurrentContent Specify true for appending contents to the end of the current contents of the file.
	 * @throws ConnectorException Thrown when it has failed to open the file.
	 */
	private synchronized void openForWriting(boolean appendsToCurrentContent) throws ConnectorException {
		try {
			this.fileOutputStream = new FileOutputStream(this.file, appendsToCurrentContent);
		} catch (IOException ioe) {
			this.mode = FileIOMode.UNOPEND_OR_CLOSED;
			if (this.isJapanese) {
				throw new ConnectorException("指定されたファイルを開く際に、エラーが発生しました： " + this.file.getPath(), ioe);
			} else {
				throw new ConnectorException("An error has occurred when opening the specified file: " + this.file.getPath(), ioe);
			}
		}

		try {
			this.outputStreamWriter = new OutputStreamWriter(this.fileOutputStream, this.encodingName);
		} catch (UnsupportedEncodingException uee) {
			this.mode = FileIOMode.UNOPEND_OR_CLOSED;
			try {
				this.fileOutputStream.close();
			} catch (IOException ioe2) {
				if (this.isJapanese) {
					throw new ConnectorException("指定されたファイルを閉じる際に、エラーが発生しました： " + this.file.getPath(), ioe2);
				} else {
					throw new ConnectorException("An error has occurred when closing the specified file: " + this.file.getPath(), ioe2);
				}
			}
			if (this.isJapanese) {
				throw new ConnectorException("指定された文字コード「 " + this.encodingName + " 」はサポートされていません。");
			} else {
				throw new ConnectorException("The specified caracter encoding \"" + this.encodingName + "\" is unsupported.");
			}
		}

		this.bufferedWriter = new BufferedWriter(this.outputStreamWriter);
	}


	/**
	 * Closes the file.
	 * 
	 * @throws ConnectorException Thrown when it has failed to close the file.
	 */
	public synchronized void close() throws ConnectorException {
		if (FileIOMode.READ_MODE_SET.contains(this.mode)) {
			try {
				this.fileInputStream.close();
				this.inputStreamReader.close();
				this.bufferedReader.close();
			} catch (IOException ioe) {
				if (this.isJapanese) {
					throw new ConnectorException("指定されたファイルを閉じる際に、エラーが発生しました： " + this.file.getPath(), ioe);
				} else {
					throw new ConnectorException("An error has occurred when closing the specified file: " + this.file.getPath(), ioe);
				}
			}
		} else if (FileIOMode.APPEND_MODE_SET.contains(this.mode) || FileIOMode.WRITE_MODE_SET.contains(this.mode)) {
			try {
				this.bufferedWriter.flush();
				this.fileOutputStream.close();
				this.outputStreamWriter.close();
				this.bufferedWriter.close();
			} catch (IOException ioe) {
				if (this.isJapanese) {
					throw new ConnectorException("指定されたファイルを閉じる際に、エラーが発生しました： " + this.file.getPath(), ioe);
				} else {
					throw new ConnectorException("An error has occurred when closing the specified file: " + this.file.getPath(), ioe);
				}
			}
		} else {
			throw new ConnectorFatalException("Unexpected file I/O mode: " + mode);
		}
	}


	// TODO: implement I/O methods

}

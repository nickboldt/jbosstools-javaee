/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.cdi.core;

/**
 * @author Alexey Kazakov
 */
public enum CDIVersion {

	/**
	 * CDI version 1.0
	 */
	CDI_1_0("1.0", 0), //$NON-NLS-1$

	/**
	 * CDI version 1.1
	 */
	CDI_1_1("1.1", 1), //$NON-NLS-1$

	/**
	 * CDI version 1.2
	 */
	CDI_1_2("1.2", 2); //$NON-NLS-1$

	String version = ""; //$NON-NLS-1$

	int versionIndex;

	CDIVersion(String version, int versionIndex) {
		this.version = version;
		this.versionIndex = versionIndex;
	}

	/**
	 * Unique index used for accessing arrays that keep ordered data for each version.
	 * For example of such an array see CDIValidationMessages.
	 * 
	 * @return
	 */
	public int getIndex() {
		return versionIndex;
	}

	/**
	 * Return a string representation of the version
	 * 
	 * @return
	 * 	version number as string
	 */
	@Override
	public String toString() {
		return version;
	}

	public static final CDIVersion[] ALL_VERSIONS = 
			new CDIVersion[]{CDI_1_0, CDI_1_1, CDI_1_2};

	public static CDIVersion getLatestDefaultVersion() {
		return CDI_1_2;
	}
}

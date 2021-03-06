/*******************************************************************************
 * Copyright (c) 2010 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.jboss.tools.cdi.bot.test.wizard;


import org.eclipse.swt.SWTException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.jboss.tools.cdi.bot.test.CDIAllBotTests;
import org.jboss.tools.cdi.bot.test.CDISmokeBotTests;
import org.jboss.tools.cdi.bot.test.CDITestBase;
import org.jboss.tools.cdi.bot.test.annotations.CDIWizardType;
import org.jboss.tools.ui.bot.ext.Timing;
import org.jboss.tools.ui.bot.ext.helper.ContextMenuHelper;
import org.jboss.tools.ui.bot.ext.types.IDELabel;
import org.jboss.tools.ui.bot.ext.types.PerspectiveType;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

/**
* Test operates on CDI perspective
* 
* @author Jaroslav Jankovic
*/

@SuiteClasses({ CDIAllBotTests.class, CDISmokeBotTests.class })
public class PerspectiveTest extends CDITestBase {

	@Override
	public void prepareWorkspace() {
		if (!projectHelper.projectExists(getProjectName())) {
			projectHelper.createCDIProjectWithCDIWizard(getProjectName());
			eclipse.openPerspective(PerspectiveType.CDI);
			LOGGER.info("CDI perspective selected");
			bot.sleep(Timing.time2S());
		}	
	}
	
	@Override
	public String getProjectName() {
		return "CDIPerspectiveTest";
	}
		
	@Test
	public void testCDIArtifactBeanWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.BEAN));
	}
	
	@Test
	public void testCDIArtifactAnnotationLiteralWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.ANNOTATION_LITERAL));
	}
	
	@Test
	public void testCDIArtifactBeanXMLWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.BEANS_XML));
	}
	
	@Test
	public void testCDIArtifactDecoratorWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.DECORATOR));
	}
	
	@Test
	public void testCDIArtifactQualifierWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.QUALIFIER));
	}
	
	@Test
	public void testCDIArtifactScopeWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.SCOPE));
	}
	
	@Test
	public void testCDIArtifactStereoscopeWizard() {	
							
		assertTrue(openCDIArtifactsWizard(CDIWizardType.STEREOTYPE));
	}
			
	private boolean openCDIArtifactsWizard(CDIWizardType wizardType) {
		
		SWTBotTree tree = packageExplorer.bot().tree();
		SWTBotTreeItem item = tree.getTreeItem(getProjectName());
		item.expand();
		boolean artifactWizardExists = true;
		try {
			ContextMenuHelper.prepareTreeItemForContextMenu(tree, item);
			SWTBotMenu menu = new SWTBotMenu(
					ContextMenuHelper.getContextMenu(
					tree, IDELabel.Menu.NEW, false));
			menu.menu(wizardType.getAnnotationType()).click();
			bot.sleep(Timing.time500MS());
			bot.activeShell().bot().button(IDELabel.Button.CANCEL).click();
	
		} catch (SWTException exc) {			
			artifactWizardExists = false;
		}
		bot.sleep(Timing.time1S());
		util.waitForNonIgnoredJobs();
		return artifactWizardExists;
	}
			
}

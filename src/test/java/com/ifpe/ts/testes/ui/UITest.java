package com.ifpe.ts.testes.ui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.JTextComponent;

import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ifpe.ts.testes.ui.GUI;




public class UITest{

	private FrameFixture window;
	private JPanelFixture panel;


	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void onSetUp() {
		GUI gui = GuiActionRunner.execute(() -> new GUI());
		window = new FrameFixture(gui);
		window.resizeWidthTo(500);
		window.resizeHeightTo(400);
		window.show();
	}

	@Test
	public void checkTitleTest() {
		//window.show();
		window.requireTitle("Aula Testes UI");
		
	}
	
	@Test
	public void clickBtnListar() {
		//window.show();
		JMenuItemFixture menuItem = window.menuItemWithPath("Pessoa", "Cadastrar");
		menuItem.click();
		JPanelFixture panel = window.panel();
		JButtonFixture button = panel.button(new GenericTypeMatcher<JButton>(JButton.class) {

			@Override
			protected boolean isMatching(JButton component) {
				// TODO Auto-generated method stub
				return "Listar".equals(component.getText());
			}
				
		});
		button.click();
		JTextComponentFixture text = panel.textBox("textField");
		assertTrue(text.text().contains("Lucas"));
		
				
		
		
		
	}
	
	

	@After
	public void onTearDown() {
		window.cleanUp();
	}


}


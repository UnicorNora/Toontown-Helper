package com.tylerroyer.ttr_helper.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.tylerroyer.ttr_helper.driver.Driver;
import com.tylerroyer.ttr_helper.globals.GlobalFonts;
import com.tylerroyer.ttr_helper.globals.GlobalStrings;

public class Window extends JFrame {
	public static final int INITIAL_WIDTH = 950, INITIAL_HEIGHT = 720;

	private JTabbedPane tabbedPane;

	private InvasionsPanel invasionsPanel;
	private CogsPanel cogsPanel;
	private MapsPanel mapsPanel;

	public Window() {
		this.setTitle(GlobalStrings.ProgramName + " " + GlobalStrings.ProgramVersion);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		invasionsPanel = new InvasionsPanel();
		cogsPanel = new CogsPanel();
		mapsPanel = new MapsPanel();

		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(GlobalFonts.mickeyFont.deriveFont(18f));
		tabbedPane.addTab("Invasions", invasionsPanel);
		tabbedPane.addTab("Cogs", cogsPanel);
		tabbedPane.addTab("Maps", mapsPanel);
		this.add(tabbedPane);

		this.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		this.setVisible(true);

		// Stop execution when window closes.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Driver.running = false;
			}
		});
	}

	public void update() {
		invasionsPanel.doUpdate(tabbedPane.getSelectedComponent() == invasionsPanel);
		cogsPanel.doUpdate(tabbedPane.getSelectedComponent() == cogsPanel);
	}
}

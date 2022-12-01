package fr.tp.maze.ui;


import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial") 
public class ClearShortestPathButton extends JButton implements ActionListener {
	
	private final MazeEditor mazeEditor;
	
	public ClearShortestPathButton(MazeEditor mazeEditor) {
		super("Clear Shortest Path");
		
		this.mazeEditor = mazeEditor;
		
		addActionListener(this);
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		mazeEditor.getMaze().clearShortestPath();
		
		mazeEditor.setModified( true );
	}
}

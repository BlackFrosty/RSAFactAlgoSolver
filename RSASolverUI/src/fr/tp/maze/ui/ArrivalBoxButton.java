package fr.tp.maze.ui;

import java.awt.event.ActionListener;

import fr.tp.maze.model.MazeBoxModel;

@SuppressWarnings("serial") 
public class ArrivalBoxButton extends AbstractBoxTypeRadioButton implements ActionListener {
	
	public ArrivalBoxButton( final MazePanel mazePanel ) {
		super( "Arrival Box", mazePanel );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isArrival() ) {
			boxModel.setArrival();
		
			setModified( true );
		}
	}
}

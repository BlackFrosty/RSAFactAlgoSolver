package fr.tp.maze.ui;

import fr.tp.maze.model.MazeBoxModel;

@SuppressWarnings("serial") 
public class DepartureBoxButton extends AbstractBoxTypeRadioButton {
	
	public DepartureBoxButton( MazePanel mazeDrawingPanel ) {
		super( "Departure Box", mazeDrawingPanel );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isDeparture() ) {
			boxModel.setDeparture();
		
			setModified( true );
		}
	}
}

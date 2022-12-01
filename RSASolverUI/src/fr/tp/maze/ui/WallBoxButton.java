package fr.tp.maze.ui;

import fr.tp.maze.model.MazeBoxModel;

@SuppressWarnings("serial") 
public class WallBoxButton extends AbstractBoxTypeRadioButton {
	
	public WallBoxButton( MazePanel mazePanel ) {
		super("Wall Box", mazePanel );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isWall() ) {
			boxModel.setWall();
		
			setModified( true );
		}
	}
}

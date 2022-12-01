package fr.tp.maze.ui;

import fr.tp.maze.model.MazeBoxModel;

@SuppressWarnings("serial") 
public class EmptyBoxButton extends AbstractBoxTypeRadioButton {
	
	public EmptyBoxButton( MazePanel mazeEditor ) {
		super("Empty Box", mazeEditor );
	}

	@Override
	public void setBoxModelType( final MazeBoxModel boxModel ) {
		if ( !boxModel.isEmpty() ) {
			boxModel.setEmpty();
			
			setModified( true );
		}
	}
}

package RSA;

import java.awt.Component;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileMazePersistenceManager implements MazePersistenceManager {
	
	private final char BOX_DEPARTURE = 'D';
	private final char BOX_ARRIVAL = 'A';
	private final char BOX_WALL = 'W';
	private final char BOX_EMPTY = 'E';
	
	private Component editor;
	
    private final FileNameExtensionFilter filter;
	
	public FileMazePersistenceManager() {
		this.editor = null;
		filter = new FileNameExtensionFilter( "Maze files only (*.maze)", "maze" );
	}
	
	public void setEditor( Component editor ) {
		this.editor = editor;
	}
	
	@Override
	public MazeModel read( String mazeId ) 
	throws IOException {
		if ( mazeId == null || mazeId.isEmpty() ) {
			final JFileChooser chooser = new JFileChooser(); //This class enable us to open a file explorer for a more ergonomic design. 
		    chooser.setFileFilter(filter);
		    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		   
		    final int returnVal = chooser.showOpenDialog( editor );
		    
		    if ( returnVal == JFileChooser.APPROVE_OPTION ) {
		    	final File file = chooser.getSelectedFile();
		    	mazeId = file.getPath();
			}
		    else {
		    	throw new IOException( "No file selected!" );
		    }
		}
		
		return doRead( mazeId );
	}
	
	protected MazeModel doRead( final String mazeId ) 
	throws IOException {	
		Maze maze = new Maze(getMaxRowIndex(mazeId), getMaxColIndex(mazeId));
		maze.setId(mazeId);
		try (
			BufferedReader bufReader = new BufferedReader(new FileReader(mazeId));
		) {	
			for ( int rowIndex = 0 ; rowIndex < maze.getHeigth() ; rowIndex++ ) {
				String line = bufReader.readLine();
				System.out.println("line = " + line);
				for ( int colIndex = 0 ; colIndex < line.length() ; colIndex++ ) {
					switch (line.charAt(colIndex)) {
						case BOX_DEPARTURE :
							maze.changeToDepartureBox(new DepartureBox(maze, rowIndex, colIndex));
							System.out.println("Departure box at " + rowIndex + " " + colIndex);
							System.out.println(maze.getMazeBox(rowIndex, colIndex).getClass().getName() + " at " + rowIndex + " " + colIndex);
							break;
						case BOX_ARRIVAL :
							maze.changeToArrivalBox(new ArrivalBox(maze, rowIndex, colIndex));
							System.out.println("Arrival box at " + rowIndex + " " + colIndex);
							System.out.println(maze.getMazeBox(rowIndex, colIndex).getClass().getName() + " at " + rowIndex + " " + colIndex);
							break;
						case BOX_WALL :
							maze.changeToWallBox(new WallBox(maze, rowIndex, colIndex));	//some iterations seem to be not working... mystery!
							System.out.println("Wall box at " + rowIndex + " " + colIndex);
							System.out.println(maze.getMazeBox(rowIndex, colIndex).getClass().getName() + " at " + rowIndex + " " + colIndex);
							break;
						case BOX_EMPTY :
							//do nothing as maze is initialized with EmptyBoxes.
//							maze.changeToEmptyBox(new EmptyBox(maze, rowIndex, colIndex));
//							System.out.println("Empty box at lineNumber " + rowIndex + " colPos " + colIndex);
//							System.out.println(maze.getMazeBox(rowIndex, colIndex).getClass().getName() + " at " + rowIndex + " " + colIndex);
							break;
						default :
							throw new MazeReadingException(mazeId, rowIndex, "Invalid character!");
					}
				}
			}
		}
//		for ( int rowIndex = 0 ; rowIndex < maze.getHeigth() ; rowIndex++ )
//			for ( int colIndex = 0 ; colIndex < line.length() ; colIndex++ )
//				System.out.println(maze.getMazeBox(rowIndex, colIndex).toString() + " at " + rowIndex + " " + colIndex);
		return maze;
	}

	private int	getMaxRowIndex(final String mazeId) {
		int	maxRowIndex = 0;
		try (
			BufferedReader bufReader = new BufferedReader(new FileReader(mazeId));
		) {
			if (bufReader.readLine() == null)
				throw new MazeReadingException(mazeId, 0, "Empty file!");
			
			maxRowIndex = 1;
			while (bufReader.readLine() != null)
				maxRowIndex++;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return maxRowIndex;
	}
	
	private int	getMaxColIndex(final String mazeId) {
		int	maxColIndex = 0;
		try (
			BufferedReader bufReader = new BufferedReader(new FileReader(mazeId));
		) {
			if (bufReader.readLine() == null)
				throw new MazeReadingException(mazeId, 0, "Empty file!");
			
			maxColIndex = bufReader.readLine().length();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return maxColIndex;
	}
	
	@Override
	public void persist( final MazeModel mazeModel )
	throws IOException {
		String mazeId = mazeModel.getId();
		
		if ( mazeId == null || mazeId.isEmpty() ) {
			mazeId = newMazeId();
			
			if (mazeId == null || mazeId.isEmpty() ) {
				throw new IOException("No file path was choosen!" );
			}
			
			mazeModel.setId( mazeId );
		}
		
		doPersist( mazeModel );
	}
		
	protected void doPersist( final MazeModel mazeModel )
	throws IOException {	
		try (
		PrintWriter pWriter = new PrintWriter(new FileWriter(mazeModel.getId()));
		) {
			for (int rowIndex = 0 ; rowIndex < mazeModel.getHeigth() ; rowIndex++) {
				for (int colIndex = 0 ; colIndex < mazeModel.getWidth() ; colIndex++) {
					MazeBoxModel box = mazeModel.getMazeBox(rowIndex, colIndex);

					if ((MazeBox) box instanceof DepartureBox)
						pWriter.print(BOX_DEPARTURE);
					if ((MazeBox) box instanceof ArrivalBox)
						pWriter.print(BOX_ARRIVAL);
					if ((MazeBox) box instanceof WallBox)
						pWriter.print(BOX_WALL);
					if ((MazeBox) box instanceof EmptyBox)
						pWriter.print(BOX_EMPTY);
				}
				pWriter.println();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(MazeModel mazeModel) 
	throws IOException {
		return new File( mazeModel.getId() ).delete();
	}

	private String newMazeId() {
		final JFileChooser chooser = new JFileChooser();
	    chooser.setFileFilter( filter );
	    chooser.setDialogType( JFileChooser.SAVE_DIALOG );
	    final int returnVal = chooser.showSaveDialog( editor );
	    
	    if ( returnVal == JFileChooser.APPROVE_OPTION ) {
	    	final File file = chooser.getSelectedFile();

	    	return file.getPath();
	    }
	    
	    return null;
	}
}

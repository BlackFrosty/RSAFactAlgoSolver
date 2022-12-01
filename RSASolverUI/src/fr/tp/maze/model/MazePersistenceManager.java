package fr.tp.maze.model;

import java.io.IOException;

/**
 * Interface providing methods to manage the persistence of maze models into a data store such as files, databases or any other permanent storage. 
 * Required by the maze editor when the maze must be saved or read from the storage.
 * 
 * @author Dominique Blouin
 *
 */
public interface MazePersistenceManager {

	/**
	 * Reads a maze model from the data store of this persistence manager from the ID of the required maze model.
	 * 
	 * @param mazeId A <code>String</code> identifying this maze model uniquely in the data store of this persistence manager.
	 * 
	 * @return The read maze model
	 * 
	 * @throws IOException When a problem occurs when reading the maze (e.g. wrong <code>mazeId</code>, unavailable data store
	 * or locked resource, etc.).
	 */
	MazeModel read( String mazeId )
	throws IOException;

	/**
	 * Persists the specified maze model into the data store of this persistence manager.
	 * 
	 * @param mazeModel The maze model object.
	 * 
	 * @throws IOException When a problem occurs when persisting the maze (e.g. wrong <code>mazeId</code>, unavailable data store
	 * or locked resource, etc.).
	 */
	void persist( MazeModel mazeModel )
	throws IOException;

	/**
	 * Deletes the specified maze model from the data store of this persistence manager.
	 * 
	 * @param mazeModel The maze model object.
	 * 
	 * @return A <code>boolean</code> stating if the specified maze model was removed or not from the data store, according to its existence in the data store.
	 * 
	 * @throws IOException When a problem occurs when deleting the maze (e.g. wrong <code>mazeId</code>, unavailable data store
	 * or locked resource, etc.).
	 */
	boolean delete( MazeModel mazeModel )
	throws IOException;
}

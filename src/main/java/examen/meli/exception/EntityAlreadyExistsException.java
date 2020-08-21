package examen.meli.exception;

/**
 * The type examen.meli.Entity already exists examen.meli.exception.
 */
public class EntityAlreadyExistsException extends EntityCRUDException {

	/**
	 * Instantiates a new examen.meli.Entity already exists examen.meli.exception.
	 *
	 * @param entityName the entity name
	 * @param entityId   the entity id
	 */
	public EntityAlreadyExistsException(String entityName, String entityId) {
		super(entityName, entityId, "Already exists");
	}

	/**
	 * Instantiates a new examen.meli.Entity already exists examen.meli.exception.
	 *
	 * @param entityName the entity name
	 * @param entityId   the entity id
	 */
	public EntityAlreadyExistsException(String entityName, Long entityId) {
		super(entityName, entityId.toString(), "Already exists");
	}

}

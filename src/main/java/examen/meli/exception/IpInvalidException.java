package examen.meli.exception;

/**
 * The type examen.meli.Entity already exists examen.meli.exception.
 */
public class IpInvalidException extends EntityCRUDException {

	/**
	 * Instantiates a new examen.meli.Entity already exists examen.meli.exception.
	 *
	 * @param entityName the entity name
	 * @param entityId   the entity id
	 */
	public IpInvalidException(String entityName, String entityId) {
		super(entityName, entityId, "La ip es inválida o tiene formato erróneo");
	}

	/**
	 * Instantiates a new examen.meli.Entity already exists examen.meli.exception.
	 *
	 * @param entityName the entity name
	 * @param entityId   the entity id
	 */
	public IpInvalidException(String entityName, Long entityId) {
		super(entityName, entityId.toString(), "La ip es inválida o tiene formato erróneo");
	}

}

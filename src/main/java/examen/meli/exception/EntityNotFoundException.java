package examen.meli.exception;

/**
 * The type examen.meli.Entity not found examen.meli.exception.
 */
public class EntityNotFoundException extends EntityCRUDException {

    /**
     * Instantiates a new examen.meli.Entity not found examen.meli.exception.
     *
     * @param entityName the entity name
     * @param entityId   the entity id
     */
    public EntityNotFoundException(String entityName, String entityId) {
        super(entityName, entityId, "Not found");
    }

    /**
     * Instantiates a new examen.meli.Entity not found examen.meli.exception.
     *
     * @param entityName the entity name
     * @param entityId   the entity id
     */
    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName, entityId.toString(), "Not found");
    }

}

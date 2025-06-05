package org.generation.italy.collectionarchive.models.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(Class<?> entityClass, int id) {
    super(String.format("La Entity %s con Id %d non esiste", entityClass.getSimpleName(), id));
  }
}

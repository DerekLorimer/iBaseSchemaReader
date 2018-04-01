package com.Test;

import javax.persistence.EntityManager;

public interface AutoCloseableEntityManager extends EntityManager, AutoCloseable {
}
package com.Test;

import java.util.Map;
import javax.persistence.EntityManagerFactory;

public interface AutoCloseableEntityManagerFactory extends EntityManagerFactory {

	@Override
	AutoCloseableEntityManager createEntityManager();

	@Override
	AutoCloseableEntityManager createEntityManager(Map args);
}
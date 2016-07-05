package com.qualfacul.hades.college;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = College.class, idClass = Long.class)
public interface CollegeRepository {

	void save(College college);

	Optional<College> findByCnpj(String cnpj);
	
}
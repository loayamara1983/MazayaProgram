package org.bh.housing.mazaya.repository;

import org.bh.housing.mazaya.domain.Request;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RequestRepository extends MazayaBaseRepository<Request, Long>{

	long countByQuery(String query);
}

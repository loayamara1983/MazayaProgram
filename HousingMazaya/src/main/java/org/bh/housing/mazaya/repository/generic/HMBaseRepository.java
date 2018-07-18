/**
 * 
 */
package org.bh.housing.mazaya.repository.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Loay
 *
 */

@NoRepositoryBean
public interface HMBaseRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	List<T> findAllActive();
	
	List<T> findAll(Map<String, String> params);
	
	List<T> findAll(Map<String, String> params, Sort sort, Pageable pageable);

}

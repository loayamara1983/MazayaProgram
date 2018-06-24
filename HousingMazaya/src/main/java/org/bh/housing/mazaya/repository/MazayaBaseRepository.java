package org.bh.housing.mazaya.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface MazayaBaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

}

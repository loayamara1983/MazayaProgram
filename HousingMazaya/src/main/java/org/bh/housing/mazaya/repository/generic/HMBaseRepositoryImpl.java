package org.bh.housing.mazaya.repository.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author Loay
 *
 * @param <T>
 * @param <ID>
 */
public class HMBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements HMBaseRepository<T, ID> {

	private final EntityManager entityManager;

	private final JpaEntityInformation<T, ID> entityInformation;

	public HMBaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);

		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
	}

	@Override
	public List<T> findAllActive() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());

		Root<T> from = criteriaQuery.from(getDomainClass());

		Map<String, String> params = new HashMap<>();
		params.put("active", "true");

		applySearchFilter(params, criteriaBuilder, criteriaQuery, from);

		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<T> findAll(Map<String, String> params) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());

		Root<T> from = criteriaQuery.from(getDomainClass());

		applySearchFilter(params, criteriaBuilder, criteriaQuery, from);

		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<T> findAll(Map<String, String> params, Sort sort, Pageable pageable) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());

		Root<T> from = criteriaQuery.from(getDomainClass());

		applySearchFilter(params, criteriaBuilder, criteriaQuery, from);

		applyQuerySort(sort, criteriaBuilder, criteriaQuery, from);

		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

		applyQueryPagination(pageable, query);

		return query.getResultList();
	}

	private void applySearchFilter(Map<String, String> params, CriteriaBuilder criteriaBuilder,
			CriteriaQuery<T> criteriaQuery, Root<T> from) {

		if (CollectionUtils.isEmpty(params)) {
			return;
		}

		final List<Predicate> predicates = new ArrayList<Predicate>();

		for (final Map.Entry<String, String> param : params.entrySet()) {

			final String key = param.getKey();
			final String value = param.getValue();

			if ((key != null) && (value != null)) {

				if (value.contains("%")) {
					predicates.add(criteriaBuilder.like(from.<String>get(key), value));
				} else {
					predicates.add(criteriaBuilder.equal(from.get(key), value));
				}
			}
		}

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
	}

	private void applyQuerySort(Sort sort, CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery,
			Root<T> from) {

		if (sort == null) {
			return;
		}

		Iterator<Order> iterator = sort.iterator();
		while (iterator.hasNext()) {
			Order order = iterator.next();

			if (order.isAscending()) {
				criteriaQuery.orderBy(criteriaBuilder.asc(from.get(order.getProperty())));
			} else {
				criteriaQuery.orderBy(criteriaBuilder.desc(from.get(order.getProperty())));
			}
		}
	}

	private void applyQueryPagination(Pageable pageable, TypedQuery<T> query) {
		if (pageable == null) {
			return;
		}

		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
	}
}

package org.sscn.core.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.sscn.core.persistence.dao.CountLayerDao;
import org.sscn.core.persistence.exception.CoreExceptionText;
import org.sscn.core.persistence.exception.CorePersistenceException;
import org.sscn.core.persistence.tools.PropCriteriaAndValue;
import org.sscn.core.persistence.tools.QueryComparator;
import org.sscn.core.util.SqlUtil;

/**
 * @author Roberto
 * 
 */
public class CountLayerDaoImpl<T> extends
		CriteriaLayerDaoImpl<T> implements CountLayerDao<T> {

	/**
	 * constructor.
	 * 
	 * @param clazz
	 *            The same class as the typed parameter used when defining
	 *            CoreDaoImpl<T> sadly we need it to be passed in the
	 *            constructor also to be able to access class methods
	 * @param sessionFactory
	 *            This is the sessionFactory to be injected upon construction of
	 *            the bean. Remember to annotate the constructor with @Autowired
	 *            annotation
	 */
	public CountLayerDaoImpl(Class clazz,
			SessionFactory sessionFactory) {
		super(clazz, sessionFactory);
	}

	/**
	 * @return
	 */
	public Integer countAll() {
		LOG.debug("count all " + clazz.getSimpleName() + " instances");
		StringBuilder sqlString = new StringBuilder(getSelectCountQuery());
		try {
			Query query = createQuery(sqlString);
			return Integer.valueOf(query.uniqueResult().toString());
		} catch (Exception re) {
			LOG.error("count all failed", re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * @param propertiesMap
	 * @return
	 */
	public Integer countByMapOfProperties(
			Map<String, ? extends Object> propertiesMap) {
		StringBuilder queryString = new StringBuilder(getSelectCountQuery())
				.append(WHERE);
		Query query = createQueryForMaps(queryString.toString(),
				QueryComparator.EQUALS, propertiesMap, null, null);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	/**
	 * @param propertiesMap
	 * @return
	 */
	public Integer countLikeMapOfProperties(
			Map<String, ? extends Object> propertiesMap) {
		StringBuilder queryString = new StringBuilder(getSelectCountQuery())
				.append(WHERE);
		Query query = createQueryForMaps(queryString.toString(),
				QueryComparator.LIKE, propertiesMap, null, null);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Integer countLikeProperty(String propertyName, Object value) {
		return countGenericUsingProperty(propertyName, QueryComparator.LIKE,
				value);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Integer countByProperty(String propertyName, Object value) {
		return countGenericUsingProperty(propertyName, QueryComparator.EQUALS,
				value);
	}

	/**
	 * Performs the count HQL operation using the comparator (EQUALS, LIKE)
	 * 
	 * @param propertyName
	 * @param comparator
	 * @param value
	 * @return
	 */
	private Integer countGenericUsingProperty(String propertyName,
			QueryComparator comparator, Object value) {
		StringBuilder queryString = new StringBuilder(getSelectCountQuery())
				.append(WHERE);
		queryString.append(SqlUtil.createSingleWhereClause(propertyName, value,
				comparator));
		try {
			Query query = createQuery(queryString);
			setsParamToValueOrToLowercaseValue(query, propertyName, comparator,
					value, 0);
			return Integer.valueOf(query.uniqueResult().toString());
		} catch (Exception re) {
			LOG.error(I18N_COUNT + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * @param filter
	 * @param leftJoinFetch
	 * @return int
	 */
	public Integer countUsingFilter(List<String> leftJoinFetch, List<PropCriteriaAndValue> filter) {
		Criteria criteria = createHibernateCriteriaObject(leftJoinFetch, filter, null);
		criteria.setProjection(Projections.rowCount());		
		Integer row = Integer.valueOf(criteria.uniqueResult().toString());
		return row;
	}

}

package com.galdovich.esm.dao.util;

import com.galdovich.esm.entity.Certificate;
import com.galdovich.esm.util.QueryParams;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Query creator.
 *
 * @author Alexander Galdovich
 * @version 1.0
 */
public class QueryCreator {
    /**
     * The constant PERCENT.
     */
    public static final String PERCENT = "%";
    /**
     * The constant TAGS.
     */
    public static final String TAGS = "tags";

    /**
     * Create query criteria query.
     *
     * @param builder the builder
     * @param params  the params
     * @return the criteria query
     */
    public CriteriaQuery<Certificate> createQuery(CriteriaBuilder builder, QueryParams params) {
        CriteriaQuery<Certificate> criteria = builder.createQuery(Certificate.class);
        Root<Certificate> root = criteria.from(Certificate.class);
        List<Predicate> list = new ArrayList<>();
        list.addAll(setName(params, builder, root));
        list.addAll(addTags(builder, root, params));
        list.addAll(setDescription(params, builder, root));
        list.addAll(setCreatedDate(params, builder, root));
        addSortType(builder, criteria, root, params);
        return criteria.select(root).where(list.toArray(new Predicate[]{}));
    }

    private List<Predicate> setName(QueryParams params,
                                    CriteriaBuilder builder,
                                    Root<Certificate> root) {
        List<Predicate> list = new ArrayList<>();
        if (params.getName() != null) {
            list.add(builder.like(root.get(ColumnName.CERTIFICATE_NAME),
                    PERCENT + params.getName() + PERCENT));
        }
        return list;
    }

    private List<Predicate> setDescription(QueryParams params,
                                           CriteriaBuilder builder,
                                           Root<Certificate> root) {
        List<Predicate> list = new ArrayList<>();
        if (params.getDescription() != null) {
            list.add(builder.like(root.get(ColumnName.CERTIFICATE_DESCRIPTION),
                    PERCENT + params.getDescription() + PERCENT));
        }
        return list;
    }

    private List<Predicate> setCreatedDate(QueryParams params,
                                           CriteriaBuilder builder,
                                           Root<Certificate> root) {
        List<Predicate> list = new ArrayList<>();
        if (params.getCreate_date() != null) {
            list.add(builder.like(root.get(ColumnName.CERTIFICATE_CREATE_DATE),
                    PERCENT + params.getCreate_date() + PERCENT));
        }
        return list;
    }

    private List<Predicate> addTags(CriteriaBuilder builder,
                                    Root<Certificate> root, QueryParams params) {
        List<Predicate> list = new ArrayList<>();
        if (params.getTags() != null) {
            list = Arrays.stream(params.getTags())
                    .map(t -> builder.equal(root.join(TAGS).get(ColumnName.TAG_NAME), t))
                    .collect(Collectors.toList());
        }
        return list;
    }

    private void addSortType(CriteriaBuilder builder, CriteriaQuery<Certificate> criteria,
                             Root<Certificate> root, QueryParams params) {
        if (params.getSortType() != null) {
            String sortName = params.getSortType().getName();
            if (params.getDirection() != null && params.getDirection() == DirectionType.DESC) {
                criteria.orderBy(builder.desc(root.get(sortName)));
            } else {
                criteria.orderBy(builder.asc(root.get(sortName)));
            }
        }

    }

}

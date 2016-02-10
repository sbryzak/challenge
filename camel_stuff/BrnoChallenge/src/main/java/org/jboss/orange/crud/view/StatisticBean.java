package org.jboss.orange.crud.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jboss.orange.crud.model.Statistic;
import org.jboss.orange.crud.model.History;

/**
 * Backing bean for Statistic entities.
 * <p/>
 * This class provides CRUD functionality for all Statistic entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class StatisticBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Statistic entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Statistic statistic;

	public Statistic getStatistic() {
		return this.statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(unitName = "orange-crud-persistence-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		this.conversation.setTimeout(1800000L);
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversation.setTimeout(1800000L);
		}

		if (this.id == null) {
			this.statistic = this.example;
		} else {
			this.statistic = findById(getId());
		}
	}

	public Statistic findById(Long id) {

		return this.entityManager.find(Statistic.class, id);
	}

	/*
	 * Support updating and deleting Statistic entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.statistic);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.statistic);
				return "view?faces-redirect=true&id=" + this.statistic.getId();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			Statistic deletableEntity = findById(getId());

			this.entityManager.remove(deletableEntity);
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching Statistic entities with pagination
	 */

	private int page;
	private long count;
	private List<Statistic> pageItems;

	private Statistic example = new Statistic();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Statistic getExample() {
		return this.example;
	}

	public void setExample(Statistic example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Statistic> root = countCriteria.from(Statistic.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Statistic> criteria = builder
				.createQuery(Statistic.class);
		root = criteria.from(Statistic.class);
		TypedQuery<Statistic> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Statistic> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String buzzword = this.example.getBuzzword();
		if (buzzword != null && !"".equals(buzzword)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("buzzword")),
					'%' + buzzword.toLowerCase() + '%'));
		}
		int instanceCount = this.example.getInstanceCount();
		if (instanceCount != 0) {
			predicatesList.add(builder.equal(root.get("instanceCount"),
					instanceCount));
		}
		int frequency = this.example.getFrequency();
		if (frequency != 0) {
			predicatesList.add(builder.equal(root.get("frequency"), frequency));
		}
		int density = this.example.getDensity();
		if (density != 0) {
			predicatesList.add(builder.equal(root.get("density"), density));
		}
		History history = this.example.getHistory();
		if (history != null) {
			predicatesList.add(builder.equal(root.get("history"), history));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Statistic> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Statistic entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Statistic> getAll() {

		CriteriaQuery<Statistic> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Statistic.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Statistic.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final StatisticBean ejbProxy = this.sessionContext
				.getBusinessObject(StatisticBean.class);

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ejbProxy.findById(Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Statistic) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Statistic add = new Statistic();

	public Statistic getAdd() {
		return this.add;
	}

	public Statistic getAdded() {
		Statistic added = this.add;
		this.add = new Statistic();
		return added;
	}
}

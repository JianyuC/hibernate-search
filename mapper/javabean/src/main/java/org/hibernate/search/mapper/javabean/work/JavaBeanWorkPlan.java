/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.mapper.javabean.work;

/**
 * A set of works to be executed on mapped indexes.
 * <p>
 * Works are accumulated when methods such as {@link #add(Object)} or {@link #update(Object, Object)} are called,
 * and executed only when the search manager is closed.
 * <p>
 * Relative ordering of works within a work plan will be preserved.
 * <p>
 * This contract is not thread-safe.
 */
public interface JavaBeanWorkPlan {

	/**
	 * Add an entity to the index, assuming that the entity is absent from the index.
	 * <p>
	 * Shorthand for {@code add(null, entity)}; see {@link #add(Object, Object)}.
	 *
	 * @param entity The entity to add to the index.
	 */
	void add(Object entity);

	/**
	 * Add an entity to the index, assuming that the entity is absent from the index.
	 * <p>
	 * <strong>Note:</strong> depending on the backend, this may lead to errors or duplicate entries in the index
	 * if the entity was actually already present in the index before this call.
	 * When in doubt, you should rather use {@link #update(Object, Object)} or {@link #update(Object)}.
	 *
	 * @param id The provided ID for the entity.
	 * If {@code null}, Hibernate Search will attempt to extract the ID from the entity.
	 * @param entity The entity to add to the index.
	 */
	void add(Object id, Object entity);

	/**
	 * Update an entity in the index, or add it if it's absent from the index.
	 * <p>
	 * Shorthand for {@code update(null, entity)}; see {@link #update(Object, Object)}.
	 *
	 * @param entity The entity to update in the index.
	 */
	void update(Object entity);

	/**
	 * Update an entity in the index, or add it if it's absent from the index.
	 *
	 * @param id The provided ID for the entity.
	 * If {@code null}, Hibernate Search will attempt to extract the ID from the entity.
	 * @param entity The entity to update in the index.
	 */
	void update(Object id, Object entity);

	/**
	 * Update an entity in the index, or add it if it's absent from the index,
	 * but try to avoid reindexing if the given dirty paths
	 * are known not to impact the indexed form of that entity.
	 * <p>
	 * Assumes that the entity may already be present in the index.
	 * <p>
	 * Shorthand for {@code update(null, entity, dirtyPaths)}; see {@link #update(Object, Object)}.
	 *
	 * @param entity The entity to update in the index.
	 * @param dirtyPaths The paths to consider dirty, formatted using the dot-notation
	 * ("directEntityProperty.nestedPropery").
	 */
	void update(Object entity, String... dirtyPaths);

	/**
	 * Update an entity in the index, or add it if it's absent from the index,
	 * but try to avoid reindexing if the given dirty paths
	 * are known not to impact the indexed form of that entity.
	 *
	 * @param id The provided ID for the entity.
	 * If {@code null}, Hibernate Search will attempt to extract the ID from the entity.
	 * @param entity The entity to update in the index.
	 * @param dirtyPaths The paths to consider dirty, formatted using the dot-notation
	 * ("directEntityProperty.nestedPropery").
	 */
	void update(Object id, Object entity, String... dirtyPaths);

	/**
	 * Delete an entity from the index.
	 * <p>
	 * Shorthand for {@code delete(null, entity)}; see {@link #delete(Object, Object)}.
	 *
	 * @param entity The entity to delete from the index.
	 */
	void delete(Object entity);

	/**
	 * Delete an entity from the index.
	 * <p>
	 * No effect on the index if the entity is not in the index.
	 *
	 * @param id The provided ID for the entity.
	 * If {@code null}, Hibernate Search will attempt to extract the ID from the entity.
	 * @param entity The entity to delete from the index.
	 */
	void delete(Object id, Object entity);

}

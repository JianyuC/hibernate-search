/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.lucene.search.query.impl;

import org.hibernate.search.backend.lucene.multitenancy.impl.MultiTenancyStrategy;
import org.hibernate.search.backend.lucene.orchestration.impl.LuceneQueryWorkOrchestrator;
import org.hibernate.search.backend.lucene.search.extraction.impl.LuceneDocumentStoredFieldVisitorBuilder;
import org.hibernate.search.backend.lucene.search.impl.LuceneSearchTargetModel;
import org.hibernate.search.backend.lucene.search.projection.impl.LuceneSearchProjection;
import org.hibernate.search.backend.lucene.work.impl.LuceneWorkFactory;
import org.hibernate.search.engine.mapper.session.context.spi.SessionContextImplementor;
import org.hibernate.search.engine.search.query.spi.ProjectionHitMapper;
import org.hibernate.search.util.common.reporting.EventContext;

public class SearchBackendContext {
	private final EventContext eventContext;

	private final LuceneWorkFactory workFactory;
	private final MultiTenancyStrategy multiTenancyStrategy;

	private final LuceneQueryWorkOrchestrator orchestrator;

	public SearchBackendContext(EventContext eventContext,
			LuceneWorkFactory workFactory,
			MultiTenancyStrategy multiTenancyStrategy,
			LuceneQueryWorkOrchestrator orchestrator) {
		this.eventContext = eventContext;
		this.multiTenancyStrategy = multiTenancyStrategy;
		this.workFactory = workFactory;
		this.orchestrator = orchestrator;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + eventContext + "]";
	}

	public EventContext getEventContext() {
		return eventContext;
	}

	<T> LuceneSearchQueryBuilder<T> createSearchQueryBuilder(
			LuceneSearchTargetModel searchTargetModel,
			SessionContextImplementor sessionContext,
			ProjectionHitMapper<?, ?> projectionHitMapper,
			LuceneSearchProjection<?, T> rootProjection) {
		multiTenancyStrategy.checkTenantId( sessionContext.getTenantIdentifier(), eventContext );

		LuceneDocumentStoredFieldVisitorBuilder storedFieldFilterBuilder = new LuceneDocumentStoredFieldVisitorBuilder();
		rootProjection.contributeFields( storedFieldFilterBuilder );

		return new LuceneSearchQueryBuilder<>(
				workFactory,
				orchestrator,
				multiTenancyStrategy,
				searchTargetModel,
				sessionContext,
				storedFieldFilterBuilder.build(),
				projectionHitMapper,
				rootProjection
		);
	}
}

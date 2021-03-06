/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.integrationtest.backend.tck.search;

import org.hibernate.search.engine.search.DocumentReference;

class StubTransformedHit {
	private final DocumentReference documentReference;

	StubTransformedHit(DocumentReference documentReference) {
		this.documentReference = documentReference;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "["
				+ "documentReference=" + documentReference
				+ "]";
	}
}

/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.backend.lucene.types.formatter.impl;

import org.hibernate.search.engine.backend.spatial.GeoPoint;

public final class GeoPointFieldFormatter implements LuceneFieldFormatter<GeoPoint> {

	public static final GeoPointFieldFormatter INSTANCE = new GeoPointFieldFormatter();

	private GeoPointFieldFormatter() {
	}

	@Override
	public GeoPoint format(Object value) {
		return (GeoPoint) value;
	}
}
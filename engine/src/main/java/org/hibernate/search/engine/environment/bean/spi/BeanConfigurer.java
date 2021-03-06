/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.search.engine.environment.bean.spi;

public interface BeanConfigurer {

	/**
	 * Configure beans as necessary using the given {@code context}.
	 * @param context A context exposing methods to configure beans.
	 */
	void configure(BeanConfigurationContext context);

}

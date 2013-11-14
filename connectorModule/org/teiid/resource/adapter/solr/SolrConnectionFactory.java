package org.teiid.resource.adapter.solr;


import org.teiid.resource.spi.BasicConnectionFactory;

import org.apache.solr.client.solrj.SolrServerException;
import javax.resource.ResourceException;

public class SolrConnectionFactory extends BasicConnectionFactory<SolrConnectionImpl>{

	private SolrManagedConnectionFactory config;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7636834759365334558L;

	public SolrConnectionFactory(
			SolrManagedConnectionFactory config) {
		this.config = config;
	}

	@Override
	public SolrConnectionImpl getConnection() {
		return new SolrConnectionImpl(config);
	}

}

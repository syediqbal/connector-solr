package org.teiid.resource.adaptor.solr;


import org.teiid.resource.spi.BasicConnectionFactory;

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
	public SolrConnectionImpl getConnection() throws ResourceException {
		// TODO Auto-generated method stub
		return new SolrConnectionImpl(config);
	}

}

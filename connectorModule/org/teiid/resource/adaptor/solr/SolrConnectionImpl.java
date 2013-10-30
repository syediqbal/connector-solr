package org.teiid.resource.adaptor.solr;

import java.io.IOException;

import javax.resource.ResourceException;
//import javax.resource.cci.Connection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.teiid.logging.LogManager;
import org.teiid.resource.spi.BasicConnection;
import org.teiid.translator.solr.SolrConnection;

public class SolrConnectionImpl extends BasicConnection implements
		SolrConnection {

	private HttpSolrServer server;

	// private MetadataStore metadata = null;

	public SolrConnectionImpl(SolrManagedConnectionFactory config) {
		try {

			server = new HttpSolrServer(config.getUrl());

			if (config.getSoTimeout() != null) {
				server.setSoTimeout(new Integer(config.getSoTimeout()));
			}
			if (config.getConnTimeout() != null) {
				server.setConnectionTimeout(new Integer(config.getConnTimeout()));
			}
			if (config.getMaxConns() != null) {
				server.setMaxTotalConnections(new Integer(config.getMaxConns()));
			}
			if (config.getAllowCompression() != null) {
				server.setAllowCompression(new Boolean(config
						.getAllowCompression()));
			}
			if (config.getMaxRetries() != null) {
				server.setMaxRetries(new Integer(config.getMaxRetries()));
			}

		} catch (Exception ne) {
			LogManager
					.logError(
							"Property could not be converted to correctly. Please check the binding properties.",
							ne);
		}

	}

	/**
	 * /* Close the server connection
	 */
	@Override
	public void close() throws ResourceException {
		if (server != null)
			server.shutdown();
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		try {
			server.ping();
		} catch (SolrServerException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public QueryResponse executeQuery(SolrQuery params) {
		try {
			return server.query(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
			LogManager.logCritical(params.getQuery(),
					"solr query failed, review expresion");
		}
		return null;
	}

}

package org.teiid.resource.adapter.solr.test;

import java.io.IOException;

import javax.resource.ResourceException;

import junit.framework.Assert;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;
import org.teiid.resource.adapter.solr.SolrConnectionFactory;
import org.teiid.resource.adapter.solr.SolrManagedConnectionFactory;


public class TestSolrConnection {

	// <jndi-name>Solr</jndi-name>
	// <rar-name>resourceAdaptor-solr.rar</rar-name>
	// <use-java-context>true</use-java-context>
	// <connection-definition>javax.resource.cci.ConnectionFactory</connection-definition>
	// <jmx-invoker-name>jboss:service=invoker,type=jrmp</jmx-invoker-name>
	// <min-pool-size>0</min-pool-size>
	// <max-pool-size>10</max-pool-size>
	// <blocking-timeout-millis>3000</blocking-timeout-millis>
	// <idle-timeout-minutes>30</idle-timeout-minutes>
	// <prefill>false</prefill>
	// <background-validation>false</background-validation>
	// <background-validation-millis>0</background-validation-millis>
	// <validate-on-match>true</validate-on-match>
	// <use-fast-fail>false</use-fast-fail>
	// <statistics-formatter>org.jboss.resource.statistic.pool.JBossDefaultSubPoolStatisticFormatter</statistics-formatter>
	// <isSameRM-override-value>false</isSameRM-override-value>
	// <allocation-retry>0</allocation-retry>
	// <allocation-retry-wait-millis>5000</allocation-retry-wait-millis>
	// <config-property type="java.lang.String"
	// name="url">http://localhost:8983/solr</config-property>

	@Test
	public void testSetUp() throws ResourceException {

		SolrManagedConnectionFactory mcf = new SolrManagedConnectionFactory();
		mcf.setUrl("http://localhost:8983/solr");

		SolrConnectionFactory cf = new SolrConnectionFactory(mcf);
		cf.getConnection();
		Assert.assertNotNull(cf.getConnection().isAlive());

	}
	@Test
	public void testPing() throws ResourceException, SolrServerException, IOException {

		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr");

		SolrQuery params = new SolrQuery();
		QueryResponse queryResponse = null;
		
		System.out.println(server.ping());
		System.out.println(server.getParser());
		
		params.setQuery("NOT name:'test'");
		queryResponse = server.query(params);
		System.out.println("query response: " + queryResponse.getResponse().toString());

		System.out.println("fields: " + params.getFields());
//		SolrConnectionFactory cf = new SolrConnectionFactory(mcf);
//		cf.getConnection();
//		Assert.assertNotNull(cf.getConnection().isAlive());

	}
//
//	public void testSelectStar() throws Exception {
//
//		// column test, all columns translates to price, weight and popularity
//		Assert.assertEquals(getSolrTranslation("select * from example"), "*:*");
//
//	}

}

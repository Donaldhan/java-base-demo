package cn.home.uri;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author donald 2017年12月27日 下午9:18:49
 */
public class URLTest {
	private static final Logger log = LoggerFactory.getLogger(URLTest.class);
	private static URL url = null;
	private static URI uri = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		url = new URL("https://www.donald.com:8080/goods/index.html?username=donald&passwd=123456#springboot");
		uri = url.toURI();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		url = null;
		uri = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testURL() {
		log.info("url         ：{}", url.toString());
		log.info("protocol    ：{}", url.getProtocol());
		log.info("authority   ：{}", url.getAuthority());
		log.info("file name   ：{}", url.getFile());
		log.info("host        ：{}", url.getHost());
		log.info("path        ：{}", url.getPath());
		log.info("port        ：{}", url.getPort());
		log.info("default port：{}", url.getDefaultPort());
		log.info("query       ：{}", url.getQuery());
		log.info("ref         ：{}", url.getRef());
	}
	@Test
	public void testURI() {
		log.info("uri                : {}", uri.toString());
		log.info("scheme             : {}", uri.getScheme());
		log.info("SchemeSpecificPart : {}", uri.getSchemeSpecificPart());
		log.info("Authority          : {}", uri.getAuthority());
		log.info("host               : {}", uri.getHost());
		log.info("port               : {}", uri.getPort());
		log.info("path               : {}", uri.getPath());
		log.info("query              : {}", uri.getQuery());
		log.info("fragment           : {}", uri.getFragment());
	}

}

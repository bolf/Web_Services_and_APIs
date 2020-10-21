package com.udacity.pricing;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PricingServiceApplicationTests {
	private Long vehicleId = 5l;

	@Test
	public void testOKResponseStatusFromPricingService() throws IOException {
		HttpUriRequest request = new HttpGet( "http://localhost:8082/prices/" + vehicleId);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertTrue(response.getStatusLine().getStatusCode() == 200);
	}

	@Test
	public void	testResponseMimeTypeFromPricingService() throws IOException {
		HttpUriRequest request = new HttpGet( "http://localhost:8082/prices/" + vehicleId);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();

		assertEquals( "application/hal+json", mimeType );
	}

	@Test
	public void getPriceFromPricingService() throws IOException, JSONException {
		HttpUriRequest request = new HttpGet( "http://localhost:8082/prices/" + vehicleId);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		JSONObject priceObject = new JSONObject(EntityUtils.toString(response.getEntity()));

		assertTrue(Long.parseLong(priceObject.get("vehicleId").toString()) == vehicleId);
	}

	@Test
	public void getAllPricesFromPricingService() throws IOException, JSONException {
		HttpUriRequest request = new HttpGet( "http://localhost:8082/prices/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		JSONObject priceObject = new JSONObject(EntityUtils.toString(response.getEntity()));
		assertTrue(priceObject.getJSONObject("_embedded").getJSONArray("prices").length() > 0);
	}

}

package guru.sfg.beer.order.service.services.beer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-21 20:49
 */
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {

  private static final String BEER_SERVICE_PATH = "/api/v1/beer-upc/{beerUpc}";

  private String beerServiceHost;

  public void setBeerServiceHost(String beerServiceHost) {
    this.beerServiceHost = beerServiceHost;
  }

  private RestTemplate restTemplate;

  public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Override
  public BeerDto getBeerByUpc(String upc) {

    ResponseEntity<BeerDto> response =
        restTemplate.exchange(
            beerServiceHost + BEER_SERVICE_PATH,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<BeerDto>() {},
            (Object) upc);

    return response.getBody();
  }
}

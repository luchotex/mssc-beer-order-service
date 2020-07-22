package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerDto;
import guru.sfg.beer.order.service.services.beer.BeerServiceRestTemplateImpl;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-21 21:15
 */
public class BeerOrderLineDecorator implements BeerOrderLineMapper {

  private BeerOrderLineMapper beerOrderLineMapper;
  private BeerServiceRestTemplateImpl beerServiceRestTemplate;

  @Autowired
  public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
    this.beerOrderLineMapper = beerOrderLineMapper;
  }

  @Autowired
  public void setBeerServiceRestTemplate(BeerServiceRestTemplateImpl beerServiceRestTemplate) {
    this.beerServiceRestTemplate = beerServiceRestTemplate;
  }

  @Override
  public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
    BeerOrderLineDto result = beerOrderLineMapper.beerOrderLineToDto(line);

    BeerDto beerDto = beerServiceRestTemplate.getBeerByUpc(line.getUpc());
    result.setBeerName(beerDto.getBeerName());
    result.setBeerStyle(beerDto.getBeerStyle().name());
    result.setPrice(beerDto.getPrice());
    result.setBeerId(line.getBeerId());

    return result;
  }

  @Override
  public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
    return beerOrderLineMapper.dtoToBeerOrderLine(dto);
  }
}

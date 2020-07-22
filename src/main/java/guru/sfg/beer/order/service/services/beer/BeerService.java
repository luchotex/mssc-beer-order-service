package guru.sfg.beer.order.service.services.beer;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-21 20:45
 */
public interface BeerService {


    BeerDto getBeerByUpc(String beerId);
}

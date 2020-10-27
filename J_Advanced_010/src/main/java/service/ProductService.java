package service;

import java.util.Map;

import domain.Product;
import shared.AbstractCrud;

public interface ProductService extends AbstractCrud<Product> {

	public Map<Integer, Product> readAllMap();
}

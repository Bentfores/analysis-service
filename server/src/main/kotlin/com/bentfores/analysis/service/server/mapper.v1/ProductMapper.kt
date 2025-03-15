package com.bentfores.analysis.service.server.mapper.v1

import com.bentfores.analysis.service.ProductInfo
import com.bentfores.analysis.service.server.external.goodsAggregator.model.Products
import org.springframework.stereotype.Component

@Component
class ProductMapper {

  fun mapToProductsList(productsInfo: List<ProductInfo.ProductsInfo.Product>): List<Products> {
    return productsInfo.map {
      Products(
        article = it.article,
        name = it.name,
        imageUrl = it.imageUrl,
        profitPlace = it.profitPlace,
      )
    }
  }
}
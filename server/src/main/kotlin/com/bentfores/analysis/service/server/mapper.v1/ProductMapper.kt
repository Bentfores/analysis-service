package com.bentfores.analysis.service.server.mapper.v1

import com.bentfores.analysis.service.ProductInfo
import com.bentfores.analysis.service.server.external.goodsAggregator.model.Products
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model.ProductsInfo
import org.springframework.stereotype.Component

@Component
class ProductMapper {

  fun mapToProductsList(productsInfo: List<ProductInfo.ProductsInfo.Product>) =
    productsInfo.map {
      Products(
        article = it.article,
        name = it.name,
        imageUrl = it.imageUrl,
        profitPlace = it.profitPlace,
      )
  }

  fun mapProductsInfoToProductsList(productsInfo: ProductsInfo): List<Products> =
    listOf(
      Products(
        article = productsInfo.article,
        name = productsInfo.name,
        imageUrl = productsInfo.imageUrl,
        profitPlace = productsInfo.profitPlace,
      )
    )

  fun mapProductsInfoListToProductsList(productsInfo: List<ProductsInfo>): List<Products> =
    productsInfo.map {
      Products(
        article = it.article,
        name = it.name,
        imageUrl = it.imageUrl,
        profitPlace = it.profitPlace
      )
    }
}
package com.bentfores.analysis.service.server.service.v1

import com.bentfores.analysis.service.ProductInfo.ProductsInfo
import com.bentfores.analysis.service.api.model.ParsedSupplier
import com.bentfores.analysis.service.api.model.SuppliersInfo
import com.bentfores.analysis.service.server.data.repository.SupplierRepository
import com.bentfores.analysis.service.server.external.goodsAggregator.GoodsAggregatorApi
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.GoodsAndSellerManagementApi
import com.bentfores.analysis.service.server.mapper.v1.ProductMapper
import com.bentfores.analysis.service.server.mapper.v1.SupplierMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class AnalysisServiceV1(
  private val supplierRepository: SupplierRepository,
  private val goodsAndSellerManagementApi: GoodsAndSellerManagementApi,
  private val goodsAggregatorApi: GoodsAggregatorApi,
  private val productMapper: ProductMapper,
  private val supplierMapper: SupplierMapper
) {

  fun updateSuppliers(message: ProductsInfo) {
    val ignoredArticles = goodsAndSellerManagementApi.getProductsInfo().map { it.article }
    val processedProducts = message.productsList.filter { !ignoredArticles.contains(it.article) }

    goodsAggregatorApi.getSuppliersInfo(productMapper.mapToProductsList(processedProducts))
  }

  fun getSuppliers(article: String): List<SuppliersInfo> {
    val suppliers = supplierRepository.findAllByArticle(article)
    val suppliersInfo = goodsAndSellerManagementApi.getSuppliersInfoByNames(
      suppliers.map { it.supplierName }
    )
    return supplierMapper.mapToSuppliersInformationList(suppliers, suppliersInfo)
      .sortedWith(
        compareBy<SuppliersInfo> { it.price }
          .thenByDescending { it.rating }
          .thenByDescending { it.years }
      )
  }

  fun postSuppliersSearch(article: String) {
    val product = goodsAndSellerManagementApi.getProductInfo(article)
    goodsAggregatorApi.getSuppliersInfo(productMapper.mapProductsInfoToProductsList(product))
  }

  fun sendMessage(productUrl: String, article: String?, suppliers: UUID) {
    goodsAndSellerManagementApi.patchSuppliersStatus(suppliers = listOf(suppliers), article = listOf(article))
    goodsAggregatorApi.postSendMessage(productUrl)
  }

  @Transactional
  fun saveParsedSuppliers(parsedSupplier: List<ParsedSupplier>) {
    supplierRepository.deleteAllByArticle(parsedSupplier[0].article)

    val ignoredSuppliers = goodsAndSellerManagementApi.getSuppliersInfo(listOf("BLACKLISTED")).map { it.name }
    val processedSuppliers = parsedSupplier.filter { !ignoredSuppliers.contains(it.supplierName) }

    supplierRepository.saveAll(supplierMapper.mapParsedSupplierToSupplierList(processedSuppliers))

    goodsAndSellerManagementApi.saveSuppliersInfo(
      parsedSupplier[0].article,
      parsedSupplier[0].imageUrl,
      parsedSupplier[0].supplierProductUrl,
      parsedSupplier[0].profitPlace,
      supplierMapper.mapToNewSupplierInfoList(parsedSupplier)
    )
  }
}


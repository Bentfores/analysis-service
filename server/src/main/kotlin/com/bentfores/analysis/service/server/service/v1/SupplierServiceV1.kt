package com.bentfores.analysis.service.server.service.v1

import com.bentfores.analysis.service.ProductInfo.ProductsInfo
import com.bentfores.analysis.service.api.model.SuppliersInformation
import com.bentfores.analysis.service.server.data.repository.SupplierRepository
import com.bentfores.analysis.service.server.external.goodsAggregator.GoodsAggregatorApi
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.GoodsAndSellerManagementApi
import com.bentfores.analysis.service.server.mapper.v1.ProductMapper
import com.bentfores.analysis.service.server.mapper.v1.SupplierMapper
import org.springframework.stereotype.Service

@Service
class SupplierServiceV1(
  private val supplierRepository: SupplierRepository,
  private val goodsAndSellerManagementApi: GoodsAndSellerManagementApi,
  private val goodsAggregatorApi: GoodsAggregatorApi,
  private val productMapper: ProductMapper,
  private val supplierMapper: SupplierMapper
) {

  fun updateSuppliers(message: ProductsInfo) {
    supplierRepository.deleteAll()

    val ignoredArticles = goodsAndSellerManagementApi.getProductsInfo()
    val processedProducts = message.productsList.filter { !ignoredArticles.contains(it.article) }

    val suppliers = goodsAggregatorApi.getSuppliersInfo(productMapper.mapToProductsList(processedProducts))
    val ignoredSuppliers = goodsAndSellerManagementApi.getSuppliersInfo(listOf("BLACKLISTED"))
    val processedSuppliers = suppliers.filter { !ignoredSuppliers.contains(it.supplierUrl) }

    supplierRepository.saveAll(supplierMapper.mapToSupplierList(processedSuppliers))
  }

  fun getSuppliers(): List<SuppliersInformation> {
    return supplierMapper.mapToSuppliersInformationList(supplierRepository.findAllAndSortByProfitPlace())
  }
}


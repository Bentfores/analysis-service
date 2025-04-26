package com.bentfores.analysis.service.server.job

import com.bentfores.analysis.service.server.data.repository.SupplierRepository
import com.bentfores.analysis.service.server.external.goodsAggregator.GoodsAggregatorApi
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.GoodsAndSellerManagementApi
import com.bentfores.analysis.service.server.mapper.v1.ProductMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.transaction.Transactional
import org.quartz.Job
import org.quartz.JobExecutionContext

open class BlankSupplierInfoGetterJob(
  private val supplierRepository: SupplierRepository,
  private val goodsAggregatorApi: GoodsAggregatorApi,
  private val goodsAndSellerManagementApi: GoodsAndSellerManagementApi,
  private val productMapper: ProductMapper,
) : Job {

  @Transactional
  override fun execute(context: JobExecutionContext) {
    val products = goodsAndSellerManagementApi.getProductsInfo("NOT_PROCESSED")
    products.forEach { product ->
      val suppliersCount = supplierRepository.countByArticle(product.article)
      if (suppliersCount < 30) {
        goodsAggregatorApi.getSuppliersInfo(productMapper.mapProductsInfoToProductsList(product))
        log.info { "Less than 30 suppliers found for ${product.article}. Count: $suppliersCount" }
      }
    }
  }

  companion object {
    private val log = KotlinLogging.logger(BlankSupplierInfoGetterJob::class.simpleName!!)
  }
}
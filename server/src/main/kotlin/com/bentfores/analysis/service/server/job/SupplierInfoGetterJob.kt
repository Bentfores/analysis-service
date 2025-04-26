package com.bentfores.analysis.service.server.job

import com.bentfores.analysis.service.server.data.entity.SchedulerInfo
import com.bentfores.analysis.service.server.data.repository.SchedulerInfoRepository
import com.bentfores.analysis.service.server.external.goodsAggregator.GoodsAggregatorApi
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.GoodsAndSellerManagementApi
import com.bentfores.analysis.service.server.mapper.v1.ProductMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.transaction.Transactional
import org.quartz.Job
import org.quartz.JobExecutionContext
import java.time.LocalDate

open class SupplierInfoGetterJob(
  private val schedulerInfoRepository: SchedulerInfoRepository,
  private val goodsAggregatorApi: GoodsAggregatorApi,
  private val goodsAndSellerManagementApi: GoodsAndSellerManagementApi,
  private val productMapper: ProductMapper,
) : Job {

  @Transactional
  override fun execute(context: JobExecutionContext) {
    val timeNow = LocalDate.now()
    val task = schedulerInfoRepository.existsByCreatedAt(timeNow)
    if (!task) {
      val products = goodsAndSellerManagementApi.getProductsInfo("NOT_PROCESSED")
      products.forEach { product ->
        goodsAggregatorApi.getSuppliersInfo(productMapper.mapProductsInfoToProductsList(product))
      }
      schedulerInfoRepository.save(SchedulerInfo())
    } else {
      log.info { "Scheduler info already exists: $timeNow" }
    }
  }

  companion object {
    private val log = KotlinLogging.logger(SupplierInfoGetterJob::class.simpleName!!)
  }
}
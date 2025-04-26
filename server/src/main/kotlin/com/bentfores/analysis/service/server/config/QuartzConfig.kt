package com.bentfores.analysis.service.server.config

import com.bentfores.analysis.service.server.config.properties.JobProperties
import com.bentfores.analysis.service.server.job.BlankSupplierInfoGetterJob
import com.bentfores.analysis.service.server.job.SupplierInfoGetterJob
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.SimpleScheduleBuilder
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Date

@Configuration
class QuartzConfig(
  private val jobProperties: JobProperties
) {

  @Bean
  @ConditionalOnProperty(
    name = ["jobs.supplier-info-getter.enabled"],
    havingValue = "true",
    matchIfMissing = false
  )
  fun supplierInfoGetterJobDetail(): JobDetail =
    JobBuilder.newJob()
      .ofType(SupplierInfoGetterJob::class.java)
      .storeDurably()
      .withIdentity("SUPPLIER_INFO_GETTER")
      .withDescription("Get supplier info by day from external system")
      .build()

  @Bean
  @ConditionalOnProperty(
    name = ["jobs.blank-supplier-info-getter.enabled"],
    havingValue = "true",
    matchIfMissing = false
  )
  fun blankSupplierInfoGetterJobDetail(): JobDetail =
    JobBuilder.newJob()
      .ofType(BlankSupplierInfoGetterJob::class.java)
      .storeDurably()
      .withIdentity("BLANK_SUPPLIER_INFO_GETTER")
      .withDescription("Get Supplier info if product exists from external system")
      .build()

  @Bean
  @ConditionalOnBean(name = ["supplierInfoGetterJobDetail"])
  fun trigger(): Trigger =
    TriggerBuilder.newTrigger()
      .forJob(supplierInfoGetterJobDetail())
      .withIdentity("DEFAULT_TRIGGER")
      .withDescription("Default job trigger")
      .withSchedule(
        SimpleScheduleBuilder.repeatSecondlyForever(jobProperties.supplierInfoGetter.fixedDelay)
      ).startAt(
        Date(
          System.currentTimeMillis() + jobProperties.supplierInfoGetter.initialDelayInMillis
        )
      )
      .forJob(blankSupplierInfoGetterJobDetail())
      .withIdentity("DEFAULT_TRIGGER")
      .withDescription("Default job trigger")
      .withSchedule(
        SimpleScheduleBuilder.repeatSecondlyForever(jobProperties.blankSupplierInfoGetter.fixedDelay)
      ).startAt(
        Date(
          System.currentTimeMillis() + jobProperties.blankSupplierInfoGetter.initialDelayInMillis
        )
      )
      .build()
}
package com.bentfores.analysis.service.server.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "scheduler_info")
data class SchedulerInfo(
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  var id: UUID? = null,

  @Column(name = "scheduler_name")
  var schedulerName: SchedulerName = SchedulerName.SUPPLIER_INFO_GETTER,

  @Column(name = "created_at")
  var createdAt: LocalDate = LocalDate.now()
) {
  enum class SchedulerName {
    SUPPLIER_INFO_GETTER
  }
}

package com.bentfores.analysis.service.server.data.repository

import com.bentfores.analysis.service.server.data.entity.SchedulerInfo
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.UUID

interface SchedulerInfoRepository : JpaRepository<SchedulerInfo, UUID> {
  fun findByCreatedAt(createdAt: LocalDate): MutableList<SchedulerInfo>
  fun existsByCreatedAt(createdAt: LocalDate): Boolean
}
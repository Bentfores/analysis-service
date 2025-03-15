package com.bentfores.analysis.service.server.data.repository

import com.bentfores.analysis.service.server.data.entity.Supplier
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SupplierRepository : JpaRepository<Supplier, UUID> {

  @Query(
    """
      select s from Supplier s
      order by s.profitPlace
    """
  )
  fun findAllAndSortByProfitPlace(): List<Supplier>
}
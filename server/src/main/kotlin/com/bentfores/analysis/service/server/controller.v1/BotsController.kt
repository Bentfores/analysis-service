package com.bentfores.analysis.service.server.controller.v1

import com.bentfores.analysis.service.api.model.SuppliersInformation
import com.bentfores.analysis.service.api.v1.SuppliersApi
import com.bentfores.analysis.service.server.service.v1.SupplierServiceV1
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class BotsController(
  private val supplierServiceV1: SupplierServiceV1
) : SuppliersApi {

  override fun suppliersByGet(): ResponseEntity<List<SuppliersInformation>> {
    return ResponseEntity.ok(
      supplierServiceV1.getSuppliers()
    )
  }
}
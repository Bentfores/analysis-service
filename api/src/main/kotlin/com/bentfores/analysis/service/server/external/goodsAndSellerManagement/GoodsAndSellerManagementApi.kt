package com.bentfores.analysis.service.server.external.goodsAndSellerManagement

import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model.ManagementSuppliersInfo
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model.NewSupplier
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model.ProductsInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal
import java.util.UUID

interface GoodsAndSellerManagementApi {
  @GetMapping("/management/products/{status}")
  fun getProductsInfo(
    @PathVariable("status") status: String = "IGNORED"
  ): List<ProductsInfo>

  @GetMapping("/management/product-article/{article}")
  fun getProductInfo(
    @PathVariable("article") article: String
  ): ProductsInfo

  @GetMapping("/management/suppliers")
  fun getSuppliersInfo(
    @RequestParam(name = "statuses", required = true) statuses: List<String>,
  ): List<ManagementSuppliersInfo>

  @PostMapping("/management/suppliers/names")
  fun getSuppliersInfoByNames(
    @RequestBody supplierNames: List<String>,
  ): List<ManagementSuppliersInfo>

  @PostMapping("/management/suppliers")
  fun saveSuppliersInfo(
    @RequestParam("article", required = true) article: String,
    @RequestParam("imageUrl", required = true) imageUrl: String,
    @RequestParam("productName", required = true) productName: String,
    @RequestParam("profitPlace", required = true) profitPlace: BigDecimal,
    @RequestBody suppliers: List<NewSupplier>
  )

  @PatchMapping("/management/suppliers/{status}")
  fun patchSuppliersStatus(
    @PathVariable("status") status: String = "MESSAGE_SENT",
    @RequestParam suppliers: List<UUID>,
    @RequestParam article: List<String?>,
  )
}
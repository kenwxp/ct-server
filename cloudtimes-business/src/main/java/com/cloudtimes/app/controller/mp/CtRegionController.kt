package com.cloudtimes.app.controller.mp

import com.cloudtimes.agent.dto.request.CtRegionRequest
import com.cloudtimes.app.constant.PrefixPathConstants
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.resources.domain.CtRegion
import com.cloudtimes.resources.dto.response.CtRegionResponse
import com.cloudtimes.resources.service.ICtRegionService
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

class RegionListResponse: RestPageResult<CtRegion>()
class RegionTreeListResponse: RestPageResult<CtRegionResponse>()

@RestController
@RequestMapping(PrefixPathConstants.WX_OFFICIAL_PATH_PREFIX + "/region")
@Tag(name = "代理-区域")
class CtRegionController {
    @Autowired
    private lateinit var ctRegionService: ICtRegionService

    @PostMapping("/list")
    @Operation(summary = "查询地区信息列表")
    fun list(@Valid @RequestBody request: CtRegionRequest): RegionListResponse {
        val region = CtRegion()
        region.regionLevel = request.regionLevel
        val regions: List<CtRegion> = ctRegionService.selectCtRegionList(region)
        return RegionListResponse().apply {
            data = regions
            total = regions.size.toLong()
        }
    }

    @PostMapping("/tree")
    @Operation(summary = "查询地区树")
    fun tree(): RegionTreeListResponse {
        val regions: List<CtRegionResponse> = ctRegionService.selectCtRegionTree()

        return RegionTreeListResponse().apply {
            data = regions
            total = regions.size.toLong()
        }
    }
}

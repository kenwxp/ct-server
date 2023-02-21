package com.cloudtimes.app.controller.agent

import com.cloudtimes.agent.dto.request.CtRegionRequest
import com.cloudtimes.common.core.domain.RestPageResult
import com.cloudtimes.resources.domain.CtRegion
import com.cloudtimes.resources.service.ICtRegionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

class RegionListResponse: RestPageResult<CtRegion>()

@RestController
@RequestMapping("/agent/region")
@Api(tags = ["代理-区域"])
class CtRegionController {
    @Autowired
    private lateinit var ctRegionService: ICtRegionService

    @PostMapping("/list")
    @ApiOperation("查询地区信息列表")
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
    @ApiOperation("查询地区树")
    fun tree(): RegionListResponse {
        val regions: List<CtRegion> = ctRegionService.selectCtRegionTree()

        return RegionListResponse().apply {
            data = regions
            total = regions.size.toLong()
        }
    }
}
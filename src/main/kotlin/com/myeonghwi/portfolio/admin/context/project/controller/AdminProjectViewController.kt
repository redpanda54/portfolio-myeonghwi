package com.myeonghwi.portfolio.admin.context.project.controller

import com.myeonghwi.portfolio.admin.context.project.service.AdminProjectService
import com.myeonghwi.portfolio.admin.data.FormElementDTO
import com.myeonghwi.portfolio.admin.data.SelectFormElementDTO
import com.myeonghwi.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project")
class AdminProjectViewController(
    private val adminProjectService: AdminProjectService
) {

    @GetMapping
    fun project(model: Model): String {
        // FORM 요소 세팅
        val elements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 4),
            TextFormElementDTO("description", 8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 2, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("elements", elements)

        // 상세 FORM 요소 세팅
        val detailElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 4),
            TextFormElementDTO("url", 6),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("detailElements", detailElements)

        // 테이블 정보 세팅
        val table = adminProjectService.getProjectTable()
        model.addAttribute("table", table)

        // 상세 테이블 정보 세팅
        val detailTable = adminProjectService.getProjectDetailTable(null)
        model.addAttribute("detailTable", detailTable)

        // 페이지 속성 세팅
        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Projects"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", true)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"

    }
}
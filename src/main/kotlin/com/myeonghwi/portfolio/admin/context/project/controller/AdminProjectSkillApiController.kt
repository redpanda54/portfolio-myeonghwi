package com.myeonghwi.portfolio.admin.context.project.controller

import com.myeonghwi.portfolio.admin.context.project.form.ProjectSkillForm
import com.myeonghwi.portfolio.admin.context.project.service.AdminProjectSkillService
import com.myeonghwi.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/projects/skills")
class AdminProjectSkillApiController(
    private val adminProjectSkillService: AdminProjectSkillService
) {

    @PostMapping
    fun postProjectSkill(@RequestBody form: ProjectSkillForm): ResponseEntity<Any> {
        adminProjectSkillService.save(form)
        return ApiResponse.successCreate()
    }

    @DeleteMapping("/{id}")
    fun deleteProjectSkill(@PathVariable("id") id: Long): ResponseEntity<Any> {
        adminProjectSkillService.deleteProjectSkill(id)
        return ApiResponse.successDelete()
    }

}
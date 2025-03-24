package com.myeonghwi.portfolio.admin.context.project.service

import com.myeonghwi.portfolio.admin.context.project.form.ProjectSkillForm
import com.myeonghwi.portfolio.admin.data.TableDTO
import com.myeonghwi.portfolio.admin.exception.AdminBadRequestException
import com.myeonghwi.portfolio.admin.exception.AdminInternalServerErrorException
import com.myeonghwi.portfolio.domain.entity.ProjectSkill
import com.myeonghwi.portfolio.domain.repository.ProjectRepository
import com.myeonghwi.portfolio.domain.repository.ProjectSkillRepository
import com.myeonghwi.portfolio.domain.repository.SkillRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectSkillService(
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository,
    private val projectSkillRepository: ProjectSkillRepository
) {

    @Transactional
    fun getProjectSkillTable(): TableDTO {
        val projects = projectRepository.findAll()

        val columns = mutableListOf<String>(
            "id", "projectId", "projectName", "skillId",
            "skillName", "createdDateTime", "updatedDateTime"
        )
        val records = mutableListOf<MutableList<String>>()

        for (project in projects) {
            project.skills.forEach {
                val record = mutableListOf<String>()
                record.add(it.id.toString())
                record.add(it.project.id.toString())
                record.add(it.project.name)
                record.add(it.skill.id.toString())
                record.add(it.skill.name)
                record.add(it.createdDateTime.toString())
                record.add(it.updatedDateTime.toString())
                records.add(record)
            }
        }

        return TableDTO(name = "ProjectSkill", columns = columns, records = records)
    }

    fun getProjectList(): List<String> {
        val projects = projectRepository.findAll()
        return projects.map { "${it.id} (${it.name})" }.toList()
    }

    fun getSkillList(): List<String> {
        val skills = skillRepository.findAll()
        return skills.map { "${it.id} (${it.name})" }.toList()
    }

    @Transactional
    fun save(form: ProjectSkillForm) {

        // 이미 매핑된 Project - Skill 여부 검증
        val projectId = parseId(form.project)
        val skillId = parseId(form.skill)
        projectSkillRepository.findByProjectIdAndSkillId(projectId, skillId)
            .ifPresent { throw AdminBadRequestException("이미 매핑된 데이터입니다.") }

        // 유효한 ProjectSkill 생성
        val project = projectRepository.findById(projectId)
            .orElseThrow { throw AdminBadRequestException("ID ${projectId}에 해당하는 데이터를 찾을 수 없습니다.") }
        val skill = skillRepository.findById(skillId)
            .orElseThrow { throw AdminBadRequestException("ID ${skillId}에 해당하는 데이터를 찾을 수 없습니다.") }
        val projectSkill = ProjectSkill(project = project, skill = skill)

        // 연관관계 세팅
        project.skills.add(projectSkill)
    }

    private fun parseId(line: String): Long {
        try {
            val endIndex = line.indexOf(" ") - 1
            val id = line.slice(0..endIndex).toLong()
            return id
        } catch (e: Exception) {
            throw AdminInternalServerErrorException("ID 추출 중 오류가 발생했습니다.")
        }
    }

    fun deleteProjectSkill(id: Long) {
        projectSkillRepository.deleteById(id)
    }
}
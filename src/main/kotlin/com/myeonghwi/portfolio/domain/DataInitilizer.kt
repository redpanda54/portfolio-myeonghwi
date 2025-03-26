package com.myeonghwi.portfolio.domain

import com.myeonghwi.portfolio.domain.constant.SkillType
import com.myeonghwi.portfolio.domain.entity.*
import com.myeonghwi.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitilizer<Achievement>(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository
) {

    val log = LoggerFactory.getLogger(DataInitilizer::class.java)

    @PostConstruct
    fun initializeData() {
        log.info("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        val achievement = mutableListOf<com.myeonghwi.portfolio.domain.entity.Achievement>(
            Achievement(
                title = "정보처리기사",
                description = "자료구조, 운영체제, 알고리즘, 데이터베이스 등",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2024, 9, 10),
                isActive = true,
            )
        )
        achievementRepository.saveAll(achievement)

        val introduction = mutableListOf<Introduction>(
            Introduction(content = "주도적으로 문제를 찾고, 해결하는 고양이입니다.", isActive = true),
            Introduction(content = "기술을 위한 기술이 아닌, 비즈니스 문제를 풀기 위한 기술을 추구합니다.", isActive = true)
        )
        introductionRepository.saveAll(introduction)

        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/redpanda54", isActive = true)
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "한국외국어대학교(Hankuk University of Foreign Studies)",
            description = "수학과",
            startYear = 2019,
            startMonth = 3,
            endYear = 2025,
            endMonth = 8,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "학점 4.01/4.5", isActive = true
                )
            )
        )

        val experience2 = Experience(
            title = "한국외국어대학교(Hankuk University of Foreign Studies)",
            description = "컴퓨터공학과",
            startYear = 2022,
            startMonth = 9,
            endYear = 2025,
            endMonth = 8,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "학점 4.07/4.5", isActive = true
                )
            )
        )

        val experience3 = Experience(
            title = "UMC HUFS 4th Master's course",
            description = "Node.js Team",
            startYear = 2023,
            startMonth = 3,
            endYear = 2023,
            endMonth = 8,
            isActive = true
        )
        experience3.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "pre-course: 벡엔드 기초 스터디", isActive = true
                ),
                ExperienceDetail(
                    content = "final-project: 러브키퍼",isActive = true
                )
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val javascript = Skill(name = "JavaScript", type = SkillType.LANGUAGE.name, isActive = true)
        val python = Skill(name = "Python", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val django = Skill(name = "Django", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val mongodb = Skill(name = "MongoDB", type = SkillType.DATABASE.name, isActive = true)
        val kafka = Skill(name = "Kafka", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, javascript, python, spring, django, mysql, mongodb, kafka))

        val project1 = Project(
            name = "러브키퍼",
            description = "소중한 연인과의 관계를 유지하고 싸움을 방지하는 웹/앱 어플리케이션입니다.",
            startYear = 2023,
            startMonth = 7,
            endYear = 2023,
            endMonth = 8,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "연인 간 싸움 발생시 화해 요청 기능으로 원활한 갈등 개선 가능", url = null, isActive = true),
                ProjectDetail(content = "연인 간 금칙어(금칙어를 입력시 입력 제한)가 적용된 채팅 가능", url = null, isActive = true)
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = javascript),
                ProjectSkill(project = project1, skill = mongodb)
            )
        )

        projectRepository.saveAll(mutableListOf(project1))
    }
}
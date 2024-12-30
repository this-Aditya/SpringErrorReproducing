package org.base.server.repository

import org.base.server.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProjectRepository: JpaRepository<Project, Long> {
    fun findByProjectId(projectId: String): Optional<Project>
    fun existsByProjectId(projectId: String): Boolean
}
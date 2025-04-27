package org.base.server.repository

import org.base.server.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProjectRepository : JpaRepository<Project?, Long?> {
    fun findByProjectId(projectId: String?): Optional<Project?>?

    fun existsByProjectId(projectId: String?): Boolean?
}
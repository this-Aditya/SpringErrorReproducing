### Server Test 

## How To reproduce error when converting to kotlin;

Either refactor the `ProjectRepository` or `ProjectService` class to kotlin code like:

```kotlin
package org.base.server.repository

import org.base.server.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectRepository : JpaRepository<Project?, Long?> {
    fun findByProjectId(projectId: String?): Optional<Project?>?

    fun existsByProjectId(projectId: String?): Boolean?
```

Or update the ProjectService class to: 

```kotlin
package org.base.server.service

import org.base.server.converter.ProjectConverter
import org.base.server.dto.ProjectDto
import org.base.server.dto.ProjectDtos
import org.base.server.entity.Project
import org.base.server.exception.InvalidProjectDetailsException
import org.base.server.exception.NotFoundException
import org.base.server.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProjectService @Autowired constructor(
    @field:Transient private val projectRepository: ProjectRepository,
    @field:Transient private val projectConverter: ProjectConverter
) {
    @get:Transactional(readOnly = true)
    val allProjects: ProjectDtos?
        get() = ProjectDtos()
            .setProjects(projectConverter.entitiesToDtos(projectRepository.findAll()))

    @Transactional(readOnly = true)
    fun getProjectById(id: Long): ProjectDto? {
        val project = projectRepository.findById(id)

        if (project.isPresent()) {
            return projectConverter.entityToDto(project.get())
        } else {
            throw NotFoundException("Project not found with id" + id)
        }
    }

    @Transactional(readOnly = true)
    fun getProjectByProjectId(projectId: String?): ProjectDto? {
        val project = projectRepository.findByProjectId(projectId)

        if (project.isPresent()) {
            return projectConverter.entityToDto(project.get())
        } else {
            throw NotFoundException("Project not found with projectId" + projectId)
        }
    }

    @Transactional
    fun addProject(projectDto: ProjectDto): ProjectDto? {
        if (projectDto.getId() != null) {
            throw InvalidProjectDetailsException(
                projectDto,
                IllegalArgumentException(
                    "'id' must not be supplied when creating a project as it is autogenerated."
                )
            )
        }

        if (projectDto.getProjectId() == null || projectDto.getProjectId().isEmpty()) {
            throw InvalidProjectDetailsException(
                projectDto, IllegalArgumentException("At least 'project id' must be supplied")
            )
        }

        if (projectRepository.existsByProjectId(projectDto.getProjectId())) {
            throw InvalidProjectDetailsException(
                String.format(
                    "The project with specified project-id %s already exists. Use Update endpoint if need to update the project.",
                    projectDto.getProjectId()
                )
            )
        }

        return projectConverter.entityToDto(
            this.projectRepository.save<Project?>(projectConverter.dtoToEntity(projectDto))
        )
    }

    @Transactional
    fun updateProject(projectDto: ProjectDto): ProjectDto? {
        if (projectDto.getId() == null) {
            throw InvalidProjectDetailsException(
                projectDto,
                IllegalArgumentException("The 'id' of the project must be supplied for updating.")
            )
        }
        val project = projectRepository.findById(projectDto.getId())

        val resultProject: Project
        if (project.isPresent()) {
            resultProject = project.get().setProjectId(projectDto.getProjectId())
        } else {
            throw NotFoundException("The project could not be found with details " + projectDto)
        }

        val savedProject = this.projectRepository.save<Project>(resultProject)
        return projectConverter.entityToDto(savedProject)
    }
```

And it will result in error when using mixed kotlin-java code 
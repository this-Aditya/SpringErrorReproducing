
package org.base.server.converter;

import org.base.server.dto.ProjectDto;
import org.base.server.entity.Project;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProjectConverter implements Converter<Project, ProjectDto> {

    @Override
    public Project dtoToEntity(ProjectDto projectDto) {

        return new Project().setProjectId(projectDto.getProjectId());
    }

    @Override
    public ProjectDto entityToDto(Project project) {

        return new ProjectDto()
                .setId(project.getId())
                .setProjectId(project.getProjectId())
                .setCreatedAt(project.getCreatedAt().toInstant())
                .setUpdatedAt(project.getUpdatedAt().toInstant());
    }
}
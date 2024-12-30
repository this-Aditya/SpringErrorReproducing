
package org.base.server.dto;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectDtos {

    @Size(max = 500)
    private List<ProjectDto> projects;

    public ProjectDtos() {
        this.projects = new ArrayList<>();
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public ProjectDtos setProjects(List<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    public ProjectDtos addProject(ProjectDto project) {
        this.projects.add(project);
        return this;
    }
}
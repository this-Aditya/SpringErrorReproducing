
package org.base.server.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.base.server.dto.ProjectDto;

@Table(name = "projects")
@Entity
@Getter
@ToString
@NoArgsConstructor
public class Project extends AuditModel implements Serializable {

    private static final long serialVersionUID = 12312466855464L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "project_id", unique = true, nullable = false)
    private String projectId;

    public Project setId(Long id) {
        this.id = id;
        return this;
    }

    public Project setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(getProjectId(), project.getProjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId());
    }
}
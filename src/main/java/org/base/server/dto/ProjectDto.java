package org.base.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDto implements Serializable {

    private static final long serialVersionUID = 2L;

    private Long id;

    private String projectId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }

    public ProjectDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ProjectDto setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ProjectDto setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ProjectDto setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
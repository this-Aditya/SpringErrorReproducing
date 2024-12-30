
package org.base.server.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.base.server.exception.NotFoundException;
import org.base.server.dto.ProjectDto;
import org.base.server.dto.ProjectDtos;
import org.base.server.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@Slf4j
public class ProjectController {

    private transient ProjectService projectService;

    public ProjectController(
            ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Method for updating a project.
     *
     * @param projectDto The project info to update
     * @return The updated Project DTO. Throws {@link
     *     NotFoundException} if project was not found.
     */
    @PostMapping(
            value = "/" + PathsUtil.PROJECT_PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectDto> addProject(
            HttpServletRequest request, @Valid @RequestBody ProjectDto projectDto)
            throws URISyntaxException, IOException {
        ProjectDto projectDtoNew = this.projectService.addProject(projectDto);
        return ResponseEntity.created(new URI("/projects/project?id=" + projectDtoNew.getId()))
                .body(projectDtoNew);
    }

    /**
     * Method for updating a project.
     *
     * @param projectDto The project info to update
     * @return The updated Project DTO. Throws {@link
     *     NotFoundException} if project was not found.
     */
    @PutMapping(
            value = "/" + PathsUtil.PROJECT_PATH + "/" + PathsUtil.PROJECT_ID_CONSTANT,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectDto> updateProject(
            @Valid @PathParam("projectId") String projectId, @Valid @RequestBody ProjectDto projectDto,
            HttpServletRequest request) {
        ProjectDto projectDto1 = this.projectService.updateProject(projectDto);
        return ResponseEntity.ok(projectDto1);
    }

    @GetMapping("/" + PathsUtil.PROJECT_PATH)
    public ResponseEntity<ProjectDtos> getAllProjects(HttpServletRequest request) {

        ProjectDtos projectDtos = this.projectService.getAllProjects();
        return ResponseEntity.ok(projectDtos);
    }

    // TODO think about plain authorized
    @GetMapping("/" + PathsUtil.PROJECT_PATH + "/project")
    public ResponseEntity<ProjectDto> getProjectsUsingId(
            HttpServletRequest request, @Valid @PathParam("id") Long id) {
        ProjectDto projectDto = this.projectService.getProjectById(id);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping("/" + PathsUtil.PROJECT_PATH + "/" + PathsUtil.PROJECT_ID_CONSTANT)
    public ResponseEntity<ProjectDto> getProjectsUsingProjectId(
            @Valid @PathVariable String projectId) {
        return ResponseEntity.ok(this.projectService.getProjectByProjectId(projectId));
    }
}
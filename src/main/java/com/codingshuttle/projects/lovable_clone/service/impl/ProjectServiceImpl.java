package com.codingshuttle.projects.lovable_clone.service.impl;

import com.codingshuttle.projects.lovable_clone.dto.project.ProjectRequest;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectResponse;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.codingshuttle.projects.lovable_clone.entity.Project;
import com.codingshuttle.projects.lovable_clone.entity.User;
import com.codingshuttle.projects.lovable_clone.error.ResourceNotFoundException;
import com.codingshuttle.projects.lovable_clone.mapper.ProjectMapper;
import com.codingshuttle.projects.lovable_clone.repository.ProjectRepository;
import com.codingshuttle.projects.lovable_clone.repository.UserRepository;
import com.codingshuttle.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    //private final ProjectRepository projectRepository;// if you are using FieldDefaults then no need to use private final
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        //we are returning ProjectResponse here
        //which is dto
        //but here we have project = which is entity
        //so we need to convert this (project)entity to Dto(ProjectResponse)

        //return new ProjectResponse(project.getId(), project.getName(), project.getCreatedAt(), project.getUpdatedAt(), );
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {


        // return projectRepository.findAllAccessibleByUser(userId);//this is giving us array of Project but we want ProjectSummaryResponse
        //there are two ways where we can convert this to ProjectSummaryResponse
        //1.stream
        /*return projectRepository.findAllAccessibleByUser(userId).stream()
                .map(project -> projectMapper.toProjectSummaryResponse(project))
                .collect(Collectors.toList());*/
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }


    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update ");
        }

        project.setName(request.name());
        project = projectRepository.save(project);//we do not have to write this because we have transactional context written

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    //Internal Function
    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("project ", projectId.toString()));
    }
}

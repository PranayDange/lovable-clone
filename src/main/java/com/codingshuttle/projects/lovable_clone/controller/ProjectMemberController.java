package com.codingshuttle.projects.lovable_clone.controller;

import com.codingshuttle.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.codingshuttle.projects.lovable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.codingshuttle.projects.lovable_clone.entity.ProjectMember;
import com.codingshuttle.projects.lovable_clone.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    //this is constructor injection as we are using RequiredArgsConstructor
    private final ProjectMemberService projectMemberService;

    /*//this is also constructor injection
    public ProjectMemberService(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }*/

    //this is field injection
    /*@Autowired
    private ProjectMemberService projectMemberService2;*/

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId, @RequestBody InviteMemberRequest request) {
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request, userId));
    }

    @PatchMapping("/{memberId}") //if you want to update only few thing use //PutMapping got updating whole
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRoleRequest request
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request, userId));
    }

    @DeleteMapping("/{memberId}") //if you want to update only few thing use //PutMapping got updating whole
    public ResponseEntity<MemberResponse> deleteMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.deleteMemberRole(projectId, memberId, userId));
    }

}

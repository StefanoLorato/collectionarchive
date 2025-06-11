package org.generation.italy.collectionarchive.restcontrollers;

import jakarta.validation.constraints.Min;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.service.AdminService;
import org.generation.italy.collectionarchive.restdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
public class AdminRestController {
    private AdminService adminService;

    @Autowired
    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> users = adminService.findAllUsers().stream().map(UserDto::toDto).toList();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<UserDto> promoteToAdmin(@PathVariable int userId) {
        User u = adminService.promoteToAdmin(userId);
        return ResponseEntity.ok(UserDto.toDto(u));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean deleted = adminService.deleteNonAdminUser(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

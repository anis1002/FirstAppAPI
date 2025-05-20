package com.api.FirstAppAPI.repository;


import com.api.FirstAppAPI.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u " +
            "WHERE (:firstname IS NULL OR LOWER(u.firstname) LIKE LOWER(CONCAT('%', :firstname, '%'))) " +
            "AND (:lastname IS NULL OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :lastname, '%'))) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    Page<User> findByFilters(String firstname, String lastname, String email, Pageable pageable);
}

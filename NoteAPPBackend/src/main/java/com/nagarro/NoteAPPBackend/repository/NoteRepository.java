package com.nagarro.NoteAPPBackend.repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagarro.NoteAPPBackend.models.Notes;
import com.nagarro.NoteAPPBackend.models.User;

/**
 * The `NoteRepository` interface provides CRUD operations for the `Notes` entity.
 */
public interface NoteRepository extends JpaRepository<Notes, Integer>{

	 /**
     * Find all notes by user.
     */
	List<Notes> findByUser(User user);
	
	// Find the top 10 notes by user ordered by creation date in descending order.
	List<Notes> findTop10ByUserOrderByCreatedDtDesc(User user);
	
	
	@Query("SELECT n.id FROM Notes n WHERE n.user.id = :userId ORDER BY n.createdDt DESC")

    List<Long> findLastTenNoteIds(@Param("userId") Long userId, PageRequest pageRequest);

	
	//Delete notes for a user that are not in the last ten notes.
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM notes WHERE user_id = :userId AND id NOT IN :lastTenNoteIds", nativeQuery = true)

	void deleteUsers(@Param("userId") Long userId, @Param("lastTenNoteIds") List<Long> lastTenNoteIds);

}



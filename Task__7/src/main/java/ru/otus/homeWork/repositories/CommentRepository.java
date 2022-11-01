package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeWork.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { }
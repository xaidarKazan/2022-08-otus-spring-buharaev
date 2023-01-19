package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeWork.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin (String login);
}
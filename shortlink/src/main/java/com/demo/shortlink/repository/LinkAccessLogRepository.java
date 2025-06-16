package com.demo.shortlink.repository;

import com.demo.shortlink.model.LinkAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkAccessLogRepository extends JpaRepository<LinkAccessLog, Long> {
}

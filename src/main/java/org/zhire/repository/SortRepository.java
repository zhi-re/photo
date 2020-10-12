package org.zhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.dao.Photo;
import org.zhire.dao.Sort;


public interface SortRepository extends JpaRepository<Sort, Long> {
}


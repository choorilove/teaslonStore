package com.example.teastore.repo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.teastore.models.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long>{
        Item findBySort(String sort);
        Item findById(long id);

        @Query("SELECT i FROM Item i ORDER BY i.sort")
        List<Item> findAllSorted();

        List<Item> findByDescriptionContainingIgnoreCase(String description);
;
}

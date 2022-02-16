package com.teleclimb;

import com.teleclimb.rest.entities.CategoryEntity;
import com.teleclimb.rest.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired
    private CategoryRepository repo;

    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        CategoryEntity category = new CategoryEntity();
        category.setName("best");
        category.setFromAge(10);
        category.setToAge(20);
        repo.save(category);
        List<CategoryEntity> users = repo.findAll();

        Assertions.assertEquals(users.get(0), category);
    }

}

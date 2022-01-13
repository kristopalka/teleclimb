package com.teleclimb;

import com.teleclimb.rest.entity.Category;
import com.teleclimb.rest.repository.CategoryRepository;
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
        Category category = new Category();
        category.setName("best");
        category.setFromAge(10);
        category.setToAge(20);
        repo.save(category);
        List<Category> users = repo.findAll();

        Assertions.assertEquals(users.get(0), category);
    }

}

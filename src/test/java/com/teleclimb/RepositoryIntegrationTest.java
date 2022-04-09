package com.teleclimb;

import com.teleclimb.rest.repositories.CategoryRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired
    private CategoryRepository repo;



}

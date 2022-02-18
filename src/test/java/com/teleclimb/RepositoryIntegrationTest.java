package com.teleclimb;

import com.google.gson.Gson;
import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.Round;
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

    @Test
    public void json() {
        Round eliminationRound = Round.builder()
                .competitionId(null)
                .name("Eliminacyje")
                .sequenceNumber(0)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_ELIMINATIONS)
                .build();

        Round finalRound = Round.builder()
                .competitionId(null)
                .name("Finał")
                .sequenceNumber(1)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(1)
                .maxParticipants(8)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_FINAL)
                .build();

        Gson gson = new Gson();
        String eliminations = gson.toJson(eliminationRound);
        System.out.println("----------------------------------------------------------");
        System.out.println(eliminations);
        String finals = gson.toJson(finalRound);
        System.out.println(finals);
        System.out.println("----------------------------------------------------------");
    }


}

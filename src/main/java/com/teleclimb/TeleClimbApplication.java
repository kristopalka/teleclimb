package com.teleclimb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeleClimbApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeleClimbApplication.class, args);
    }
}

//todo zrobić obiekty DTO osobne do posta i puta, tak żeby można było wsadzić tylko te dane które są potrzebne/można edytować
//todo prznieść generowanie rund z generatora do jakiegoś jsona jako rekor w bazie. Generator powinien tylko brać jsona i tworzyć dokładnie to co zapisano, bez tego całego switch case
//todo przenieść dane testowe z pliku sql do JAWY (bo tam nie ma walidacji)
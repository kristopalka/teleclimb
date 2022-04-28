
# TeleClimb-App
This repository holds code of application backend developed in Java for head judge in climbing competitions.

- Web app repository: https://github.com/AndrzejKrzywda00/TeleClimb-Web
- Mobile app repository: https://github.com/AndrzejKrzywda00/TeleClimb-Mobile


## Description:
Celem projektu jest ukończenie oraz rozwój systemu do sędziowania zawodów wspinaczkowych. Główne założenie systemu, to zautomatyzowanie przekazywania oraz przetwarzania danych podczas zawodów wspinaczkowych. Projekt ten zostanie zrealizowany jako odpowiedź na zapotrzebowanie rynkowe - nie istnieje system działający w dany sposób. Jedyny istniejący system realizujący podobne zadania jest dostępny tylko dla Międzynarodowej Federacji Wspinaczki Sportowej (IFSC).
System na poziomie fizycznych urządzeń składa się z komputera oraz kilku urządzeń mobilnych. Na komputerze uruchomione będą dwie aplikacje: backend oraz aplikacja webowa, na urządzeniach mobilnych natomiast, aplikacje mobilne. Łączność pomiędzy komponentami w podstawowym scenariuszu powinna być zapewniona przez lokalną sieć bezprzewodową. 

### Schemat fizyczny systemu zestawiony z jego komponentami
![Schemat fizyczny systemu zestawiony z jego komponentami](https://lh3.googleusercontent.com/fife/AAWUweVL1B61DO8RzhjLKkIZqc5VcdfUtMZbmgD_fnvDt-UxustAwflgBiAM03mswN4UnWWerdMCzyHb_7aDsHu_LlrO-6Lwk8mFu_3HbF1iqgsAzbHZxZinryOAzsalffmod3gzNKqwDOZ3pyZgItM-OWDju7ujqq9XoKxblhdMWSGZNLiQPSwp4ysveKWshdGo7EJC_X0mhE1_7N8dlIGH84j6uWVtSd3oe7fl3D42bVSAIqoetBfYL_QVbShWzDPkej7xjVWavSh6FUBUDEdIZQL84h8usJDsu4LErkE1HiYuMqEXqEEzB1JMaxJwww46JLA4nqblGg6lPln7wIpvHg6OAtLxC5c78UmrIhwN_4MRGywWNEDMDhp3gNU36AbUiIwBMqEMWFsu08wRSryW_NUsWrc5eM3Bb62JGmaTAwDYY_tGmOJZu8RgH2BTjMAysGBeYTLewEmPj_Xgc1mge83jwP0YW8it1omk2T6k3UbHD8Z6qgtYOfMH824GFr4f8IerE69TpCtZY2t1q7v3d5-kzlr7jvsnbCxzmkUViXTeOyTxJsNYP9rWAqSJ8fpRAmAODtRgHrcs5A1Ltu99sbsWdvvKeifn-qzm79ZD45C57UL-kKX5XJo65ydb1IQ9442JjpOS_0j6SYSLt_AS3Y1YYWhABLIGiuM4l2HiOTwx9w4YL4pTWjBQUYdY8oQEbzixsYCZJjcKl8WWBoGd0XV6qXOXvFVDA04wnIzK3kMnsPOe8a7nd-pf0h-one9RgIfTw5aUGoCdVIfaT9n29m71jNdc8YQ5Lq3CF4j1npKOhN_c0lrwbRK1B2g=w1919-h1004)



### System będzie składał się z:
- aplikacja webowa (JavaScript, ReactJS) służy do konfiguracji zawodów, obsługi listy zawodników, zarządzania kolejnymi etapami zawodów, eksportowania wyników, itp. Korzystać będzie z niej sędzia główny - do jego zadań należy zarządzanie całym wydarzeniem.
- aplikacja mobilna (JavaScript, ReactNative) służy do wpisywania wyników zebranych podczas zawodów. Używana będzie przez sędziów problemów. Każdemu sędziemu za pomocą algorytmów zgodnych z przepisami wspinaczki sportowej, przydzieleni zostaną zawodnicy, aby maksymalnie zautomatyzować proces przeprowadzania zawodów.
- backend (Java, Spring) wystawia REST API. Będzie zawierał całą logikę działania systemu. Dane będzie zapisywał i pobierał ze wbudowanej plikowej bazy danych. Dodatkowo powinien tworzyć logi, niezbędne w razie awarii do odtworzenia informacji o startach.

Część systemu została już wykonana. Po stronie backendu istnieje większość architektury bazy danych, jednak na pewno będzie ona wymagać dopracowania. Aplikacja webowa zawiera tylko kreator zawodów. Prace nad aplikacją mobilną nie zostały rozpoczęte. W ramach projektu zostaną zaimplementowane pozostałe główne składniki aplikacji. Aplikacja po wstępnych testach zostanie przetestowana na zawodach wspinaczkowych rangi Mistrzostw Polski.

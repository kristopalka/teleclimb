
# TeleClimb-App
This repository holds code of application backend developed in Java for head judge in climbing competitions.

- Web app repository: https://github.com/AndrzejKrzywda00/TeleClimb-Web
- Mobile app repository: https://github.com/AndrzejKrzywda00/TeleClimb-Mobile


## Description:
Celem projektu jest ukończenie oraz rozwój systemu do sędziowania zawodów wspinaczkowych. Główne założenie systemu, to zautomatyzowanie przekazywania oraz przetwarzania danych podczas zawodów wspinaczkowych. Projekt ten zostanie zrealizowany jako odpowiedź na zapotrzebowanie rynkowe - nie istnieje system działający w dany sposób. Jedyny istniejący system realizujący podobne zadania jest dostępny tylko dla Międzynarodowej Federacji Wspinaczki Sportowej (IFSC).
System na poziomie fizycznych urządzeń składa się z komputera oraz kilku urządzeń mobilnych. Na komputerze uruchomione będą dwie aplikacje: backend oraz aplikacja webowa, na urządzeniach mobilnych natomiast, aplikacje mobilne. Łączność pomiędzy komponentami w podstawowym scenariuszu powinna być zapewniona przez lokalną sieć bezprzewodową. 

### Schemat fizyczny systemu zestawiony z jego komponentami
![Schemat fizyczny systemu zestawiony z jego komponentami](https://lh5.googleusercontent.com/QMGq5Gy2UtMunCDRY2sW-yhqJUWFOccCsJfw3uO25O15ZmQLlMJv1VHhKmDWFauI1DDgR2ZLyie3Ww=w1470-h973)



### System będzie składał się z:
- aplikacja webowa (JavaScript, ReactJS) służy do konfiguracji zawodów, obsługi listy zawodników, zarządzania kolejnymi etapami zawodów, eksportowania wyników, itp. Korzystać będzie z niej sędzia główny - do jego zadań należy zarządzanie całym wydarzeniem.
- aplikacja mobilna (JavaScript, ReactNative) służy do wpisywania wyników zebranych podczas zawodów. Używana będzie przez sędziów problemów. Każdemu sędziemu za pomocą algorytmów zgodnych z przepisami wspinaczki sportowej, przydzieleni zostaną zawodnicy, aby maksymalnie zautomatyzować proces przeprowadzania zawodów.
- backend (Java, Spring) wystawia REST API. Będzie zawierał całą logikę działania systemu. Dane będzie zapisywał i pobierał ze wbudowanej plikowej bazy danych. Dodatkowo powinien tworzyć logi, niezbędne w razie awarii do odtworzenia informacji o startach.

Część systemu została już wykonana. Po stronie backendu istnieje większość architektury bazy danych, jednak na pewno będzie ona wymagać dopracowania. Aplikacja webowa zawiera tylko kreator zawodów. Prace nad aplikacją mobilną nie zostały rozpoczęte. W ramach projektu zostaną zaimplementowane pozostałe główne składniki aplikacji. Aplikacja po wstępnych testach zostanie przetestowana na zawodach wspinaczkowych rangi Mistrzostw Polski.

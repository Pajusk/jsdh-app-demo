# 🚒 JSDH App – Webová aplikace pro dobrovolné hasiče

Tato aplikace slouží pro správu výjezdů a potvrzení účasti členů jednotky sboru dobrovolných hasičů. Vyvíjeno pro JSDH Ostrava Heřmanice jako interní nástroj pro organizaci výjezdů a rychlou zpětnou vazbu od členů.

## 🧩 Funkce

- ✅ Přihlášení s rolemi (ADMIN, VELITEL, HASIČ)
- 📢 Zobrazení aktuálního výjezdu
- 👨‍🚒 Potvrzení účasti členů na výjezdu
- 📊 Přehled kdo jede / nejede / nepotvrdil účast
- 🛠️ Administrátorské rozhraní pro spuštění testovacího výjezdu

## 👥 Role uživatelů

- **ADMIN** – přístup ke všem stránkám, aktivace testovacích výjezdů
- **VELITEL** – přehled o účasti, zobrazení stanice
- **HASIC** – potvrzení účasti, zobrazení aktivního výjezdu

## 💻 Technologie

- **Backend**: Spring Boot, Spring Security, Hibernate
- **Frontend**: Thymeleaf + Bootstrap 5
- **Databáze**: MySQL (testováno s Railway cloud DB)
- **Jazyk**: Java 17+

## 📁 Struktura

src/
├── controller/ # Webové kontrolery
├── dto/ # Přenosové objekty
├── model/ # Entitní třídy (User, Vyjezd, Potvrzeni, Role)
├── repository/ # JPA repozitáře
├── security/ # Custom security třídy
├── service/ # Servisní logika
└── templates/ # HTML šablony (Thymeleaf)

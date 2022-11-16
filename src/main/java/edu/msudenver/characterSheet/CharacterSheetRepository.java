package edu.msudenver.characterSheet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, String> {
    CharacterSheet getCharacterSheetByCharacterName(String characterName);
}
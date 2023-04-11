package com.mediscreen.history.config;


import com.mediscreen.history.model.Note;
import com.mediscreen.history.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = NoteRepository.class)
@Configuration
public class MongoConfig {
    @Bean
    CommandLineRunner commandLineRunner (NoteRepository noteRepository){
        return strings->{
            noteRepository.save(new Note("1n","1","01/01/2021","Le patient déclare qu'il «se sent très bien»\n" +
                    "Poids égal ou inférieur au poids recommandé"));
            noteRepository.save(new Note("2n","1","01/02/2021","Le patient déclare qu'il se sent fatigué pendant la journée\n" +
                    "Il se plaint également de douleurs musculaires\n" +
                    "Tests de laboratoire indiquant une microalbumine élevée"));
            noteRepository.save(new Note("3n","1","03/03/2021","Le patient déclare qu'il ne se sent pas si fatigué que ça\n" +
                    "Fumeur, il a arrêté dans les 12 mois précédents\n" +
                    "Tests de laboratoire indiquant que les anticorps sont élevés"));
            noteRepository.save(new Note("4n","2","03/01/2021","Le patient déclare qu'il ressent beaucoup de stress au travail\n" +
                    "Il se plaint également que son audition est anormale dernièrement"));
            noteRepository.save(new Note("5n","2","03/02/2021","Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois\n" +
                    "Il remarque également que son audition continue d'être anormale"));
            noteRepository.save(new Note("6n","2","03/03/2021","Tests de laboratoire indiquant une microalbumine élevée"));
            noteRepository.save(new Note("7n","2","03/04/2021","Le patient déclare que tout semble aller bien\n" +
                    "Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé\n" +
                    "Le patient déclare qu’il fume depuis longtemps"));
            noteRepository.save(new Note("8n","3","03/01/2021","Le patient déclare qu'il fume depuis peu"));
            noteRepository.save(new Note("9n","3","03/02/2021","Tests de laboratoire indiquant une microalbumine élevée"));
            noteRepository.save(new Note("10n","3","03/03/2021","Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière\n" +
                    "Il se plaint également de crises d’apnée respiratoire anormales\n" +
                    "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));
            noteRepository.save(new Note("11n","3","03/04/2021","Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));
            noteRepository.save(new Note("12n","4","03/01/2021","Le patient déclare qu'il lui est devenu difficile de monter les escaliers\n" +
                    "Il se plaint également d’être essoufflé\n" +
                    "Tests de laboratoire indiquant que les anticorps sont élevés\n" +
                    "Réaction aux médicaments"));
            noteRepository.save(new Note("13n","4","03/02/2021","Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"));
            noteRepository.save(new Note("14n","4","03/03/2021","hLe patient déclare avoir commencé à fumer depuis peu\n" +
                    "Hémoglobine A1C supérieure au niveau recommandé"));
            noteRepository.save(new Note("15n","5","03/01/2021","Le patient déclare avoir des douleurs au cou occasionnellement\n" +
                    "Le patient remarque également que certains aliments ont un goût différent\n" +
                    "Réaction apparente aux médicaments\n" +
                    "Poids corporel supérieur au poids recommandé"));
            noteRepository.save(new Note("16n","5","03/02/2021","Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\n" +
                    "Taille incluse dans la fourchette concernée"));
            noteRepository.save(new Note("17n","5","03/03/2021","Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles\n" +
                    "Tests de laboratoire indiquant une microalbumine élevée\n" +
                    "Fumeur, il a arrêté dans les 12 mois précédents"));
            noteRepository.save(new Note("18n","5","03/04/2021","Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\n" +
                    "Tests de laboratoire indiquant que les anticorps sont élevés"));
            noteRepository.save(new Note("19n","6","03/01/2021","Le patient déclare qu'il se sent bien\n" +
                    "Poids corporel supérieur au poids recommandé"));
            noteRepository.save(new Note("20n","6","03/02/2021","Le patient déclare qu'il se sent bien"));
            noteRepository.save(new Note("21n","7","03/02/2021","Le patient déclare qu'il se réveille souvent avec une raideur articulaire\n" +
                    "Il se plaint également de difficultés pour s’endormir\n" +
                    "Poids corporel supérieur au poids recommandé\n" +
                    "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));
            noteRepository.save(new Note("22n","8","03/02/2021","Les tests de laboratoire indiquent que les anticorps sont élevés\n" +
                    "Hémoglobine A1C supérieure au niveau recommandé"));
            noteRepository.save(new Note("23n","9","03/001/2021","Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires\n" +
                    "Hémoglobine A1C supérieure au niveau recommandé"));
            noteRepository.save(new Note("24n","9","03/02/2021","Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée\n" +
                    "Il signale également que les produits du distributeur automatique ne sont pas bons\n" +
                    "Tests de laboratoire signalant des taux anormaux de cellules sanguines"));
            noteRepository.save(new Note("25n","9","03/003/2021","Le patient signale qu'il est facilement irrité par des broutilles\n" +
                    "Il déclare également que l'aspirateur des voisins fait trop de bruit\n" +
                    "Tests de laboratoire indiquant que les anticorps sont élevés"));
            noteRepository.save(new Note("26n","10","03/01/2021","Le patient déclare qu'il n'a aucun problème"));
            noteRepository.save(new Note("27n","10","03/02/2021","Le patient déclare qu'il n'a aucun problème\n" +
                    "Taille incluse dans la fourchette concernée\n" +
                    "Hémoglobine A1C supérieure au niveau recommandé"));
            noteRepository.save(new Note("28n","10","03/03/2021","Le patient déclare qu'il n'a aucun problème\n" +
                    "Poids corporel supérieur au poids recommandé\n" +
                    "Le patient a signalé plusieurs épisodes de vertige depuis sa dernière visite"));
            noteRepository.save(new Note("29n","10","03/04/2021","Le patient déclare qu'il n'a aucun problème\n" +
                    "Tests de laboratoire indiquant une microalbumine élevée"));

        };
    }
}

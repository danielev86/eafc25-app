package com.redcatdev86.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fifa16LeaguesView extends VBox {

    private final Label instruction = new Label("Sceglie un campionato tra quelli disponibili");
    private final Button chooseLeague = new Button("SCEGLI");
    private final Label leagueLabel = new Label("");

    private final List<String> leagues = List.of(
            "Argentina Primera División",
            "Australia A-League",
            "Austria Fußball-Bundesliga",
            "Belgium Pro League",
            "China FA Super League",
            "Denmark Superliga",
            "England Football League Championship",
            "England Football League One",
            "England Football League Two",
            "England Premier League",
            "France Ligue 1",
            "France Ligue 2",
            "Germany 1. Bundesliga",
            "Germany 2. Bundesliga",
            "Germany 3. Liga",
            "India Super League",
            "Italy Serie A",
            "Italy Serie B",
            "Korea Republic K League 1",
            "Netherlands Eredivisie",
            "Norway Eliteserien",
            "Poland Ekstraklasa",
            "Portugal Primeira Liga",
            "Republic of Ireland Premier Division",
            "Romania Liga I",
            "Saudi Arabia Pro League",
            "Scotland Premiership",
            "Spain Primera División",
            "Spain Segunda División",
            "Sweden Allsvenskan",
            "Switzerland Super League",
            "Turkey Süper Lig",
            "USA Major League Soccer",
            "Bangladesh Football League",
            "Cambodia Premier League",
            "Hong Kong Premier League",
            "Indonesia Liga I",
            "Japan J1 League",
            "Laos League 1",
            "Malaysia Super League",
            "Myanmar National League",
            "Qatar Stars League",
            "Singapore Premier League",
            "Tajikistan Higher League",
            "Thailand League 1",
            "UAE Pro-League",
            "Uzbekistan Super League",
            "Algeria Ligue Professionnelle 1",
            "Angola Girabola",
            "Egypt Premier League",
            "Ethiopia Premier League",
            "Ghana Premier League",
            "Morocco Botola",
            "Nigeria NPFL",
            "South Africa Premier Division",
            "Tunisia Ligue Professionnelle 1",
            "Uganda Premier League",
            "Canada Premier League",
            "Costa Rica Primera División",
            "Dominican Republic LDF",
            "Honduras LNFP",
            "Mexico Liga MX",
            "Panama Football League",
            "USA USL Championship",
            "USA USL League One",
            "Bolivia Liga de Fútbol Profesional",
            "Brazil Serie A",
            "Brazil Serie B",
            "Chile Primera División",
            "Colombia Categoría Primera A",
            "Ecuador Serie A",
            "Paraguay Primera División",
            "Peru Primera División",
            "Uruguay Primera División",
            "Venezuela Primera División",
            "Albania Superliga",
            "Andorra Primera Divisió",
            "Andorra Segona Divisió",
            "Armenia Premier League",
            "Austria 2. Liga",
            "Azerbaijan Premier League",
            "Belarus Premier League",
            "Belgium Challenger Pro League",
            "Bosnia-Herzegovina Premijer Liga",
            "Bulgaria Parva Liga",
            "Croatia HNL",
            "Croatia Prva NL",
            "Cyprus First Division",
            "Czech Republic First League",
            "Czech Republic FNL",
            "England National League",
            "England National League North",
            "England National League South",
            "Estonia Meistriliiga",
            "Faroe Islands Betri Deildin",
            "Finland Veikkausliiga",
            "France Championnat National",
            "Georgia Erovnuli Liga",
            "Germany Regionalliga Bayern",
            "Germany Regionalliga Nord",
            "Germany Regionalliga Nordost",
            "Germany Regionalliga Südwest",
            "Germany Regionalliga West",
            "Gibraltar Football League",
            "Greece Super League 1",
            "Greece Super League 2 North",
            "Greece Super League 2 South",
            "Hungary Nemzeti Bajnokság I",
            "Hungary Nemzeti Bajnokság II",
            "Iceland Besta Deildin",
            "Israel Premier League",
            "Italy Serie C Girone A",
            "Italy Serie C Girone B",
            "Italy Serie C Girone C",
            "Kazakhstan Premier Liga",
            "Kosovo Superliga",
            "Latvia Virslīga",
            "Lithuania A Lyga",
            "Luxembourg Nationaldivisioun",
            "Malta Premier League",
            "Moldova Super Liga",
            "Montenegro First League",
            "Netherlands Eerste Divisie",
            "Netherlands Tweede Divisie",
            "North Macedonia First Football League",
            "Northern Ireland NIFL Premiership",
            "Norway 1. Divisjon",
            "Norway 2. Divisjon Avdeling 1",
            "Norway 2. Divisjon Avdeling 2",
            "Poland 1 Liga",
            "Poland 2 Liga",
            "Portugal Segunda Liga",
            "Republic of Ireland First Division",
            "Romania Liga II",
            "Romania Liga III A *",
            "Romania Liga III B *",
            "Russia Premier Liga",
            "San Marino Campionato di Calcio",
            "Scotland Championship",
            "Scotland League One",
            "Scotland League Two",
            "Serbia SuperLiga",
            "Serbia Prva Liga",
            "Slovakia Super Liga",
            "Slovakia 2. Liga",
            "Slovenia Prva Liga",
            "Spain Primera Federación Grupo I",
            "Spain Primera Federación Grupo II",
            "Sweden Superettan",
            "Sweden Ettan Norra",
            "Sweden Ettan Södra",
            "Switzerland Challenge League",
            "Turkey TFF 1. Lig",
            "Ukraine Premier League",
            "Wales Cymru Premier"
    );

    public Fifa16LeaguesView() {
        getStyleClass().add("page");
        setAlignment(Pos.TOP_CENTER);
        setSpacing(14);
        setPadding(new Insets(18, 10, 10, 10));

        Label title = new Label("FIFA 16 LEAGUES");
        title.getStyleClass().add("page-title");

        instruction.getStyleClass().add("home-muted");
        instruction.setAlignment(Pos.CENTER);
        instruction.setWrapText(true);

        chooseLeague.getStyleClass().add("cta-btn");

        leagueLabel.getStyleClass().add("result-type");
        leagueLabel.setMaxWidth(Double.MAX_VALUE);
        leagueLabel.setAlignment(Pos.CENTER);
        leagueLabel.setWrapText(true);

        getChildren().addAll(
                title,
                instruction,
                chooseLeague,
                leagueLabel
        );

        chooseLeague.setOnAction(e -> chooseRandomLeague());
    }

    private void chooseRandomLeague() {
        int idx = ThreadLocalRandom.current().nextInt(leagues.size());
        leagueLabel.setText("SELECTED LEAGUE: " + leagues.get(idx));
    }
}
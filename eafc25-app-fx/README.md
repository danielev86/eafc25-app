Use this command to create exe file

jpackage --type app-image --name EAFCIssueGenerator --input target\app --main-jar eafc25-app-fx-1.0.0.jar --main-class com.redcatdev86.App --module-path "C:\work\jdk17.0.11\jmods;C:\work\javafx-jmods-17.0.18" --add-modules java.sql,java.logging,javafx.controls,javafx.graphics --dest dist
\c

create user "bentfores-analysis-service" with password 'bentfores-analysis-service';

create database "bentfores-analysis-service" with owner = postgres;

grant all privileges on database "bentfores-analysis-service" to "bentfores-analysis-service";

\c "bentfores-analysis-service"

alter role "bentfores-analysis-service" set search_path = bentfores_analysis_service, public;

create schema bentfores_analysis_service authorization "bentfores-analysis-service";

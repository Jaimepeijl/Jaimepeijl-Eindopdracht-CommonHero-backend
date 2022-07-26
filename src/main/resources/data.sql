insert into users(id,username,password,email,name, city, enabled)
    values (1, 'Jaime', '$2a$12$Un2aO49GQ7cbSVh8h/ydAe.KXyhUFjoNYePhUZXs6Gk6laOYv64le', 'j.d.peijl@gmail.com', 'Jaime', 'Utrecht', true),
           (2, 'Erik', '$2a$12$Un2aO49GQ7cbSVh8h/ydAe.KXyhUFjoNYePhUZXs6Gk6laOYv64le', 'hoi@hotmail.com', 'gebruiker', 'Zaandam', true);

insert into authorities(username, authority)
    values ('Jaime', 'ADMIN'),
           ('Erik', 'USER');

insert into vacancies(id, publisher, title, hours, vactype, description)
values(1, 'Jaime', 'Rechtenstudent biedt hulp aan', 5, 'offer', 'Ik ben een 3e jaars rechtenstudent en ik doe graag buiten mijn studie om extra ervaring op. Ik help graag bij civielrecht problemen. Schroom niet om een berichtje te sturen als je ergens hulp bij kan gebruiken.'),
       (2, 'Erik', 'Kan helpen bij klusjes', 3, 'offer', 'Sinds mijn pensioen mis ik het klussen heel erg. Ik ben een lange tijd timmerman geweest en heb daarnaast nog andere klussen gedaan. Ik zou je graag voor niks willen helpen met klussen in en rondom het huis. Scheelt je weer iemand inhuren.'),
       (3, 'Jaime', 'Oma naar een verjaardag rijden', 1, 'search', 'Mijn oma is 87 en mag niet meer zelf rijden. Ze wil erg graag naar de verjaardag van een goede vriendin in Arnhem. Ik moet helaas werken. Zou iemand haar van Utrecht naar Arnhem kunnen rijden op zaterdag 15 augustus?');
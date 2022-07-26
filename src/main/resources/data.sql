insert into users(id,username,password,email,name, city, enabled)
    values (1, 'Jaime', '$2a$12$Un2aO49GQ7cbSVh8h/ydAe.KXyhUFjoNYePhUZXs6Gk6laOYv64le', 'j.d.peijl@gmail.com', 'Jaime', 'Utrecht', true),
           (2, 'Erik', '$2a$12$Un2aO49GQ7cbSVh8h/ydAe.KXyhUFjoNYePhUZXs6Gk6laOYv64le', 'hoi@hotmail.com', 'gebruiker', 'Zaandam', true);

insert into authorities(username, authority)
    values ('Jaime', 'ADMIN'),
           ('Erik', 'USER');

insert into vacancy_offer(id, publisher, title, hours, description)
values(1, 'Jaime', 'Rechtenstudent biedt hulp aan', 5, 'Ik ben een 3e jaars rechtenstudent en ik doe graag buiten mijn studie om extra ervaring op. Ik help graag bij civielrecht problemen. Schroom niet om een berichtje te sturen als je ergens hulp bij kan gebruiken.'),
       (2, 'Erik', 'Kan helpen bij klusjes', 3, 'Sinds mijn pensioen mis ik het klussen heel erg. Ik ben een lange tijd timmerman geweest en heb daarnaast nog andere klussen gedaan. Ik zou je graag voor niks willen helpen met klussen in en rondom het huis. Scheelt je weer iemand inhuren.'),
       (3, 'Erik', 'kan je altijd een lift geven', 1, 'Ik vind het leuk om te rijden en heb een mooie auto. Mocht iemand een keer een lift nodig hebben ergens heen dan kan ik je wellicht brengen. Scheelt weer een taxi ritje');

-- insert into vacancy_search(id, publisher, title, hours, description)
-- values(1, 'Jaime', 'Oma naar een verjaardag rijden', 1, 'Mijn oma is 87 en mag niet meer zelf rijden. Ze wil erg graag naar de verjaardag van een goede vriendin in Arnhem. Ik moet helaas werken. Zou iemand haar van Utrecht naar Arnhem kunnen rijden op zaterdag 15 augustus?'),
--        (2, 'Erik', 'Helpen op een AZC', 3, 'Wij willen op het AZC in Dondrecht graag een mooie dag bezorgen aan alle vluchtelingen. We hebben nog een kok nodig en iemand die Arabisch spreekt. Ik hoor graag van u.');

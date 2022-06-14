# [A1S2-SAE201-204](https://docs.google.com/document/d/11juwPCNEA2fKKK1GGAqvxkfQHdSBS_Eog4N-AeLFhj4/edit)



### Set Up MARIA DB
> https://info.blaisepascal.fr/rpi-installer-un-sgbd
> https://raspberrytips.com/install-mariadb-raspberry-pi/

### Set Up Postgres
> https://opensource.com/article/17/10/set-postgres-database-your-raspberry-pi


iutbg-lamp.univ-lyon1.fr:3306/edt
installer wpagui
sudo wpa-gui


### JFreeChart DOC

Exemple de code JFreeChart : <br />

Camembert :
>> https://www.javatips.net/blog/jfreechart-example

Histogramme :
>> https://www.roseindia.net/tutorial/java/jfreechart/createhistogram.html <br/>
>> https://coderslegacy.com/java/jfreechart-histogram/


## REQUETE SQL
Création de profiles utilisateurs : 
>> https://www.hostinger.fr/tutoriels/creer-un-utilisateur-mysql#:~:text=Ensuite%2C%20exécutez%20la%20commande%20suivante,de%20passe%20de%20cet%20utilisateur.
<br/>
Ancien fichier de revision adminDB

```
-- donne le droit de lecture select sur Employe à l'utilisateur Y;
grant select on Employe to p2103280;
revoke select on Employe from p2103280;

-- affiche les permissions 
show grants;

-- affiche toute la table en question
select * from p2100832.Employe;

-- donne le droite de lecture sur la colonne nom de la table Service à l'utilisateur X;
grant select(nom) on Service to p2100832;
revoke select(nom) on Service from p2100832;

-- modifie le salaire de Maurice en y ajoutant 10%
update p2100832.Employe set salaire = salaire *1.1 where prenom = "Maurice";

-- supprime le service scolarité 
delete from p2103280.Service where nom = 'Scolarité';
grant alter on Employe to p2103280;

-- supprimer une colonne
ALTER TABLE nom_table DROP COLUMN nom_colonne;

-- commande pour la VM
su
ifconfig
nano /etc/mysql/my.cnf
systemctl restart mysql.service
mysql -u root -p

-- cree utilisateur avec mdp myroot
create user myroot identified by 'mdp';

grant all on (database).(table) to myroot with grant option;
```


Document claroline : configuer NetBeam Remote
https://clarolineconnect.univ-lyon1.fr/clarolinepdfplayerbundle/pdf/7073456

ActionListener JMenu :
https://koor.fr/Java/TutorialSwing/swing_JMenuBar.wp

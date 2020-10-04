import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;

public class test {
	public static void main(String[] args) {
		String s = "";
		String s2 = "";
		String s3 = "";
		String s16 = "";
		String s19 = "";
		String s22 = "";
		boolean retour = true;
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projet?characterEncoding=latin1&useConfigs=maxPerformance";
			Properties info = new Properties();
			info.put("user", "root");
			info.put("password", "");

			dbConnection = DriverManager.getConnection(url, info);

			if (dbConnection != null) {
				System.out.println("Bienvenue!");
			}
			Scanner sc = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			Scanner sc3 = new Scanner(System.in);

			while (retour == false || (s3.equals("0") || s16.equals("0") || s19.equals("0") || s22.equals("0")
					|| s2.equals("0") || !(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")
							|| s.equals("5") || s.equals("6") || s.equals("0")))) {

				System.out.println("Choisir une option:");
				System.out.println("1 pour avoir des informations sur la base");
				System.out.println("2 pour afficher l'une des tables");
				System.out.println("3 pour faire une recherche patient");
				System.out.println("4 pour faire une recherche CIM10");
				System.out.println("5 pour faire une recherche CCAM");
				System.out.println("6 pour afficher les statistiques");
				System.out.println("0 pour quitter");
				s2 = "";
				s3 = "";
				s16 = "";
				s19 = "";
				s22 = "";
				s = sc.nextLine();

				switch (s) {
				case "1":
					DatabaseMetaData dbmd = (DatabaseMetaData) dbConnection.getMetaData();

					System.out.println("Driver Name: " + dbmd.getDriverName());
					System.out.println("Driver Version: " + dbmd.getDriverVersion());
					System.out.println("UserName: " + dbmd.getUserName());
					System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
					System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
					Scanner continuer = new Scanner(System.in);
					System.out.println("\nAppuyer sur une touche pour retourner au menu principal");
					String cont = continuer.nextLine();
					retour = false;
					break;
				case "2":

					while (!(s2.equals("1") || s2.equals("2") || s2.equals("3") || s2.equals("4") || s2.equals("5")
							|| s2.equals("6") || s2.equals("0"))) {
						System.out.println("1 pour afficher la table des patients");
						System.out.println("2 pour afficher la table des hospitalisations");
						System.out.println("3 pour afficher la table des actes");
						System.out.println("4 pour afficher la table des diagnostics");
						System.out.println("5 pour afficher le thesaurus des actes(CCAM)");
						System.out.println("6 pour afficher le thesaurus des diagnostics(CIM10)");
						System.out.println("0 pour revenir au menu principal");
						s2 = sc2.nextLine();
						String query = "";
						switch (s2) {
						case "1":
							query = "Select * from tab_patient";
							break;
						case "2":
							query = "Select * from tab_hospitalisation";
							break;
						case "3":
							query = "select * from tab_diagnostic";
							break;
						case "4":
							query = "Select * from tab_acte";
							break;
						case "5":
							query = "Select * from tab_ccam";
							break;
						case "6":
							query = "Select * from tab_cim10";
							break;

						}
						if (query != "") {
							PreparedStatement prepare = (PreparedStatement) dbConnection.prepareStatement(query);
							ResultSet rs = (ResultSet) prepare.executeQuery();

							ResultSetMetaData resultMeta = (ResultSetMetaData) rs.getMetaData();
							for (int i = 1; i <= resultMeta.getColumnCount(); i++)
								System.out.print(resultMeta.getColumnName(i).toUpperCase() + "/");
							System.out.println();
							while (rs.next()) {
								for (int i = 1; i <= resultMeta.getColumnCount(); i++)
									System.out.print(rs.getString(i) + " ");
								System.out.println();
							}

						}
					}
					if (!s2.equals("0")) {
						Scanner continuer1 = new Scanner(System.in);
						System.out.println("\nAppuyer sur une touche pour retourner au menu principal");
						String cont1 = continuer1.nextLine();
						retour = false;
					}
					break;
				case "3":
					while (!(s3.equals("1") || s3.equals("2") || s3.equals("3") || s3.equals("4") || s3.equals("5")
							|| s3.equals("6") || s3.equals("7") || s3.equals("8") || s3.equals("9") || s3.equals("10")
							|| s3.equals("0"))) {
						System.out.println("Veuillez choisir votre critère de recherche");
						System.out.println("1 pour id_patient");
						System.out.println("2 pour nom");
						System.out.println("3 pour prenom");
						System.out.println("4 pour nom et prenom");
						System.out.println("5 pour l'age");
						System.out.println("6 pour la date d'entrée");
						System.out.println("7 pour la date de sortie");
						System.out.println("8 pour la date d'entrée et de sortie");
						System.out.println("9 pour le diagnostic");
						System.out.println("10 pour les actes");
						System.out.println("0 pour revenir au menu principal");

						s3 = sc3.nextLine();
					}
					String query = "";
					switch (s3) {
					case "1":
						System.out.println("saisir id_patient");
						Scanner sc4 = new Scanner(System.in);
						String s4 = sc4.nextLine();
						query = "Select prenom,nom,date_naissance,sexe from tab_patient where id_patient= " + "'" + s4
								+ "'";
						break;
					case "2":
						System.out.println("Saisir nom patient");
						Scanner sc5 = new Scanner(System.in);
						String s5 = sc5.nextLine();
						query = "Select id_patient,prenom,date_naissance from tab_patient where nom=" + "'" + s5 + "'";
						break;
					case "3":
						System.out.println("Saisir prenom patient");
						Scanner sc6 = new Scanner(System.in);
						String s6 = sc6.nextLine();
						query = "select id_patient,nom,date,naissance from tab_patient where prenom=" + "'" + s6 + "'";
						break;
					case "4":
						System.out.println("Saisir nom patient");
						Scanner sc7 = new Scanner(System.in);
						String s7 = sc7.nextLine();
						System.out.println("Saisir prenom patient");
						Scanner sc8 = new Scanner(System.in);
						String s8 = sc8.nextLine();
						query = "Select id_patient from tab_patient where nom=" + "'" + s7 + "'" + " and prenom=" + "'"
								+ s8 + "'";
						break;
					case "5":
						System.out.println("Saisir age");
						Scanner sc9 = new Scanner(System.in);
						String s9 = sc9.nextLine();
						query = "Select * from tab_patient where Year(now())-Year(date_naissance)=" + "'" + s9 + "'";
						break;
					case "6":
						System.out.println("saisir date d'entrée sous format AAAA-MM-JJ");
						Scanner sc10 = new Scanner(System.in);
						String s10 = sc10.nextLine();
						query = "Select id_patient,nom,prenom,date_sortie from tab_patient join tab_hospitalisation using(id_patient) where date_entree ="
								+ "'" + s10 + "'";
						break;
					case "7":
						System.out.println("saisir date de sortie sous format AAAA-MM-JJ");
						Scanner sc11 = new Scanner(System.in);
						String s11 = sc11.nextLine();
						query = "Select id_patient,nom,prenom,date_entree from tab_patient join tab_hospitalisation using(id_patient) where date_sortie="
								+ "'" + s11 + "'";
						break;
					case "8":
						System.out.println("saisir date d'entrée sous format AAAA-MM-JJ");
						Scanner sc12 = new Scanner(System.in);
						String s12 = sc12.nextLine();
						System.out.println("saisir date de sortie sous format AAAA-MM-JJ");
						Scanner sc13 = new Scanner(System.in);
						String s13 = sc13.nextLine();
						query = "Select id_patient,nom,prenom from tab_patient join tab_hospitalisation using(id_patient) where date_entree="
								+ "'" + s12 + "'" + " and date_sortie=" + "'" + s13 + "'";
						break;
					case "9":
						System.out.println("saisir un mot clé");
						Scanner sc14 = new Scanner(System.in);
						String s14 = sc14.nextLine();
						query = "SELECT distinct id_patient,nom,prenom,libelle_CIM10 ,dgtype FROM tab_patient join tab_hospitalisation using(id_patient)"
								+ "join tab_diagnostic using (id_hospitalisation) join tab_cim10 using(id_cim10) where libelle_CIM10 like "
								+ "'%" + s14 + "%'";
						break;
					case "10":
						System.out.println("saisir un mot clé");
						Scanner sc15 = new Scanner(System.in);
						String s15 = sc15.nextLine();
						query = "SELECT distinct id_patient,nom,prenom,libelle_CCAM,date_acte FROM  tab_patient join tab_hospitalisation using(id_patient)"
								+ "join tab_acte using (id_hospitalisation) join tab_ccam using(id_ccam) where libelle_CCAM like "
								+ "'%" + s15 + "%'";
						break;
					}
					if (query != "") {
						PreparedStatement prepare = (PreparedStatement) dbConnection.prepareStatement(query);
						ResultSet rs = (ResultSet) prepare.executeQuery();

						ResultSetMetaData resultMeta = (ResultSetMetaData) rs.getMetaData();
						for (int i = 1; i <= resultMeta.getColumnCount(); i++)
							System.out.print(resultMeta.getColumnName(i).toUpperCase() + "/");
						System.out.println();
						while (rs.next()) {
							for (int i = 1; i <= resultMeta.getColumnCount(); i++)
								System.out.print(rs.getString(i) + " ");
							System.out.println();
						}

					}
					if (!s3.equals("0")) {
						Scanner continuer1 = new Scanner(System.in);
						System.out.println("\nAppuyer sur une touche pour retourner au menu principal");
						String cont1 = continuer1.nextLine();
						retour = false;
					}
					break;
				case "4":
					while (!(s16.equals("1") || s16.equals("2") || s16.equals("0"))) {
						System.out.println("1 pour une recherche par libellé");
						System.out.println("2 pour recherche par code");
						System.out.println("0 pour retour menu principal");
						Scanner sc16 = new Scanner(System.in);
						s16 = sc16.nextLine();
					}
					String query1 = "";
					switch (s16) {
					case "1":
						System.out.println("saisir mot clé");
						Scanner sc17 = new Scanner(System.in);
						String s17 = sc17.nextLine();
						query1 = "Select * from tab_cim10 where libelle_cim10 like  " + "'%" + s17 + "%'";
						break;
					case "2":
						System.out.println("saisir code CIM10 ou le début de celui-ci");
						Scanner sc18 = new Scanner(System.in);
						String s18 = sc18.nextLine();
						query1 = "Select * from tab_cim10 where id_cim10 like" + "'" + s18 + "%'";
						break;
					}
					if (query1 != "") {
						PreparedStatement prepare = (PreparedStatement) dbConnection.prepareStatement(query1);
						ResultSet rs = (ResultSet) prepare.executeQuery();

						ResultSetMetaData resultMeta = (ResultSetMetaData) rs.getMetaData();
						for (int i = 1; i <= resultMeta.getColumnCount(); i++)
							System.out.print(resultMeta.getColumnName(i).toUpperCase() + "/");
						System.out.println();
						while (rs.next()) {
							for (int i = 1; i <= resultMeta.getColumnCount(); i++)
								System.out.print(rs.getString(i) + " ");
							System.out.println();
						}

					}

					if (!s16.equals("0")) {
						Scanner continuer2 = new Scanner(System.in);
						System.out.println("\nAppuyer sur une touche pour retourner au menu principal");
						String cont2 = continuer2.nextLine();
						retour = false;
					}
					break;
				case "5":
					while (!(s19.equals("1") || s19.equals("2") || s19.equals("0"))) {
						System.out.println("1 pour une recherche par libellé");
						System.out.println("2 pour recherche par code");
						System.out.println("0 pour retour menu principal");
						Scanner sc19 = new Scanner(System.in);
						s19 = sc19.nextLine();
					}
					String query2 = "";
					switch (s19) {
					case "1":
						System.out.println("saisir mot clé");
						Scanner sc20 = new Scanner(System.in);
						String s20 = sc20.nextLine();
						query2 = "Select * from tab_ccam where libelle_ccam like  " + "'%" + s20 + "%'";
						break;
					case "2":
						System.out.println("saisir code CCAM ou le début de celui-ci");
						Scanner sc21 = new Scanner(System.in);
						String s21 = sc21.nextLine();
						query2 = "Select * from tab_ccam where id_ccam like" + "'" + s21 + "%'";
						break;
					}
					if (query2 != "") {
						PreparedStatement prepare = (PreparedStatement) dbConnection.prepareStatement(query2);
						ResultSet rs = (ResultSet) prepare.executeQuery();

						ResultSetMetaData resultMeta = (ResultSetMetaData) rs.getMetaData();
						for (int i = 1; i <= resultMeta.getColumnCount(); i++)
							System.out.print(resultMeta.getColumnName(i).toUpperCase() + "/");
						System.out.println();
						while (rs.next()) {
							for (int i = 1; i <= resultMeta.getColumnCount(); i++)
								System.out.print(rs.getString(i) + " ");
							System.out.println();
						}

					}

					if (!s19.equals("0")) {
						Scanner continuer3 = new Scanner(System.in);
						System.out.println("\nAppuyer sur une touche pour retourner au menu principal");
						String cont3 = continuer3.nextLine();
						retour = false;
					}

					break;
				case "6":
					while (!(s22.equals("1") || s22.equals("2") || s22.equals("3") || s22.equals("4") || s22.equals("5")
							|| s22.equals("0"))) {
						System.out.println("1 pour afficher la moyenne des ages des patients et leur ecart type");
						System.out.println("2 pour afficher la repartition du nombre de patient selon leur sexe");
						System.out.println("3 pour afficher le classement des actes realisés");
						System.out.println("4 pour afficher le classement des diagnostics établis");
						System.out.println("0 pour retour menu principal");
						Scanner sc22 = new Scanner(System.in);
						s22 = sc22.nextLine();
					}
					String query3 = "";
					switch (s22) {
					case "1":

						query3 = "select round(avg(year(now())-year(date_naissance)),2) as MOYENNE_AGES,round(std(year(now())-year(date_naissance)),2) as ECART_TYPE from tab_patient";
						break;
					case "2":

						query3 = "select count(*) as nombre_patients,SEXE  from tab_patient\r\n" + "group by sexe";
						break;
					case "3":
						query3 = "SELECT count(ID_CCAM) as nombre_actes,ID_CCAM,libelle_ccam FROM tab_acte\r\n"
								+ "join tab_ccam using(id_ccam)\r\n" + "group by ID_CCAM\r\n"
								+ "order by count(ID_CCAM)desc limit 20";
						break;
					case "4":
						query3 = "SELECT count(ID_Cim10) as nombre_diagnostics,ID_Cim10,libelle_cim10 FROM tab_diagnostic\r\n"
								+ "join tab_cim10 using(id_cim10)\r\n" + "group by ID_Cim10\r\n"
								+ "order by count(ID_Cim10)desc limit 20";
						break;

					}
					if (query3 != "") {
						PreparedStatement prepare = (PreparedStatement) dbConnection.prepareStatement(query3);
						ResultSet rs = (ResultSet) prepare.executeQuery();

						ResultSetMetaData resultMeta = (ResultSetMetaData) rs.getMetaData();
						for (int i = 1; i <= resultMeta.getColumnCount(); i++)
							System.out.print(resultMeta.getColumnName(i).toUpperCase() + "/");
						System.out.println();
						while (rs.next()) {
							for (int i = 1; i <= resultMeta.getColumnCount(); i++)
								System.out.print(rs.getString(i) + " ");
							System.out.println();
						}

					}

					if (!s22.equals("0")) {
						Scanner continuer4 = new Scanner(System.in);
						System.out.println("\nAppuyer sur une touche pour retourner au menu principal");
						String cont4 = continuer4.nextLine();
						retour = false;
					}
					break;
				case "0":
					System.out.println("Au revoir!");
					System.exit(0);
					break;
				}

			}
			sc.close();
			sc2.close();
			sc3.close();
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Erreur connection à MySQL");
			ex.printStackTrace();
		}

	}

}